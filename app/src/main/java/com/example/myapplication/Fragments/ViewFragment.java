package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Activities.MainActivity;
import com.example.myapplication.Adapters.ViewAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

import com.example.myapplication.database.dbHelper;
import com.example.myapplication.models.Item;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private dbHelper dbHelper = MainActivity.dbHelper;

    // TODO: Rename and change types of parameters
    private String name;
    private String amount;

    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewFragment newInstance(String param1, String param2) {
        ViewFragment fragment = new ViewFragment();
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
            name = getArguments().getString(ARG_PARAM1);
            amount = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //Toast.makeText(getActivity(), "Hello this is a test", Toast.LENGTH_SHORT).show();
        System.out.println("OPENING THE VIEW PAGE -----------------------------");
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.view_all);
        ArrayList<Item> items = dbHelper.getItems();
        ViewAdapter adapter = new ViewAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        return view;
    }
}