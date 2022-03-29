package com.rus.encard02.ui.fragment.addCategory;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.rus.encard02.base.BaseBottomSheetFragment;
import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.databinding.FragmentAddCategoryDialogBinding;

public class AddCategoryFragmentDialog extends BaseBottomSheetFragment<FragmentAddCategoryDialogBinding> {
    private AddCategoryViewModel viewModel;

    @Override
    protected FragmentAddCategoryDialogBinding bind() {
        return FragmentAddCategoryDialogBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        binding.btnSaveCategory.setOnClickListener(v -> {
            String category = binding.etCategory.getText().toString();
            viewModel.insert(new CategoryModel(category));
            dismiss();
        });
    }
}