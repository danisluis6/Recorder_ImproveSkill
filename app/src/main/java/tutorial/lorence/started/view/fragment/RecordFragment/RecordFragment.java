package tutorial.lorence.started.view.fragment.RecordFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tutorial.lorence.started.R;
import tutorial.lorence.started.view.fragment.BaseFragment;


/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment {

    private static final String ARG_POSITION = "mPosition";
    private int mPosition;

    public Fragment newInstance(RecordFragment fragment, int position) {
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        fragment.setArguments(b);
        return fragment;
    }

    public RecordFragment() {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        bindView(view);
        mPosition = getArguments().getInt(ARG_POSITION);
        return view;
    }
}
