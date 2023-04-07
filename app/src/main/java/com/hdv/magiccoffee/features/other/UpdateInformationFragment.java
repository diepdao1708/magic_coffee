package com.hdv.magiccoffee.features.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hdv.magiccoffee.databinding.FragmentUpdateInformationBinding;

public class UpdateInformationFragment extends Fragment {

    FragmentUpdateInformationBinding binding;

    public UpdateInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateInformationBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}