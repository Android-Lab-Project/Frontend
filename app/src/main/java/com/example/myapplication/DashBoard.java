package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityDashboardBinding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityDashboardBinding binding;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.doctors) {
                replaceFragment(new DoctorFragment());
            } else if (itemId == R.id.medicine) {
                replaceFragment(new MedicineFragment());
            } else if (itemId == R.id.more) {
                replaceFragment(new MoreFragment());
            }
            return true;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemIdf = item.getItemId();
        Log.d("Doctor Frag : ", String.valueOf(R.id.nav_medicine));
        switch (itemIdf) {
            case 2131362171 :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentDashboard()).commit();
                break;
            case  2131362173:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DoctorFragment()).commit();
                break;
            case 2131362172:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineFragment()).commit();
                break;

        }

        if(itemIdf==R.id.nav_dashboard){
            Intent intent=new Intent(this, DashBoard.class);
            startActivity(intent);
        }else if(itemIdf==R.id.nav_doctor){
            Intent intent=new Intent(this, DoctorMyMain.class);
            startActivity(intent);
        }
        if (itemIdf == R.id.nav_diagnosis) {
            Intent doctorIntent = new Intent(this, Diagnosis.class);
            startActivity(doctorIntent);

        }
        else if (itemIdf == R.id.nav_about) {
            Intent doctorIntent = new Intent(this, AboutOurselves.class);
            startActivity(doctorIntent);

        } else if (itemIdf == R.id.nav_signout) {
           // Toast.makeText(this,"Sign Out",Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(this, Login.class);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            }, 2000);

//            startActivity(intent);
        }else if(itemIdf==R.id.nav_medicine){
            Intent intent=new Intent(this, MedicineActivity2.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}