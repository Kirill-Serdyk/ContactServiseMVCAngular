package com.kirill.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.model.Contact;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * Created by Kirill on 08.02.2016.
 */
public class MappedMessage {

    @Id
    @GeneratedValue
    @Column(name = "PLACE_ID")
    private long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @OneToOne
    private Contact from;

    @OneToOne
    private Contact to;

    @Column(name = "CONTENT")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Contact getFrom() {
        return from;
    }

    public void setFrom(Contact from) {
        this.from = from;
    }

    public Contact getTo() {
        return to;
    }

    public void setTo(Contact to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappedMessage that = (MappedMessage) o;

        return Objects.equal(this.date, that.date) & Objects.equal(this.from, that.from) & Objects.equal(this.to, that.to) & Objects.equal(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.date, this.from, this.to, this.content);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(this.date)
                .addValue(this.from)
                .addValue(this.to)
                .addValue(this.content)
                .toString();
    }
}
