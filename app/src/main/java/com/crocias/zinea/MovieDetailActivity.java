package com.crocias.zinea;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.crocias.zinea.adapter.MovieReviewAdapter;
import com.crocias.zinea.adapter.MovieTrailerAdapter;
import com.crocias.zinea.constructur.Movie;
import com.crocias.zinea.constructur.MovieReview;
import com.crocias.zinea.constructur.MovieTrailer;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";

    private Movie mMovie;
    ImageView backdrop;
    ImageView poster;
    TextView title, releaseDate, rating;
    DocumentView description;
    RecyclerView mRecyclerView,mRRecyclerView ;
    MovieTrailerAdapter mAdapter;
    MovieReviewAdapter mRAdapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(mMovie.getTitle());

        backdrop = (ImageView) findViewById(R.id.backdrop);
        title = (TextView) findViewById(R.id.movie_title);
        description = (DocumentView) findViewById(R.id.movie_description);
        poster = (ImageView) findViewById(R.id.movie_poster);
        rating = (TextView) findViewById(R.id.rating);

        releaseDate = (TextView) findViewById(R.id.releaseDate);

        title.setText(mMovie.getTitle());
        rating.setText(mMovie.getRating()+"/10.0");
        releaseDate.setText(mMovie.getReleaseDate().substring(0,4));
        description.setText(mMovie.getDescription());
        id= mMovie.getIdMovie();
        Picasso.with(this)
                .load(mMovie.getPoster())
                .into(poster);
        Picasso.with(this)
                .load(mMovie.getBackdrop())
                .into(backdrop);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewReview);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRRecyclerView.setLayoutManager(manager);

        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager2);


        mAdapter = new MovieTrailerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRAdapter= new MovieReviewAdapter(this);
        mRRecyclerView.setAdapter(mRAdapter);
        getTrailerMovies();
    }





    private void getTrailerMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "9f99f38508e14e4607bd217df834a8d7");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        MoviesApiService service = restAdapter.create(MoviesApiService.class);
        service.getTrailerMovies(id,new Callback<MovieTrailer.MovieTrailerResult>() {
            @Override
            public void success(MovieTrailer.MovieTrailerResult movieResult, Response response) {
                mAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

        service.getReviewMovies(id,new Callback<MovieReview.MovieReviewResult>() {
            @Override
            public void success(MovieReview.MovieReviewResult movieResult, Response response) {
                mRAdapter.setMovieList(movieResult.getResults());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });


    }








}
