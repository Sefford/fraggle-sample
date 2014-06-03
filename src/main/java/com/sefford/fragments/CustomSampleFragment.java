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
