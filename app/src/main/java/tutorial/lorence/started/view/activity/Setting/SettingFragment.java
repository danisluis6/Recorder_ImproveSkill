package tutorial.lorence.started.view.activity.Setting;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import tutorial.lorence.started.BuildConfig;
import tutorial.lorence.started.R;
import tutorial.lorence.started.app.Application;
import tutorial.lorence.started.di.module.SettingModule;
import tutorial.lorence.started.local.AppSharedPreferences;

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
