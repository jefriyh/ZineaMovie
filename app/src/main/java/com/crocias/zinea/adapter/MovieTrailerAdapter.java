package com.crocias.zinea.adapter;

/**
 * Created by Jefri Yushendri on 8/5/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crocias.zinea.constructur.MovieTrailer;
import com.crocias.zinea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefri Yushendri on 7/5/2016.
 */
class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView trailer_title;
    public View line;
    public MovieTrailerViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
       trailer_title = (TextView) itemView.findViewById(R.id.trailer_title);
        line =(View) itemView.findViewById(R.id.line);
    }
}
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerViewHolder> {
    private List<MovieTrailer> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieTrailerAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MovieTrailerViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = mInflater.inflate(R.layout.row_movietrailer, parent, false);
        final MovieTrailerViewHolder viewHolder = new MovieTrailerViewHolder(view);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                MovieTrailer movie = mMovieList.get(position);

                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getKey())));;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieTrailerViewHolder holder, int position) {
        MovieTrailer movie = mMovieList.get(position);
       holder.trailer_title.setText(movie.getTitle());

        if(position==mMovieList.size()-1){
            holder.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<MovieTrailer> movieList) {
        this.mMovieList = new ArrayList<>();
        this.mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }



}
