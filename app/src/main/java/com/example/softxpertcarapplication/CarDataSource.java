package com.example.softxpertcarapplication;



import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.example.softxpertcarapplication.Api.RetrofitHandler;
import com.example.softxpertcarapplication.Pojo.CarData;
import com.example.softxpertcarapplication.Pojo.CarResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDataSource extends PageKeyedDataSource<Integer, CarData> {

    private static final int PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, CarData> callback) {
        RetrofitHandler.getInstance()
                .getApi().getCars(PAGE)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getData(), null, PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CarData> callback) {
        RetrofitHandler.getInstance()
                .getApi().getCars(params.key)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {


                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            callback.onResult(response.body().getData(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, CarData> callback) {
        RetrofitHandler.getInstance()
                .getApi()
                .getCars(params.key)
                .enqueue(new Callback<CarResponse>() {
                    @Override
                    public void onResponse(Call<CarResponse> call, Response<CarResponse> response) {

                        if (response.body() != null) {

                            Integer key = response.body().getStatus() != "0" ? params.key + 1 : null;

                            callback.onResult(response.body().getData(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<CarResponse> call, Throwable t) {

                    }
                });
    }
}