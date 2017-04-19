package kz.clinica.model;

/**
 *Описание шаблона доктора
 */
public class Doctor {
    //айди доктора
    private int doctor_id;
    //имя доктора
    private String name;
    //номер доктора
    private String phone;
    //айди клиники, к которой относится доктор
    private int clinic_id;

    public Doctor() {
    }

    public Doctor(int doctor_id, String name, String phone, int clinic_id) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.phone = phone;
        this.clinic_id = clinic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
    }
}

