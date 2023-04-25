package com.hdv.magiccoffee.features.orderhistory;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.databinding.ItemOrderHistoryBinding;
import com.hdv.magiccoffee.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {

    private List<Order> orders = new ArrayList<>();

    OnClickListener listener;

    public OrderHistoryAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderHistoryBinding binding = ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderHistoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        holder.bind(orders.get(position));
        holder.binding.item.setOnClickListener(view -> listener.OnItemClick(orders.get(position), view));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public interface OnClickListener {
        void OnItemClick(Order order, View view);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    public static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
        ItemOrderHistoryBinding binding;

        public OrderHistoryViewHolder(@NonNull ItemOrderHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Order order) {
            binding.statusTxt.setText(order.getStatus());
            binding.nameTxt.setText(order.getName());
            binding.dateTxt.setText(order.getTimeOrder());
            binding.priceTxt.setText(String.valueOf(order.getTotal()));
        }
    }
}
