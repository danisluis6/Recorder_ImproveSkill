package tutorial.lorence.started.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

import javax.inject.Inject;

import butterknife.BindView;
import tutorial.lorence.started.R;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.di.module.MainModule;
import tutorial.lorence.started.view.activity.adapter.PagerAdapterPushed;

public class MainActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @Inject
    FragmentManager mFragmentManager;

    @Inject
    PagerAdapterPushed mPagerAdapterPushed;

    @Override
    public void distributedDaggerComponents() {
        super.distributedDaggerComponents();
        Application.getInstance()
                .getAppComponent()
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAttributes() {
        mViewPager.setAdapter(mPagerAdapterPushed);
        mTabs.setViewPager(mViewPager);
        mToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        setSupportActionBar(mToolbar);
    }
}
