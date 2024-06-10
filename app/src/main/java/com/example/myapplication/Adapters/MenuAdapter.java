package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.comparators.MenuItemComparator;

import java.util.ArrayList;

import models.MenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private ArrayList<MenuItem> items;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class MenuHolder extends RecyclerView.ViewHolder {
        private final TextView day_view;
        private final TextView name_view;


        public MenuHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            day_view = view.findViewById(R.id.menu_day);
            name_view = view.findViewById(R.id.menu_name);
        }

        public TextView getDay_view() {
            return day_view;
        }

        public TextView getName_view(){
            return name_view;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet ArrayList<Item> containing the data to populate views to be used
     * by RecyclerView.
     */
    public MenuAdapter(ArrayList<MenuItem> dataSet) {
        this.items = dataSet;
        MenuItemComparator menuItemComparator = new MenuItemComparator();
        items.sort(menuItemComparator);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MenuAdapter.MenuHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_row, viewGroup, false);
        return new MenuAdapter.MenuHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MenuAdapter.MenuHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        MenuItem item = items.get(position);
        System.out.println(item.toString() + " " +getItemCount());

        TextView nameView = viewHolder.getName_view().findViewById(R.id.menu_name);
        nameView.setText(String.valueOf(item.getName()));

        TextView dayView = viewHolder.getDay_view();//.findViewById(R.id.menu_day);
        System.out.println(dayView);
        System.out.println(item.dayStringHashMap.get(item.getDay()));
        dayView.setText(item.dayStringHashMap.get(item.getDay()));

        //TextView itemNameTextView = viewHolder.itemView.findViewById(R.id.menu_day);
        //itemNameTextView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}