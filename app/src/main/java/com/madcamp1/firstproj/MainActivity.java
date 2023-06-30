package com.madcamp1.firstproj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //button variables
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pop-up
        final GridView gv = (GridView)findViewById(R.id.gridView);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
        //button1
        button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //button2
        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mIntent);
            }
        });
    }
    public class MyGridAdapter extends BaseAdapter {
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }

        Integer[] picID = {
                R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5
        };

        @Override
        public int getCount() {
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(picID[i]);

            // 갤러리의 이미지뷰를 눌렀을 때
            // 다이얼로그뷰를 팝업하여 큰 이미지를 출력합니다.
            final int pos = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPic = dialogView.findViewById(R.id.ivpic);
                    ivPic.setImageResource(picID[pos]);
                    dlg.setTitle(" ");
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }
}