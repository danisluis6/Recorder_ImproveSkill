# Recorder_ImproveSkill
# LEVEL 4 [![Build Status](https://travis-ci.org/nomensa/jquery.hide-show.svg)](https://travis-ci.org/nomensa/jquery.hide-show.svg?branch=master)

   ```You just do.```
  
## Content:
- [x] Enhance level

```java
@Override
    public void setDefaultFragment() {
        mFragmentUtils.pushFragment(FragmentUtils.PushFrgType.REPLACE, mSettingFragment.newInstance(mSettingFragment, mActivity), mTagString[0]);
    }
```

```java
@SuppressLint("ValidFragment")
public class SettingFragment extends PreferenceFragment {

    @Inject
    Context mContext;

    @Inject
    SettingFragment mSettingFragment;

    @Inject
    SettingActivity mActivity;

    @Inject
    LicensesFragment mLicensesFragment;

    public Fragment newInstance(SettingFragment fragment, SettingActivity activity) {
        Application.getInstance()
                .getAppComponent()
                .plus(new SettingModule(activity))
                .plus(new SettingFragmentModule())
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
            @SuppressLint("CommitTransaction")
            @Override
            public boolean onPreferenceClick(Preference preference) {
                mLicensesFragment.show(mActivity.getSupportFragmentManager().beginTransaction(), "dialog_licenses");
                return true;
            }
        });
    }
}
```
