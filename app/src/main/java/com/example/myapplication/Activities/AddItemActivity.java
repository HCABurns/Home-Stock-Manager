package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import com.example.myapplication.R;
import com.example.myapplication.comparators.ItemComparator;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        AppCompatButton add_button = findViewById(R.id.item_add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("--------------------------------------------------------");

                EditText nameEditText = findViewById(R.id.item_name);
                String name = String.valueOf(nameEditText.getText());

                MainActivity.dbHelper.addItem(name);
                MainActivity.dbHelper.itemSort();
                //todo: Update DB here

                //End activity
                finish();
                //Sort the array to keep the output correct.
                MainActivity.dbHelper.items.sort(new ItemComparator());

            }
        });


    }
}