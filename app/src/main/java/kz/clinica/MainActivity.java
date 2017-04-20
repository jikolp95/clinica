package kz.clinica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kz.clinica.model.City;
import kz.clinica.utilities.SlidingTabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView city;
    private List<City> cityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final CharSequence Titles[] = {getResources().getString(R.string.list), getResources().getString(R.string.map)};
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, 2);
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(viewPagerAdapter);

        final SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(pager);

        city = (TextView) findViewById(R.id.city);
        city.setOnClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to call", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city:
                showDialog();
                break;
        }
    }


    private void showDialog() {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item);
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Выберите город");
        final Gson gson = new GsonBuilder().create();
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(getString(R.string.base_url))
                .build();
        final Calls.Cities service = retrofit.create(Calls.Cities.class);
        Call<List<City>> call = service.getCities();
        call.enqueue(new Callback<List<City>>() {

            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                final List<City> cities= response.body();
                for (City city : cities) {
                    arrayAdapter.add(city.getName());
                    cityList.add(city);
                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
//                Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show();
            }
        });


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int city_id = cityList.get(i).getCity_id();
                city.setText(cityList.get(i).getName());
                FragmentManager manager = getSupportFragmentManager();
                ClinicListFragment fragment = (ClinicListFragment) manager.getFragments().get(0);
                fragment.getClinics(city_id);
            }
        });
        builderSingle.show();
    }
}