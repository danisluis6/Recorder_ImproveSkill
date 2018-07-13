package tutorial.lorence.started.view.activity.home;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import javax.inject.Inject;

import butterknife.BindView;
import tutorial.lorence.started.R;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.di.module.HomeModule;
import tutorial.lorence.started.view.activity.BaseActivity;
import tutorial.lorence.started.view.activity.home.adapter.PagerAdapterPushed;
import tutorial.lorence.started.view.activity.Setting.SettingActivity;

public class HomeActivity extends BaseActivity {

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
                .plus(new HomeModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initAttributes() {
        mViewPager.setAdapter(mPagerAdapterPushed);
        mTabs.setViewPager(mViewPager);
        mToolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
