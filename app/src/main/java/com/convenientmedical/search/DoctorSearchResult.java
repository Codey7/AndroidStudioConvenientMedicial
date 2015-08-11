package com.convenientmedical.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.convenientmedical.frag.Registration;
import com.convenientmedical.frag.Registration.CallBack;
import com.convenientmedical.json.ParseJson;
import com.convenientmedical.main.MainActivity;
import com.convenientmedical.main.R;
import com.convenientmedical.net.AppController;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author Mr.Codey 按医生名字搜索结果
 */
public class DoctorSearchResult extends Activity {
	private ImageButton mibBack;
	private ListView mResultList;
	private TextView mtvTitle;
	private HashMap<String, Object> mHash;
	private List<HashMap<String, Object>> mList;
	private JSONObject jsonObject;
	private static final String URL = "";
	private static final String SEARCH_TAG = "";
	private SharedPreferences preferences;
	private String[] s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);
		initView();
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); 
		preferences = getSharedPreferences("myshare", MODE_PRIVATE);

		// 返回按钮
		mibBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		packageJson();
		mtvTitle.setText(s[1]);//设置标题字体
		RegInfo.getInstanse().setDoctor(s[1]);
		// Bundle mbundle=new Bundle();
		// registrationFrag=new Registration();
		// getContentFromFrag();
		mList = new ArrayList<HashMap<String, Object>>();
		getResult();
		addData();
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
				mList, R.layout.search_result_list, new String[] { "area",
						"hospital", "dept" }, new int[] { R.id.tv_area,
						R.id.tv_hos_name, R.id.tv_doc_name });
		mResultList.setAdapter(adapter);
		mResultList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				HashMap<String, Object> mhash=(HashMap<String, Object>)parent.getItemAtPosition(position);
				RegInfo.getInstanse().setArea(mhash.get("area").toString());
				RegInfo.getInstanse().setHospital(mhash.get("hospital").toString());
				RegInfo.getInstanse().setDepartment(mhash.get("dept").toString());
				Intent intent=new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}

	private void packageJson() {
		Intent intent = getIntent();
		s = intent.getStringArrayExtra("search");
		Log.i("bundle", s[0] + "   " + s[1]);
		jsonObject = ParseJson.getInstanse().packJson(1, s[1], "name");
		Log.i("json", jsonObject.toString());
	}

	private void initView() {
		mtvTitle=(TextView)findViewById(R.id.Tv_search_result);
		mibBack = (ImageButton) findViewById(R.id.Ib_back_to_main);
		mResultList = (ListView) findViewById(R.id.search_result_list);
	}

	private void addData() {
		mHash = new HashMap<String, Object>();
		mHash.put("area", "杭州");
		mHash.put("hospital", "东方医院");
		mHash.put("dept", "儿科");
		mList.add(mHash);
		mHash = new HashMap<String, Object>();
		mHash.put("area", "杭州");
		mHash.put("hospital", "杭州市第一人民医院");
		mHash.put("dept", "内分泌门诊");
		mList.add(mHash);
		mHash = new HashMap<String, Object>();
		mHash.put("area", "杭州");
		mHash.put("hospital", "杭州天目山妇产医院");
		mHash.put("dept", "妇科门诊");
		mList.add(mHash);
	}

	private void getResult() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL,
				jsonObject, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject jsonObject) {
						// TODO Auto-generated method stub
						Log.i("search_res", jsonObject.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				}) {

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> mHeadHash = new HashMap<String, String>();
				mHeadHash.put("Cookie", SharePreferenceUtil.getInstanse()
						.getshareString(preferences, "Cookie"));
				return mHeadHash;
			}

		};
		AppController.getInstance().addToRequestQueue(jsonObjectRequest,
				SEARCH_TAG);
	}
	/*
	 * public void getContentFromFrag() { registrationFrag.getSearchContent(new
	 * CallBack() {
	 * 
	 * @Override public void getResult(HashMap<String, String> mHash) { // TODO
	 * Auto-generated method stub Log.i("hash", mHash.toString()); } }); }
	 */
}
