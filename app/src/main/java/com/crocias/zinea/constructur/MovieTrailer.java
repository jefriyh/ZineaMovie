package com.crocias.zinea.constructur;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jose on 10/6/15.
 */
public class MovieTrailer implements Parcelable{

    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String title;





    public MovieTrailer() {}

    protected MovieTrailer(Parcel in) {
        key = in.readString();
        title = in.readString();
      }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return "https://www.youtube.com/watch?v=" + key;
    }

    public void setKey(String key) {
        this.key= key;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(title);


    }

    public static class MovieTrailerResult {
        private List<MovieTrailer> results;
        public List<MovieTrailer> getResults() {
            return results;
        }
    }
}
