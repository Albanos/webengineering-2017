package com.micromata.webengineering.demo.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.micromata.webengineering.demo.user.User;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.tomcat.jni.Time;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Luan Hajzeraj on 25.04.2017.
 */

//@Entity: Sagt aus, dass Post eine Entinität in Datenbank ist, also in der Datenbank persistieren
@Entity
public class Post {

    private static AtomicLong nextId = new AtomicLong();

    //@ID: Sorgt dafür, dass ID primärschlüssel in Datenbank wird
    //@GeneratedValue: Sorgt dafür, dass ID eigenschaften eines primärschlüssels hat, also bspw. eindeutig ist
    //MERKE: getter und setter müssen da sein, damit Spring dies verarbeiten und anzeien kann...
    @Id
    @GeneratedValue
    private Long id;


    //Standardwert für die Spalte "Titel" in Datenbank ist 255. Mit der Annotation @Column und Parameter length
    //erhöhen wir diesen Wert auf 1024
    @Column(length = 1024)
    private String title;

    //Ausgabe in Milli-sekunden, von 1970 an
    private Date time_Stamp;

    //Jetzt hat jeder Post einen User. Die Annotation sorgt dafür, dass jeder Post GENAU einen Author besitzt.
    //Dies ist nötig, da ja bspw. nur der Author eines Posts diesen löschen und ändern können soll
    @ManyToOne(optional = false)
    private User author;

    public Post(String title){
        this.title = title;
        //time_Stamp = new Date();
        id=nextId.getAndIncrement();
    }
    public Post(){

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

    //Sobald wir persistieren wollen, setzen wir das Datum (ist ansichtssache, man könnte das Datum auch beim anlegen setzen)
    //Durch die Annotation @PrePersist wird die darunter liegende Methode immer beim abspeichern aufgerufen
    @PrePersist
    public void prePersistent(){
        time_Stamp = new Date();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
