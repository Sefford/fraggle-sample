package com.sefford.manager;

import com.sefford.fraggle.FraggleManager;
import com.sefford.fraggle.interfaces.Logger;

/**
 * Extension of FraggleManager
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class SampleFraggleManager extends FraggleManager {

    /**
     * Creates a new instance of Fraggle Manager
     *
     * @param log Logger Facilities
     */
    public SampleFraggleManager(Logger log) {
        super(log);
    }

    /**
     * Returns if FraggleManager signals the Activity to finish.
     *
     * This method is here instead of the
     * FraggleManager because I did not want to enforce any termination conditions itself. However
     * this is a good start to extend your own Activity Finishes.
     *
     * @return TRUE if the activity is finishable, FALSE otherwise
     */
    public boolean canActivityFinish() {
        return getBackStackEntryCount() <= 1 || peek().isEntryFragment();
    }
}
