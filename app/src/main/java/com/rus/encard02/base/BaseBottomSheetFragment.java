package com.rus.encard02.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.rus.encard02.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public abstract class BaseBottomSheetFragment<VB extends ViewBinding> extends BottomSheetDialogFragment {
    protected VB binding;
    protected NavController controller;
    protected abstract VB bind();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = bind();
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        setupUI();
        setupObservers();
        return binding.getRoot();
    }

    protected abstract void setupUI();
    protected abstract void setupObservers();
}
