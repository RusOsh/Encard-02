package com.rus.encard02.repository.room;

import androidx.lifecycle.LiveData;

import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.db.CategoryDao;
import com.rus.encard02.db.WordsDao;

import java.util.List;

import javax.inject.Inject;

public class RoomRepository {
    private final CategoryDao dao;
    private final WordsDao wordsDao;

    @Inject
    public RoomRepository(CategoryDao dao, WordsDao wordsDao) {
        this.dao = dao;
        this.wordsDao = wordsDao;
    }

    public LiveData<List<CategoryModel>> getCategory() {
        return dao.getCategory();
    }

    public void insert(CategoryModel model) {
        dao.addCategory(model);
    }

}
