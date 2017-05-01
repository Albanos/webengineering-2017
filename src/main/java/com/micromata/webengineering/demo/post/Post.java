package com.micromata.webengineering.demo.post;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.tomcat.jni.Time;

/**
 * Created by Luan Hajzeraj on 25.04.2017.
 */
public class Post {
    private String title;
    private String time_Stamp;
    private int id;

    public Post(String title,String time_Stamp,int id){
        this.title = title;
        this.time_Stamp = time_Stamp;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
