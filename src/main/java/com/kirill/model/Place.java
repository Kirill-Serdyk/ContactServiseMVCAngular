package com.kirill.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.entity.MappedPlace;
import com.kirill.holders.HolderContactPlace;

/**
 * Created by Kirill on 08.02.2016.
 */
public class Place {

    private String title;

    private double longitude;

    private double latitude;

    private String description;

    public Place() {
    }

    public Place(MappedPlace mappedPlace) {
        this.title = mappedPlace.getTitle();
        this.longitude = mappedPlace.getLongitude();
        this.latitude = mappedPlace.getLatitude();
        this.description = mappedPlace.getDescription();
    }

    public Place(HolderContactPlace holder) {
        this.title = holder.getTitle();
        this.longitude = holder.getLongitude();
        this.latitude = holder.getLatitude();
        this.description = holder.getDescription();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place that = (Place) o;

        return Objects.equal(this.title, that.title) & Objects.equal(this.description, that.description) & Objects.equal(this.longitude, that.longitude) & Objects.equal(this.latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.title, this.description, this.longitude, this.latitude);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.title)
                .addValue(this.description)
                .addValue(this.longitude)
                .addValue(this.latitude)
                .toString();
    }
}
