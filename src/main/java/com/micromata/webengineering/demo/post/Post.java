package com.micromata.webengineering.demo.post;

import org.apache.tomcat.jni.Time;

/**
 * Created by Luan Hajzeraj on 25.04.2017.
 */
public class Post {
    private String title;
    private String time_Stamp;

    public Post(String title,String time_Stamp){
        this.title = title;
        this.time_Stamp = time_Stamp;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeStamp(String time_Stamp) {
        this.time_Stamp = time_Stamp;
    }

    public String getTimeStamp() {
        return time_Stamp;
    }

    public String getTitle() {
        return title;
    }


}
