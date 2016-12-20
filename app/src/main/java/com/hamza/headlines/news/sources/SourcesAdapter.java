package com.hamza.headlines.news.sources;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamza.headlines.R;
import com.hamza.headlines.news.NewsActivity;
import com.hamza.headlines.news.feedSelect.FeedSelectFragment;
import com.hamza.headlines.util.Constants;
import com.hamza.headlines.util.Helpers;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    List<Source> sources;
    private Context context;

    public void set(List<Source> sources, Context context){
        this.sources = sources;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.source_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Source src = sources.get(position);
        holder.name_TV.setText(src.getName());
        holder.category_TV.setText(Helpers.toSentenceCase(src.getCategory()));
        try {
            Log.d("imageurl", src.getUrlsToLogos().small);
            Picasso.with(context).load(src.getUrlsToLogos().small).into(holder.logo_IV);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo_IV;
        TextView name_TV;
        TextView category_TV;
        CardView item_root_CV;

        public ViewHolder(View itemView) {
            super(itemView);
            logo_IV = (ImageView) itemView.findViewById(R.id.imageView_logo);
            name_TV = (TextView) itemView.findViewById(R.id.textView_name);
            category_TV = (TextView) itemView.findViewById(R.id.textView_category);
            item_root_CV = (CardView) itemView.findViewById(R.id.item_root);

            item_root_CV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((NewsActivity)context).findViewById(R.id.content_news) != null) {

                        Source src = sources.get(getAdapterPosition());
                        FeedSelectFragment fragment = new FeedSelectFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(Constants.SOURCE_KEY, src.getId());
                        bundle.putStringArray(Constants.SORT_BY_KEYS_LIST, src.getSortBysAvailable());
                        fragment.setArguments(bundle);
                        ((NewsActivity) context).getSupportFragmentManager().beginTransaction().add(R.id.content_news,
                                fragment ,null).addToBackStack(null).commit();
                    }
                }
            });
        }


    }
}
