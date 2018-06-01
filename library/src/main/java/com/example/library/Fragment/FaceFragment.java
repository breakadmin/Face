package com.example.library.Fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.Adapter.MySwitchAdapter;
import com.example.library.Component.MyViewPager;
import com.example.library.FaceData.EmojiData;
import com.example.library.Interface.FaceListener;
import com.example.library.Interface.PictureClickListener;
import com.example.library.R;
import com.example.library.Utils.DisplayUtils;
import com.example.library.disPlayGif.AnimatedGifDrawable;
import com.example.library.disPlayGif.AnimatedImageSpan;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FaceFragment extends Fragment {
    private RecyclerView FaceOptions;
    MyViewPager ViewPager;
    MySwitchAdapter myRecyclerViewAdapter;
    PictureClickListener listener;
    EditText Text;
    TextView emoji;
    int height = 0;
    DisplayUtils displayUtils;


    public void setHeight(int height) {
        this.height = height;
    }

    public void setPictureClickListener(PictureClickListener listener) {
        this.listener = listener;
    }

    /**
     * 绑定需要显示表情的文本输入框 与需要显示gif的TextView
     *
     * @param Text
     * @param emoji
     */
    public void bind(EditText Text, TextView emoji) {
        this.Text = Text;
        this.emoji = emoji;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_face, null);
        initView(view);
        displayUtils=new DisplayUtils(getContext());
        ViewGroup.LayoutParams layoutParams = ViewPager.getLayoutParams();
        if (height == 0) {
            height = displayUtils.dp2px(185);
        }
        Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();


        layoutParams.height = height;


        ViewPager.setLayoutParams(layoutParams);
        List<Fragment> fragments = new ArrayList<Fragment>();
        ImgFragment recyclerFragment = new ImgFragment();

        EmojiFragment faceFragment = new EmojiFragment();
        faceFragment.init(23);
        fragments.add(faceFragment);
        fragments.add(recyclerFragment);
        faceFragment.setFaceListener(new FaceListener() {
            @Override
            public void display(Map<String, Integer> face) {
                if (Text != null) {
                    Text.append(new EmojiData().disPlayEmoji(face, getContext()));
                }
                if (emoji != null) {
                    int faces = 0;
                    for (Map.Entry<String, Integer> entry : face.entrySet()) {//获取表情ID
                        faces = entry.getValue();
                    }
                    SpannableString value = SpannableString.valueOf("搜索");
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeResource(getResources(), faces, options);
                    String type = options.outMimeType;
                    if (type.toLowerCase().contains("gif")) {//判断是否gif
                        try {
                            WeakReference<AnimatedImageSpan> localImageSpanRef = new WeakReference<AnimatedImageSpan>(new AnimatedImageSpan(new AnimatedGifDrawable(getResources()
                                    .openRawResource(faces), new AnimatedGifDrawable.UpdateListener() {
                                @Override
                                public void update() {//update the textview
                                    emoji.postInvalidate();
                                }
                            })));
                            value.setSpan(localImageSpanRef.get(), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                            emoji.setText(value);
                        } catch (Exception e) {

                        }
                    }
                }


            }

            @Override
            public void delete() {
                if (Text != null) {
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


            }
        });
        recyclerFragment.setPictureClickListener(new PictureClickListener() {//展示表情
            @Override
            public void PictureDisplay(int res) {
                if (listener != null) {
                    listener.PictureDisplay(res);
                }
            }
        });
        ViewPager.setAdapter(new MyViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments));
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        FaceOptions.setLayoutManager(mLayoutManager);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        FaceOptions.setHasFixedSize(true);


//创建并设置Adapter
        List<Integer> s = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            s.add(R.mipmap.face_tip);
        }
        myRecyclerViewAdapter = new MySwitchAdapter(getContext(), s);
        FaceOptions.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new MySwitchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                myRecyclerViewAdapter.setPosition(position);
                myRecyclerViewAdapter.notifyDataSetChanged();

                ViewPager.setCurrentItem(position, false);

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    private void initView(View view) {
        FaceOptions = (RecyclerView) view.findViewById(R.id.FaceOptions);
        ViewPager = (MyViewPager) view.findViewById(R.id.MyViewPager);
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
