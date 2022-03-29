package com.rus.encard02.ui.fragment.categoryFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.repository.room.RoomRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CategoryViewModel extends ViewModel {
    private final RoomRepository repository;
    private LiveData<List<CategoryModel>> listLiveData;

    @Inject
    public CategoryViewModel(RoomRepository repository) {
        this.repository = repository;
        listLiveData = new MutableLiveData<>();
    }

    public void getCategory() {
        listLiveData = repository.getCategory();
    }

    public LiveData<List<CategoryModel>> getListLiveData() {
        return listLiveData;
    }
}
