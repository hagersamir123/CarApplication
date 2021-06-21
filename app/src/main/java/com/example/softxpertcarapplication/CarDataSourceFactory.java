package com.example.softxpertcarapplication;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.softxpertcarapplication.Pojo.CarData;

public class CarDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, CarData>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, CarData> create() {

        CarDataSource itemDataSource = new CarDataSource();

        itemLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }


    public MutableLiveData<PageKeyedDataSource<Integer, CarData>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}