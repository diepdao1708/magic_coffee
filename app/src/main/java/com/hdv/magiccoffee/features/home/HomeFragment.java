package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    HomeViewModel homeViewModel;
    HeaderAdapter headerAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        headerAdapter = new HeaderAdapter();
        binding.headerViewPage.setAdapter(headerAdapter);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> headerAdapter.reloadData(uiState.images));

        setUpIndicators();
        setCurrentIndicator(0);
        binding.headerViewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

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
}