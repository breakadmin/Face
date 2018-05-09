package com.example.library.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.R;

import java.util.List;
import java.util.Map;

/**
 * 默认表情适配器
 */

public class MyEmojiAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Integer>> lists;//数据源
    private int Index; // 页数下标，标示第几页，从0开始
    private int PageSize;// 每页显示的最大的数量


    public MyEmojiAdapter(Context context, List<Map<String,Integer>> lists, int Index, int PageSize) {
        this.context = context;
        this.lists = lists;
        this.Index = Index;
        this.PageSize = PageSize;

    }

    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size() > (Index + 1) * PageSize ?
                PageSize+1: (lists.size() - Index*PageSize)+1;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + Index * PageSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + Index * PageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.emoji_panel, null);
            holder.emoji_text = (TextView)convertView.findViewById(R.id.emoji_text);
            holder.emoji = (ImageView)convertView.findViewById(R.id.emoji);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //重新计算position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + Index * PageSize;

        if (position==getCount()-1){//判断是不是每页末尾，若是，则添加删除图标
//
//               Glide.with( context )
//                    .load(R.drawable.face_delete)
//                    .skipMemoryCache( true )
//                    .into( holder.emoji);
           holder.emoji.setImageResource(R.drawable.face_delete);
        }else{
            Map<String, Integer> map = lists.get(pos);

                for (Integer  k : map.values()){
//                    Glide
//                            .with( context )
//                            .load(k)//显示gif
////                            .asGif()
////                            .crossFade()//图片淡入加载
//                            .asBitmap()
////                            .animate( animationObject )
//                            .placeholder(k)
//                            .skipMemoryCache( true )//不加人内存缓存
//                            .diskCacheStrategy( DiskCacheStrategy.NONE )//不加人磁盘缓存
//                            .into( holder.emoji);
                  holder.emoji.setImageResource(k);
                }



        }
        holder.emoji_text.setVisibility(View.GONE);


        return convertView;
    }
    static class ViewHolder{
        private ImageView emoji;
        private TextView emoji_text;
    }
}