package com.example.renhao.activitytest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.orhanobut.logger.Logger;

/**
 * 项目名称：ActivityTest
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/1 15:21
 * 修改备注：
 */
public class MyPreferenceActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener
        , Preference.OnPreferenceChangeListener {
    private static final String TAG = "MyPreferenceActivity";

    private SharedPreferences sp;

    private EditTextPreference mEtPreference;
    private ListPreference mListPreference;
    private CheckBoxPreference mCheckPreference;

    private EditTextPreference mUsernameEditTextPreference;
    private ListPreference mSexPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.activity_preference);
        initPreferences();
    }

    private void initPreferences() {

        mUsernameEditTextPreference = (EditTextPreference) findPreference(Consts.USERNAME_KEY);
        mUsernameEditTextPreference.setOnPreferenceChangeListener(this);
        mUsernameEditTextPreference.setSummary(mUsernameEditTextPreference.getText() == null ?
                "请输入用户名" : mUsernameEditTextPreference.getText());

        mSexPreference= (ListPreference) findPreference(Consts.SEX_KEY);
        mSexPreference.setOnPreferenceChangeListener(this);
        mSexPreference.setSummary(mSexPreference.getTitleRes());


        mEtPreference = (EditTextPreference) findPreference(Consts.EDIT_KEY);
        mListPreference = (ListPreference) findPreference(Consts.LIST_KEY);
        mCheckPreference = (CheckBoxPreference) findPreference(Consts.CHECKOUT_KEY);

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Logger.v(TAG, "key  " + key);
       /* if (key.equals(Consts.USERNAME_KEY)) {
            mUsernameEditTextPreference.setSummary(mUsernameEditTextPreference.getText());
        }*/
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Logger.v(TAG, "onPreferenceChange  " + newValue);
        preference.setSummary(newValue.toString());
        return true;
    }
}
