package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.origgin.boafo.R;
import com.origgin.boafo.models.BoafoMenuItem;
import com.origgin.boafo.ui.activities.CategoryListACT;
import com.origgin.boafo.ui.activities.LocationServicesACT;
import com.origgin.boafo.ui.adapter.MainMenuAdapter;
import com.origgin.boafo.utils.GenUtils;
import com.origgin.boafo.utils.ItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment {

    Button buttonService, buttonLocation;
    private List<BoafoMenuItem> boafoMenuItems;


    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
//        buttonLocation = (Button)view.findViewById(R.id.buttonLocation);
//        buttonService = (Button) view.findViewById(R.id.buttonService);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        initializeData();
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(boafoMenuItems);
//        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
        rv.addItemDecoration(new VerticalDividerItemDecoration.Builder(getActivity()).build());
        rv.setAdapter(mainMenuAdapter);

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
             

                if (position ==0){
                    startActivity(new Intent(getActivity(), CategoryListACT.class));
                }else if (position ==1){
                    startActivity(new Intent(getActivity(), LocationServicesACT.class));
                }
            }
        });

//        buttonService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),CategoryListACT.class));
//            }
//        });
//
//        buttonLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),LocationServicesACT.class));
//            }
//        });

        return view;
    }

    private void initializeData() {
        boafoMenuItems = new ArrayList<>();
        boafoMenuItems.add(new BoafoMenuItem("Services", R.drawable.services));
        boafoMenuItems.add(new BoafoMenuItem("Location Services", R.drawable.locations));
    }
}
