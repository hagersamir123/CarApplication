package com.example.softxpertcarapplication;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.softxpertcarapplication.Pojo.CarData;

public class CarViewModel extends ViewModel {

    LiveData<PagedList<CarData>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, CarData>> liveDataSource;
    CarDataSourceFactory carDataSourceFactory;


    public CarViewModel() {

        carDataSourceFactory = new CarDataSourceFactory();

        liveDataSource = carDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(5).build();

        itemPagedList = (new LivePagedListBuilder(carDataSourceFactory, pagedListConfig)).build();
    }

    public void refresh() {
        carDataSourceFactory.getItemLiveDataSource().getValue().invalidate();
    }
}
