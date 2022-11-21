package de.hawlandshut.pluto23_gkw.Testdata;

import java.util.ArrayList;
import java.util.Date;

import de.hawlandshut.pluto23_gkw.model.Post;

public class Testdata {

    public static ArrayList<Post> createPostList(){
        ArrayList<Post> postList = new ArrayList<Post>();

        postList.add(
                new Post( "uid",
                        "email@google.com",
                        "title",
                        "body",
                        new Date(),
                        "fbkey")
        );

        postList.add(
                new Post( "uid1",
                        "email1@google.com",
                        "title1",
                        "body1",
                        new Date(),
                        "fbkey1")
        );

        return postList;
    }
}
