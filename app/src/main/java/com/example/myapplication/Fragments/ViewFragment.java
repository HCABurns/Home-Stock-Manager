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

import com.example.myapplication.database.dbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private final ViewAdapter adapter = new ViewAdapter();

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * This function will create a view for the viewing of items.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Returns view including all required items.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("OPENING THE VIEW PAGE -----------------------------");
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        //Set recycler view and run the update to add items.
        recyclerView = view.findViewById(R.id.view_all);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        updateRecycler();
        return view;
    }


    /**
     *This function will update the recycler view. It will get the new updated list from the adapter
     * (the adapter calls get_items from the dbHelper class.) then it will set the new updated
     * adapter to the view.
     */
    public void updateRecycler(){
        adapter.update_items();
        recyclerView.setAdapter(adapter);
    }


    /**
     * This function runs when the view page is resumed. This occurs when the page is reloaded
     * either by selecting it by the navigation menu or once the add item activity ends and the user
     * was on the view page when they selected it.
     */
    @Override
    public void onResume() {
        System.out.println("Resumed View Page ------------------------------");
        super.onResume();
        //This will update the recycler view.
        updateRecycler();
    }
}