package kz.clinica.model;

import java.io.Serializable;
import java.util.List;


public class Clinic implements Serializable {
    //айди клиники
    private int clinic_id;
    //название клиники
    private String name;
    //описание
    private String description;
    //контактный номер
    private String phone;
    //адрес
    private String address;
    //широта
    private long lat;
    //долгота
    private long lon;
    //список фотографий клиники
    private List<Image> images;
    //список докторов клиники
    private List<Doctor> doctors;
    private int city_id;

    public Clinic() {
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public int getCity_id() {
        return city_id;
    }

    public static Builder newBuilder() {
        return new Clinic().new Builder();
    }

    public class Builder {
        private int clinic_id;
        private String name;
        private String description;
        private String phone;
        private String address;
        private float lat;
        private float lon;
        private List<Image> images;
        private List<Doctor> doctors;
        private int city_id;

        private Builder() {
        }

        public Builder clinic_id(int clinic_id) {
            Clinic.this.clinic_id = clinic_id;
            return this;
        }

        public Builder name(String name) {
            Clinic.this.name = name;
            return this;
        }

        public Builder description(String description) {
            Clinic.this.description = description;
            return this;
        }

        public Builder phone(String phone) {
            Clinic.this.phone = phone;
            return this;
        }


        public Builder address(String address) {
            Clinic.this.address = address;
            return this;
        }

        public Builder lat(long lat) {
            Clinic.this.lat = lat;
            return this;
        }

        public Builder lon(long lon) {
            Clinic.this.lon = lon;
            return this;
        }

        public Builder images(List<Image> images) {
            Clinic.this.images = images;
            return this;
        }

        public Builder doctors(List<Doctor> doctors) {
            Clinic.this.doctors = doctors;
            return this;
        }

        public Builder city_id(int city_id) {
            Clinic.this.city_id = city_id;
            return this;
        }

        public Clinic build() {
            return Clinic.this;
        }
    }

}
