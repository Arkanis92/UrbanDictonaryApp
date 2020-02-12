package com.example.urbandictonaryapp.view.searchwordfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urbandictonaryapp.R;
import com.example.urbandictonaryapp.adapter.SearchAdapter;
import com.example.urbandictonaryapp.model.Definition;
import com.example.urbandictonaryapp.view.BaseFragment;
import com.example.urbandictonaryapp.view.mainactivity.MainActivity;
import com.example.urbandictonaryapp.viewmodel.SearchWordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchWordFragment extends BaseFragment {
    private SearchWordViewModel searchViewModel;
    private SearchAdapter adapter;
    private RecyclerView rvUrban;
    private Button btnSearch;
    private EditText etSearch;

    public SearchWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_word, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSearch = view.findViewById(R.id.btnSearch);
        etSearch = view.findViewById(R.id.etSearch);

        rvUrban = view.findViewById(R.id.rvUrban);
        rvUrban.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchAdapter();
        rvUrban.setAdapter(adapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(rvUrban.getContext(), DividerItemDecoration.HORIZONTAL);
        rvUrban.addItemDecoration(itemDecor);

        searchViewModel = new ViewModelProvider
                .AndroidViewModelFactory(requireActivity()
                .getApplication())
                .create(SearchWordViewModel.class);

        setupObservers();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = etSearch.getText().toString();
                searchViewModel.getDefinitionsObservable(term);
            }
        });
    }

    private void setupObservers() {
        searchViewModel.getDefinitions().observe(getActivity(), new Observer<List<Definition>>() {
            @Override
            public void onChanged(List<Definition> definitions) {
                if(definitions != null) {
                    if (!definitions.isEmpty()) {
                        adapter.setupData(definitions);
                    } else {
                        Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        searchViewModel.getError().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty()){
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
