package com.hdv.magiccoffee.features.other;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.MainActivity;
import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.DialogConfirmBinding;
import com.hdv.magiccoffee.databinding.FragmentOtherBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OtherFragment extends Fragment {

    FragmentOtherBinding binding;
    OtherViewModel viewModel;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(OtherViewModel.class);

        binding.personInfoBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_otherFragment_to_updateInformationFragment)
        );

        viewModel.getNavigate().observe(getViewLifecycleOwner(), navigationDestination -> {
            if (NavigationDestination.LOGOUT == navigationDestination) {
                ((MainActivity) requireActivity()).logout();
            }
        });

        binding.logoutBtn.setOnClickListener(view -> showDialog());

        return binding.getRoot();
    }

    private void showDialog() {
        DialogConfirmBinding dialogConfirmBinding;
        dialogConfirmBinding = DialogConfirmBinding.inflate(LayoutInflater.from(requireContext()));
        AlertDialog alertDialog = new AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).create();
        alertDialog.setView(dialogConfirmBinding.getRoot());

        dialogConfirmBinding.messageTxt.setText(getString(R.string.are_you_sure_you_want_to_logout));
        dialogConfirmBinding.acceptBtn.setText(getString(R.string.accept_text));
        dialogConfirmBinding.cancelBtn.setText(getString(R.string.cancel_text));

        dialogConfirmBinding.cancelBtn.setOnClickListener(view -> alertDialog.dismiss());
        dialogConfirmBinding.acceptBtn.setOnClickListener(view -> {
            viewModel.logout();
            alertDialog.dismiss();
        });

        alertDialog.show();
    }
}