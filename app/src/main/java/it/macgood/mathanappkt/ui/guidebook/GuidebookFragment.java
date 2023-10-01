package it.macgood.mathanappkt.ui.guidebook;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import it.macgood.mathanappkt.common.Constants;
import it.macgood.mathanappkt.databinding.FragmentGuidebookBinding;

public class GuidebookFragment extends Fragment {


    private static final int REQUEST_CODE = 100;

    public GuidebookFragment() {
    }

    FragmentGuidebookBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGuidebookBinding.inflate(inflater, container, false);

        GuidebookAdapter adapter = new GuidebookAdapter(getContext(), Constants.INSTANCE.getList(), 100);

        binding.recyclerView.setAdapter(adapter);

        try {
            if (checkSelfPermission(inflater.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

        } catch (RuntimeException e) {

        }

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}