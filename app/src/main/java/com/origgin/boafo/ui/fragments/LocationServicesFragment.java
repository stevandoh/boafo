package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableRow;

import com.origgin.boafo.R;
import com.origgin.boafo.models.BoafoMenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class LocationServicesFragment extends Fragment {
    //    ImageButton buttonAtm, buttonHospital, buttonPolice, buttonFireservice;
    //    @BindView(R.id.card_recycler_view)
//    RecyclerView rv;
    Unbinder unbinder;

    @BindView(R.id.imageButtonAtm)
    ImageButton imageButtonAtm;
    @BindView(R.id.imageButtonHospital)
    ImageButton imageButtonHospital;
    @BindView(R.id.tableRow1)
    TableRow tableRow1;
    @BindView(R.id.imageButtonFire)
    ImageButton imageButtonFire;
    @BindView(R.id.imageButtonPolice)
    ImageButton imageButtonPolice;

    private List<BoafoMenuItem> boafoMenuItems;

    public LocationServicesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_services, container, false);

//        buttonAtm = (ImageButton) view.findViewById(R.id.imageButtonAtm);
//        buttonHospital = (ImageButton) view.findViewById(R.id.imageButtonHospital);
//        buttonPolice = (ImageButton) view.findViewById(R.id.imageButtonPolice);
//        buttonFireservice = (ImageButton) view.findViewById(R.id.imageButtonFire);
//
//        buttonAtm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Search for restaurants nearby
//                Uri hostpitalIntentUri = Uri.parse("geo:0,0?q=atm");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//
//                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//
//                    startActivity(mapIntent);
//                }
//            }
//        });
//
//        buttonHospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Search for restaurants nearby
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospital");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            }
//        });
//
//        buttonPolice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Search for restaurants nearby
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=police");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            }
//        });
//
//        buttonFireservice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Search for restaurants nearby
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=fire_station");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            }
//        });
        unbinder = ButterKnife.bind(this, view);
//        initializeData();
//        CategoryAdapter mainMenuAdapter = new CategoryAdapter(boafoMenuItems);
////        rv.setHasFixedSize(true);
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        rv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
//        rv.addItemDecoration(new VerticalDividerItemDecoration.Builder(getActivity()).build());
//        rv.setAdapter(mainMenuAdapter);

//        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                GenUtils.getToastMessage(getContext(), String.valueOf(position));
//
////                if (position ==0){
////                    startActivity(new Intent(getActivity(), CategoryListACT.class));
////                }else if (position ==1){
////                    startActivity(new Intent(getActivity(), LocationServicesACT.class));
////                }
//            }
//        });

//        GridView gridview = (GridView) view.findViewById(R.id.gri);
//        gridview.setAdapter(new ImageAdapter(getContext()));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(getActivity(), "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imageButtonAtm, R.id.imageButtonHospital, R.id.imageButtonFire, R.id.imageButtonPolice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButtonAtm:
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=atm");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {

                    startActivity(mapIntent);
                }
                break;
            case R.id.imageButtonHospital:
                Uri hospitalIntentUri = Uri.parse("geo:0,0?q=hospital");
                Intent hospitalIntent = new Intent(Intent.ACTION_VIEW, hospitalIntentUri);
                hospitalIntent.setPackage("com.google.android.apps.maps");
                startActivity(hospitalIntent);
                break;
            case R.id.imageButtonFire:
                Uri fireIntentUri = Uri.parse("geo:0,0?q=police");
                Intent fireIntent = new Intent(Intent.ACTION_VIEW, fireIntentUri);
                fireIntent.setPackage("com.google.android.apps.maps");
                startActivity(fireIntent);

                break;
            case R.id.imageButtonPolice:
                Uri policeIntentUri = Uri.parse("geo:0,0?q=police");
                Intent policeIntent = new Intent(Intent.ACTION_VIEW, policeIntentUri);
                policeIntent.setPackage("com.google.android.apps.maps");
                startActivity(policeIntent);

                break;
        }
    }

//    private void initializeData() {
//        boafoMenuItems = new ArrayList<>();
//        boafoMenuItems.add(new BoafoMenuItem("ATM", R.drawable.atm));
//        boafoMenuItems.add(new BoafoMenuItem("HOSPITAL", R.drawable.hospital));
//        boafoMenuItems.add(new BoafoMenuItem("POLICE", R.drawable.policeman));
//        boafoMenuItems.add(new BoafoMenuItem("FIRE SERVICE", R.drawable.fireman));
//    }
}
