package kz.clinica;

import java.util.List;

import kz.clinica.model.City;
import kz.clinica.model.Clinic;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yeldos on 4/19/17.
 */

public class Calls {

    private static final String CLINICS = "clinics/{clinic_id}";
    private static final String CITIES = "cities";

    public interface Clinics {
        @GET(CLINICS)
        Call<List<Clinic>> getClinics(@Path("clinic_id") int clinic_id);
    }

    public interface Cities {
        @GET(CITIES)
        Call<List<City>> getCities();
    }
}
