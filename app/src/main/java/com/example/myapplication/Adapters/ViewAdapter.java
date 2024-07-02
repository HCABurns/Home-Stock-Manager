package com.example.myapplication.Adapters;


import static androidx.core.content.ContextCompat.startActivity;
import static com.example.myapplication.Activities.MainActivity.dbHelper;

import android.app.Dialog;
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

import com.example.myapplication.Activities.MainActivity;
import com.example.myapplication.Activities.SingleItemActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

import com.example.myapplication.models.Item;

import org.w3c.dom.Text;


public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private ArrayList<Item> items;
    /**
     * Provide a reference to the views, switches and Item used for an item row.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SwitchCompat required_switch;
        private final TextView itemCountTextView;
        private final TextView itemNameTextView;
        private Item item;

        public ViewHolder(View view) {
            super(view);
            // Define parameters
            required_switch = view.findViewById(R.id.required_switch);
            AppCompatButton add_button = view.findViewById(R.id.count_plus);
            AppCompatButton reduce_button = view.findViewById(R.id.count_remove);
            itemCountTextView = view.findViewById(R.id.menu_name);
            itemNameTextView = view.findViewById(R.id.item_name);

            // Define click listeners for the ViewHolder's View
            itemNameTextView.setOnClickListener(view1 -> {
                System.out.println("ONCLICK HAS OCCURRED");
                Intent intent = new Intent(view1.getContext(), SingleItemActivity.class);
                intent.putExtra("name", itemNameTextView.getText());
                intent.putExtra("quantity", itemCountTextView.getText());
                startActivity(view1.getContext(), intent, null);
            });

            itemNameTextView.setOnLongClickListener(view1 -> {
                Dialog dialog = new Dialog(this.itemCountTextView.getContext());
                dialog.setContentView(R.layout.delete_diaglog);

                TextView textView = dialog.findViewById(R.id.dialog_help);
                String text1 = "Are you sure you want to delete ";
                String item = (String) itemNameTextView.getText();
                String text2 = " from the list?";
                String text;
                if (item.length() > 50){
                    text = String.format("%s%s...%s", text1, item.substring(0, 60), text2);
                }
                else{
                    text = String.format("%s%s%s", text1, item, text2);
                }
                textView.setText(text);

                AppCompatButton yes_button = dialog.findViewById(R.id.yes_button);
                yes_button.setOnClickListener(view_1 ->{
                    int pos = getAdapterPosition();
                    dbHelper.removeItem(pos);

                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos,items.size());

                    dialog.cancel();
                });

                AppCompatButton no_button = dialog.findViewById(R.id.no_button);
                no_button.setOnClickListener(view_1 -> dialog.cancel());

                dialog.show();
                return true;
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
                dbHelper.editItem(item.getName(),item.getName(),item.getCount()+1);
                itemCountTextView.setText(String.valueOf(item.getCount()));
            });

            reduce_button.setOnClickListener(reduce_view -> {
                int count = item.getCount();
                if (count != 0) {
                    dbHelper.editItem(item.getName(),item.getName(),item.getCount()-1);
                    itemCountTextView.setText(String.valueOf(item.getCount()));
                }
                else{
                    Toast.makeText(reduce_view.getContext(), "Can't have less than 0 stock!",
                            Toast.LENGTH_SHORT).show();
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