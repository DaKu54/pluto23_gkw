package de.hawlandshut.pluto23_gkw.model;

import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;

public class Post {
    private static String TAG ="POST";

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

    public static Post fromDocument( DocumentSnapshot document){
        Log.d(TAG, " Converting document");
        String uid = (String) document.get("uid");
        String email = (String) document.get("email");
        String title = (String) document.get("title");
        String body = (String) document.get("body");
        Date createdAt = ((Timestamp)document.get("createdAt")).toDate();
        String firestoreKey = document.getId();
        return new Post(uid, email, title, body, createdAt, firestoreKey);
    }
}
