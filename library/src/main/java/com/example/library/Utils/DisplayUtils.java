package com.example.library.Utils;

import android.content.Context;
import android.util.TypedValue;

/**
 *单位转化工具类
 */

public class DisplayUtils {
    Context context;
    public DisplayUtils( Context context) {
        this.context=context;
    }

    /**
     * px 转dp
     * @param dp
     * @return
     */
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
