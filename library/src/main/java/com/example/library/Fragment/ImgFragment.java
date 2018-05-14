package com.example.library.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.library.FaceData.EmojiData;
import com.example.library.Interface.PictureClickListener;
import com.example.library.R;

import java.util.List;
import java.util.Map;

/**
 * 表情图片
 */

public class ImgFragment extends Fragment {

    RecyclerView myRecycler;
    PictureClickListener pictureClickListener;

    public void setPictureClickListener(PictureClickListener pictureClickListener) {
        this.pictureClickListener = pictureClickListener;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_face, container, false);
        myRecycler = (RecyclerView) view.findViewById(R.id.myRecycler);



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
        layoutManager.setOrientation(OrientationHelper.VERTICAL);//设置为垂直布局，这也是默认的
        EmojiData emojiDatas = new EmojiData();
        final List<Map<String,Integer>> data = emojiDatas.jgzDatas();//获取表情包资源
        MyAdapter myAdapter = new MyAdapter(getContext(), data);
        myRecycler.setAdapter(myAdapter);
        myAdapter.setItemClickListener(new MyAdapter.ItemClickListener() {
            @Override
            public void ItemListener(View v, int position) {
                if (pictureClickListener!=null){
                    Map<String,Integer> map=data.get(position);
                    for(Map.Entry<String,Integer> entry:map.entrySet()){
                        pictureClickListener.PictureDisplay(entry.getValue());
                    }

                }

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }



    static class  MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{

        Context context;
        List<Map<String,Integer>> data;
        ItemClickListener itemClickListener;
        public interface ItemClickListener{
            void ItemListener(View v, int position);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        public MyAdapter(Context context, List<Map<String,Integer>> data) {
            this.context = context;
            this.data = data;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.emoji_panel, parent, false);
            MyViewHolder vh = new MyViewHolder(view);
            view.setOnClickListener(this);

            return vh;

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Map<String,Integer> map=data.get(position);
            for(Map.Entry<String, Integer> entry: map.entrySet()){
                holder.imageView.setImageResource(entry.getValue());

            }

            holder.textView.setVisibility(View.GONE);
            holder.itemView.setTag(position);
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener!=null){
                itemClickListener.ItemListener(view, (Integer) view.getTag());
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout emoji_layout;
            ImageView imageView;
            TextView textView;
            public MyViewHolder(View itemView) {
                super(itemView);
                emoji_layout=itemView.findViewById(R.id.emoji_layout);
                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5,5,5,5);
                emoji_layout.setLayoutParams(layoutParams);
                imageView=itemView.findViewById(R.id.emoji);
                textView=itemView.findViewById(R.id.emoji_text);
            }
    }
    }



}
