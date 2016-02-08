package com.kirill.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.model.Hobby;

import javax.persistence.*;

/**
 * Created by Kirill on 29.01.2016.
 */
@Entity
@Table(name = "Hobby")
public class MappedHobby {

    @Id
    @GeneratedValue
    @Column(name = "HOBBY_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    public MappedHobby() {
    }

    public MappedHobby(Hobby hobby) {
        this.title = hobby.getTitle();
        this.description = hobby.getDescription();
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

        MappedHobby that = (MappedHobby) o;

        return Objects.equal(this.title, that.title) & Objects.equal(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.title, this.description);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.id)
                .addValue(this.title)
                .addValue(this.description)
                .toString();
    }

}
