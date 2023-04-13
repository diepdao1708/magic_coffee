package com.hdv.magiccoffee.features.order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.ItemOrderProductBinding;
import com.hdv.magiccoffee.features.commondata.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.OrderViewHolder> {

    private List<Product> products;
    OnClickListener listener;

    public ProductAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderProductBinding binding = ItemOrderProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(products.get(position));
        holder.binding.itemProduct.setOnClickListener(view -> listener.OnItemProductClick(position, view));
        holder.binding.choseBtn.setOnClickListener(view -> listener.OnChoseButtonClick(position, view));
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

    public interface OnClickListener {
        void OnItemProductClick(int position, View view);

        void OnChoseButtonClick(int position, View view);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        ItemOrderProductBinding binding;

        public OrderViewHolder(@NonNull ItemOrderProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(product.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.drinkImage);
            binding.drinkNameTxt.setText(product.getName());
            binding.drinkCostTxt.setText(String.valueOf(product.getCost()));
        }
    }
}
