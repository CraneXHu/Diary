package com.pkhope.diary.activity;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pkhope.diary.MyApplication;
import com.pkhope.diary.R;

/**
 * Created by thinkpad on 2015/8/10.
 */
public class SettingActivity extends AppCompatActivity {

    private SettingFragment mSettingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){
            mSettingFragment = new SettingFragment();
            replaceFragment(R.id.layout_setting_container,mSettingFragment);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void replaceFragment(int viewId, Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(viewId, fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    static public class SettingFragment extends PreferenceFragment {

//        private SharedPreferences mSharePreference;
//        private CheckBoxPreference mNet;
        private Preference mAccount;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);

            mAccount = findPreference("account");
            mAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(getActivity(),ResetPasswordActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().finish();
                    return true;
                }
            });

        }
    }
}
