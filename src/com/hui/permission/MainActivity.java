package com.hui.permission;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

	public static final int REQUEST_SHOWMULTIPLE = 0;

	private static final String[] PERMISSION_SHOWMULTIPLE = new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.CAMERA };

	public static final int REQUEST_SHOWREADWRITE = 1;

	private static final String[] PERMISSION_SHOWREADWRITE = new String[] {
			Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private Button mbtnOne;
    private Button mbtnMore;
    private final static String TAG="MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbtnOne=(Button)findViewById(R.id.btn_one);
		mbtnMore=(Button)findViewById(R.id.btn_more);
		if(android.os.Build.VERSION.SDK_INT>=23){
			mbtnOne.setOnClickListener(this);
			mbtnMore.setOnClickListener(this);
		}
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_one:
			 PermissionsDispatcher.getInstance().showReadWriteWithCheck(this,PERMISSION_SHOWREADWRITE);
			break;
		case R.id.btn_more:
			 PermissionsDispatcher.getInstance().showMultipleWithCheck(this,PERMISSION_SHOWMULTIPLE);
			break;
		default: break;
		}
		
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		 PermissionsDispatcher.getInstance().onRequestPermissionsResult(this, requestCode,permissions,grantResults);
	}
}