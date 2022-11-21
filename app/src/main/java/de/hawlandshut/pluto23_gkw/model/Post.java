package de.hawlandshut.pluto23_gkw.model;

import java.util.Date;

public class Post {
    public String uid; // UID des sendenden Users
    public String email; // Email des sendenden Users
    public String title; // Titel der Message
    public String body; // Body der Message
    public Date createdAt; // Datum/Uhrzeit der Erzeugung der Message
    public String firestoreKey; // Primärschlüssel der Message

    public Post(String uid, String email, String title, String body, Date createdAt, String firestoreKey) {
        this.uid = uid;
        this.email = email;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.firestoreKey = firestoreKey;
    }
}
