package kz.clinica;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {
    private final static String LOG_TAG = MapFragment.class.getName();


    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreateView");
        final View view = inflater.inflate(R.layout.map_fragment, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(LOG_TAG, "OnResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(LOG_TAG, "onDestroyView");
        try {
            Fragment fragment = (getChildFragmentManager()
                    .findFragmentById(R.id.map));
            FragmentTransaction ft = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            ft.remove(fragment);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG,"exception blya");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(LOG_TAG, "onPause");
    }

}
