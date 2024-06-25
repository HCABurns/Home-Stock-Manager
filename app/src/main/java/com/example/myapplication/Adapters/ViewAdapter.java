package com.example.myapplication.Adapters;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.comparators.ItemComparator;

import java.util.ArrayList;

import com.example.myapplication.models.Item;


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
        TextView itemCountTextView = viewHolder.itemView.findViewById(R.id.menu_name);
        itemCountTextView.setText(String.valueOf(item.getCount()));

        TextView itemNameTextView = viewHolder.itemView.findViewById(R.id.item_name);
        itemNameTextView.setText(item.getName());

        Switch required_switch =  viewHolder.itemView.findViewById(R.id.required_switch);

        if (item.getRequired().equals(true)){
            Drawable layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                    R.drawable.rounded_layout_red);
            viewHolder.itemView.findViewById(R.id.item_container).setBackground(layout);
            //Set switch to checked if the item is required.
            required_switch.setChecked(true);
            //viewHolder.getTextView().setBackgroundColor(Color.RED);
        }

        /*
         * Adds an onchecked listener item to the required switch. Changes colour of the row based
         * on if the item has been checked or not.
         */
        required_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                Drawable layout;
                if (isChecked){
                    layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                            R.drawable.rounded_layout_red);
                    //todo: Update DB here
                    item.setRequired(true);
                }
                else{
                    layout = ContextCompat.getDrawable(viewHolder.itemView.getContext(),
                            R.drawable.rounded_layout);
                    //todo: Update DB here
                    item.setRequired(false);
                }
                viewHolder.itemView.findViewById(R.id.item_container).setBackground(layout);
            }});

        AppCompatButton add_button = viewHolder.itemView.findViewById(R.id.count_plus);
        AppCompatButton remove_button = viewHolder.itemView.findViewById(R.id.count_remove);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setCount(item.getCount()+1);
                itemCountTextView.setText(String.valueOf(item.getCount()));
                //todo: Update DB here
            }
        });
        remove_button.setOnClickListener(new View.OnClickListener() {
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

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }


}