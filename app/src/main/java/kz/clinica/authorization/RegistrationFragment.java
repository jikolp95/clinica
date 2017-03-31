package kz.clinica.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kz.clinica.MainActivity;
import kz.clinica.R;


public class RegistrationFragment extends Fragment implements View.OnClickListener {
    private Button nextBtn;

    public RegistrationFragment() {
    }


    public static RegistrationFragment newInstance() {
        RegistrationFragment fragment = new RegistrationFragment();
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
        final View view = inflater.inflate(R.layout.registration_fragment, container, false);
        nextBtn = (Button) view.findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((EnterActivity) getActivity()).getSupportActionBar() != null) {
            ((EnterActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.enter_credentials));
            ((EnterActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_btn:
                 getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }
}
