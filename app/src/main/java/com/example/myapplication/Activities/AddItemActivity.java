package com.example.myapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.myapplication.OnSwipeTouchListener;
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

                EditText nameEditText = findViewById(R.id.item_name);
                String name = String.valueOf(nameEditText.getText());

                MainActivity.dbHelper.addItem(name);
                MainActivity.dbHelper.itemSort();
                //todo: Update DB here


                //Sort the array to keep the output correct.
                //MainActivity.dbHelper.items.sort(new ItemComparator());
                Toast.makeText(getBaseContext(),"Item Successful added!",Toast.LENGTH_LONG).show();

                //End activity
                finish();
            }
        });

        ConstraintLayout layout = findViewById(R.id.add_item_container);
        layout.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });

    }
}