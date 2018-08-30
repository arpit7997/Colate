package com.feedr.arpit.colateassignment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feedr.arpit.colateassignment.Model.Post;
import com.feedr.arpit.colateassignment.R;

import java.util.List;

public class PostFragmentAdapter  extends RecyclerView.Adapter<PostFragmentAdapter.ViewHolder> {


    Context context;
    List<Post> list;

    public PostFragmentAdapter(Context context, List<Post> List) {
        this.context = context;
        this.list = List;
    }


    @Override
    public PostFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostFragmentAdapter.ViewHolder holder, int position) {

        final Post post = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.post_item, null);

        holder.name.setText(post.getName());

        holder.time.setText(post.getTime());

        holder.profile_pic.setImageResource(post.getProfile_image());

        holder.title.setText(post.getTitle());

        if(post.getPostImage()!= null){
            holder.text_circular.setVisibility(View.GONE);
            holder.image_arrow.setVisibility(View.GONE);
            holder.image_post.setImageResource(post.getPostImage());
        }
        else{
            holder.text_circular.setText(post.getDescription());
            holder.image_post.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView time;
        public TextView title;
        public TextView text_circular;
        public ImageView profile_pic;
        public ImageView image_arrow;
        public ImageView image_post;

        View view;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.text_name);
            title = (TextView) itemView.findViewById(R.id.title);
            image_post = (ImageView) itemView.findViewById(R.id.image_post);
            profile_pic = (ImageView) itemView.findViewById(R.id.profile_pic);
            image_arrow = (ImageView) itemView.findViewById(R.id.image_arrow);
            text_circular = (TextView) itemView.findViewById(R.id.text_circular);
            time = (TextView) itemView.findViewById(R.id.time);

            view = itemView;
        }
    }

}
