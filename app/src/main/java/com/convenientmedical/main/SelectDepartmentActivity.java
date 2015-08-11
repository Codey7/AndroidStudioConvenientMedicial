package com.convenientmedical.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonObjectRequest;
import com.convenientmedical.json.ParseJson;
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
 * 选择科室
 * @author Mr.Codey
 *
 */
public class SelectDepartmentActivity extends Activity {
	private ListView listView;
	private ImageButton mibBack;
	private ArrayAdapter<String> adapter;
	private List<String> data = null;// 表示数据源
	//选择科室
	private HashMap<String, String> mHash;
	private SharedPreferences preferences;
	private static final String URL = "https://120.26.83.51/demo/order/findDepartment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_department);
		preferences=getSharedPreferences("myshare", MODE_PRIVATE);
		Log.i("hos", RegInfo.getInstanse().getHospital()+"asdfasdfasdf");
		listView = (ListView) this.findViewById(R.id.select_department_list);
		mibBack=(ImageButton)findViewById(R.id.Ib_back_to_main);
		mibBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		getDeptJson();
		setListAdapter();
	}
	/**
	 * 设置数据
	 */
	private void setData()
	{
		data=new ArrayList<String>();
		String s="[{"+"\"name\""+":\"named\"}]";
		Log.e("string", s);
		
		/*if(ParseJson.getInstanse().parseSelJson(s).get("name").toString()!=null)
		{data.add(ParseJson.getInstanse().parseSelJson(s).get("name").toString());}
		else {
			data.add("请求失败！");
		}*/
		data.add("中医乳腺病专科");
		data.add("中医儿科");
		data.add("中医门诊");
		data.add("临床心理门诊");
		data.add("产科门诊");
		data.add("儿科门诊");
		data.add("内分泌门诊");
		data.add("口腔科门诊");
		data.add("名医门诊");
		data.add("妇科门诊");
		data.add("小儿内分泌专科");
		data.add("心血管门诊");
		data.add("感染门诊");
		data.add("普通外科门诊");
		data.add("甲状腺联合门诊");
		data.add("皮肤性病科");
		
	}
	private void setListAdapter() {
//		data=SelectAreaData.getDataSource();
		setData();
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.select_list_text, data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				RegInfo.getInstanse().setDepartment(parent.getItemAtPosition(position).toString());
				Intent intent=new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}
	/**
	 * 通过医院获取科室	 */
	private void getDeptJson() {
		mHash = new HashMap<String, String>();
		mHash.put("hospital", "浙江省中医院");
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
}
