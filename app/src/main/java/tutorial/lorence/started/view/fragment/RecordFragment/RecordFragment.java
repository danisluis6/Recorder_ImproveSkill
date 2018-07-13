package tutorial.lorence.started.view.fragment.RecordFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import butterknife.BindView;
import tutorial.lorence.started.R;
import tutorial.lorence.started.service.RecordingService;
import tutorial.lorence.started.view.fragment.BaseFragment;


/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class RecordFragment extends BaseFragment {

    @BindView(R.id.chronometer)
    Chronometer mChronometer;

    @BindView(R.id.recording_status_text)
    TextView mRecordingPrompt;

    @BindView(R.id.btnRecord)
    FloatingActionButton mRecordButton;

    @BindView(R.id.btnPause)
    Button mPauseButton;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

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

        mRecordButton.setColorNormal(getResources().getColor(R.color.primary));
        mRecordButton.setColorPressed(getResources().getColor(R.color.primary_dark));

        mRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
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
            // TODO
        }
    }

    private void onPauseRecord(boolean pauseRecording) {
    }
}
