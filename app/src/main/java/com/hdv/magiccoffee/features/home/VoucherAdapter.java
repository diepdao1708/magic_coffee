package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.ItemHomeVoucherBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private List<Voucher> vouchers;
    OnClickListener listener;

    public VoucherAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnItemVoucherClick(int id);
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeVoucherBinding binding = ItemHomeVoucherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new VoucherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        holder.bind(vouchers.get(position));
        holder.binding.voucherCardView.setOnClickListener(view -> listener.OnItemVoucherClick(vouchers.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return vouchers.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Voucher> vouchers) {
        this.vouchers = vouchers;
        notifyDataSetChanged();
    }

    public static class VoucherViewHolder extends RecyclerView.ViewHolder {
        ItemHomeVoucherBinding binding;

        public VoucherViewHolder(@NonNull ItemHomeVoucherBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Voucher voucher) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(voucher.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.voucherImage);
            binding.voucherNameTxt.setText(voucher.getName());
            binding.voucherQuantityTxt.setText(voucher.getQuantity());
            binding.voucherDateTxt.setText(voucher.getDate());
        }
    }
}
