package com.hdv.magiccoffee.features.updateaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentUpdateInformationBinding;
import com.squareup.picasso.Picasso;

public class UpdateInformationFragment extends Fragment {

    FragmentUpdateInformationBinding binding;
    private UpdateInformationViewModel viewModel;

    public UpdateInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateInformationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(UpdateInformationViewModel.class);

        viewModel.getAccount().observe(getViewLifecycleOwner(), account -> {
            Picasso.get()
                    .load(account.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.avatarImg);
            binding.enterFirstName.setText(account.getFirstName());
            binding.enterLastName.setText(account.getLastName());
            binding.enterEmail.setText(account.getEmail());
            binding.enterPhoneNum.setText(account.getPhoneNumber());
        });

        binding.updateBtn.setOnClickListener(view ->
                viewModel.updateAccount(
                        binding.enterFirstName.getText().toString().trim(),
                        binding.enterLastName.getText().toString().trim(),
                        binding.enterEmail.getText().toString().trim(),
                        binding.enterPhoneNum.getText().toString().trim()
                )
        );

        viewModel.getMessage().observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );
        viewModel.getNavigate().observe(getViewLifecycleOwner(), navigationDestination -> {
            if (NavigationDestination.BACK == navigationDestination) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_updateInformationFragment_to_otherFragment);
            }
        });

        binding.deleteBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_updateInformationFragment_to_deleteAccountFragment)
        );

        binding.backBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_updateInformationFragment_to_otherFragment)
        );

        return binding.getRoot();
    }
}