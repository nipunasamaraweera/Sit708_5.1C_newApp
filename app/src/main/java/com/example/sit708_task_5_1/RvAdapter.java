package com.example.sit708_task_5_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for a RecyclerView that displays news items with images and text.
 * Each news item displays an image and a sequential number as title.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    // Array of image resource IDs to be used for each news item
    private final int[] array;
    // Listener to handle interactions with RecyclerView items
    private OnItemClickListener listener;

    /**
     * Interface to define the interaction callback method when a news item is clicked.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * Sets the callback listener for item clicks.
     * @param listener the listener that will handle item clicks.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Constructs the adapter using an array of image resource IDs.
     * @param array the array of drawable resource IDs for the images of the news items.
     */
    public RvAdapter(int[] array) {
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout for individual news items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the image and the title for each news item based on its position in the RecyclerView
        holder.imageView.setImageResource(array[position]);
        holder.newsNumber.setText(String.format("News Number %d", position + 1));

        // Setup a click listener for each item
        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (listener != null && currentPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(currentPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the data set
        return array.length;
    }

    /**
     * ViewHolder class for the RecyclerView, which holds references to all views within each item.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;           // ImageView to display the image of the news item
        TextView newsNumber;           // TextView to display the sequential number of the news item
        TextView newsDescription;      // TextView to display the description of the news item

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views from the layout
            imageView = itemView.findViewById(R.id.imageView);
            newsNumber = itemView.findViewById(R.id.newsNumber);
            newsDescription = itemView.findViewById(R.id.newsDescription);
        }
    }
}
