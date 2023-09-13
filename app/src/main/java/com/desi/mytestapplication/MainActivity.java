package com.desi.mytestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.desi.mytestapplication.dataModel.CurrencyList;
import com.google.android.material.navigation.NavigationView;
import com.desi.mytestapplication.fragments.HomeFragment;
import com.desi.mytestapplication.fragments.ChartFragment;
import com.desi.mytestapplication.fragments.RatesFragment;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private Toolbar toolbar;
    CurrencyList list;
    ArrayList<CurrencyList> currencyLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Currency Converter");
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        SimpleDateFormat dateFormat = new SimpleDateFormat("'Aggiornato il' d MMMM yyyy HH:mm", new Locale("it", "IT"));
        toolbar.setSubtitleTextAppearance(this, R.style.SubtitleTextAppearance);
        toolbar.setSubtitle(dateFormat.format(new Date()));
        list = new CurrencyList();
        list.updateRates(this, new CurrencyList.CurrencyListUpdateListener() {
            @Override
            public void onUpdateComplete() {
                changeFragment(new HomeFragment(list));
            }
        });
        currencyLists = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 1;i<=30;i++){
            CurrencyList ll = new CurrencyList();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Log.v("days in main", dateF.format(calendar.getTime()));
            ll.updateRates(this, dateF.format(calendar.getTime()), new CurrencyList.CurrencyListUpdateListener() {
                @Override
                public void onUpdateComplete() {
                    currencyLists.add(ll);
                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            toolbar.setTitle("Currency Converter");
            navigationView.setCheckedItem(R.id.nav_home);
            changeFragment(new HomeFragment(list));
        }else if(id == R.id.nav_chart) {
            if(currencyLists.size() == 30){
                toolbar.setTitle("Rate History");
                navigationView.setCheckedItem(R.id.nav_chart);
                changeFragment(new ChartFragment(currencyLists));
            }
        }else if(id == R.id.nav_rates){
            toolbar.setTitle("Exchange Rates");
            navigationView.setCheckedItem(R.id.nav_rates);
            changeFragment(new RatesFragment(list));
        }else{
            return false;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment newFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).commit();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}