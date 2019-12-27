package com.yatoooon.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yhl on 2017/12/29.
 */

public class MessageBean implements Parcelable {
    private String content;
    private int level;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeInt(this.level);
    }

    public MessageBean() {
    }

    protected MessageBean(Parcel in) {
        this.content=in.readString();
        this.level=in.readInt();
    }



    public MessageBean(String content, int level) {
        this.content = content;
        this.level = level;
    }



    public void readFromParcel(Parcel dest){
        this.content=dest.readString();
        this.level=dest.readInt();
    }


    public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
        @Override
        public MessageBean createFromParcel(Parcel in) {
            return new MessageBean(in);
        }

        @Override
        public MessageBean[] newArray(int size) {
            return new MessageBean[size];
        }
    };

    @Override
    public String toString() {
        return "MessageBean{" +
                "content='" + content + '\'' +
                ", level=" + level +
                '}';
    }
}
