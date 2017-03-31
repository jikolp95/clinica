package kz.clinica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kz.clinica.utilities.DescriptionOfClinicActivity;
import kz.clinica.utilities.RecyclerItemClickListener;


public class HospitalListFragment extends Fragment {


    public HospitalListFragment() {

    }

    public static HospitalListFragment newInstance() {
        HospitalListFragment fragment = new HospitalListFragment();
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
        final RecyclerView hospitalsRV = (RecyclerView) view.findViewById(R.id.list_of_hospitals);
        final List<HospitalModel> listOfHospitals = new ArrayList<>();
        final List<String> listOfDoctors = Arrays.asList("Nth", "Гениколог", "Офтальмолог", "ЛОР", "Хирург", "Мамолог", "Квадратолог", "Треуголок");
        listOfHospitals.add(new HospitalModel("OnClinic", "+7 778 248 03 03", "г.Алматы, ул. Габдуллина, уг. улицы Ауезова", "0", "0", listOfDoctors));
        listOfHospitals.add(new HospitalModel("MedicalPark", "+7 778 248 03 03", "г.Алматы, ул. Габдуллина, уг. улицы Ауезова", "0", "0", listOfDoctors));
        listOfHospitals.add(new HospitalModel("SunRise", "+7 778 248 03 03", "г.Алматы, ул. Габдуллина, уг. улицы Ауезова", "0", "0", listOfDoctors));
        listOfHospitals.add(new HospitalModel("Городская 12", "+7 778 248 03 03", "г.Алматы, ул. Габдуллина, уг. улицы Ауезова", "0", "0", listOfDoctors));
        listOfHospitals.add(new HospitalModel("Ауырма жаным", "+7 778 248 03 03", "г.Алматы, ул. Габдуллина, уг. улицы Ауезова", "0", "0", listOfDoctors));
        final HospitalRecyclerViewAdapter adapter = new HospitalRecyclerViewAdapter(listOfHospitals, getActivity());
//        adapter.setListViewHeightBasedOnChildren(listOfDoctors);
        hospitalsRV.setAdapter(adapter);
        hospitalsRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        hospitalsRV.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), hospitalsRV, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),DescriptionOfClinicActivity.class));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
