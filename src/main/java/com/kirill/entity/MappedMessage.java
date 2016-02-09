package com.kirill.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.kirill.model.Contact;
import com.kirill.model.Message;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Kirill on 08.02.2016.
 */

@Entity
@Table(name = "Message")
public class MappedMessage {

    @Id
    @GeneratedValue
    @Column(name = "PLACE_ID")
    private long id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @OneToOne
    private MappedContact from;

    @OneToOne
    private MappedContact to;

    @Column(name = "CONTENT")
    private String content;


    public MappedMessage() {
    }

    public MappedMessage(Message message, MappedContact mappedContact1, MappedContact mappedContact2) {
        this.date = message.getDate();
        this.from = mappedContact1;
        this.to = mappedContact2;
        this.content = message.getContent();
    }

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

    public MappedContact getFrom() {
        return from;
    }

    public void setFrom(MappedContact from) {
        this.from = from;
    }

    public MappedContact getTo() {
        return to;
    }

    public void setTo(MappedContact to) {
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
