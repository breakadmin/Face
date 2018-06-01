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
    private RelativeLayout MyScreen;
    boolean isShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        final DisplayMetrics dm=getResources().getDisplayMetrics();


        MyScreen.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                MyScreen.getWindowVisibleDisplayFrame(r);
                int height = r.bottom;

                int screenHeight = MyScreen.getRootView().getHeight();

                int keyboardHeight = dm.heightPixels - (r.bottom + r.top);
                if (isShow) {
                    // 当可见区域大于屏幕4/5时即关闭键盘，隐藏输入框
                    if (height >dm.heightPixels * 0.8f) {
                        isShow = false;
                        Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 当可见区域小于屏幕4/5时即弹出键盘
                    if (height < dm.heightPixels * 0.8f) {
                        isShow = true;
                        Face.onDestroy();
                        Face=null;
                        Face = (FaceFragment) getSupportFragmentManager().findFragmentById(R.id.Face);

                        Face.setHeight(keyboardHeight);
                        Toast.makeText(MainActivity.this, "打开"+keyboardHeight, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        Text = (EditText) findViewById(R.id.Text);
        imageView = (ImageView) findViewById(R.id.imageView);
        emoji = findViewById(R.id.emojitext);

        Text.setSelection(Text.length());
        Face.bind(Text, null);
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

        MyScreen = (RelativeLayout) findViewById(R.id.MyScreen);

    }
}

