package com.rus.encard02.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.data.model.RoomModel.WordsModel;

@Database(entities = {CategoryModel.class, WordsModel.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    public abstract CategoryDao getDao();
    public abstract WordsDao getWordDao();
}
