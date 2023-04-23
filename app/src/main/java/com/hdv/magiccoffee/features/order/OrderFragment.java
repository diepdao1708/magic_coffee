package com.hdv.magiccoffee.features.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentOrderBinding;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.Product;
import com.hdv.magiccoffee.models.RedirectingData;

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
        orderViewModel.getAllProduct();

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

        orderViewModel.getCoffees().observe(getViewLifecycleOwner(), coffees -> coffeeAdapter.reloadData(coffees));
        orderViewModel.getFruits().observe(getViewLifecycleOwner(), fruits -> fruitJuiceAdapter.reloadData(fruits));
        orderViewModel.getCakes().observe(getViewLifecycleOwner(), cakes -> cakeAdapter.reloadData(cakes));
        orderViewModel.getTeas().observe(getViewLifecycleOwner(), teas -> teaAdapter.reloadData(teas));
        orderViewModel.getMessage().observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );

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
        binding.cartFab.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_orderFragment_to_checkoutBottomSheet)
        );

        return binding.getRoot();
    }

    @Override
    public void OnItemProductClick(Product product, View view) {
        Bundle bundle = new Bundle();
        OrderProduct orderProduct = new OrderProduct(product.getId(), product.getImage(), product.getName(), product.getCost(), product.getDescription());
        RedirectingData redirectingData = new RedirectingData(orderProduct, "ORDER_FRAGMENT");
        bundle.putSerializable("orderProduct", redirectingData);
        Navigation.findNavController(view).navigate(R.id.action_orderFragment_to_productBottomSheet, bundle);
    }

    @Override
    public void OnChoseButtonClick(Product product, View view) {
        Bundle bundle = new Bundle();
        OrderProduct orderProduct = new OrderProduct(product.getId(), product.getImage(), product.getName(), product.getCost(), product.getDescription());
        RedirectingData redirectingData = new RedirectingData(orderProduct, "ORDER_FRAGMENT");
        bundle.putSerializable("orderProduct", redirectingData);
        Navigation.findNavController(view).navigate(R.id.action_orderFragment_to_productBottomSheet, bundle);
    }
}