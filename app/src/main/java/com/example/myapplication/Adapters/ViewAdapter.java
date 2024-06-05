package com.example.myapplication.Adapters;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.comparators.ItemComparator;

import java.util.ArrayList;

import models.Item;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private ArrayList<Item> items;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.item_name);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet ArrayList<Item> containing the data to populate views to be used
     * by RecyclerView.
     */
    public ViewAdapter(ArrayList<Item> dataSet) {
        this.items = dataSet;
        //Sort the arrayList by having lowest quantity items first.
        ItemComparator itemComparator = new ItemComparator();
        dataSet.sort(itemComparator);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Item item = items.get(position);
        System.out.println(position  + " , " + item.toString() + items.size());
        TextView textView = viewHolder.itemView.findViewById(R.id.item_count);
        textView.setText(String.valueOf(item.getCount()));

        textView = viewHolder.itemView.findViewById(R.id.item_name);
        textView.setText(item.getName());

        if (item.getRequired().equals(true)){
            Drawable layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.rounded_layout_red);
            viewHolder.itemView.findViewById(R.id.item_container).setBackground(layout);
            //viewHolder.getTextView().setBackgroundColor(Color.RED);
        }
        //viewHolder.getTextView().setText(items.get(position).toString());

        //TextView textView = (TextView) findViewById(R.id.item_cost);
        //viewHolder.g
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
}