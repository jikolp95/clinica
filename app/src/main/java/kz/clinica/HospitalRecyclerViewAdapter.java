package kz.clinica;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class HospitalRecyclerViewAdapter extends RecyclerView.Adapter<HospitalRecyclerViewAdapter.ViewHolder> {
    private List<HospitalModel> listOfHospital;
    private Context context;


    HospitalRecyclerViewAdapter(List<HospitalModel> listOfHospital, Context context) {
        this.listOfHospital = listOfHospital;
        this.context = context;

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView address;
        private ListView listOfDoctors;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            listOfDoctors = (ListView) view.findViewById(R.id.list_of_doctor);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final List<Map<String, String>> list = new ArrayList<>();
        for (String doctor : listOfHospital.get(position).getListOfDoctors()) {
            Map<String, String> doctors = new HashMap<>();
            doctors.put("DOCTOR", doctor);
            list.add(doctors);
        }
        String[] from = {"DOCTOR"};
        int[] to = {R.id.doctor_name};
        holder.name.setText(listOfHospital.get(position).getName());
        holder.address.setText(listOfHospital.get(position).getAddress());
        holder.listOfDoctors.setAdapter(new SimpleAdapter(context, list, R.layout.doctor_item, from, to));
    }

    @Override
    public int getItemCount() {
        return listOfHospital.size();
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
