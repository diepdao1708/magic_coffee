package com.hdv.magiccoffee;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.hdv.magiccoffee.databinding.ActivityMainBinding;
import com.hdv.magiccoffee.databinding.DialogConfirmBinding;
import com.hdv.magiccoffee.features.login.LoginActivity;
import com.mapbox.mapboxsdk.Mapbox;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getResources().getString(R.string.mapbox_access_token));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getCurrentUser();

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            viewModel.checkOrder(uri);
        }

        viewModel.showDialog().observe(this, it -> {
            if (it != null) {
                DialogConfirmBinding dialogConfirmBinding;
                dialogConfirmBinding = DialogConfirmBinding.inflate(LayoutInflater.from(this));
                AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.CustomAlertDialog).create();
                alertDialog.setView(dialogConfirmBinding.getRoot());

                dialogConfirmBinding.messageTxt.setText(it.getMessage());
                dialogConfirmBinding.acceptBtn.setText(getString(R.string.ok));
                dialogConfirmBinding.cancelBtn.setVisibility(View.GONE);

                dialogConfirmBinding.acceptBtn.setOnClickListener(view -> alertDialog.dismiss());

                alertDialog.show();
            }
        });

        setUpNavigation();
    }

    public void setUpNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController;
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(binding.bottomNav, navController);
            navController.addOnDestinationChangedListener((navController1, navDestination, bundle) -> {
                if (navDestination.getId() == R.id.homeFragment
                        || navDestination.getId() == R.id.orderFragment
                        || navDestination.getId() == R.id.storeFragment
                        || navDestination.getId() == R.id.otherFragment) {
                    showBottomNavigation();
                } else {
                    hideBottomNavigation();
                }
            });
        }
    }

    public void logout() {
        viewModel.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void hideBottomNavigation() {
        binding.bottomNav.setVisibility(View.GONE);
    }

    private void showBottomNavigation() {
        binding.bottomNav.setVisibility(View.VISIBLE);
    }
}