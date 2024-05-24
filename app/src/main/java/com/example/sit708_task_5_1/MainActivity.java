package com.example.sit708_task_5_1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Main activity class that manages two RecyclerViews, one for horizontal display and one for grid display.
 */
public class MainActivity extends AppCompatActivity {
    // RecyclerView for horizontal display
    private RecyclerView rv;
    // Data source for the horizontal RecyclerView
    private ArrayList<String> dataSource;
    // LayoutManager for horizontal RecyclerView
    private LinearLayoutManager linearLayoutManager;
    // Generic LayoutManager, used here for the grid layout
    private RecyclerView.LayoutManager layoutManager;
    // Adapter for the horizontal RecyclerView
    private MyRvAdapter myRvAdapter;
    // RecyclerView for grid display
    private RecyclerView newsRv;
    // Adapter for the grid RecyclerView
    private RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the horizontal RecyclerView
        initHorizontalRecyclerView();
        // Initialize the grid RecyclerView
        initGridRecyclerView();
    }

    /**
     * Initializes the horizontal RecyclerView with LinearLayoutManager and sets the adapter.
     */
    private void initHorizontalRecyclerView() {
        rv = findViewById(R.id.horizontalRv);
        dataSource = new ArrayList<>(Arrays.asList("News", "News", "News", "News", "News", "News"));
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);

        // Set the click listener for the horizontal RecyclerView items
        myRvAdapter.setOnItemClickListener(new MyRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                displayDetailsFragment(dataSource.get(position), "Sample Description", R.drawable.ic_launcher_background);
            }
        });
    }

    /**
     * Initializes the grid RecyclerView with GridLayoutManager and sets the adapter.
     */
    private void initGridRecyclerView() {
        newsRv = findViewById(R.id.newsRv);
        layoutManager = new GridLayoutManager(this, 2);
        int[] array = new int[6];
        Arrays.fill(array, R.drawable.ic_launcher_background);
        rvAdapter = new RvAdapter(array);
        newsRv.setLayoutManager(layoutManager);
        newsRv.setAdapter(rvAdapter);
        newsRv.setHasFixedSize(true);

        // Set the click listener for the grid RecyclerView items
        rvAdapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                displayDetailsFragment("Sample Title", "Sample Description", R.drawable.ic_launcher_background);
            }
        });
    }

    /**
     * Displays a detail fragment for the clicked news item.
     *
     * @param title       The title of the news item.
     * @param description The description of the news item.
     * @param imageResId  The resource ID for the image.
     */
    private void displayDetailsFragment(String title, String description, int imageResId) {
        Log.d("NewsApp", "News item clicked, title: " + title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.newsDetailFragmentContainer, NewsDetailFragment.newInstance(title, description, imageResId))
                .addToBackStack(null)
                .commit();

        // Make the container visible
        findViewById(R.id.newsDetailFragmentContainer).setVisibility(View.VISIBLE);
    }
}
