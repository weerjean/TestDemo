package com.weerjean.testdemo.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weerjean.testdemo.R;
import com.weerjean.testdemo.bean.UpdateHistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weerjean at 2019/7/30
 * description:
 */
public class PullRefreshAdapter extends RecyclerView.Adapter {

    private List<ItemData> arrayList = new ArrayList();

    private Context context;


    public PullRefreshAdapter(){
        super();
    }
    public PullRefreshAdapter(Context context){
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_update_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemData itemData = arrayList.get(position);
        ((MyViewHolder)holder).dateTv.setText(itemData.getUrl());
        Picasso.with(context).load(itemData.getUrl()).resize(500,500).into(((MyViewHolder) holder).img);
    }

    @Override
    public int getItemCount() {
        return arrayList ==null?0: arrayList.size();
    }

    public void setData(List list) {
        if(list!=null){
            arrayList.clear();
            arrayList.addAll(list);
        }
    }

    public void addData(List list) {
        if(list!=null) {
            arrayList.addAll(list);
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView dateTv;
        private ImageView img;


        public MyViewHolder(View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.tv_update_date);
            img = itemView.findViewById(R.id.iv_img);

        }
    }
}
