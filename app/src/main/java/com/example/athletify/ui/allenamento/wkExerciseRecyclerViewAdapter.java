package com.example.athletify.ui.allenamento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.athletify.R;
import com.example.athletify.models.wkExercise;

import java.util.List;

public class wkExerciseRecyclerViewAdapter extends RecyclerView.Adapter<wkExerciseRecyclerViewAdapter.wkExerciseViewHolder> {

    private List<wkExercise> exerciseList;
    private OnItemClickListener onItemClickListener;

    public wkExerciseRecyclerViewAdapter(List<wkExercise> exerciseList,OnItemClickListener onItemClickListener) {
        this.exerciseList = exerciseList;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(wkExercise esercizio);
    }

    @NonNull
    @Override
    public wkExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new wkExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wkExerciseViewHolder holder, int position) {
        holder.bind(exerciseList.get(position));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class wkExerciseViewHolder extends RecyclerView.ViewHolder {

        private final TextView exerciseName;
        private final TextView set;
        private final TextView reps;
        private final TextView weight;

        public wkExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.nameExercise);
            set = itemView.findViewById(R.id.set);
            reps = itemView.findViewById(R.id.reps);
            weight = itemView.findViewById(R.id.weight);
        }

        public void bind(wkExercise esercizio) {
            exerciseName.setText(esercizio.getExerciseName());
            set.setText(esercizio.getSet());
            reps.setText(esercizio.getReps());
            weight.setText(esercizio.getWeight());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    onItemClickListener.onItemClick(esercizio);
                }
            });
        }
    }
}
