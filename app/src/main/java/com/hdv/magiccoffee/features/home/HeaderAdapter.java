package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.ItemHomeHeaderBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    private List<Header> images = new ArrayList<>();
    OnClickListener listener;

    public HeaderAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnItemHeaderClick(int id);
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeHeaderBinding binding =
                ItemHomeHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        holder.bind(images.get(position));
        holder.binding.imageCardView.setOnClickListener(view -> listener.OnItemHeaderClick(images.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Header> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        ItemHomeHeaderBinding binding;

        public HeaderViewHolder(@NonNull ItemHomeHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Header image) {
            Picasso.get()
                    .load(image.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.image);
        }
    }
}
