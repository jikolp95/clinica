package kz.clinica;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.clinica.model.Clinic;
import kz.clinica.model.Doctor;


class ClinicRecyclerViewAdapter extends RecyclerView.Adapter<ClinicRecyclerViewAdapter.ViewHolder> {
    private List<Clinic> listOfClinic;
    private Activity context;
    private static final int REQUEST_CALL = 1;
    private static final String CLINIC = "Clinic";


    ClinicRecyclerViewAdapter(List<Clinic> listOfClinic, Activity context) {
        this.listOfClinic = listOfClinic;
        this.context = context;

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private ListView listOfDoctors;
        private ImageView avatar;
        private ImageView phone_number;
        private LinearLayout top_layout;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            listOfDoctors = (ListView) view.findViewById(R.id.list_of_doctor);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            phone_number = (ImageView) view.findViewById(R.id.phone_number);
            top_layout = (LinearLayout) view.findViewById(R.id.top_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final List<Map<String, String>> list = new ArrayList<>();

        for (Doctor doctor : listOfClinic.get(position).getDoctors()) {
            Map<String, String> doctors = new HashMap<>();
            doctors.put("doctor_id", String.valueOf(doctor.getDoctor_id()));
            doctors.put("doctor_name", doctor.getName());
            doctors.put("doctor_phone", doctor.getPhone());
            list.add(doctors);
        }
        String[] from = {"doctor_name"};
        int[] to = {R.id.doctor_name};
        holder.name.setText(listOfClinic.get(position).getName());
        holder.address.setText(listOfClinic.get(position).getAddress());
        holder.listOfDoctors.setAdapter(new SimpleAdapter(context, list, R.layout.doctor_item, from, to));
        holder.listOfDoctors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("click", "click");
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "8" + list.get(i).get("doctor_phone"), null));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            context,
                            new String[]{android.Manifest.permission.CALL_PHONE,}, REQUEST_CALL);
                } else {
                    context.startActivity(intent);
                }

            }
        });
        setListViewHeightBasedOnChildren(holder.listOfDoctors);

        holder.phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "8" + listOfClinic.get(position).getPhone(), null));
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            context,
                            new String[]{android.Manifest.permission.CALL_PHONE,}, REQUEST_CALL);
                } else {
                    context.startActivity(intent);
                }

            }
        });

        holder.top_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Gson gson = new Gson();
                String clinic = gson.toJson(listOfClinic.get(position));
                Intent intent = new Intent(context, DescriptionOfClinicActivity.class);
                intent.putExtra(CLINIC, clinic);
                context.startActivity(intent);
            }
        });

//        holder.avatar.setImageDrawable(context.getDrawable(R.drawable.aurma_zhanym));
    }


    @Override
    public int getItemCount() {
        return listOfClinic.size();
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
}
