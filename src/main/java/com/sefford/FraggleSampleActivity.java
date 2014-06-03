package com.sefford;

import android.app.Activity;
import android.os.Bundle;

import com.sefford.fraggle.FragmentAnimation;
import com.sefford.fragments.CustomSampleFragment;
import com.sefford.fragments.SampleFragment;
import com.sefford.logger.SampleLogger;
import com.sefford.manager.SampleFraggleManager;

/**
 * Sample Fraggle Activity
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class FraggleSampleActivity extends Activity {

    /**
     * FraggleManager instance
     */
    protected SampleFraggleManager fraggleManager;
    /**
     * Default Animation package
     */
    protected FragmentAnimation animation = new FragmentAnimation(android.R.animator.fade_in,
            android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out);

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fraggleManager = new SampleFraggleManager(new SampleLogger());
        fraggleManager.initialize(getFragmentManager());
        openNormalFragment(SampleFragment.createBundle("", 1, false, false));
    }

    /**
     * Opens a SampleFragment. This has a normal back behavior.
     *
     * @param bundle Bundle to inject the SampleFragment
     */
    public void openNormalFragment(Bundle bundle) {
        // This method as the openCustomFragment should be refactored into a Factory in normal
        // usage situations.
        // The communication could also be done via Handlers, but for this sample, please forgive me
        SampleFragment fragment = new SampleFragment();
        fragment.setArguments(bundle);
        fragment.setTag(getString(R.string.fragment_tag,
                bundle.getInt(SampleFragment.EXTRA_NUMBER)));

        fraggleManager.addFragment(fragment, getString(R.string.fragment_tag,
                bundle.getInt(SampleFragment.EXTRA_NUMBER)), animation, 0, R.id.rl_container);
    }

    /**
     * Opens a SampleFragment. This has a customized back behavior.
     *
     * @param bundle Bundle to inject the SampleFragment
     */
    public void openCustomFragment(Bundle bundle) {
        SampleFragment fragment = new CustomSampleFragment();
        fragment.setArguments(bundle);
        fragment.setTag(getString(R.string.fragment_tag,
                bundle.getInt(SampleFragment.EXTRA_NUMBER)));

        fraggleManager.addFragment(fragment, getString(R.string.fragment_tag,
                bundle.getInt(SampleFragment.EXTRA_NUMBER)), animation, 0, R.id.rl_container);
    }

    @Override
    public void onBackPressed() {
        if (fraggleManager.canActivityFinish()) {
            finish();
        } else {
            fraggleManager.popBackStack(R.id.rl_container);
        }
    }
}

