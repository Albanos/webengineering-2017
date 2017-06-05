package com.micromata.webengineering.demo.comment;

import com.micromata.webengineering.demo.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Luan Hajzeraj on 05.06.2017.
 */

//@Entity bedeutet: Wir wollen dies Persistieren
@Entity
public class Comment {
    //Maximale Kommentarlänge = 65 kByte
    public static final int COMMENT_LENGTH = 65536;

    //Wir haben einen primär-schlüssel, der auch automatisch generiert wird
    @Id
    @GeneratedValue
    private Long id;

    //Viele Kommentare können zu einem Author gehören
    @ManyToOne(optional = false)
    private User author;

    @Column(length = Comment.COMMENT_LENGTH)
    private String text;

    private Date createdAt;

    public static int getCommentLength() {
        return COMMENT_LENGTH;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //Rufe diese Methode vor dem Speichern auf und setze dabei das Creation-Datum
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
