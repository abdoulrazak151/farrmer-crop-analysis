package com.soft.conseilagricole;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soft.conseilagricole.accueil.FragmentAccueil;
import com.soft.conseilagricole.outil.FragmentOutil;
import com.soft.conseilagricole.parametre.FragmentParametre;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentAccueil fragmentAccueil=new FragmentAccueil();
    FragmentOutil fragmentOutil=new FragmentOutil();
    FragmentParametre fragmentParametre=new FragmentParametre();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_general, fragmentAccueil).commit();
        BadgeDrawable badgeDrawable=bottomNavigationView.getOrCreateBadge(R.id.menu_outil_item);
        badgeDrawable.setVisible(true, true);
        badgeDrawable.setNumber(8);
        bottomNavigationView.setOnItemSelectedListener(item->{
            switch (item.getItemId()){
                case R.id.menu_accueil_item:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_general, fragmentAccueil).commit();
                    return  true;
                case R.id.menu_outil_item:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_general, fragmentOutil).commit();
                    return true;
                case R.id.menu_parametre_item:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_general, fragmentParametre).commit();
                    return true;
            }
            return false;
        });
    }
}