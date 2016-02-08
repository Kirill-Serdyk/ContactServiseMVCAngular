package com.kirill.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.entity.MappedHobby;
import com.kirill.holders.HolderContactHobby;

/**
 * Created by Kirill on 29.01.2016.
 */
public class Hobby {
    private String title;

    private String description;

    public Hobby() {

    }

    public Hobby(MappedHobby mappedHobby) {
        this.title = mappedHobby.getTitle();
        this.description = mappedHobby.getDescription();
    }

    public Hobby(HolderContactHobby holder) {
        this.title = holder.getTitle();
        this.description = holder.getDescription();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        Hobby that = (Hobby) o;

        return Objects.equal(this.title, that.title) & Objects.equal(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.title, this.description);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.title)
                .addValue(this.description)
                .toString();
    }
}