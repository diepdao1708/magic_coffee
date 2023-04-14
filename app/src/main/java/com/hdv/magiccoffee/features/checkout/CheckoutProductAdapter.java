package com.hdv.magiccoffee.features.checkout;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.databinding.ItemCheckoutBinding;
import com.hdv.magiccoffee.features.commondata.Product;

import java.util.List;

public class CheckoutProductAdapter extends RecyclerView.Adapter<CheckoutProductAdapter.CheckoutProductViewHolder> {

    private List<Product> products;
    OnClickListener listener;

    public CheckoutProductAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnItemCheckoutClick(Product product, int position, View view);

        void OnDeleteItem(int position);
    }

    @NonNull
    @Override
    public CheckoutProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCheckoutBinding binding = ItemCheckoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CheckoutProductViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CheckoutProductViewHolder holder, int position) {
        holder.bind(products.get(position));
        holder.binding.itemCheckout.setOnClickListener(view -> listener.OnItemCheckoutClick(products.get(position), position, view));
        holder.binding.delete.setOnClickListener(view -> {
            listener.OnDeleteItem(position);
            notifyDataSetChanged();
        });
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

    public static class CheckoutProductViewHolder extends RecyclerView.ViewHolder {

        ItemCheckoutBinding binding;

        public CheckoutProductViewHolder(@NonNull ItemCheckoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("DefaultLocale")
        public void bind(Product product) {
            String title = product.getQuantity() + " x " + product.getName();
            binding.titleTxt.setText(title);
            binding.sizeTxt.setText(product.getSize().getSize(product.getCost()));
            binding.priceTxt.setText(String.format("%.3fđ", product.getPrice()));
        }
    }
}
