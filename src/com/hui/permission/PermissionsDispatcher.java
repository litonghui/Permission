package com.hui.permission;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.widget.Toast;

public final class PermissionsDispatcher {
	
	private static PermissionsDispatcher instance;
	public static PermissionsDispatcher getInstance(){
		if(instance==null){
			synchronized (PermissionsDispatcher.class) {
				instance = new PermissionsDispatcher();
			}
		}
		return instance;
	}
	public  void showMultipleWithCheck(Activity target,String [] PERMISSION_SHOWMULTIPLE) {

		if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWMULTIPLE)) {
			Toast.makeText(target, "权限已打开", Toast.LENGTH_SHORT).show();
		} else {
			List<String> permissionsNeeded = new ArrayList<String>();
			for (int i = 0; i < PERMISSION_SHOWMULTIPLE.length; i++) {
				if (!PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWMULTIPLE[i])) {
					permissionsNeeded.add(PERMISSION_SHOWMULTIPLE[i]);
				}
			}

			if (PermissionUtils.shouldShowRequestPermissionRationale(target,
					permissionsNeeded.toArray(new String[permissionsNeeded.size()]))) {
				Toast.makeText(target, "本次出现不再提示", Toast.LENGTH_SHORT).show();
			}
			target.requestPermissions(permissionsNeeded.toArray(new String[permissionsNeeded.size()]),
					MainActivity.REQUEST_SHOWMULTIPLE);
		}
	}

	public  void showReadWriteWithCheck(Activity target,String []PERMISSION_SHOWREADWRITE) {
		if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWREADWRITE)) {
			Toast.makeText(target, "权限已打开", Toast.LENGTH_SHORT).show();
		} else {
			if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWREADWRITE)) {
				Toast.makeText(target, "本次出现不再提示", Toast.LENGTH_SHORT).show();
			}
			target.requestPermissions(PERMISSION_SHOWREADWRITE, MainActivity.REQUEST_SHOWREADWRITE);
		}
	}

	public  void onRequestPermissionsResult(Activity target, int requestCode, String[] permissions,
			int[] grantResults) {
		switch (requestCode) {
		case MainActivity.REQUEST_SHOWMULTIPLE:
			if (PermissionUtils.verifyPermissions(grantResults)) {
				Toast.makeText(target, "权限已打开", Toast.LENGTH_SHORT).show();
			} else {
				/*if (PermissionUtils.shouldRequiredPermission(PERMISSION_SHOWREADWRITE[0], permissions)) {
					Toast.makeText(target, "请在设置打开权限", Toast.LENGTH_SHORT).show();
				   } 制定权限，特殊处理
				*/				
				Toast.makeText(target, "请在设置打开权限", Toast.LENGTH_SHORT).show();
			}
			break;
		case MainActivity.REQUEST_SHOWREADWRITE:
			if (PermissionUtils.verifyPermissions(grantResults)) {
				Toast.makeText(target, "权限已打开", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(target, "请在设置打开权限", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}