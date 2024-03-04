package com.example.athletify.ui.allenamento;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.athletify.R;
import com.example.athletify.models.wkDays;
import com.example.athletify.models.wkExercise;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class wkDaysListFragmentListView extends Fragment {

    private static final String TAG = "wkDaysListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_wk_weekly, container, false);

        ListView listView = (ListView) root.findViewById(R.id.wk_Weeks);
        List<wkDays> wkDaysListWithJsonReader = getwkDaysWithJsonReader();
        ArrayList<wkDays> listaGiorni = new ArrayList<>(wkDaysListWithJsonReader.size());
        listaGiorni.addAll(wkDaysListWithJsonReader);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listDays", listaGiorni);
        DaysAdapter daysAdapter = new DaysAdapter(wkDaysListWithJsonReader, getActivity());
        listView.setAdapter(daysAdapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.wk_Weeks);
        List<wkDays> wkDaysListWithJsonReader = getwkDaysWithJsonReader();

        DaysAdapter daysAdapter = new DaysAdapter(wkDaysListWithJsonReader, getActivity());
        listView.setAdapter(daysAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), wkDayActivity.class);

                ArrayList<wkExercise> listaExercise = new ArrayList<>();
                listaExercise.addAll(wkDaysListWithJsonReader.get(i).getWkExerciseList());
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listEs", listaExercise);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private List<wkDays> getwkDaysWithJsonReader(){

        List<wkDays> wkDaysList = new ArrayList<>();
        String allenamento_scelto = wkWeekActivity.getWorkoutString();

        try {
            InputStream fileInputStream = getActivity().getAssets().open("workouts.json");
            JsonReader reader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));

            reader.beginObject(); // root object
            while (reader.hasNext()) {
                String workouts = reader.nextName();
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    String wkObjectName = reader.nextName();
                    String nomeAllenamento = reader.nextString();
                    Log.d(TAG, wkObjectName + ":" + nomeAllenamento);
                    wkObjectName = reader.nextName();
                    Log.d(TAG, wkObjectName + ":" + reader.nextString());
                    wkObjectName = reader.nextName();
                    Log.d(TAG, wkObjectName + ":" + reader.nextString());
                    wkObjectName = reader.nextName();
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        wkDays wkDay = new wkDays();
                        while (reader.hasNext()) {
                            String daysObjectName = reader.nextName();
                            String dayName = reader.nextString();
                            wkDay.setDayName(dayName.toUpperCase());
                            daysObjectName = reader.nextName();
                            reader.beginArray();
                            List<wkExercise> wkExerciseList = new ArrayList<>();
                            while (reader.hasNext()) {
                                reader.beginObject();
                                wkExercise wkExercise = new wkExercise();
                                while (reader.hasNext()) {
                                    String exerciseObjectName = reader.nextName();
                                    String nomeEsercizio = reader.nextString();
                                    wkExercise.setExerciseName(nomeEsercizio);
                                    exerciseObjectName = reader.nextName();
                                    String setEsercizio = reader.nextString();
                                    wkExercise.setSet(setEsercizio);
                                    exerciseObjectName = reader.nextName();
                                    String repsEsercizio = reader.nextString();
                                    wkExercise.setReps(repsEsercizio);
                                    exerciseObjectName = reader.nextName();
                                    String weightEsercizio = reader.nextString();
                                    wkExercise.setWeight(weightEsercizio);
                                }
                                wkExerciseList.add(wkExercise);
                                reader.endObject();
                            }
                            wkDay.setWkExerciseList(wkExerciseList);
                            reader.endArray();
                        }
                        if(nomeAllenamento.equals(allenamento_scelto)){
                            wkDaysList.add(wkDay);
                        }
                        reader.endObject();
                    }
                    reader.endArray();
                    reader.endObject();
                }
                reader.endArray();
            }
            reader.endObject();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wkDaysList;

    }

}
