package com.hdv.magiccoffee.features.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentDeleteAccountBinding;

public class DeleteAccountFragment extends Fragment {

    FragmentDeleteAccountBinding binding;

    public DeleteAccountFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeleteAccountBinding.inflate(inflater, container, false);

        binding.backBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_deleteAccountFragment_to_updateInformationFragment)
        );

        return binding.getRoot();
    }
}
