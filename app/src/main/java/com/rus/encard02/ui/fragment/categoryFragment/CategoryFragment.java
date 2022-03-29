package com.rus.encard02.ui.fragment.categoryFragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.rus.encard02.R;
import com.rus.encard02.base.BaseFragment;
import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.databinding.FragmentCategoryBinding;
import com.rus.encard02.ui.fragment.addCategory.AddCategoryFragmentDialog;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding> implements CategoryAdapter.ClickableCard {
    private CategoryViewModel viewModel;
    private CategoryAdapter adapter;

    @Override
    protected FragmentCategoryBinding bind() {
        return FragmentCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        openDialog();
    }

    private void openDialog() {
        binding.btnAddCategory.setOnClickListener(v -> {
            new AddCategoryFragmentDialog().show(requireActivity().getSupportFragmentManager(), "");
        });
    }

    @Override
    protected void setupUI() {
        adapter = new CategoryAdapter(this);
        binding.rvCategory.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.getCategory();
        viewModel.getListLiveData().observe(getViewLifecycleOwner(), categoryModels -> {
            adapter.setList(categoryModels);
        });
    }

    @Override
    public void listener(CategoryModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", model);
        controller.navigate(R.id.fragment_words, bundle);

    }
}