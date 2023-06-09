package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.ItemHomeSuggestionBinding;
import com.hdv.magiccoffee.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {

    private List<Product> products = new ArrayList<>();
    OnClickListener listener;

    public SuggestionAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnItemSuggestionClick(Product Product, View view);

        void OnChoseButtonClick(Product Product, View view);
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeSuggestionBinding binding = ItemHomeSuggestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SuggestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        holder.bind(products.get(position));
        holder.binding.itemSuggestion.setOnClickListener(view -> listener.OnItemSuggestionClick(products.get(position), view));
        holder.binding.choseBtn.setOnClickListener(view -> listener.OnChoseButtonClick(products.get(position), view));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public static class SuggestionViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSuggestionBinding binding;

        public SuggestionViewHolder(@NonNull ItemHomeSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("DefaultLocale")
        public void bind(Product product) {
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.drinkImage);
            binding.drinkNameTxt.setText(product.getName());
            binding.drinkCostTxt.setText(String.format("%.3fđ", product.getCost()));
        }
    }
}
