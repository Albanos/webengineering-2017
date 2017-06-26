package com.micromata.webengineering.demo.integration;

import com.micromata.webengineering.demo.post.Post;
import com.micromata.webengineering.demo.post.PostService;
import com.micromata.webengineering.demo.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Luan Hajzeraj on 11.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationTest.class);

    @LocalServerPort
    int port;

    @Autowired
    private UserService userService;


    /**
     * Test that listing posts works.
     */
    @Test
    public void testPostList() {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<List> response = rest.getForEntity(getPostURL(), List.class);

        List<Post> posts = response.getBody();
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(posts.size() == 0);
    }

    /**
     * Test that authentication and posting posts works.
     */
    @Test
    public void testAddPost() {
        RestTemplate rest = new RestTemplate();

        //Wichtig ist für das setzen eines Posts, einen authentifizierten User zu haben,
        //deshalb muss über den Header ein JWT-Token mitgegeben werden
        HttpHeaders headers = new HttpHeaders();

        //WICHTIG: Token ist ungleich hash!!! Mit dem Hash-wert wird das token erst generiert, im AuthenticationService
        //war zuvor ein richtiger Denkfehler...
        //Authentifizierung als Luan, mit entsprechendem Token
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWFuIiwianRpIjoiMSJ9.OOfxIrCaXhZhAGLTAmEWneNDiY--N15g5CxI7b8n7WI2HtGmEQwNYAz05z69bldDwRmfBqJ8eWXwkcvQbRXTIw");

        Post post = new Post();

        String title = "test-post";
        post.setTitle(title);

        //Aus irgendeinem Grund wird der Author für einen Post nicht direkt gesetzt, weshalb die folgenden
        //beiden Zeilen immer nötig sind, abweichend vom Muster der Vorlesung: Author muss beim Post immer gesetzt
        //werden
        //userService.setCurrentUser(1L,"luan");
        //post.setAuthor(userService.getCurrentUser());

        HttpEntity<Post> entity = new HttpEntity<>(post, headers);
        ResponseEntity<Map> response = rest.postForEntity(getPostURL(), entity, Map.class);

        //Wir nutzen die von Hand generierte URL, da diese Klasse random-Ports nutzt und daher
        //der Port bei jedem Durchlauf anders wäre (addPost wäre so nicht testbar...)
        String url = fixResponseURL((String) response.getBody().get("url"));
        Post storedPost = rest.getForObject(url, Post.class);

        //Vergleiche Titel mit gesetztem Titel und Author mit authorisiertem, gesetzten User
        assertEquals(title, storedPost.getTitle());
        assertEquals("luan", storedPost.getAuthor().getEmail());
    }

    private String getPostURL() {
        return "http://localhost:" + port + "/api/post";
    }

    //Da wir in dieser Testklasse random-Ports nutzten, kennen wir nie die genutzte Port-nummer, was
    //schlecht für testAddPost() ist. Deshalb setzen wir die URL an dieser Stelle temporär
    private String fixResponseURL(String url) {
        return url.replaceAll(":8080/", ":" + port + "/");
    }
}
