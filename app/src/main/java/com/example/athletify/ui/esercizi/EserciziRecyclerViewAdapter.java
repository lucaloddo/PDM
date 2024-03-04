package com.example.athletify.ui.esercizi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.athletify.R;
import com.example.athletify.models.Esercizio;

import java.util.ArrayList;
import java.util.List;

public class EserciziRecyclerViewAdapter extends RecyclerView.Adapter<EserciziRecyclerViewAdapter.EserciziViewHolder>{

    private List<Esercizio> esercizioList;
    private List<Esercizio> esercizioListCopy;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Esercizio esercizio);
    }

    public EserciziRecyclerViewAdapter(Context context, List<Esercizio> esercizioList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.esercizioList = esercizioList;
        this.esercizioListCopy = esercizioList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EserciziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.esercizi_item, parent, false);
        return new EserciziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EserciziViewHolder holder, int position) {
        holder.bind(esercizioList.get(position));
    }

    @Override
    public int getItemCount() {
        if (esercizioList != null) {
            return esercizioList.size();
        }
        return 0;
    }

    public void addData(List<Esercizio> esercizioList) {
        this.esercizioList = esercizioList;
    }

    public class EserciziViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final ImageView gifUrlTextView;

        public EserciziViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            gifUrlTextView = itemView.findViewById(R.id.gifUrl);
        }

        public void bind(Esercizio esercizio) {

            nameTextView.setText(esercizio.getName());

            Glide
                .with(context)
                .load(esercizio.getGifUrl())
                .fitCenter()
                .into(gifUrlTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    onItemClickListener.onItemClick(esercizio);
                }
            });
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                esercizioList = (List<Esercizio>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Esercizio> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = esercizioListCopy;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    protected List<Esercizio> getFilteredResults(String constraint) {
        List<Esercizio> results = new ArrayList<>();

        for (Esercizio item : esercizioListCopy) {
            if (item.getName().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }
}
