package com.jameslittle.ighs.ighs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class UserAreaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    String name;
    String email;
    Switch toggleService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        String email = intent.getStringExtra("email");

//        SharedPreferences prefs = getSharedPreferences("navBarInfo", MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
//        if (restoredText != null) {
//            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//            String email = prefs.getString("email", "No email defined");
//            NavigationView navigationView = findViewById(R.id.nav_view);
//            navigationView.setNavigationItemSelectedListener(this);
//            View hView =  navigationView.getHeaderView(0);
//            TextView tvNavHeaderName = (TextView)hView.findViewById(R.id.tvNavHeaderName);
//            tvNavHeaderName.setText(name);
//            TextView tvNavHeaderEmail = (TextView)hView.findViewById(R.id.tvNavHeaderEmail);
//            tvNavHeaderEmail.setText(email);
//        }

        //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        //final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        //final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toggleService = (Switch)findViewById(R.id.toggleService);
        //  toggleService.setOnCheckedChangeListener(this);

        Switch toggleService = (Switch) findViewById(R.id.toggleService); // NEED TO FIX BLOCK OF CODE AFTER THIS SECTION IN ORDER TO SIMULATE WHAT STARTING THE SREVICE WOULD LOOK LIKE.
//        toggleService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) //Line A
//            {
//                if (isChecked) {
//                    // The toggle is enabled
//                    Toast.makeText(UserAreaActivity.this, "Service Started", Toast.LENGTH_LONG).show();
//
//                } else {
//                    // The toggle is disabled
//                    Toast.makeText(UserAreaActivity.this, "Service Stopped", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            name = bundle.getString("name");
            email = bundle.getString("email");
            TextView tvNavHeaderName = (TextView)hView.findViewById(R.id.tvNavHeaderName);
            tvNavHeaderName.setText(name);
            TextView tvNavHeaderEmail = (TextView)hView.findViewById(R.id.tvNavHeaderEmail);
            tvNavHeaderEmail.setText(email);
        }else {
            TextView tvNavHeaderName = (TextView)hView.findViewById(R.id.tvNavHeaderName);
            tvNavHeaderName.setText(name);
            TextView tvNavHeaderEmail = (TextView)hView.findViewById(R.id.tvNavHeaderEmail);
            tvNavHeaderEmail.setText(email);
        }
//        String name = intent.getStringExtra("name");
//        String email = intent.getStringExtra("email");
//        TextView tvNavHeaderName = (TextView)hView.findViewById(R.id.tvNavHeaderName);
//        tvNavHeaderName.setText(name);
//        TextView tvNavHeaderEmail = (TextView)hView.findViewById(R.id.tvNavHeaderEmail);
//        tvNavHeaderEmail.setText(email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
//            Intent intent2 = new Intent(UserAreaActivity.this, HomeFragment.class);
//            intent2.putExtra("name", name);
//            intent2.putExtra("username", username);
//            intent2.putExtra("email", email);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_map:
//  *not needed*              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
//                Intent mapsActivity = new Intent(UserAreaActivity.this, MapsActivity.class);
//                UserAreaActivity.this.startActivity(mapsActivity);
                Intent mapFunciton = new Intent(UserAreaActivity.this, MapFunction.class);
                UserAreaActivity.this.startActivity(mapFunciton);
                break;
            case R.id.nav_message:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();
                Intent messageActivity = new Intent(UserAreaActivity.this, MessageActivity.class);
                UserAreaActivity.this.startActivity(messageActivity);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
