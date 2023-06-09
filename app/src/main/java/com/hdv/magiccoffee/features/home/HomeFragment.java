package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentHomeBinding;
import com.hdv.magiccoffee.models.OrderProduct;
import com.hdv.magiccoffee.models.Product;
import com.hdv.magiccoffee.models.RedirectingData;
import com.hdv.magiccoffee.models.Voucher;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SuggestionAdapter.OnClickListener, VoucherAdapter.OnClickListener {

    FragmentHomeBinding binding;
    HomeViewModel homeViewModel;
    HeaderAdapter headerAdapter;
    SuggestionAdapter suggestionAdapter;
    VoucherAdapter voucherAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        headerAdapter = new HeaderAdapter();
        suggestionAdapter = new SuggestionAdapter(this);
        voucherAdapter = new VoucherAdapter(this);

        binding.headerViewPage.setAdapter(headerAdapter);
        binding.suggestionRecyclerView.setAdapter(suggestionAdapter);
        binding.suggestionRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.voucherRecyclerView.setAdapter(voucherAdapter);
        binding.voucherRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        homeViewModel.getSuggestionProduct();
        homeViewModel.getHeaders();
        homeViewModel.getUsersVouchers();
        homeViewModel.getSuggestion().observe(getViewLifecycleOwner(), products -> suggestionAdapter.reloadData(products));
        homeViewModel.getHears().observe(getViewLifecycleOwner(), it -> headerAdapter.reloadData(it));
        homeViewModel.getVouchers().observe(getViewLifecycleOwner(), it -> voucherAdapter.reloadData(it));
        homeViewModel.getMessage().observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );

        setUpIndicators();
        setCurrentIndicator(0);
        binding.headerViewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        binding.cartFab.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_checkoutBottomSheet)
        );

        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setUpIndicators() {
        List<ImageView> indicators = new ArrayList<ImageView>() {{
            add(new ImageView(getContext()));
            add(new ImageView(getContext()));
            add(new ImageView(getContext()));
            add(new ImageView(getContext()));
            add(new ImageView(getContext()));
        }};
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8, 0, 8, 0);
        indicators.forEach(imageView -> {
            imageView.setImageDrawable(requireContext().getDrawable(R.drawable.background_indicator_inactive));
            imageView.setLayoutParams(layoutParams);
            binding.indicatorsLinearLayout.addView(imageView);
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setCurrentIndicator(int position) {
        int childCount = binding.indicatorsLinearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) binding.indicatorsLinearLayout.getChildAt(i);
            int drawable = position == i ? R.drawable.background_indicator_active : R.drawable.background_indicator_inactive;
            imageView.setImageDrawable(requireContext().getDrawable(drawable));
        }
    }

    @Override
    public void OnItemSuggestionClick(Product product, View view) {
        Bundle bundle = new Bundle();
        OrderProduct orderProduct = new OrderProduct(product.getId(), product.getImage(), product.getName(), product.getCost(), product.getDescription());
        RedirectingData redirectingData = new RedirectingData(orderProduct, "HOME_FRAGMENT");
        bundle.putSerializable("orderProduct", redirectingData);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productBottomSheet, bundle);
    }

    @Override
    public void OnChoseButtonClick(Product product, View view) {
        Bundle bundle = new Bundle();
        OrderProduct orderProduct = new OrderProduct(product.getId(), product.getImage(), product.getName(), product.getCost(), product.getDescription());
        RedirectingData redirectingData = new RedirectingData(orderProduct, "HOME_FRAGMENT");
        bundle.putSerializable("orderProduct", redirectingData);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productBottomSheet, bundle);
    }

    @Override
    public void OnItemVoucherClick(Voucher voucher, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("voucher", voucher);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_voucherBottomSheet, bundle);
    }
}