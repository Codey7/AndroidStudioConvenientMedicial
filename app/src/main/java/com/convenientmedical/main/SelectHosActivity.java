package com.convenientmedical.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.convenientmedical.net.AppController;
import com.convenientmedicial.DataList.SelectAreaData;
import com.convenitentmedical.savedata.RegInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 选择医院
 * 
 * @author Mr.Codey
 *
 */
public class SelectHosActivity extends Activity {
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private List<String> data = null;// 表示数据源
	private ImageButton mibBack;
	private HashMap<String, String> mHash;
	private SharedPreferences preferences;
	private static final String URL = "https://120.26.83.51/demo/order/findHospital";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_hospital);
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);
		listView = (ListView) this.findViewById(R.id.select_hospital_list);
		mibBack = (ImageButton) findViewById(R.id.Ib_back_to_main);
		backClick();
		getHosJson();
		setListAdapter();
	}

	/**
	 * 通过省份获取医院
	 */
	private void getHosJson() {
		mHash = new HashMap<String, String>();
		mHash.put("province", "杭州");
		Log.e("res",mHash.toString());
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Method.POST, URL, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject jsonObject) {
						// TODO Auto-generated method stub
						Log.e("res", jsonObject.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						/*if (error != null) {
							Log.e("res", error.getMessage());
						}*/
					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				return mHash;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> mHashheader = new HashMap<String, String>();
				mHashheader.put("Cookie", SharePreferenceUtil.getInstanse()
						.getshareString(preferences, "Cookie"));
				Log.e("header", mHashheader.toString());
				return mHashheader;
			}

		};
		AppController.getInstance().addToRequestQueue(jsonObjectRequest);
	}

	/**
	 * 设置list的适配器及数据
	 */
	private void setListAdapter() {
		data = SelectAreaData.getDataSource(1);
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.select_list_text, data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String hosName = parent.getItemAtPosition(position).toString()
						.trim();
				RegInfo.getInstanse().setHospital(hosName);
				Log.i("hosnaem", RegInfo.getInstanse().getHospital());
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	/**
	 * 设置返回按钮的点击事件
	 */
	private void backClick() {
		mibBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
