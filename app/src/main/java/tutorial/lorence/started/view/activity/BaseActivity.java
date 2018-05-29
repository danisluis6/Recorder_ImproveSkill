package tutorial.lorence.started.view.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import butterknife.ButterKnife;
import tutorial.lorence.started.R;

/**
 * Created by vuongluis on 4/14/2018.
 * @author vuongluis
 * @version 0.0.1
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        distributedDaggerComponents();
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initAttributes();
    }

    public void distributedDaggerComponents() {

    }

    protected int getLayoutRes() {
        return -1;
    }

    protected void initAttributes(){}

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this).unbind();
        super.onDestroy();
    }
}
