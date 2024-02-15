package com.example.referenciabibliografica;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.referenciabibliografica.fragmentos.AboutusFragment;
import com.example.referenciabibliografica.fragmentos.HomeFragment;
import com.example.referenciabibliografica.fragmentos.LibraryFragment;
import com.example.referenciabibliografica.fragmentos.SettingsFragment;
import com.example.referenciabibliografica.fragmentos.ShareFragment;
import com.example.referenciabibliografica.fragmentos.ShortsFragment;
import com.example.referenciabibliografica.fragmentos.SubscriptionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    NavigationView navigationView;

    private static List<Elemento> libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            String title = item.getTitle().toString();

            if ("Inicio".equals(title)) {
                replaceFragment(new HomeFragment());
            } else if ("Buscar".equals(title)) {
                replaceFragment(new ShortsFragment());
            } else if ("Biblioteca".equals(title)) {
                replaceFragment(new SubscriptionsFragment());
            } else if ("Vocabulario".equals(title)) {
                replaceFragment(new LibraryFragment());
            }

            //drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Drawer después de hacer clic en un elemento


            return true;
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            String title = item.getTitle().toString();

            if ("HomeDrawer".equals(title)) {
                replaceFragment(new HomeFragment());
            } else if ("Settings".equals(title)) {
                replaceFragment(new SettingsFragment());
            } else if ("Share".equals(title)) {
                replaceFragment(new ShareFragment());
            } else if ("About Us".equals(title)) {
                replaceFragment(new AboutusFragment());
            }

            //drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Drawer después de hacer clic en un elemento

            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout shortsLayout = dialog.findViewById(R.id.layoutShorts);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Upload a Video is clicked", Toast.LENGTH_SHORT).show();

            }
        });

        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Create a short is Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public static List<Elemento> getLibros() {
        return libros;
    }

    public static void setLibros(List<Elemento> libros) {
        MainActivity.libros = libros;
    }
}