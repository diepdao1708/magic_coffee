package com.hdv.magiccoffee.features.address;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.databinding.ItemAddressBinding;
import com.hdv.magiccoffee.models.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private List<Address> list = new ArrayList<>();

    OnClickListener listener;

    public AddressAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnDeleteButtonClick(Address address, View view);

        void OnEditButtonClick(Address address, View view);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Address> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAddressBinding binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AddressViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.binding.deleteBtn.setOnClickListener(view -> listener.OnDeleteButtonClick(list.get(position), view));
        holder.binding.editBtn.setOnClickListener(view -> listener.OnEditButtonClick(list.get(position), view));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        ItemAddressBinding binding;


        public AddressViewHolder(@NonNull ItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Address address) {
            binding.nameTxt.setText(address.getAddress());
        }
    }
}
