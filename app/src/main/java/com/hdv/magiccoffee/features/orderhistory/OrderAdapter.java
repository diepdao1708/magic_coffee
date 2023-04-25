package com.hdv.magiccoffee.features.orderhistory;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.data.models.order.OrderProductResponse;
import com.hdv.magiccoffee.databinding.ItemOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderProductResponse> products = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<OrderProductResponse> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ItemOrderBinding binding;

        public OrderViewHolder(@NonNull ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint({"SetTextI18n"})
        public void bind(OrderProductResponse product) {
            binding.nameTxt.setText(product.getProduct().getName());
            binding.sizeTxt.setText(product.getSize());
            binding.priceTxt.setText("x " + product.getQuantity());
        }
    }
}
