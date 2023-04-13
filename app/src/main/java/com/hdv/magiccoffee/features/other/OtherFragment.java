package com.hdv.magiccoffee.features.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentOtherBinding;

public class OtherFragment extends Fragment {

    FragmentOtherBinding binding;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(inflater, container, false);

        binding.personInfoBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_otherFragment_to_updateInformationFragment)
        );

        return binding.getRoot();
    }
}