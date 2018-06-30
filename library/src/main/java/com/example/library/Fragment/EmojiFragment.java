package com.example.library.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.library.Adapter.MyEmojiAdapter;
import com.example.library.view.MyGridView;
import com.example.library.view.MyViewPager;
import com.example.library.Utils.EmojiData;
import com.example.library.Interface.FaceListener;
import com.example.library.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认表情
 */

public class EmojiFragment extends Fragment {

    int PageSize;//每行显示的最大数量
    MyViewPager emojiPanel;
    LinearLayout ViewGroup;
    FaceListener faceListener;
    int num;

    public void setFaceListener(FaceListener faceListener) {
        this.faceListener = faceListener;
    }



    public void init( int num,int PageSize) {
        this.PageSize = PageSize;
        this.num=num;
    }

    List<Map<String, Integer>> emojis=new ArrayList<>();
    private int totalPage;//总的页数
    List<GridView> gridViews = new ArrayList<GridView>();
    ImageView[] pointView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_emoji, null);
        ViewGroup = (LinearLayout) view.findViewById(R.id.ViewGroup);
        emojiPanel = (MyViewPager) view.findViewById(R.id.emoji_panel);


        initDatas();
        setLabelPoint();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void initDatas() {
        EmojiData emojiDatas = new EmojiData();
        Map<String,Integer> map=emojiDatas.initDatas();//获取表情包资源
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            Map<String,Integer> m= new HashMap<>();
            m.put(entry.getKey(),entry.getValue());
            emojis.add(m);

        }



        totalPage = (int) Math.ceil(emojis.size() * 1.0 / PageSize);//总数据÷每页最大显示数

        for (int i = 0; i < totalPage; i++) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.face_view,null);
            MyGridView gv =view.findViewById(R.id.faceView);
            gv.setNumColumns(num);
            gv.setSelector(new ColorDrawable(Color.TRANSPARENT)); // 去除点击时的背景色
            gv.setAdapter(new MyEmojiAdapter(getContext(), emojis, i, PageSize));


            gridViews.add(gv);
            final int position = i;
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int pos = i + position * PageSize;//重新计算位置
                    //是否末页删除按钮
                    if (pos < emojis.size()) {//否
                        //是否每页删除按钮
                        if (i != PageSize) {//否
                            // 展示表情
                            if (faceListener!=null){
                                Map<String,Integer> map=emojis.get(pos);
                                faceListener.display(map);
                            }

                        } else {//是
                            if (faceListener!=null){
                            faceListener.delete();
                            }
                        }
                    } else {//是
                            if (faceListener!=null){
                        faceListener.delete();
                            }

                    }


                }
            });
        }
        emojiPanel.setAdapter(new MyViewAdapter(getContext(), gridViews));


    }


    class MyClickListener implements View.OnClickListener {
        int Position;

        public MyClickListener(int Position) {
            this.Position = Position;
        }

        @Override
        public void onClick(View view) {
            emojiPanel.setCurrentItem(Position, false);
        }
    }

    /**
     * 设置选中的"·"的状态，是否选中
     *
     * @param selectItems
     */
    private void setPointState(int selectItems) {
        for (int i = 0; i < totalPage; i++) {
            if (i == selectItems) {

               pointView[i].setBackgroundResource(R.mipmap.indicator_point_select);
            } else {
                pointView[i].setBackgroundResource(R.mipmap.indicator_point_normal);
            }
        }
    }

    /**
     * 小点的设置与监听
     */
    private void setLabelPoint() {

        //添加"·"的图片
        pointView = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            ImageView view = new ImageView(getContext());
            pointView[i] = view;
            pointView[i].setOnClickListener(new MyClickListener(i));
            ViewGroup.addView(view);

        }
        setPointState(0);
        //监听ViewPager的变化，从而更新·(小点)
        emojiPanel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                setPointState(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    class MyViewAdapter extends PagerAdapter {
        Context context;
        List<GridView> data;

        public MyViewAdapter(Context context, List<GridView> data) {
            this.context = context;
            this.data = data;
        }

        // 显示多少个页面
        @Override
        public int getCount() {
            return gridViews.size();
        }

        // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 初始化显示的条目对象
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            // return super.instantiateItem(container, position);
            container.addView(gridViews.get(position)); // 添加到ViewPager容器

            return gridViews.get(position);
        }


        // 销毁条目对象
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView(gridViews.get(position));
        }
    }

}
