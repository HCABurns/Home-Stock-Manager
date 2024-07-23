package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Fragments.HomeFragment;
import com.example.myapplication.Fragments.MenuFragment;
import com.example.myapplication.R;
import com.example.myapplication.Fragments.SearchFragment;
import com.example.myapplication.Fragments.ViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.myapplication.database.dbHelper;

public class MainActivity extends AppCompatActivity {

    public static dbHelper dbHelper;
    //Create the fragments that will be used to change screens


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dbHelper = new dbHelper(getBaseContext());
        dbHelper = new dbHelper();

        //Create the fragments that will be used to change screens
        Fragment homeFragment = new HomeFragment();
        Fragment viewFragment = new ViewFragment();
        Fragment menuFragment = new MenuFragment(getBaseContext());

        //Create a fragment manager and swap the empty frame to the home fragment frame.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,homeFragment).commit();

        //Create an item to get the bottom navigation. Set the active item to an invisible item
        //for ascetics.
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.invisible);

        //Create an onclick to replace the frame with the correct required frame.
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.view_all_nav_bar) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, viewFragment)
                        .commit();
                return true;
            }
            else if (item.getItemId() == R.id.Add){
                Intent intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                return false;
            }
            else if (item.getItemId() == R.id.menu){

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, menuFragment)
                        .commit();
                return true;
            }
            else{
                return false;
            }
        });
    }
}