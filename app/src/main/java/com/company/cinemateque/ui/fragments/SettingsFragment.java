package com.company.cinemateque.ui.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.cinemateque.ServiceLocator;
import com.ctacekscompany.cinemateque.R;
import com.ctacekscompany.cinemateque.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;


    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            binding.editTextPersonName.setText(getArguments().getString("secretKey"));
        }

        binding.applyButton.setOnClickListener(v -> {
            ServiceLocator.getInstance().getPreferencesRepository(requireContext()).put("USERNAME",
                    String.valueOf(binding.editTextPersonName.getText()));
            Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_mainFragment);
        });

        binding.editTextPersonName.setText(ServiceLocator.getInstance().getPreferencesRepository(getContext()).out("USERNAME"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}