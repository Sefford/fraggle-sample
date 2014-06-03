/*
 * Copyright (C) 2014 Saúl Díaz
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
package com.sefford.fragments;

import android.widget.Toast;

import com.sefford.R;

/**
 * Fragment that has a customized behavior for onBackPressed. I know I violated liskov.
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class CustomSampleFragment extends SampleFragment {

    /**
     * This flag will allow FraggleManager to resume the normal flow once the action has been executed
     */
    boolean customActionPerformed = false;

    @Override
    public boolean customizedOnBackPressed() {
        return !customActionPerformed;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getActivity(), getString(R.string.fragment_toast), Toast.LENGTH_LONG).show();
        customActionPerformed = true;
    }
}
