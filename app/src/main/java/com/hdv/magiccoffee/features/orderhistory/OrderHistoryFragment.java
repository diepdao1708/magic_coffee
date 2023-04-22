package com.hdv.magiccoffee.features.orderhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hdv.magiccoffee.databinding.FragmentOrderHistoryBinding;

public class OrderHistoryFragment extends Fragment {

    private FragmentOrderHistoryBinding binding;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}