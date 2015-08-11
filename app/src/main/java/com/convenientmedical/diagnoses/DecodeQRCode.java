package com.convenientmedical.diagnoses;

import java.io.File;

import com.convenientmedical.main.MainActivity;
import com.convenientmedical.main.R;
import com.convenientmedical.main.RegistratInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DecodeQRCode extends Activity {
	private ImageView imageView;
	private Button mButton;
	private SharedPreferences preferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode);
		imageView=(ImageView)findViewById(R.id.imageView1);
		mButton=(Button)findViewById(R.id.button1);
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);
		
		//生成二维码
		final String filePath = getFileRoot(getApplicationContext())
				+ File.separator + "qr_" + System.currentTimeMillis() + ".jpg";
		// 二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
		new Thread(new Runnable() {
			@Override
			public void run() {
				boolean success = QRCodeUtil.createQRImage("老王", 1000, 1000,
						BitmapFactory.decodeResource(getResources(),
								R.drawable.icon_main), filePath);

				if (success) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							imageView.setImageBitmap(BitmapFactory
									.decodeFile(filePath));
						}
					});
				}
			}
		}).start();
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(SharePreferenceUtil.getInstanse().getIntData(preferences, "report")==1)
				{
					Intent intent=new Intent(getApplicationContext(), IllDetailsActivity.class);
					intent.putExtra("pay_status", 1);
					startActivity(intent);
					finish();
				}
				else
				{
				Intent intent=new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("come", false);
				startActivity(intent);
				finish();
			}
			}
		});
		//返回
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
	}

	// 文件存储根目录
	private String getFileRoot(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File external = context.getExternalFilesDir(null);
			if (external != null) {
				return external.getAbsolutePath();
			}
		}
		return context.getFilesDir().getAbsolutePath();
	}
}
