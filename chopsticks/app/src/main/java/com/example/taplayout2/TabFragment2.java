package com.example.taplayout2;
import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import androidx.fragment.app.Fragment;


public class TabFragment2 extends Fragment {

    public ArrayList<Uri> uriArrayList;


    //button variables
    Button button1;
    Button button2;
    public GridView gv;
    public MyGridAdapter gAdapter;
    private Uri selectedImageUri;

    int check=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v1 = inflater.inflate(R.layout.fragment2, container, false);
        uriArrayList=new ArrayList<>();

        //
        //Grid view////////////////////////////////////////////////////
        //
        gv = (GridView)v1.findViewById(R.id.gridView);
        //
        //button1////////////////////////////////////////////////////
        //

        button1=(Button) v1.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"삭제할 사진을 고르시오",Toast.LENGTH_SHORT).show();
                check=1;
                gAdapter.notifyDataSetChanged();
            }
        });


        //
        //button2///////////////////////////////////////////////////
        //

        button2=(Button) v1.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });
        /*
        Button button3=(Button)v1.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"회전시킬 사진을 고르시오",Toast.LENGTH_SHORT).show();
                check=2;
                gAdapter.notifyDataSetChanged();
            }
        });
        */

        gAdapter = new MyGridAdapter(uriArrayList);
        gv.setAdapter(gAdapter);

        return v1;

    }
    //
    //invoke gallery image///////////////////////////////////////
    //

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        uriArrayList.add(uri);

                        gAdapter.notifyDataSetChanged();
                    }
                }
            }
    );


    //
    //grid view//////////////////////////////////////////////////
    //
    public class MyGridAdapter extends BaseAdapter {
        ArrayList<Uri> uriArrayList;


        public MyGridAdapter(ArrayList<Uri> uriArrayList){
            this.uriArrayList=uriArrayList;
        }


        @Override
        public int getCount() {
            //return uriArrayList.size();
            return uriArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View v2, ViewGroup viewGroup) {

            v2=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog,viewGroup,false);
            ImageView imageview= v2.findViewById(R.id.ivpic);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(262,200));
            imageview.setScaleType(ImageView.ScaleType.FIT_START);
            imageview.setImageURI(uriArrayList.get(i));
            // 갤러리의 이미지뷰를 눌렀을 때
            // 다이얼로그뷰를 팝업하여 큰 이미지를 출력합니다.
            final int pos = i;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(check==0){
                        View dialogView = View.inflate(getActivity(), R.layout.dialog, null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                        ImageView ivPic = dialogView.findViewById(R.id.ivpic);
                        ivPic.setImageURI(uriArrayList.get(i));
                        dlg.setTitle(" ");
                        dlg.setView(dialogView);
                        dlg.setNegativeButton("닫기", null);
                        dlg.show();
                    }else{
                        imageview.setImageResource(0);
                        uriArrayList.remove(pos);
                        check=0;
                    }

                }
            });

            return imageview;
        }
    }



}