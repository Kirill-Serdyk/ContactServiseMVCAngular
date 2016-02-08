package com.kirill.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.LocalDateTime;

/**
 * Created by Kirill on 08.02.2016.
 */
public class Message {

    private LocalDateTime date;

    private Contact from;

    private Contact to;

    private String content;



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

        Message that = (Message) o;

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