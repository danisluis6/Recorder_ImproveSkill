# Recorder_ImproveSkill
# LEVEL 3 [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)

   ```You just do.```
  
## Content:
- [x] We notice to restore Fragment and attach Dagger everywhere in the APplication

```java
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
        mFragmentUtils.pushFragment(FragmentUtils.PushFrgType.REPLACE, mSettingFragment.newInstance(mSettingFragment), mTagString[0]);
    }
}
```

```java
@SuppressLint("ValidFragment")
public class SettingFragment extends PreferenceFragment {

    @Inject
    Context mContext;

    public Fragment newInstance(SettingFragment fragment) {
        Application.getInstance()
                .getAppComponent()
                .plus(new SettingModule(this))
                .inject(fragment);
        return fragment;
    }

    public SettingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference highQualityPref = (CheckBoxPreference) findPreference(getResources().getString(R.string.pref_high_quality_key));
        highQualityPref.setChecked(AppSharedPreferences.getPrefHighQuality(getActivity()));
        highQualityPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppSharedPreferences.setPrefHighQuality(getActivity(), (boolean) newValue);
                return true;
            }
        });

        Preference aboutPref = findPreference(getString(R.string.pref_about_key));
        aboutPref.setSummary(getString(R.string.pref_about_desc, BuildConfig.VERSION_NAME));
        aboutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
//                LicensesFragment licensesFragment = new LicensesFragment();
//                licensesFragment.show(((SettingsActivity)getActivity()).getSupportFragmentManager().beginTransaction(), "dialog_licenses");
                return true;
            }
        });

        if (mContext != null) {
            Toast.makeText(mContext, "We completely get context successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("TAG", "NullPointerException");
        }
    }
}
```

```java
@Module
public class SettingModule {

    private SettingActivity settingActivity;
    private SettingFragment settingFragment;

    public SettingModule(SettingActivity settingActivity) {
        this.settingActivity = settingActivity;
    }

    public SettingModule(SettingFragment settingFragment) {
        this.settingFragment = settingFragment;
    }

    @Provides
    @ActivityScope
    SettingActivity provideSettingActivity() {
        return this.settingActivity;
    }

}
```

```java
@ActivityScope
@Subcomponent(

        modules = {
                SettingModule.class
        }
)
public interface SettingComponent {

    SettingActivity inject(SettingActivity activity);
    SettingFragment inject(SettingFragment fragment);
}
```
