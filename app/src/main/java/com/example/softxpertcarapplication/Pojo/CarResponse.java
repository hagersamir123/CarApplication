package com.example.softxpertcarapplication.Pojo;


import com.example.softxpertcarapplication.Pojo.CarData;

import java.util.List;


public class CarResponse {
        private String status ;
        private List<CarData> data;

        public String getStatus() {
                return status;
        }

        public List<CarData> getData() {
                return data;
        }

}
