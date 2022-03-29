package com.rus.encard02.repository.pixabay;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rus.encard02.common.Resourse;
import com.rus.encard02.data.model.RoomModel.WordsModel;
import com.rus.encard02.data.model.pixabayModel.PixabayResponce;
import com.rus.encard02.data.network.PixabayApi;
import com.rus.encard02.db.WordsDao;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository {
    private final PixabayApi api;
    private final WordsDao dao;
    private MutableLiveData<Resourse<PixabayResponce>> liveData = new MutableLiveData<>();

    @Inject
    public PixabayRepository(PixabayApi api, WordsDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resourse<PixabayResponce>> getImage(String keyWord) {
        liveData.setValue(Resourse.loading());
        api.getImages(keyWord).enqueue(new Callback<PixabayResponce>() {
            @Override
            public void onResponse(@NonNull Call<PixabayResponce> call,
                                   @NonNull Response<PixabayResponce> response) {
                Log.e("repository", response.message() + "");
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("-----", response.message() + "");
                    String url = response.body().getHits().get(0).getLargeImageURL();
                    dao.addWord(new WordsModel(url));
                    liveData.setValue(Resourse.success(response.body()));
                }else {
                    liveData.setValue(Resourse.error(response.message()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PixabayResponce> call, @NonNull Throwable t) {
                liveData.setValue(Resourse.error(t.getLocalizedMessage()));
                Log.e("repository", t.getLocalizedMessage() + "");

            }
        });
        return liveData;
    }
}
