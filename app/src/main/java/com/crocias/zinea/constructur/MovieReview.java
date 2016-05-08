package com.crocias.zinea.constructur;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jose on 10/6/15.
 */
public class MovieReview implements Parcelable{

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;





    public MovieReview() {}

    protected MovieReview(Parcel in) {
        author = in.readString();
        content = in.readString();
      }

    public static final Creator<MovieReview> CREATOR = new Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel in) {
            return new MovieReview(in);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content= content;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(content);


    }

    public static class MovieReviewResult {
        private List<MovieReview> results;
        public List<MovieReview> getResults() {
            return results;
        }
    }
}
