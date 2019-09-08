package com.example.enigma;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DisplayHome extends Fragment {

    View v;
    RecyclerView recyclerView;
    ProductAdapterhome adapterhome;

    List<Product> productList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_display_home, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        ProductAdapterhome  recycleAdapter = new ProductAdapterhome(getContext(),productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleAdapter);

        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        productList = new ArrayList<>();
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));




    }


}
