package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.OnSwipeTouchListener;
import com.example.myapplication.R;

public class SingleMenuItemActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("1111111111111111111111");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_menu_item);
        // TODO: Use the ViewModel

        String name = getIntent().getStringExtra("Name");
        EditText nameEditText = findViewById(R.id.edit_menu_name);
        nameEditText.setText(name);

        String day = getIntent().getStringExtra("Day");
        System.out.println("DAY GOT FROM THE INTENT IS: " + day);
        TextView title = findViewById(R.id.menu_title);
        title.setText(day);


        AppCompatButton edit_button = findViewById(R.id.menu_update_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("--------------------------------------------------------");
                System.out.println("NAME IS : " + nameEditText.getText());
                System.out.println("DAY IS: " + day);
                MainActivity.dbHelper.editMenuItem(day,String.valueOf(nameEditText.getText()));
                //Update DB here

                //End activity
                finish();
            }
        });

        ConstraintLayout layout = findViewById(R.id.singe_menu_item_container);
        layout.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeRight() {
                finish();
            }
        });


    }
}
/*
        String name = getIntent().getStringExtra("Name");

        TextView nameTextView = findViewById(R.id.single_item_name);
        System.out.println(name + " " + nameTextView);
        nameTextView.setText(name);

        System.out.println("HELLO");
 */