package com.example.giotto.mttext.demo.collectoractivitytext;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.mttext.demo.collectoractivitytext
 * @date 2016-09-14 11:37
 * @description Activity管理器
 */
public class ActivityCollector {
    public static List<Activity> activitys = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
