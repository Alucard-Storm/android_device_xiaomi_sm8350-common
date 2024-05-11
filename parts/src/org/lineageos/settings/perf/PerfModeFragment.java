/*
 * Copyright (C) 2023 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.perf;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import org.lineageos.settings.R;
import org.lineageos.settings.utils.FileUtils;

public class PerfModeFragment extends PreferenceFragmentCompat implements
        OnPreferenceChangeListener {

    private SwitchPreference mPerfModePreference;
    private static final String PERF_MODE_ENABLE_KEY = "perf_mode";
    private static final String PERF_MODE_NODE = "/sys/devices/virtual/thermal/thermal_message/sconfig";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.perf_mode_settings);
        mPerfModePreference = (SwitchPreference) findPreference(PERF_MODE_ENABLE_KEY);
        mPerfModePreference.setEnabled(true);
        mPerfModePreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        PERF_MODE_ENABLE_KEY.equals(preference.getKey());
        FileUtils.writeLine(PERF_MODE_NODE, (Boolean) newValue ? "10" : "0");
        return true;
    }
}
