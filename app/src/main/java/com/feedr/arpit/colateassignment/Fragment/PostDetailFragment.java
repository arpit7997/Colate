package com.feedr.arpit.colateassignment.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.feedr.arpit.colateassignment.Adapter.PostFragmentAdapter;
import com.feedr.arpit.colateassignment.Model.Post;
import com.feedr.arpit.colateassignment.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostDetailFragment extends android.support.v4.app.Fragment {

    static RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    static RecyclerView.Adapter postAdapter;

    static List<Post> list = new ArrayList<>();

    Toolbar toolbar;
    FloatingActionButton fab_filter;
    Post first,second,third;
    static View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.user_feed_fragment, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        fab_filter = (FloatingActionButton) view.findViewById(R.id.fab_filter);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10);
        }
        view.setAlpha((float) 1);
        list.clear();

        //create post
         first = new Post("Think. learn. apply. code !!", "2h", "Arpit Gupta", "Hé ! bonjour, Monsieur du Corbeau." + "Que vous êtes joli ! Que vous me semblez beau !" + "Sans mentir, si votre ramage Se rapporte à votre plumage Vous êtes le Phénix des \n hôtes de ces bois. ", null, R.drawable.img1);

         second = new Post("Company Volunteering", "1d", "Aksveer Desai", null, R.drawable.android, R.drawable.ic_launcher_background);

         third = new Post("Picture toh abhi baki hai..", "3m", "Shahrukh Khan", "Hé ! bonjour, Monsieur du Corbeau." + "Que vous êtes joli ! Que vous me semblez beau !" + "Sans mentir, si votre ramage Se rapporte à votre plumage Vous êtes le Phénix des \n hôtes de ces bois. ", null, R.drawable.shahrukh);

         //add post to list
        list.add(first);
        list.add(second);
        list.add(third);

        recyclerView = (RecyclerView) view.findViewById(R.id.reyclerview);
        recyclerView.setHasFixedSize(true);


        postAdapter = new PostFragmentAdapter(getActivity(), list);

        recyclerView.setAdapter(postAdapter);
        recyclerView.setNestedScrollingEnabled(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        postAdapter.notifyDataSetChanged();

        //filter onclick
        fab_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setAlpha((float) 0.4);
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                FilterFragment newFragment = FilterFragment.newInstance();
                newFragment.setParentFab(fab_filter);
                newFragment.show(ft, "dialog");
            }
        });

        return view;
    }


    public static class FilterFragment extends AAH_FabulousFragment implements CompoundButton.OnCheckedChangeListener {


        @SuppressLint("NewApi")
        ArrayMap<String, List<String>> applied_filters = new ArrayMap<>();
        RadioButton rbtnPost, rbtnCircular;
        RelativeLayout rl_content;
        LinearLayout ll_buttons;
        ImageButton imgbtn_refresh, imgbtn_apply;
        ImageView img_close_filter;

        private String sortBy;

        public static FilterFragment newInstance() {
            return new FilterFragment();
        }

        @Override
        public void setupDialog(Dialog dialog, int style) {

            View contentView = View.inflate(getContext(), R.layout.filter_fragment, null);

            rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
            ll_buttons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
            rbtnPost = (RadioButton) contentView.findViewById(R.id.post);
            rbtnCircular = (RadioButton) contentView.findViewById(R.id.circular);
            imgbtn_apply = (ImageButton) contentView.findViewById(R.id.imgbtn_apply);
            imgbtn_refresh = (ImageButton) contentView.findViewById(R.id.imgbtn_refresh);
            img_close_filter = (ImageView) contentView.findViewById(R.id.img_close_filter);

            rbtnPost.setOnCheckedChangeListener(this);
            rbtnCircular.setOnCheckedChangeListener(this);

            //params to set
            setAnimationDuration(400);
            setViewgroupStatic(ll_buttons);
            setViewMain(rl_content);
            setMainContentView(contentView);
            super.setupDialog(dialog, style);

            imgbtn_apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (sortBy == null) {
                        closeFilter(applied_filters);
                        return;
                    }

                    List<Post> postList = new ArrayList<>();
                    List<Post> circularList = new ArrayList<>();

                    //add to post list
                    if (sortBy.equals("post")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getPostImage() != null) {
                                postList.add(list.get(i));
                            }
                        }
                        postAdapter = new PostFragmentAdapter(getActivity(), postList);

                    }
                    //add to circular list
                    else {

                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getDescription() != null) {
                                circularList.add(list.get(i));
                            }
                        }
                        postAdapter = new PostFragmentAdapter(getActivity(), circularList);

                    }
                    recyclerView.setAdapter(postAdapter);
                    postAdapter.notifyDataSetChanged();

                    closeFilter(applied_filters);
                }
            });

            //show original list on clicking refresh
            imgbtn_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    postAdapter = new PostFragmentAdapter(getActivity(), list);
                    recyclerView.setAdapter(postAdapter);
                    postAdapter.notifyDataSetChanged();
                    closeFilter(applied_filters);
                }
            });

            img_close_filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postAdapter.notifyDataSetChanged();
                    closeFilter(applied_filters);
                }
            });

        }

        //click listner on radio buttons
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String sortBy = null;
            switch (buttonView.getId()) {
                case R.id.post:
                    if (isChecked) {
                        sortBy = "post";
                    } else {
                        sortBy = null;
                    }
                    break;

                case R.id.circular:
                    if (isChecked) {
                        sortBy = "circular";
                    } else {
                        sortBy = null;
                    }
                    break;
            }
            if (sortBy != null) {
                this.sortBy = sortBy;
            }
        }

        //set visibility to 1 when view is destroyed mainly due to backPressed
        @Override
        public void onDestroyView() {
            super.onDestroyView();

            view.setAlpha((float) 1);
            closeFilter(applied_filters);
        }
    }
}