package com.sefford.logger;

import android.util.Log;

import com.sefford.fraggle.interfaces.Logger;

/**
 * Sample Logger class
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class SampleLogger implements Logger {
    @Override
    public void e(String tag, String message, Exception exception) {
        Log.e(tag, message, exception);
    }
}
