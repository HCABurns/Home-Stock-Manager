package com.example.myapplication.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Interfaces.RecyclerViewInterface;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.comparators.MenuItemComparator;

import java.util.ArrayList;

import models.MenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private ArrayList<MenuItem> items;
    private final RecyclerViewInterface recyclerViewInterface;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class MenuHolder extends RecyclerView.ViewHolder {
        private final TextView day_view;
        private final TextView name_view;

        public MenuHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            day_view = view.findViewById(R.id.menu_day);
            name_view = view.findViewById(R.id.menu_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                    String day = day_view.getText().toString();
                    String food = name_view.getText().toString();
                    System.out.println(day + " : " + food);
                }
            });
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
    public MenuAdapter(ArrayList<MenuItem> dataSet , RecyclerViewInterface recyclerViewInterface) {
        this.items = dataSet;
        this.recyclerViewInterface = recyclerViewInterface;
        MenuItemComparator menuItemComparator = new MenuItemComparator();
        items.sort(menuItemComparator);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MenuAdapter.MenuHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_row, viewGroup, false);
        return new MenuAdapter.MenuHolder(view,recyclerViewInterface);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MenuAdapter.MenuHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        MenuItem item = items.get(position);
        viewHolder.getName_view().setText(String.valueOf(item.getName()));
        viewHolder.getDay_view().setText(MenuItem.dayStringHashMap.get(item.getDay()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}