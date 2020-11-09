package asan.example.android_lesson_5;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TitleModel implements Serializable {
    public String title;
    public String number;
    public String imageView;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }


}

