package com.example.andoid.newsiness.Model;

import java.util.List;

/**
 * Created by Chinmay on 18-Apr-18.
 */

class urlsToLogos
{
    private String small;
    private String medium;
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


}

public class Source {

    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String lnguage;
    private String country;
    private urlsToLogos urlsToLogos;
    private List<String> sortBysAvailable;

    public Source()
    {

    }

    public Source(String id, String name, String description, String url, String category, String lnguage, String country, com.example.andoid.newsiness.Model.urlsToLogos urlsToLogos, List<String> sortBysAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.lnguage = lnguage;
        this.country = country;
        this.urlsToLogos = urlsToLogos;
        this.sortBysAvailable = sortBysAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLnguage() {
        return lnguage;
    }

    public void setLnguage(String lnguage) {
        this.lnguage = lnguage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public com.example.andoid.newsiness.Model.urlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(com.example.andoid.newsiness.Model.urlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public List<String> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(List<String> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }
}
