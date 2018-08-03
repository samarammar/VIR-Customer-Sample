package com.accuragroup.eg.Vir.models;

/**
 * Created by Apex on 3/15/2018.
 */

public class FilterModel {

    int GovId;
    int CityId;
    int ZoneId;
    String GovName;
    String CityName;
    String ZoneName;

    int SectionId;


    public void setSectionId(int sectionId) {
        SectionId = sectionId;
    }

    public int getSectionId() {
        return SectionId;
    }

    public int getGovId() {
        return GovId;
    }

    public void setGovId(int govId) {
        GovId = govId;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getGovName() {
        return GovName;
    }

    public void setGovName(String govName) {
        GovName = govName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public void setZoneId(int zoneId) {
        ZoneId = zoneId;
    }

    public void setZoneName(String zoneName) {
        ZoneName = zoneName;
    }

    public int getZoneId() {
        return ZoneId;
    }

    public String getZoneName() {
        return ZoneName;
    }
}
