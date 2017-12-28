package com.philipli.smartzone.util;

import android.view.Window;
import android.view.WindowManager;

/**
 * Created by philip on 2017/10/11.
 */

public class SystemUiVisibilityUtil {


    public static void hideStatusBar(Window window, boolean enable) {
        WindowManager.LayoutParams p = window.getAttributes();
        if (enable)
        {
            p.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else
        {
            p.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        window.setAttributes(p);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
