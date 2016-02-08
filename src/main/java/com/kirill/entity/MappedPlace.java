package com.kirill.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.model.Place;

import javax.persistence.*;

/**
 * Created by Kirill on 08.02.2016.
 */

@Entity
@Table(name = "Places")
public class MappedPlace {

    @Id
    @GeneratedValue
    @Column(name = "PLACE_ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "DESCRIPTION")
    private String description;


    public MappedPlace(Place place) {
        this.title = place.getTitle();
        this.longitude = place.getLongitude();
        this.latitude = place.getLatitude();
        this.description = place.getDescription();
    }

    public MappedPlace() {
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

        MappedPlace that = (MappedPlace) o;

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
