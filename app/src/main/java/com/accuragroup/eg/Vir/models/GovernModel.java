package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 7/19/2017.
 */

public class GovernModel {

    String GovernName;
    int GovernID;

    public GovernModel(String governName, int governID) {
        GovernName = governName;
        GovernID = governID;
    }


    public String getGovernName() {
        return GovernName;
    }

    public int getGovernID() {
        return GovernID;
    }

    public void setGovernName(String governName) {
        GovernName = governName;
    }


    @Override
    public String toString() {
        return GovernName;
    }
}
