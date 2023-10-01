package it.macgood.mathanappkt.ui.handbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import it.macgood.mathanappkt.R;
import it.macgood.mathanappkt.databinding.FragmentHandbookBinding;

public class HandbookFragment extends Fragment {

    FragmentHandbookBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHandbookBinding.inflate(inflater);

        List<Handbook> handbooks = Collections.singletonList(
                new Handbook(1L,
                        R.drawable.picture_book_demidovich,
                        "Задачи и упражнения по математическому анализу",
                        "Б. П. Демидович")
        );

        binding.recyclerView.setAdapter(new HandbookAdapter(handbooks));
        return binding.getRoot();
    }
}