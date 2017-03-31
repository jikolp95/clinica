package kz.clinica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeldos on 3/10/17.
 */

public class HospitalModel {
    private String name;
    private String phone_number;
    private String address;
    private String lat;
    private String lon;
    private List<String> listOfDoctors = new ArrayList<>();

     HospitalModel(String name, String phone_number, String address, String lat, String lon, List<String> listOfDoctors) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.listOfDoctors = listOfDoctors;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getListOfDoctors() {
        return listOfDoctors;
    }


    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
