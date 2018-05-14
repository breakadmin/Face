package com.example.face;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.Fragment.FaceFragment;
import com.example.library.Interface.PictureClickListener;


public class MainActivity extends AppCompatActivity {


    EditText Text;
    ImageView imageView;
    TextView emoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = (EditText) findViewById(R.id.Text);
        imageView = (ImageView) findViewById(R.id.imageView);

        emoji = findViewById(R.id.emojitext);

        Text.setSelection(Text.length());

        FaceFragment fragmentA=new FaceFragment();
        fragmentA.bind(Text,null);
        fragmentA.setPictureClickListener(new PictureClickListener() {
            @Override
            public void PictureDisplay(int res) {
                imageView.setImageResource(res);
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentA).commit();

    }





}

