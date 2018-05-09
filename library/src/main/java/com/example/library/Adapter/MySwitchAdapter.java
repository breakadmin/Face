package com.example.library.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.library.R;

import java.util.List;

/**
 * fragment 切换适配器
 */

public class MySwitchAdapter extends RecyclerView.Adapter<MySwitchAdapter.ViewHolder> implements View.OnClickListener {
    Context context;
    List<Integer> data;
    OnItemClickListener onItemClickListener;
    int Position;
    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MySwitchAdapter(Context context, List<Integer> data){
        this.context=context;
        this.data=data;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.point_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取position
            onItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.emoji.setImageResource(data.get(position));
        holder.emoji_text.setVisibility(View.GONE);
        if(position==getPosition()){
            holder.emoji_layout.setBackgroundColor(context.getResources().getColor(R.color.plus_overlay));
        }
        else{
            holder.emoji_layout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout emoji_layout;
        private ImageView emoji;
        private TextView emoji_text;
        public ViewHolder(View itemView) {
            super(itemView);
            emoji_layout=itemView.findViewById(R.id.emoji_layout);
            emoji=itemView.findViewById(R.id.emoji);
            emoji_text=itemView.findViewById(R.id.emoji_text);

        }
    }
}
