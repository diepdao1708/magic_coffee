package com.hdv.magiccoffee.features.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hdv.magiccoffee.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment implements ProductAdapter.OnClickListener {

    FragmentOrderBinding binding;
    OrderViewModel orderViewModel;
    ProductAdapter coffeeAdapter;
    ProductAdapter teaAdapter;
    ProductAdapter fruitJuiceAdapter;
    ProductAdapter cakeAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        coffeeAdapter = new ProductAdapter(this);
        teaAdapter = new ProductAdapter(this);
        fruitJuiceAdapter = new ProductAdapter(this);
        cakeAdapter = new ProductAdapter(this);

        binding.coffeeRecyclerView.setAdapter(coffeeAdapter);
        binding.coffeeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.teaRecyclerView.setAdapter(teaAdapter);
        binding.teaRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.fruitJuiceRecyclerView.setAdapter(fruitJuiceAdapter);
        binding.fruitJuiceRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        binding.cakeRecyclerView.setAdapter(cakeAdapter);
        binding.cakeRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        orderViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            coffeeAdapter.reloadData(uiState.coffees);
            teaAdapter.reloadData(uiState.teas);
            fruitJuiceAdapter.reloadData(uiState.fruitJuices);
            cakeAdapter.reloadData(uiState.cakes);
        });

        binding.coffeeBtn.setOnClickListener(view ->
                binding.scrollView.smoothScrollTo(0, (int) binding.coffeeTitleTxt.getY())
        );
        binding.teaBtn.setOnClickListener(view ->
                binding.scrollView.smoothScrollTo(0, (int) binding.teaTitleTxt.getY())
        );
        binding.fruitJuiceBtn.setOnClickListener(view ->
                binding.scrollView.smoothScrollTo(0, (int) binding.fruitJuiceTitleTxt.getY())
        );
        binding.cakeBtn.setOnClickListener(view ->
                binding.scrollView.smoothScrollTo(0, (int) binding.cakeTitleTxt.getY())
        );

        return binding.getRoot();
    }

    @Override
    public void OnItemProductClick(int id) {
        // TODO
        Log.d("ORDER_FRAGMENT", "OnItemProductClick");
    }

    @Override
    public void OnChoseButtonClick(int id) {
        // TODO
        Log.d("ORDER_FRAGMENT", "OnChoseButtonClick");
    }
}