package kz.clinica.authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kz.clinica.R;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int EMPTY_SIZE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_activity);
        final Button enterBtn = (Button) findViewById(R.id.enter_btn);
        enterBtn.setOnClickListener(this);
        final Button registrationBtn = (Button) findViewById(R.id.registration_btn);
        registrationBtn.setOnClickListener(this);
        addFragment(LoginFragment.newInstance());
    }

    public void replaceFragment(Fragment fragment) {
        if (getSupportFragmentManager().getBackStackEntryCount() == EMPTY_SIZE ||
                !getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName()
                        .equals(fragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment, fragment.getClass().getName())
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        }
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, fragment, fragment.getClass().getName())
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter_btn:
                replaceFragment(LoginFragment.newInstance());
                break;
            case R.id.registration_btn:
                replaceFragment(RegistrationFragment.newInstance());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
