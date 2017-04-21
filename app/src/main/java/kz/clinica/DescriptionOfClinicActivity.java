package kz.clinica;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.clinica.model.Clinic;
import kz.clinica.model.Doctor;

public class DescriptionOfClinicActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CLINIC = "Clinic";
    private static final int REQUEST_CALL = 1;
    private Activity activity;
    private ImageView first, second, third;
    private ImagePopup imagePopup;
    private ImageView phone;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_of_clinic_activity);
        activity = this;
        final Gson gson = new Gson();
        final String clinic = getIntent().getStringExtra(CLINIC);
        final Clinic cl = gson.fromJson(clinic, Clinic.class);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView address = (TextView) findViewById(R.id.address);
        final TextView description = (TextView) findViewById(R.id.description);
        first = (ImageView) findViewById(R.id.first);
        first.setOnClickListener(this);
        second = (ImageView) findViewById(R.id.second);
        second.setOnClickListener(this);
        third = (ImageView) findViewById(R.id.third);
        third.setOnClickListener(this);
        name.setText(cl.getName());
        address.setText(cl.getAddress());
        description.setText(cl.getDescription());
        phoneNumber = cl.getPhone();

        imagePopup = new ImagePopup(this);
        imagePopup.setBackgroundColor(Color.BLACK);
        imagePopup.setWindowWidth(800);
        imagePopup.setWindowHeight(800);
        imagePopup.setHideCloseIcon(true);
        imagePopup.setImageOnClickClose(true);
        phone = (ImageView) findViewById(R.id.phone);
        phone.setOnClickListener(this);
        final List<Map<String, String>> list = new ArrayList<>();

        for (Doctor doctor : cl.getDoctors()) {
            Map<String, String> doctors = new HashMap<>();
            doctors.put("doctor_id", String.valueOf(doctor.getDoctor_id()));
            doctors.put("doctor_name", doctor.getName());
            doctors.put("doctor_phone", doctor.getPhone());
            list.add(doctors);
        }
        String[] from = {"doctor_name"};
        int[] to = {R.id.doctor_name};

        final ListView listOfDoctors = (ListView) findViewById(R.id.list_of_doctor);
        listOfDoctors.setAdapter(new SimpleAdapter(this, list, R.layout.doctor_item, from, to));
        listOfDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "8" + list.get(i).get("doctor_phone"), null));
                if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            activity,
                            new String[]{android.Manifest.permission.CALL_PHONE,}, REQUEST_CALL);
                } else {
                    startActivity(intent);
                }

            }
        });

        setListViewHeightBasedOnChildren(listOfDoctors);


    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RecyclerView.LayoutParams.WRAP_CONTENT));
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first:
                imagePopup.initiatePopup(first.getDrawable());
                break;
            case R.id.second:
                imagePopup.initiatePopup(second.getDrawable());
                break;
            case R.id.third:
                imagePopup.initiatePopup(third.getDrawable());
                break;
            case R.id.phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "8" + phoneNumber, null));
                if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            activity,
                            new String[]{android.Manifest.permission.CALL_PHONE,}, REQUEST_CALL);
                } else {
                    startActivity(intent);
                }
                break;
        }
    }
}
