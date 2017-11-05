package com.example.wallymisr.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by WallyMisr on 05/11/2017.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<testclass> testclasses;
    private Context context;

    public Adapter(Context context, ArrayList<testclass> testclasses) {
        this.context = context;
        this.testclasses = testclasses;

    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_android.setText(testclasses.get(position).getName());
        Picasso.with(context).load(testclasses.get(position).getImage()).resize(120, 60).into(holder.img_android);


    }

    @Override
    public int getItemCount() {
        return testclasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}
