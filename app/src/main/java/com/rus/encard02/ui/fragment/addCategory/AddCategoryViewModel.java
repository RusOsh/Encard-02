package com.rus.encard02.ui.fragment.addCategory;

import androidx.lifecycle.ViewModel;

import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.repository.room.RoomRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddCategoryViewModel extends ViewModel {
    private final RoomRepository repository;

    @Inject
    public AddCategoryViewModel(RoomRepository repository) {
        this.repository = repository;
    }

    public void insert(CategoryModel model) {
        repository.insert(model);
    }
}

