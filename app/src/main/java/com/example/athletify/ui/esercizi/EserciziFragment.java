package com.example.athletify.ui.esercizi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.athletify.R;
import com.example.athletify.repositories.esercizi.EserciziRepository;
import com.example.athletify.models.Esercizio;
import com.example.athletify.utils.EsercizioResponseCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EserciziFragment extends Fragment implements EsercizioResponseCallback {

    private EserciziRepository eserciziRepository;

    private List<Esercizio> esercizioList;

    private EserciziRecyclerViewAdapter eserciziRecyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_esercizi, container, false);

        setHasOptionsMenu(true);

        eserciziRepository = new EserciziRepository(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        esercizioList = new ArrayList<>();
        eserciziRepository.fetchEsercizi();

        RecyclerView recyclerView = view.findViewById(R.id.esercizi_list);
        eserciziRecyclerViewAdapter = new EserciziRecyclerViewAdapter(getActivity().getApplicationContext(), esercizioList, new EserciziRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Esercizio esercizio) { }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(eserciziRecyclerViewAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.esercizi_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Cerca esercizi...");

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getActivity() != null)
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                eserciziRecyclerViewAdapter.getFilter().filter(text);
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if(getActivity() != null)
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
                return false;
            }
        });

    }

    @Override
    public void onResponse(List<Esercizio> esercizioList) {
        this.esercizioList.addAll(esercizioList);
        eserciziRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT).show();
    }

}