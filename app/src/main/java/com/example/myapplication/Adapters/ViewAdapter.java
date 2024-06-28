package com.example.myapplication.Adapters;


import static androidx.core.content.ContextCompat.startActivity;
import static com.example.myapplication.Activities.MainActivity.dbHelper;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activities.SingleItemActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

import com.example.myapplication.models.Item;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private ArrayList<Item> items;
    /**
     * Provide a reference to the views, switches and Item used for an item row.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final SwitchCompat required_switch;
        private final TextView itemCountTextView;
        private final TextView itemNameTextView;
        private final LinearLayout itemContainer;
        private Item item;

        public ViewHolder(View view) {
            super(view);
            // Define parameters
            required_switch = view.findViewById(R.id.required_switch);
            AppCompatButton add_button = view.findViewById(R.id.count_plus);
            AppCompatButton reduce_button = view.findViewById(R.id.count_remove);
            itemCountTextView = view.findViewById(R.id.menu_name);
            itemNameTextView = view.findViewById(R.id.item_name);
            itemContainer = view.findViewById(R.id.item_container);

            // Define click listeners for the ViewHolder's View
            itemNameTextView.setOnClickListener(item_view -> {
                System.out.println("ONCLICK HAS OCCURRED");
                Intent intent = new Intent(view.getContext(), SingleItemActivity.class);
                intent.putExtra("name",itemNameTextView.getText());
                intent.putExtra("quantity",itemCountTextView.getText());
                startActivity(view.getContext(), intent,null);
            });


            required_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Drawable layout1;
                if (isChecked){
                    layout1 = ContextCompat.getDrawable(itemView.getContext(),
                            R.drawable.rounded_layout_red);
                    //todo: Update DB here
                    item.setRequired(true);
                }
                else{
                    layout1 = ContextCompat.getDrawable(itemView.getContext(),
                            R.drawable.rounded_layout);
                    //todo: Update DB here
                    item.setRequired(false);
                }
                itemView.findViewById(R.id.item_container).setBackground(layout1);
            });

            add_button.setOnClickListener(add_view -> {
                item.setCount(item.getCount()+1);
                itemCountTextView.setText(String.valueOf(item.getCount()));
                //todo: Update DB here
            });

            reduce_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = item.getCount();
                    if (count != 0) {
                        item.setCount(count - 1);
                        itemCountTextView.setText(String.valueOf(item.getCount()));
                    }
                    else{
                        Toast.makeText(view.getContext(), "Can't have less than 0 stock!",
                                Toast.LENGTH_SHORT).show();
                    }
                    //todo: Update DB here
                }
            });

        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * by RecyclerView.
     */
    public ViewAdapter() {
        this.items = dbHelper.getItems();
    }

    // Create new view (invoked by the layout manager)
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

        // Get element from the dataset at a position and replace the
        // contents of the view with that element.
        Item item = items.get(position);
        viewHolder.item = item;
        viewHolder.itemCountTextView.setText(String.valueOf(item.getCount()));
        viewHolder.itemNameTextView.setText(item.getName());

        //This portion of code will set the background of the item to the correct one.
        Drawable layout;
        if (item.getRequired().equals(true)){
            layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                    R.drawable.rounded_layout_red);
            //Set switch to checked if the item is required.
            viewHolder.required_switch.setChecked(true);
        }
        else{
            layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                    R.drawable.rounded_layout);
            viewHolder.required_switch.setChecked(false);
        }
        viewHolder.itemView.findViewById(R.id.item_container).setBackground(layout);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * This function will update the items list to the one stored by the dbHelper class.
     */
    public void update_items(){
        this.items = dbHelper.getItems();
    }


}