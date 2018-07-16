package tutorial.lorence.started.view.fragment.FileViewerFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
public class FileViewerFragment extends BaseFragment {

    public FileViewerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_viewer, container, false);
        bindView(view);
        return view;
    }
}
