package com.example.face;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.Fragment.FaceFragment;
import com.example.library.Interface.PictureClickListener;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {


    EditText Text;
    ImageView imageView;
    TextView emoji;
    private TextView emojitext;
    private FaceFragment Face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final DisplayMetrics dm=getResources().getDisplayMetrics();


        Text = (EditText) findViewById(R.id.Text);
        imageView = (ImageView) findViewById(R.id.imageView);
        emoji = findViewById(R.id.emojitext);

        Text.setSelection(Text.length());
        Face.bind(Text, emojitext);
        Face.setPictureClickListener(new PictureClickListener() {
            @Override
            public void PictureDisplay(int res) {
                imageView.setImageResource(res);
            }
        });


    }


    private void initView() {
        emojitext = (TextView) findViewById(R.id.emojitext);

        Face = (FaceFragment) getSupportFragmentManager().findFragmentById(R.id.Face);


    }
}

