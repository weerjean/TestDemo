package com.weerjean.testdemo.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weerjean.testdemo.R;

import java.util.List;

/**
 * Created by weerjean on 2016/8/28.
 * description：RecylerView的adapter类
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;

    private List<ItemData> mItemDatas;

    public MyRecyclerViewAdapter(Context context,List<ItemData> data) {
        this.mContext=context;
        this.mItemDatas=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // viewHolder被创建的时候的回调
        View itemView = View.inflate(mContext, R.layout.item_recyclerview, parent);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv.setText("图片："+position);
        Picasso.with(mContext).load(mItemDatas.get(position).getUrl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;

        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.iv_recyclrviewitem);
            tv = (TextView)itemView.findViewById(R.id.tv_recyclrviewitem);
        }

    }
}
