package com.hdv.magiccoffee.features.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hdv.magiccoffee.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {

    FragmentAddressBinding binding;

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}