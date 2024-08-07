package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.R;

public class SingleItemActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        // Get required text views.
        EditText itemNameText = findViewById(R.id.item_edit_name);
        EditText itemQuantityText = findViewById(R.id.item_quantity);

        // Get required information about the item.
        String name = getIntent().getStringExtra("name");
        String quantity = getIntent().getStringExtra("quantity");

        // Set the views to the correct values.
        itemNameText.setText(name);
        itemQuantityText.setText(quantity);

        // Set onClickUpdate
        AppCompatButton editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(view -> {
            MainActivity.dbHelper.editItem(name,
                    itemNameText.getText().toString(),
                    Integer.parseInt(itemQuantityText.getText().toString()));
            finish();
        });
    }

}
