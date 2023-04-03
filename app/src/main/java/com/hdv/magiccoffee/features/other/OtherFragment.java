package com.hdv.magiccoffee.features.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hdv.magiccoffee.databinding.FragmentOtherBinding;

public class OtherFragment extends Fragment {

    FragmentOtherBinding binding;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}