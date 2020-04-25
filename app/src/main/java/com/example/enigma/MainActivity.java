package com.example.enigma;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelperProduct mydb;
    Button btnDelivery;
    RecyclerView recyclerView;
    ProductAdapterhome adapterhome;


    List<Product> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mydb = new DatabaseHelperProduct(this);


        //adding some items to our list
        /*productList.add(new Product(5, "Samitha", "1asasassa" , 7.0, 4.3, R.drawable.p1));
        productList.add(new Product(2, "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)", "14 inch, Gray, 1.659 kg", 4.3, 60000, R.drawable.p2_img));
        productList.add(new Product(3, "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)", "13.3 inch, Silver, 1.35 kg", 4.3, 60000, R.drawable.p4_img));
        productList.add(new Product(4, "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)", "13.3 inch, Silver, 1.35 kg", 4.3, 60000, R.drawable.p5_img));
        productList.add(new Product(5, "Samitha", "1asasassa" , 7.0, 4.3, R.drawable.p1));
        productList.add(new Product(2, "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)", "14 inch, Gray, 1.659 kg", 4.3, 60000, R.drawable.p2_img));
        productList.add(new Product(3, "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)", "13.3 inch, Silver, 1.35 kg", 4.3, 60000, R.drawable.p4_img));
        productList.add(new Product(4, "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)", "13.3 inch, Silver, 1.35 kg", 4.3, 60000, R.drawable.p5_img));*/

        Cursor res = mydb.getAllData();

        if(res.getColumnCount() == 0){
            return;
        }

        while (res.moveToNext()){
            int id = Integer.parseInt(res.getString(0));
            String name = res.getString(1);
            String desc = res.getString(2);
            double rate = Double.parseDouble(res.getString(3));
            double price = Integer.parseInt(res.getString(4));

            productList.add(new Product(id,name,desc,rate, price,R.drawable.p2_img));

        }

//        btnDelivery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,(MainActivity)DeliveryInsert.class));
//
//            }
//        });



        adapterhome = new ProductAdapterhome(this, productList);
        recyclerView.setAdapter(adapterhome);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragmentHome = null;
        Fragment fragmentDelivery = null;
        Fragment fragmentInsertDel = null;

        if (id == R.id.nav_home) {

            Intent home = new Intent(this,MainActivity.class);
            startActivity(home);


        } else if (id == R.id.nav_account) {



        } else if (id == R.id.nav_purchases) {

        } else if (id == R.id.nav_delivery){
            fragmentDelivery = new ViewDelivery();

        } else if (id == R.id.nav_admin) {

            fragmentHome = new AdminLogin();

        } else if (id == R.id.nav_share) {
            fragmentInsertDel = new DeliveryInsert();
        }

        if(fragmentHome != null){
            FragmentManager fmhome = getSupportFragmentManager();
            FragmentTransaction fthome = fmhome.beginTransaction();
            fthome.replace(R.id.screen_area,fragmentHome);
            fthome.commit();

        }

        if(fragmentDelivery != null){
            FragmentManager fmDelivery = getSupportFragmentManager();
            FragmentTransaction fthome = fmDelivery.beginTransaction();
            fthome.replace(R.id.screen_area,fragmentDelivery);
            fthome.commit();
        }

        if(fragmentInsertDel != null){
            FragmentManager fmInsert = getSupportFragmentManager();
            FragmentTransaction fthome = fmInsert.beginTransaction();
            fthome.replace(R.id.screen_area,fragmentInsertDel);
            fthome.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
