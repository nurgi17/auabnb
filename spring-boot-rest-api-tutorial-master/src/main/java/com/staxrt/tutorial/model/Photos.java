package com.staxrt.tutorial.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "photos")
@EntityListeners(AuditingEntityListener.class)
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="photo", nullable = false)
    private byte[] photo;

    @Column(name="apartment_id", nullable = false)
    private long apartment_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(long apartment_id) {
        this.apartment_id = apartment_id;
    }

    @Override
    public String toString() {
        return "Photos{" +
                "id=" + id +
                ", photo=" + Arrays.toString(photo) +
                ", apartment_id=" + apartment_id +
                '}';
    }
}
