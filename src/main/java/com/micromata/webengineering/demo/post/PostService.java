package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserRepository;
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

    @Autowired
    private UserRepository userRepository;


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

        //Wir suchen den User (im moment ja nur einer vorhanden) und adden ihn als Author zum Post. Erst danach wird
        //persistiert
        User author = userRepository.findByEmail("l.hajzeraj@gmail.com");
        post.setAuthor(author);

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
