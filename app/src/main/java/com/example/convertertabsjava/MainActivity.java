package com.example.convertertabsjava;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout navigationTabs;
    private ViewPager2 contentPager;
    private AppPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationTabs = findViewById(R.id.tabs_navigation);
        contentPager = findViewById(R.id.pager_content);

        // Configuration de l'adaptateur pour le ViewPager
        pagerAdapter = new AppPagerAdapter(this);
        contentPager.setAdapter(pagerAdapter);

        // Synchronisation des onglets avec le ViewPager
        new TabLayoutMediator(navigationTabs, contentPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Température");
                    } else {
                        tab.setText("Distance");
                    }
                }
        ).attach();

        // Gestion du retour (back press) avec l'API moderne
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitDialog();
            }
        });
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter l'application")
                .setMessage("Souhaitez-vous fermer le convertisseur ?")
                .setPositiveButton("Oui", (dialog, which) -> finish())
                .setNegativeButton("Annuler", null)
                .show();
    }
}
