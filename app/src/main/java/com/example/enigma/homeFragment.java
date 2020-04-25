package com.example.enigma;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class homeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        //Button btnBuy = (Button) view.findViewById(R.id.btnBuy);
        Button btnDelivery = (Button) view.findViewById(R.id.btnDelivery);

        //Button btnDel = view.findViewById(R.id.btnDelivery);

        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.screen_area,new DeliveryInsert());
                ft.commit();
                Toast.makeText(getActivity(),"Button Delivery",Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }


}
