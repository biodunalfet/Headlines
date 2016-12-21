package com.hamza.headlines.news.feeds;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hamza.headlines.R;
import com.hamza.headlines.news.NewsActivity;
import com.hamza.headlines.util.Helpers;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/19/2016.
 */

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<ArticleSummary> articleSummaryList;
    private FeedPresenter presenter;

    public FeedRecyclerAdapter(Context context, List<ArticleSummary> articleSummaryList){
        this.context = context;
        this.articleSummaryList = articleSummaryList;
    }

    public void setPresenter(FeedPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.article_summary_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticleSummary articleSummary = articleSummaryList.get(position);

        holder.article_title_tv.setText(articleSummary.getTitle());
        holder.article_author_tv.setText(Helpers.FormatAuthorName(articleSummary.getAuthor()));

        try{
            Picasso.with(context)
                    .load(articleSummary.getUrlToImage())
                    .placeholder(R.color.colorPlaceHolder)
                    .into(holder.article_backg_iv);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return articleSummaryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView article_title_tv;
        TextView article_author_tv;
        ImageView article_backg_iv;
        CardView root_cv;

        public ViewHolder(View itemView) {
            super(itemView);
            article_author_tv = (TextView) itemView.findViewById(R.id.article_author_textView);
            article_backg_iv = (ImageView) itemView.findViewById(R.id.article_imageView);
            article_title_tv = (TextView) itemView.findViewById(R.id.article_title_textView);
            root_cv = (CardView) itemView.findViewById(R.id.root);

            root_cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArticleSummary articleSummary = articleSummaryList.get(getAdapterPosition());
                    presenter.openTab(articleSummary);
                }
            });
        }
    }
}
