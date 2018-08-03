package com.accuragroup.eg.Vir.connections;

/**
 * Created by Elsayed on 12/11/2017.
 */

public interface ServiceApi {
    String BASE_API = "http://vir-eg.net/app_services/";


    enum Service {

        getCities("getCities"),
        getZones("getZones"),
        getGovernorates("getGovernorates"),
        getCategories("getCategories"),
        getShops("getShops"),
        addOrder("addOrder"),
        getSendOrders("getSendOrders"),
        changeOrderStatus("changeOrderStatus");


        private final String service;

        private Service(String service) {
            this.service = service;
        }

        public String getBaseService() {
            return BASE_API + this.service;
        }


    }

}
