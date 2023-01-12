package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.createPostList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hawlandshut.pluto23_gkw.model.Post;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private static final String TAG = "xx CustomAdapter";
    // Mit leerer Liste initialisieren;
    ArrayList<Post> mPostList = new ArrayList<Post>();
    int c = 0;
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.post_view, parent, false);
        Log.d(TAG, "created Viewholder --> "+ (c++));
        return new CustomViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Post post = mPostList.get( position );
        holder.mLine1.setText( post.email +", " + post.createdAt );
        holder.mLine2.setText( post.body );
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }
}
