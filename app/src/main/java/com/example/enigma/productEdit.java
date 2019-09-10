package com.example.enigma;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class productEdit extends Fragment {

    View v;
    RecyclerView recyclerView;
    ProductAdapterhome adapterhome;

    List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_product_edit, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        ProductAdapterhome  recycleAdapter = new ProductAdapterhome(getContext(),productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleAdapter);

        return v;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        productList = new ArrayList<>();
        productList.add(new Product(1,"product1","qw",2.1,200.0,R.drawable.p7));
        productList.add(new Product(1,"product2","qw",3.1,700.0,R.drawable.p1));
        productList.add(new Product(1,"product3","qw",4.1,800.0,R.drawable.p2_img));
        productList.add(new Product(1,"product4","qw",5.1,400.0,R.drawable.p7));
        productList.add(new Product(1,"product5","qw",6.1,800.0,R.drawable.p5_img));
        productList.add(new Product(1,"product6","qw",9.1,200.0,R.drawable.p1));
        productList.add(new Product(1,"product7","qw",5.1,100.0,R.drawable.p7));




    }


}
