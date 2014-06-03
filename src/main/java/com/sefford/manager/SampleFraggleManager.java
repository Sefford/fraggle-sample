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
