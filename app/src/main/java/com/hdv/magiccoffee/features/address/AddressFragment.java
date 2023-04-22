package com.hdv.magiccoffee.features.address;

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
import com.hdv.magiccoffee.databinding.FragmentAddressBinding;
import com.hdv.magiccoffee.models.Address;
import com.hdv.magiccoffee.models.SaveAccount;

import java.util.Objects;

public class AddressFragment extends Fragment implements AddressAdapter.OnClickListener {

    FragmentAddressBinding binding;
    AddressViewModel viewModel;
    AddressAdapter adapter;

    public AddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddressBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        adapter = new AddressAdapter(this);
        binding.addressRecyclerView.setAdapter(adapter);
        if (SaveAccount.address.isEmpty()) viewModel.getUserAddress();

        viewModel.getAddress().observe(getViewLifecycleOwner(), list -> adapter.reloadData(list));
        binding.backBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_addressFragment_to_otherFragment)
        );
        binding.addAddressBtn.setOnClickListener(view -> {
            if (binding.enterNewAddress.getVisibility() == View.VISIBLE) {
                String address = binding.enterNewAddress.getText().toString().trim();
                if (address.isEmpty()) {
                    Toast.makeText(requireContext(), getString(R.string.please_enter_your_address), Toast.LENGTH_SHORT).show();
                } else {
                    viewModel.addAddress(address);
                }
                binding.enterNewAddress.setVisibility(View.GONE);
            } else {
                binding.enterNewAddress.setVisibility(View.VISIBLE);
            }
        });

        binding.updateAddressBtn.setOnClickListener(view -> {
            String s = binding.enterNewAddress.getText().toString().trim();
            if (s.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.please_enter_your_address), Toast.LENGTH_SHORT).show();
            } else {
                binding.addAddressBtn.setVisibility(View.VISIBLE);
                binding.updateAddressBtn.setVisibility(View.GONE);
                binding.enterNewAddress.setText("");
                viewModel.updateAddress(Objects.requireNonNull(viewModel.getEditAddress().getValue()).getId(), s);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void OnDeleteButtonClick(Address address, View view) {
        viewModel.deleteAddress(address.getId());
    }

    @Override
    public void OnEditButtonClick(Address address, View view) {
        binding.enterNewAddress.setVisibility(View.VISIBLE);
        binding.enterNewAddress.setText(address.getAddress());
        binding.addAddressBtn.setVisibility(View.GONE);
        binding.updateAddressBtn.setVisibility(View.VISIBLE);
        viewModel.onUpdateAddress(address);
    }
}