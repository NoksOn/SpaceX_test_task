package com.example.spacex_test_task;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.spacex_test_task.models.RetrofitModel;
import com.example.spacex_test_task.databinding.FragmentLaunchDetailsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;

/*
Fragment class. here I used a DataBinding for filling TextView`s and initialize GoogleMaps
 */

public class Launch_Details extends Fragment {

    private static final String ARG_PARAM1 = "model";
    private RetrofitModel launchInfo;
    private MapCallback mapCallback;

    public static Launch_Details newInstance(RetrofitModel retrofitModel) {
        Launch_Details fragment = new Launch_Details();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,(Serializable)retrofitModel);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_launch__details,container,false);

        if(getArguments()!=null){
            launchInfo = (RetrofitModel) getArguments().getSerializable(ARG_PARAM1);
        }

        this.Binding();
        this.initMap();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void Binding(){
        FragmentLaunchDetailsBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_launch__details);
        binding.setInfo(launchInfo);
        binding.setLinks(launchInfo.getLinks());

        StringBuilder ships = new StringBuilder();
        if(launchInfo.getShips().size()!=0) {
            ships.append("Ships: ");
            for (String s : launchInfo.getShips()) {
                ships.append(s+" ");
            }
            ships.setLength(ships.length()-1);
        }
        else {
            ships.append("Without Ships");
        }
        binding.setShip(ships.toString());
    }


    private void initMap(){
       SupportMapFragment supportMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.MapFragment);
       mapCallback = new MapCallback(getContext(),launchInfo);
       supportMapFragment.getMapAsync(mapCallback);

    }

}
