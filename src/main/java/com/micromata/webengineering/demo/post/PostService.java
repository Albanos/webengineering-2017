package com.micromata.webengineering.demo.post;

import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    private List<Post> posts = new LinkedList<>();
    private Calendar cal;
    private SimpleDateFormat sdf;


    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public List<Post> getPosts() {
        return posts;
    }


    /**
     * Add a new post.
     *
     * @param title the post title.
     */
    public void addPost(String title) {
        sdf = new SimpleDateFormat("HH:mm:ss");
        cal =  Calendar.getInstance();
        posts.add(new Post(title, sdf.format(cal.getTime())));
    }
}
