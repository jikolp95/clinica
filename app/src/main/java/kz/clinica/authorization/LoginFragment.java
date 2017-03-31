package kz.clinica.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kz.clinica.MainActivity;
import kz.clinica.R;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button enterBtn;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.login_fragment, container, false);
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().setTitle(Html.fromHtml("<![CDATA[<font color='#D3D3D3'>text                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  </font>]]>"));
        }
        enterBtn = (Button) view.findViewById(R.id.enter_btn);
        enterBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter_btn:
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(((EnterActivity)getActivity()).getSupportActionBar()!=null) {
            ((EnterActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.welcome));
            ((EnterActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((EnterActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(getActivity().getDrawable(R.drawable.ic_heart));
        }
    }
}
