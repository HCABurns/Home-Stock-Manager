package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import models.MenuItem;

public class SingleMenuItemFragment extends AppCompatActivity {

    private MenuItem item;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("1111111111111111111111");
        super.onCreate(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("IN HERE");
        View view=  inflater.inflate(R.layout.activity_single_menu_item, container, false);

        String name = getIntent().getStringExtra("Name");

        TextView nameTextView = findViewById(R.id.single_item_name);
        System.out.println(name + " " + nameTextView);
        nameTextView.setText(name);

        System.out.println("HELLO");

        return view;
    }

}
/*
        String name = getIntent().getStringExtra("Name");

        TextView nameTextView = findViewById(R.id.single_item_name);
        System.out.println(name + " " + nameTextView);
        nameTextView.setText(name);

        System.out.println("HELLO");
 */