package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Adapters.MenuAdapter;
import com.example.myapplication.Adapters.ViewAdapter;
import com.example.myapplication.Interfaces.RecyclerViewInterface;

import java.util.ArrayList;

import database.dbHelper;
import models.Item;
import models.MenuItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static Context context;

    private ArrayList<MenuItem> items;

    private RecyclerView recyclerView;

    private dbHelper dbHelper = new dbHelper();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment(Context context) {
        MenuFragment.context = context;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment(MenuFragment.context);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        System.out.println("OPENING THE MENU PAGE -----------------------------");
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.menu_recycler);
        items = MainActivity.dbHelper.getMenuItems();
        MenuAdapter adapter = new MenuAdapter(items,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        return view;
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getContext(), SingleMenuItemFragment.class);//MenuItem.class);

        intent.putExtra("Name" , items.get(position).getName());
        System.out.println("ITEM TO PUT INTO INTENT IS: " + items.get(position).getDay());
        intent.putExtra("Day" , MenuItem.dayStringHashMap.get(items.get(position).getDay()));

        startActivity(intent);

        //System.out.println(context);

        //Toast.makeText(context, "Amount can not be grater than invoice", Toast.LENGTH_SHORT).show();

        // Todo: Create activity / Fragment to display the information and allow for editing.

    }

    @Override
    public void onResume() {
        super.onResume();
        //This will update the recycler view.
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}