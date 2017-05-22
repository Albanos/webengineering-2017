package com.micromata.webengineering.demo.post;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {
    //Wir wollen nun persistiert speichern, also keine Liste mehr
    //private List<Post> posts = new LinkedList<>();

    @Autowired
    private PostRepository repository;


    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        return repository.findAll();
    }


    /**
     * Add a new post.
     *
     * @param post the post title.
     */
    public void addPost(Post post) {
        //sdf = new SimpleDateFormat("HH:mm:ss");
        //cal =  Calendar.getInstance();


        //posts.add(new Post(title, sdf.format(cal.getTime()),id++));
        //posts.add(new Post(title));
        repository.save(post);
    }

    public Post getPost(Long id){
        /*
        for(Post p : posts){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
        */
        return repository.findOne(id);
    }

    public void deletePost(Long id){
        /*
        for(Post p : posts) {
            if (p.getId() == id) {
                posts.remove(p);
            }
        }
        */
        repository.delete(id);
    }
}
