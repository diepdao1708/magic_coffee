package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements HeaderAdapter.OnClickListener, SuggestionAdapter.OnClickListener, VoucherAdapter.OnClickListener {

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
        headerAdapter = new HeaderAdapter(this);
        suggestionAdapter = new SuggestionAdapter(this);
        voucherAdapter = new VoucherAdapter(this);

        binding.headerViewPage.setAdapter(headerAdapter);
        binding.suggestionRecyclerView.setAdapter(suggestionAdapter);
        binding.suggestionRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.voucherRecyclerView.setAdapter(voucherAdapter);
        binding.voucherRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            headerAdapter.reloadData(uiState.images);
            suggestionAdapter.reloadData(uiState.products);
            voucherAdapter.reloadData(uiState.vouchers);
        });

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
    public void OnItemSuggestionClick(int position, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", Objects.requireNonNull(homeViewModel.getUiState().getValue()).products.get(position));
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productBottomSheet, bundle);
    }

    @Override
    public void OnChoseButtonClick(int position, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", Objects.requireNonNull(homeViewModel.getUiState().getValue()).products.get(position));
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_productBottomSheet, bundle);
    }

    @Override
    public void OnItemVoucherClick(int position, View view) {
        // TODO
        Log.d("HOME_FRAGMENT", "OnItemVoucherClick");
    }

    @Override
    public void OnItemHeaderClick(int position, View view) {
        // noop
    }
}