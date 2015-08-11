package com.convenientmedical.main;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.convenientmedical.json.ParseLogAndSign;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登录
 * 
 * @author Mr.Codey
 *
 */
public class LogIn extends Activity implements OnClickListener {
	private static final int LOG_IN_STATUS = 1;// 登录状态值
	private SharedPreferences preferences;// 保存登录状态和cookies
	private Button mbtLogIn, mbtSignup, mbtNoLogIn;
	private EditText mUserName, mPwd;
	private String UserName, Pwd;
	private HashMap<String, String> mHashmap;
	private static final String Url = "https://120.26.83.51/demo/user/login";
	private RequestQueue requestqueue;
	public String cookieFromResponse;
	private String mHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);
		requestqueue = Volley.newRequestQueue(getApplicationContext());
		initView();
	}

	/**
	 * 实例化控件
	 */
	private void initView() {
		mbtSignup = (Button) findViewById(R.id.Bt_register);
		mbtNoLogIn = (Button) findViewById(R.id.Bt_nolanding);
		mbtLogIn = (Button) findViewById(R.id.BT_landing);
		mUserName = (EditText) findViewById(R.id.Et_username);
		mPwd = (EditText) findViewById(R.id.Et_password);
		mbtSignup.setOnClickListener(this);
		mbtNoLogIn.setOnClickListener(this);
		mbtLogIn.setOnClickListener(this);
	}

	/**
	 * 点击事件处理
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.Bt_register:
			intent.setClass(getApplicationContext(), SignUpActivity.class);
			startActivity(intent);
			break;
		case R.id.Bt_nolanding:
			intent.setClass(getApplicationContext(), MainActivity.class);
			startActivity(intent);
            SimpleDateFormat formatter    =   new    SimpleDateFormat    ("MM月dd日    HH:mm");
            Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
            String    str    =    formatter.format(curDate);
			Log.i("time",str);
			break;
		case R.id.BT_landing:
		default:
			UserName = mUserName.getText().toString().trim();
			Pwd = mPwd.getText().toString().trim();
			if (UserName.equals("") || Pwd.equals("")) {
				Toast.makeText(getApplicationContext(), "用户名密码不能为空!",
						Toast.LENGTH_SHORT).show();
				break;
			}
			mHashmap = new HashMap<String, String>();
			mHashmap.put("username", UserName);
			mHashmap.put("password", Pwd);
			Log.i("login", mHashmap.toString());
			StringRequest stringRequest = new StringRequest(Method.POST, Url,
					new Listener<String>() {

						@Override
						public void onResponse(String response) {
							// TODO Auto-generated method stub
							Log.i("res", response.toString());
							ParseLogAndSign login = new ParseLogAndSign();
							if (login.getContent(response).equals("1")) {
								Toast.makeText(getApplicationContext(), "登录成功",
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent();
								intent.setClass(getApplicationContext(),
										MainActivity.class);
								startActivity(intent);
								finish();
								SharePreferenceUtil.getInstanse().putIntData(
										preferences, "LOG_IN_STATUS",
										LOG_IN_STATUS);
								Log.e("logstatus",
										SharePreferenceUtil.getInstanse()
												.getIntData(preferences,
														"LOG_IN_STATUS")
												+ "");
							}
						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
							if (error.getMessage() != null) {
								Log.i("res", error.getMessage());
							}
							else
							{
								Log.i("res", "no response");
							}
						}
					}) {

				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					return mHashmap;
				}

				@Override
				protected Response<String> parseNetworkResponse(
						NetworkResponse response) {
					// TODO Auto-generated method stub
					try {
						String jsonString = new String(response.data,
								HttpHeaderParser.parseCharset(response.headers));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mHeader = response.headers.toString();
					Log.w("LOG", "get headers in parseNetworkResponse "
							+ response.headers.toString());
					// 使用正则表达式从reponse的头中提取cookie内容的子串
					Pattern pattern = Pattern.compile("Set-Cookie.*?;");
					Matcher m = pattern.matcher(mHeader);
					if (m.find()) {
						cookieFromResponse = m.group();
						Log.w("LOG", "cookie from server " + cookieFromResponse);
					}
					// 去掉cookie末尾的分号
					cookieFromResponse = cookieFromResponse.substring(11,
							cookieFromResponse.length() - 1);
					// Log.w("LOG","cookie substring "+ cookieFromResponse);
					// 加入sharedpreference
					SharePreferenceUtil.getInstanse().putStringData(
							preferences, "Cookie", cookieFromResponse);
					Log.w("cookie", SharePreferenceUtil.getInstanse()
							.getshareString(preferences, "Cookie"));
					String dataString = null;
					try {
						dataString = new String(response.data, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}// 返回值

					return Response.success(dataString,
							HttpHeaderParser.parseCacheHeaders(response));
				}
			};
			requestqueue.add(stringRequest);
			break;
		}
	}

}
