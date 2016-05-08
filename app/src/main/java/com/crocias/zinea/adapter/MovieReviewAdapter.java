package com.crocias.zinea.adapter;

/**
 * Created by Jefri Yushendri on 8/5/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.crocias.zinea.R;
import com.crocias.zinea.constructur.MovieReview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefri Yushendri on 7/5/2016.
 */
class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    public TextView author;
    public DocumentView content;

    public MovieReviewViewHolder(View itemView) {
        super(itemView);

       author = (TextView) itemView.findViewById(R.id.author);
       content = (DocumentView) itemView.findViewById(R.id.movie_review);

    }
}

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewViewHolder> {
    private List<MovieReview> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieReviewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = mInflater.inflate(R.layout.row_moviereview, parent, false);
        final MovieReviewViewHolder viewHolder = new MovieReviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder holder, int position) {
        MovieReview movie = mMovieList.get(position);
           holder.author.setText(movie.getAuthor());
           holder.content.setText(movie.getContent());

    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<MovieReview> movieList) {
        this.mMovieList = new ArrayList<>();
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }



}
