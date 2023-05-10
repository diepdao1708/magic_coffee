package com.hdv.magiccoffee.features.orderhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentOrderHistoryBinding;
import com.hdv.magiccoffee.models.Order;
import com.hdv.magiccoffee.models.SaveAccount;

public class OrderHistoryFragment extends Fragment implements OrderHistoryAdapter.OnClickListener {

    FragmentOrderHistoryBinding binding;
    OrderHistoryViewModel orderHistoryViewModel;
    OrderHistoryAdapter adapter;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        orderHistoryViewModel = new ViewModelProvider(this).get(OrderHistoryViewModel.class);
        adapter = new OrderHistoryAdapter(this);
        binding.orderHistoryRecyclerView.setAdapter(adapter);
        orderHistoryViewModel.getOrderHistory(SaveAccount.id);
        orderHistoryViewModel.getOrders().observe(getViewLifecycleOwner(), orders -> {
            if (orders.isEmpty()) {
                binding.emptyTxt.setVisibility(View.VISIBLE);
            } else {
                binding.emptyTxt.setVisibility(View.GONE);
            }
            adapter.reloadData(orders);
        });
        binding.backBtn.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_orderHistoryFragment_to_otherFragment)
        );
        return binding.getRoot();
    }

    @Override
    public void OnItemClick(Order order, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_orderHistoryFragment_to_orderStatusBottomSheet, bundle);
    }
}