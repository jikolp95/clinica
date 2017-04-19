package kz.clinica.model;

/**
 *Описание шаблона города
 */

public class City {
    //название города
    private String name;
    //айди города
    private int city_id;

    //пустой конструктор
    public City() {
    }

    //конструктор с необходимыми параметрами
    public City(String name, int city_id) {
        this.name = name;
        this.city_id = city_id;
    }

    //геттеры и сеттеры. Необходимы чтобы брать информацию и вставлять информацию в шаблон.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
