package com.hui.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;


public class PermissionUtils {

    private PermissionUtils() {
    }

    /**
     * Checks all given permissions have been granted.
     *
     * @param grantResults results
     * @return returns true if all permissions have been granted.
     */
    public static boolean verifyPermissions(int... grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if <code>Activity</code> or <code>Fragment</code> has access to all given permissions.
     *
     * @param context     context
     * @param permissions permissions
     * @return returns true if <code>Activity</code> or <code>Fragment</code> has access to all given permissions.
     */
    public static boolean hasSelfPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks given permissions are needed to show rationale.
     *
     * @param activity    activity
     * @param permissions permission list
     * @return returns true if one of the permission is needed to show rationale.
     */
    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions) {
        for (String permission : permissions) {
            if (activity.shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks given permissions is or not required
     * @param require
     * @param permissions
     * @return
     */
    public static boolean shouldRequiredPermission(String require,String...permissions){
    	for (String permission : permissions) {
    		if(require.equals(permission)){
    			return true;
    		}
    	}
    	return false;
    }

}
