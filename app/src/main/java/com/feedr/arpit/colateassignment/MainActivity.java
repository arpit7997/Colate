package com.feedr.arpit.colateassignment;

import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.feedr.arpit.colateassignment.Adapter.PostFragmentAdapter;
import com.feedr.arpit.colateassignment.Model.Post;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter postAdapter;

    List<Post> list = new ArrayList<>();

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10);
        }

        Post first = new Post("Think. learn. apply. code !!", "2h", "Arpit Gupta", "Hé ! bonjour, Monsieur du Corbeau." + "Que vous êtes joli ! Que vous me semblez beau !" + "Sans mentir, si votre ramage Se rapporte à votre plumage Vous êtes le Phénix des \n hôtes de ces bois. ", null, R.drawable.img1);


        Post second = new Post("Company Volunteering", "1d", "Aksveer Desai", null,R.drawable.android , R.drawable.ic_launcher_background);


        Post third = new Post("Picture toh abhi baki hai..", "3m", "Shahrukh Khan", "Hé ! bonjour, Monsieur du Corbeau." + "Que vous êtes joli ! Que vous me semblez beau !" + "Sans mentir, si votre ramage Se rapporte à votre plumage Vous êtes le Phénix des \n hôtes de ces bois. ", null, R.drawable.shahrukh);

        list.add(first);
        list.add(second);
        list.add(third);

        recyclerView = (RecyclerView) findViewById(R.id.reyclerview);
        recyclerView.setHasFixedSize(true);


        postAdapter = new PostFragmentAdapter(this, list);

        recyclerView.setAdapter(postAdapter);
        recyclerView.setNestedScrollingEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        postAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            Toast.makeText(MainActivity.this, "Filter Button Click", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
