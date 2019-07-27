package com.example.paginglibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context ctx;
    private List<PhotoModel> dataList;

    Adapter(Context ctx, List<PhotoModel> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.recyclerview_user, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(dataList.get(i).getTitle());

        Picasso.Builder builder = new Picasso.Builder(ctx);
        builder.downloader(new OkHttp3Downloader(ctx));

        builder.build().load(dataList.get(i).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}
