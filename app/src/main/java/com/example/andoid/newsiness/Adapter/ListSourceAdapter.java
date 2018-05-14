package com.example.andoid.newsiness.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andoid.newsiness.Common.Common;
import com.example.andoid.newsiness.Interface.IconBetterIdeaService;
import com.example.andoid.newsiness.Interface.ItemClickListener;
import com.example.andoid.newsiness.ListNews;
import com.example.andoid.newsiness.Model.Icon;
import com.example.andoid.newsiness.Model.IconBetterIdea;
import com.example.andoid.newsiness.Model.WebSite;
import com.example.andoid.newsiness.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Chinmay on 19-Apr-18.
 */

class ListSourceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{


    TextView source_title;
    CircleImageView source_image;

    ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image = itemView.findViewById(R.id.source_image);
        source_title = itemView.findViewById(R.id.source_name);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }


}



public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{

    private Context context;
    private WebSite webSite;
    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;
        mService = Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_list,parent, false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {
        StringBuilder iconBetterApi = new StringBuilder("https://besticon-demo.herokuapp.com/allicons.json?url=");
        iconBetterApi.append(webSite.getSources().get(position).getUrl());

        mService.getIconUrl(iconBetterApi.toString())
                .enqueue(new Callback<IconBetterIdea>() {
                    @Override
                    public void onResponse(Call<IconBetterIdea> call, Response<IconBetterIdea> response) {
                        if(response.body()!=null && response.body().getIcons()!=null && response.body().getIcons().size() > 0 && !TextUtils.isEmpty(response.body().getIcons().get(0).getUrl()))
                        {
                            Picasso.with(context)
                                    .load(response.body().getIcons().get(0).getUrl())
                                    .into(holder.source_image);
                        }
                    }

                    @Override
                    public void onFailure(Call<IconBetterIdea> call, Throwable t) {

                    }
                });

        holder.source_title.setText(webSite.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();
    }
}
