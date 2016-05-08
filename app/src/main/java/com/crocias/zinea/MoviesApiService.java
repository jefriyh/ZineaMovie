package com.crocias.zinea;

import com.crocias.zinea.constructur.Movie;
import com.crocias.zinea.constructur.MovieReview;
import com.crocias.zinea.constructur.MovieTrailer;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by jose on 10/6/15.
 */
public interface MoviesApiService {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Movie.MovieResult> cb);

    @GET("/movie/top_rated")
    void getTopMovies(Callback<Movie.MovieResult> cb);





    @GET("/movie/{id}/videos")
    void getTrailerMovies(@Path("id") String id, Callback<MovieTrailer.MovieTrailerResult> cb);

    @GET("/movie/{id}/reviews")
    void getReviewMovies(@Path("id") String id, Callback<MovieReview.MovieReviewResult> cb);




}
