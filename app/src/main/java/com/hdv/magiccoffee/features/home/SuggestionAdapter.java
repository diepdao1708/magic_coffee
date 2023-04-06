package com.hdv.magiccoffee.features.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hdv.magiccoffee.R;
import com.hdv.magiccoffee.databinding.ItemHomeSuggestionBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {

    private List<Suggestion> suggestions;
    OnClickListener listener;

    public SuggestionAdapter(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void OnItemSuggestionClick(int id);

        void OnChoseButtonClick(int id);
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeSuggestionBinding binding = ItemHomeSuggestionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new SuggestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        holder.bind(suggestions.get(position));
        holder.binding.itemSuggestion.setOnClickListener(view -> listener.OnItemSuggestionClick(suggestions.get(position).getId()));
        holder.binding.choseBtn.setOnClickListener(view -> listener.OnChoseButtonClick(suggestions.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void reloadData(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
        notifyDataSetChanged();
    }

    public static class SuggestionViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSuggestionBinding binding;

        public SuggestionViewHolder(@NonNull ItemHomeSuggestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Suggestion suggestion) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
                    .load(suggestion.getImage())
                    .placeholder(R.drawable.img_background_login)
                    .fit()
                    .into(binding.drinkImage);
            binding.drinkNameTxt.setText(suggestion.getName());
            binding.drinkCostTxt.setText(suggestion.getCost());
        }
    }
}
