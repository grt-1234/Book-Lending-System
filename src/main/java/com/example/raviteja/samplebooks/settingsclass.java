package com.example.raviteja.samplebooks;

import android.app.Application;
import android.content.Context;
import android.support.v7.preference.Preference;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.firebase.ui.auth.AuthUI;


public class settingsclass extends PreferenceFragmentCompat {

    Preference logout;
   // Preference.OnPreferenceClickListener clickListener;


    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.xml, s);
        logout = findPreference("Logout");
        logout.setOnPreferenceClickListener(Preference -> {
            Context context = getActivity();
            AuthUI.getInstance().signOut(context);
            return true;
        });
    }
}
