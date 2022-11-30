package de.hawlandshut.pluto23_gkw.Testdata;

import java.util.ArrayList;
import java.util.Date;

import de.hawlandshut.pluto23_gkw.model.Post;

public class Testdata {

    public static String TEST_MAIL = "fhgreipl@gmail.com";
    public static String TEST_PASSWORD = "123456";

    public static ArrayList<Post> createPostList(){
        ArrayList<Post> postList = new ArrayList<Post>();

        for( int i = 0 ; i<2 ; i++) {
            postList.add(
                    new Post("uid",
                            "email@google.com",
                             "title",
                            "body " +i,
                             new Date(),
                            "fbkey")
            );
        }


        return postList;
    }
}
