package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.createPostList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hawlandshut.pluto23_gkw.model.Post;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    ArrayList<Post> mPostList = createPostList();

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.post_view, parent, false);
        return new CustomViewHolder( view );
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }
}
