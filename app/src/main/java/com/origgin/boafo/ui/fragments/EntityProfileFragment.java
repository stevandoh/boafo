package com.origgin.boafo.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.origgin.boafo.R;
import com.origgin.boafo.models.Entity;
import com.origgin.boafo.models.FavoriteItem;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class EntityProfileFragment extends Fragment {
    Realm realm;
    Entity entity;
    Bundle b;
    TextView txtName, txtProfession, txtPhone, txtCompany, txtLocation, txtSpecialization, txtExperience;
    String[] phones;
    @BindView(R.id.ivContactItem1)
    ImageView ivContactItem1;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewCompany)
    TextView textViewCompany;
    @BindView(R.id.ivContactItem2)
    ImageView ivContactItem2;
    @BindView(R.id.textViewPhone)
    TextView textViewPhone;
    @BindView(R.id.ivContactItem3)
    ImageView ivContactItem3;
    @BindView(R.id.textViewProfesssion)
    TextView textViewProfesssion;
    @BindView(R.id.textViewExperience)
    TextView textViewExperience;
    @BindView(R.id.ivContactItem4)
    ImageView ivContactItem4;
    @BindView(R.id.tvSpecialization)
    TextView tvSpecialization;
    @BindView(R.id.ivContactItem5)
    ImageView ivContactItem5;
    @BindView(R.id.textViewLocation)
    TextView textViewLocation;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Unbinder unbinder;

    MaterialFavoriteButton btnFavorite;

    public EntityProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entity_profile, container, false);

        realm = Realm.getDefaultInstance();
        Intent intentCategory = getActivity().getIntent();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);


        b = intentCategory.getExtras();
        txtName = (TextView) view.findViewById(R.id.textViewName);
        txtProfession = (TextView) view.findViewById(R.id.textViewProfesssion);
        txtLocation = (TextView) view.findViewById(R.id.textViewLocation);

        txtPhone = (TextView) view.findViewById(R.id.textViewPhone);
        txtCompany = (TextView) view.findViewById(R.id.textViewCompany);
        txtSpecialization = (TextView) view.findViewById(R.id.tvSpecialization);
        txtExperience = (TextView) view.findViewById(R.id.textViewExperience);
//        GenUtils.getToastMessage(getContext(), b.getString(getString(R.string.entity_id_key)));
        entity = realm.where(Entity.class).equalTo("id", b.getString(getString(R.string.entity_id_key))).findFirst();
        txtName.setText(entity.getName());
        txtPhone.setText(entity.getPhone());
        phones = entity.getPhone().split("/");

//        new Material

        txtProfession.setText(entity.getOccupation());
        txtLocation.setText(entity.getLocation());
        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entity.getLocation() != null) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + entity.getLocation());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {

                        startActivity(mapIntent);
                    }
                }

            }
        });
        txtSpecialization.setText(entity.getSpecialization());
        txtCompany.setText(entity.getCompany());
        txtExperience.setText(entity.getExperience() + " of experience");
        getActivity().setTitle(entity.getName());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                new MaterialDialog.Builder(getContext())
                        .title(R.string.PhoneTitle)
                        .items(phones)
//                        .itemsCallback(new MaterialDialog.ListCallback() {
//                            @Override
//                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//
//                            }
//                        })
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                callItem(which);
                                return true;
                            }
                        })
                        .positiveText(R.string.call)

                        .show();

            }
        });
        btnFavorite = (MaterialFavoriteButton) view.findViewById(R.id.btn_favorite);
        FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id",b.getString(getString(R.string.entity_id_key))).findFirst();
        if(favorit != null){
            btnFavorite.setFavorite(favorit.isFavorite());
//            personViewHolder.favorite.setFavorite(isFavorite(getItem(position)), favorit.isFavorite());
        }

        btnFavorite.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView,  boolean favorite) {

                        if (favorite){
                            final boolean fav = favorite;

                            final String id = UUID.randomUUID().toString();

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    FavoriteItem favoriteItem = realm.createObject(FavoriteItem.class, id);
                                    favoriteItem.setEntity(entity);
                                    favoriteItem.setFavorite(fav);

                                }
                            });

                            final FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
                            if (favorit != null){
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        favorit.setFavorite(true);
                                    }
                                });



                            }


                        }else if (!favorite ){
                            final FavoriteItem favorit = realm.where(FavoriteItem.class).equalTo("entity.id", entity.getId()).findFirst();
                            if (favorit != null){
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        favorit.setFavorite(false);
                                    }
                                });



                            }

                        }




                    }
                });



        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    private void callItem(int position) {
        String number = "";
        if (phones[position].length() == 10) {
            number = phones[position];

        }
        Uri call = Uri.parse("tel:" + number);
        Intent surf = new Intent(Intent.ACTION_CALL, call);
        startActivity(surf);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivContactItem2, R.id.ivContactItem5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivContactItem2:
                new MaterialDialog.Builder(getContext())
                        .title(R.string.PhoneTitle)
                        .items(phones)
//                        .itemsCallback(new MaterialDialog.ListCallback() {
//                            @Override
//                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
//
//                            }
//                        })
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                callItem(which);
                                return true;
                            }
                        })
                        .positiveText(R.string.call)

                        .show();
                break;
            case R.id.ivContactItem5:
                if (entity.getLocation() != null) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + entity.getLocation());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {

                        startActivity(mapIntent);
                    }
                }
                break;
        }
    }

//    String[] strings = location.split(Constants.COMMA);
//    lat = Double.parseDouble(strings[0]);
//    lon = Double.parseDouble(strings[1]);
}

