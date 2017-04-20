package kz.clinica.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kz.clinica.Calls;
import kz.clinica.MainActivity;
import kz.clinica.R;
import kz.clinica.model.ServerAnswer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button enterBtn;
    private EditText login, password;

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
        login = (EditText) view.findViewById(R.id.login);
        password = (EditText) view.findViewById(R.id.password);
        enterBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter_btn:
                Login();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (((EnterActivity) getActivity()).getSupportActionBar() != null) {
            ((EnterActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.welcome));
            ((EnterActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((EnterActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(getActivity().getDrawable(R.drawable.ic_heart));
        }
    }


    public void Login() {
        String username = login.getText().toString();
        String pass = password.getText().toString();
        Log.e("username: ", username);
        Log.e("pass: ", pass);
        if (username != null && pass != null) {
            String credentials = username + ":" + pass;
            final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            final Gson gson = new GsonBuilder().create();
            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(getString(R.string.base_url))
                    .build();
            final Calls.Login service = retrofit.create(Calls.Login.class);
            Call<ServerAnswer> call = service.login(basic);
            Log.e("basic", basic);
            call.enqueue(new Callback<ServerAnswer>() {

                @Override
                public void onResponse(Call<ServerAnswer> call, Response<ServerAnswer> response) {
                    if (response.body() != null) {
                        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                    } else {
                        Toast.makeText(getActivity(), "Упс! Неверный логин или пароль!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerAnswer> call, Throwable t) {
                    Toast.makeText(getActivity(), "Ошибка!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
