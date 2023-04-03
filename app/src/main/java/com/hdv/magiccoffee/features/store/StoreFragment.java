package com.hdv.magiccoffee.features.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hdv.magiccoffee.databinding.FragmentStoreBinding;

public class StoreFragment extends Fragment {

    FragmentStoreBinding binding;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStoreBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}