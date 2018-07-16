package tutorial.lorence.started.view.activity.Setting;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.BindView;
import tutorial.lorence.started.R;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.other.container.FragmentStack;
import tutorial.lorence.started.other.container.FragmentUtils;
import tutorial.lorence.started.di.module.SettingModule;
import tutorial.lorence.started.other.TitleStringUtils;
import tutorial.lorence.started.view.activity.BaseActivity;
import tutorial.lorence.started.view.fragment.SettingFragment.SettingFragment;
import tutorial.lorence.started.view.fragment.SettingFragment.SettingView;

public class SettingActivity extends BaseActivity implements SettingView {

    private Stack<FragmentStack> mCurrentFrgStack;
    private String[] mTagString;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    SettingActivity mActivity;

    @Inject
    TitleStringUtils mTitleStringUtils;

    @Inject
    FragmentUtils mFragmentUtils;

    @Inject
    SettingFragment mSettingFragment;

    @Override
    public void distributedDaggerComponents() {
        super.distributedDaggerComponents();
        Application.getInstance()
                .getAppComponent()
                .plus(new SettingModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initAttributes() {
        mToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.action_settings);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        mCurrentFrgStack = new Stack<>();
        mFragmentUtils.attachInfoFragmentManager(mActivity, mCurrentFrgStack, R.id.content_frame_setting);
        mTagString = mTitleStringUtils.getGroupTagFragment();
        setDefaultFragment();
    }

    @Override
    public void setDefaultFragment() {
        mFragmentUtils.pushFragment(FragmentUtils.PushFrgType.REPLACE, mSettingFragment.newInstance(mSettingFragment, mActivity), mTagString[0]);
    }
}
