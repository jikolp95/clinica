package kz.clinica.model;

/**
 * Описание шаблона города
 */
public class Image {
    //айди фотографии
    private int image_id;
    //картинка
    private String image;
    //айди клиники, к которой относятся фотографии
    private int clinic_id;

    public Image(int image_id, String image, int clinic_id) {
        this.image_id = image_id;
        this.image = image;
        this.clinic_id = clinic_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
    }
}

