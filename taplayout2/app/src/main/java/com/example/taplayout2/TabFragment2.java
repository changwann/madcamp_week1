package com.example.taplayout2;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import androidx.fragment.app.Fragment;


public class TabFragment2 extends Fragment {

    //public ArrayList<Uri> uriArrayList;
    //button variables
    //Button button1;
    //Button button2;
    public GridView gv;
    public MyGridAdapter gAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v1 = inflater.inflate(R.layout.fragment2, container, false);

        //
        //Grid view////////////////////////////////////////////////////
        //
        gv = (GridView)v1.findViewById(R.id.gridView);
        //uriArrayList=new ArrayList<>();
        //
        //button1////////////////////////////////////////////////////
        //
        /*
        button1=(Button) v1.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

         */
        //
        //button2///////////////////////////////////////////////////
        //
        /*
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

         */
        //gAdapter = new MyGridAdapter(uriArrayList);
        gAdapter = new MyGridAdapter(getActivity());
        gv.setAdapter(gAdapter);

        return v1;
    }
    //
    //invoke gallery image///////////////////////////////////////
    //
    /*
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


     */
    //
    //grid view//////////////////////////////////////////////////
    //
    public class MyGridAdapter extends BaseAdapter {
        //ArrayList<Uri> uriArrayList;
        Context context;
        /*
        public MyGridAdapter(ArrayList<Uri> uriArrayList){
            this.uriArrayList=uriArrayList;
        }

         */
        public MyGridAdapter(Context c){
            context = c;
        }

        Integer[] picID={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,
                R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.images12,
                R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,R.drawable.image17,R.drawable.image18,
                R.drawable.image19, R.drawable.image20};
        @Override
        public int getCount() {
            //return uriArrayList.size();
            return picID.length;
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
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(200,300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setImageResource(picID[i]);
            // 갤러리의 이미지뷰를 눌렀을 때
            // 다이얼로그뷰를 팝업하여 큰 이미지를 출력합니다.
            final int pos = i;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(getActivity(), R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                    ImageView ivPic = dialogView.findViewById(R.id.ivpic);
                    ivPic.setImageResource(picID[pos]);
                    dlg.setTitle(" ");
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageview;
        }
    }
}