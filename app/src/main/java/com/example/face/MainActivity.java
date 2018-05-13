package com.example.face;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.Adapter.MySwitchAdapter;
import com.example.library.Component.MyViewPager;
import com.example.library.FaceData.EmojiData;
import com.example.library.Fragment.FaceFragment;
import com.example.library.Fragment.SecondFragment;
import com.example.library.Interface.FaceListener;
import com.example.library.Interface.PictureClickListener;
import com.example.library.disPlayGif.AnimatedGifDrawable;
import com.example.library.disPlayGif.AnimatedImageSpan;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Text;
    ImageView imageView;
    RecyclerView FaceOptions;
    TextView emoji;
    MyViewPager MyViewPager;
    MySwitchAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text = (EditText) findViewById(R.id.Text);
        imageView = (ImageView) findViewById(R.id.imageView);
        FaceOptions = (RecyclerView) findViewById(R.id.FaceOptions);
        MyViewPager = (MyViewPager) findViewById(R.id.MyViewPager);
        emoji=findViewById(R.id.emojitext);

        Text.setSelection(Text.length());


        List<Fragment> fragments = new ArrayList<Fragment>();
        SecondFragment recyclerFragment = new SecondFragment();

        FaceFragment faceFragment = new FaceFragment();
        faceFragment.init(20);
        fragments.add(faceFragment);
        fragments.add(recyclerFragment);
        faceFragment.setFaceListener(new FaceListener() {
            @Override
            public void display(Map<String, Integer> face) {
                Text.append(new EmojiData().disPlayEmoji(face, getApplicationContext()));

                int faces = 0;
                for(Map.Entry<String, Integer> entry: face.entrySet()){//获取表情ID
                    faces=entry.getValue();
                }
                SpannableString value = SpannableString.valueOf("搜索");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(getResources(), faces, options);
                String type = options.outMimeType;
                if (type.toLowerCase().contains("gif")){//判断是否gif
                    try{
                        WeakReference<AnimatedImageSpan> localImageSpanRef = new WeakReference<AnimatedImageSpan>(new AnimatedImageSpan(new AnimatedGifDrawable(getResources()
                                .openRawResource(faces), new AnimatedGifDrawable.UpdateListener() {
                            @Override
                            public void update() {//update the textview
                                emoji.postInvalidate();
                            }
                        })));
                        value.setSpan(localImageSpanRef.get(), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        emoji.setText(value);
                    }catch(Exception e){

                    }
                }



            }

            @Override
            public void delete() {
                // 删除表情
                String text = Text.getText().toString();
                if (text.isEmpty()) {
                    return;
                }
                if (!Text.isFocused()) {//获取焦点
                    Text.setSelection(Text.length());
                    Text.requestFocus();
                }
                if ("]".equals(text.substring(text.length() - 1, text.length()))) {
                    int index = text.lastIndexOf("[");
                    Text.getText().delete(index, text.length());
                } else {
                    int index = Text.getSelectionStart();
                    Text.getText().delete(index - 1, index);

                }
            }
        });
        recyclerFragment.setPictureClickListener(new PictureClickListener() {//展示表情
            @Override
            public void PictureDisplay(int res) {

                imageView.setImageResource(res);
            }
        });
        MyViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragments));
        MyViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                myRecyclerViewAdapter.setPosition(position);
                myRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        FaceOptions.setLayoutManager(mLayoutManager);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能 FaceOptions.setHasFixedSize(true);

//创建并设置Adapter List s = new ArrayList<>(); for(int i=0;i<fragments.size();i++){ s.add(R.mipmap.face_tip); } myRecyclerViewAdapter = new MySwitchAdapter(this, s); FaceOptions.setAdapter(myRecyclerViewAdapter); myRecyclerViewAdapter.setOnItemClickListener(new MySwitchAdapter.OnItemClickListener() { @Override public void onItemClick(View view, int position) { myRecyclerViewAdapter.setPosition(position); myRecyclerViewAdapter.notifyDataSetChanged();


    }


class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragment;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragment) {
        super(fm);
        this.fragment = fragment;
    }


    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }
}
}