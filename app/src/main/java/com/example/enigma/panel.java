package com.example.enigma;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class panel extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_panel, container, false);

        Button btnadminpanel = (Button)view.findViewById(R.id.push_button_product);
        Button btnDeliverySection = (Button) view.findViewById(R.id.push_button_delivery);
        Button btnEmployeeSection = (Button) view.findViewById(R.id.push_button_account);

        btnadminpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.screen_area,new productadd_update());
                ft.commit();

            }
        });

        btnDeliverySection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.replace(R.id.screen_area,new ViewDeliveryAdmin());
                ft.replace(R.id.screen_area,new DeliveryViewAdmin());
                ft.commit();
            }
        });

        btnEmployeeSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.screen_area,new RegisterEmployee());
                ft.commit();
            }
        });

        return view;
    }


}
