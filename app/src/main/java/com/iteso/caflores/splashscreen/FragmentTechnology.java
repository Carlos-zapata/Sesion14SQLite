package com.iteso.caflores.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.caflores.splashscreen.database.DatabaseHandler;
import com.iteso.caflores.splashscreen.database.ItemProductControl;

import java.util.ArrayList;
import java.util.Iterator;

public class FragmentTechnology extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ItemProduct> myDataSet;

    public FragmentTechnology() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_technology, container, false);
            ItemProductControl itemProductControl = new ItemProductControl();
            myDataSet = new ArrayList<>();
            myDataSet = itemProductControl.getProductsWhere(null, DatabaseHandler.KEY_PRODUCT_ID + " ASC",
                    DatabaseHandler.getInstance(getActivity()));
            mAdapter = new AdapterProduct(getActivity(), myDataSet);
            recyclerView.setAdapter(mAdapter);
            itemProductControl = null;
            return view;
    }

    public void notifyDataSetChanged(ItemProduct itemProduct){
        myDataSet.add(itemProduct);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}

