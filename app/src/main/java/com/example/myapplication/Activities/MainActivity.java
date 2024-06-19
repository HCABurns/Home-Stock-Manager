package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Fragments.AddFragment;
import com.example.myapplication.Fragments.HomeFragment;
import com.example.myapplication.Fragments.MenuFragment;
import com.example.myapplication.R;
import com.example.myapplication.Fragments.SearchFragment;
import com.example.myapplication.Fragments.ViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.myapplication.database.dbHelper;

public class MainActivity extends AppCompatActivity {

    public static com.example.myapplication.database.dbHelper dbHelper = new dbHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the fragments that will be used to change screens
        Fragment homeFragment = new HomeFragment();
        //Set fragments to be used
        Fragment viewFragment = new ViewFragment();
        Fragment addFragment = new AddFragment();
        Fragment searchFragment = new SearchFragment();
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
                //List all the items
                //RecyclerView recyclerView = findViewById(R.id.view_all);
                //ArrayList<Item> items = dbHelper.getItems();
                //ViewAdapter adapter = new ViewAdapter(items);
                //recyclerView.setAdapter(adapter);
                //recyclerView.setLayoutManager(new LinearLayoutManager(this));
                return true;
            }
            else if (item.getItemId() == R.id.Add){

                //todo: Start and activity - Get name and create object (0 stock and required
                // by default)   --- No fragment as it seems unnecessary.

                //todo - Create skeletons: Activity class, activity xml, dbHelper add new item
                // checker to see if name is already in database?

                /*getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, addFragment)
                        .commit();
                 */
                Intent intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                return false;
            }
            else if (item.getItemId() == R.id.search_nav_bar){

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, searchFragment)
                        .commit();
                return true;
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

        //FrameLayout frameLayout = findViewById(R.id.frame);



    }

}

/*Switch onOffSwitch;
        onOffSwitch = (Switch) findViewById(R.id.switch1);
        onOffSwitch.setBackgroundColor(Color.BLACK);

        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onOffSwitch.setBackgroundColor(Color.GREEN);
            }
        });
        */