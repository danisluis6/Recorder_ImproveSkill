package tutorial.lorence.started.view.fragment.RecordFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import tutorial.lorence.started.R;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.di.module.HomeModule;
import tutorial.lorence.started.di.module.RecordModule;
import tutorial.lorence.started.other.Utils;
import tutorial.lorence.started.service.RecordingService;
import tutorial.lorence.started.view.activity.home.HomeActivity;
import tutorial.lorence.started.view.fragment.BaseFragment;


/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment implements RecordView {

    @BindView(R.id.chronometer)
    Chronometer mChronometer;

    @BindView(R.id.recording_status_text)
    TextView mRecordingPrompt;

    @BindView(R.id.btnRecord)
    FloatingActionButton mRecordButton;

    @BindView(R.id.btnPause)
    Button mPauseButton;

    @Inject
    HomeActivity mHomeActivity;

    @Inject
    RecordFragment mRecordFragment;

    @Inject
    File mFolder;

    @Inject
    Context mContext;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    public RecordFragment() {

    }

    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule((HomeActivity) getActivity()))
                .plus(new RecordModule((HomeActivity) getActivity(), this, this))
                .inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        distributedDaggerComponents();
        bindView(view);
        mRecordButton.setColorNormal(getResources().getColor(R.color.primary));
        mRecordButton.setColorPressed(getResources().getColor(R.color.primary_dark));
        mRecordFragment = this;
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isDoubleClick()) {
                    return;
                }
                if (Utils.checkPermission(mHomeActivity)) {
                    onRecord(mStartRecording);
                } else {
                    Utils.settingPermission(mRecordFragment);
                }
                mStartRecording = !mStartRecording;
            }
        });

        mPauseButton.setVisibility(View.GONE);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPauseRecord(mPauseRecording);
                mPauseRecording = !mPauseRecording;
            }
        });

        return view;
    }

    private void onRecord(boolean start) {
        Intent intent = new Intent(getActivity(), RecordingService.class);
        if (start) {
            mRecordButton.setImageResource(R.drawable.ic_media_stop);
            Toast.makeText(getActivity(), R.string.toast_recording_start, Toast.LENGTH_SHORT).show();
            if (!mFolder.exists()) {
                mFolder.mkdir();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                for (int permissionId : grantResults) {
                    if (permissionId != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(mContext, getString(R.string.error_permission), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                onRecord(mStartRecording);
                break;
        }
    }

    private void onPauseRecord(boolean pause) {
    }
}
