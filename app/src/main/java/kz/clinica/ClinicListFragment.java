package kz.clinica;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import kz.clinica.model.Clinic;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClinicListFragment extends Fragment {
    private RecyclerView hospitalsRV;

    public ClinicListFragment() {

    }

    public static ClinicListFragment newInstance() {
        ClinicListFragment fragment = new ClinicListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.hospital_list_fragment, container, false);
        hospitalsRV = (RecyclerView) view.findViewById(R.id.list_of_hospitals);
        getClinics(1);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void getClinics(int city_id) {
        final Gson gson = new GsonBuilder().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(getString(R.string.base_url))
                .build();
        final Calls.Clinics service = retrofit.create(Calls.Clinics.class);
        Call<List<Clinic>> call = service.getClinics(city_id);
        call.enqueue(new Callback<List<Clinic>>() {

            @Override
            public void onResponse(Call<List<Clinic>> call, Response<List<Clinic>> response) {
                List<Clinic> clinicList = response.body();
                final ClinicRecyclerViewAdapter adapter = new ClinicRecyclerViewAdapter(clinicList, getActivity());
                hospitalsRV.setAdapter(adapter);
                hospitalsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Clinic>> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
