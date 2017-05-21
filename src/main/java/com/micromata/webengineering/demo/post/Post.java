package com.micromata.webengineering.demo.post;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.tomcat.jni.Time;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Luan Hajzeraj on 25.04.2017.
 */
public class Post {

    private static AtomicLong nextId = new AtomicLong();


    //MERKE: getter und setter müssen da sein, damit Spring dies verarbeiten und anzeien kann...
    private Long id;

    private String title;

    //Ausgabe in Milli-sekunden, von 1970 an
    private Date time_Stamp;

    public Post(String title){
        this.title = title;
        time_Stamp = new Date();
        id=nextId.getAndIncrement();
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    //MERKE: Es muss offensichtlich ein geter existieren, sonst wird der TimeStamp nicht mit ausgegeben!
    //scheinbar macht das Spring im Hintergrund...
    public Date getTime_Stamp() {
        return time_Stamp;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
