package com.hdv.magiccoffee.features.deleteaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.MainActivity;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentDeleteAccountBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteAccountFragment extends Fragment {

    FragmentDeleteAccountBinding binding;
    DeleteAccountViewModel viewModel;

    public DeleteAccountFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeleteAccountBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(DeleteAccountViewModel.class);

        binding.backBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_deleteAccountFragment_to_updateInformationFragment)
        );

        binding.confirmButton.setOnCheckedChangeListener((compoundButton, b) -> binding.deleteBtn.setEnabled(b));

        binding.deleteBtn.setOnClickListener(view -> viewModel.deleteAccount());

        viewModel.getNavigate().observe(getViewLifecycleOwner(), navigationDestination -> {
            if (NavigationDestination.BACK == navigationDestination) {
                ((MainActivity) requireActivity()).logout();
            }
        });

        viewModel.getMessage().observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );


        return binding.getRoot();
    }
}
