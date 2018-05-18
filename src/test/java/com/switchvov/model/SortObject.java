package com.switchvov.model;

/**
 * Created by Switch on 2018/05/18.
 */
public class SortObject {
    private String company;
    private String standardId;
    private String standardName;

    public static SortObject getInstance() {
        return new SortObject();
    }

    public String getCompany() {
        return company;
    }

    public SortObject setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getStandardId() {
        return standardId;
    }

    public SortObject setStandardId(String standardId) {
        this.standardId = standardId;
        return this;
    }

    public String getStandardName() {
        return standardName;
    }

    public SortObject setStandardName(String standardName) {
        this.standardName = standardName;
        return this;
    }

    @Override
    public String toString() {
        return "SortObject{" +
                "company='" + company + '\'' +
                ", standardId='" + standardId + '\'' +
                ", standardName='" + standardName + '\'' +
                '}';
    }
}
