package com.convenientmedical.frag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.convenientmedical.adapter.MyExpandablelistViewAdapter;
import com.convenientmedical.main.MainActivity;
import com.convenientmedical.main.R;
import com.convenientmedical.main.RegistratInfo;
import com.convenientmedical.main.SelectAreaList;
import com.convenientmedical.main.SelectDepartmentActivity;
import com.convenientmedical.main.SelectDoctorList;
import com.convenientmedical.main.SelectHosActivity;
import com.convenientmedical.search.DoctorSearchResult;
import com.convenitentmedical.savedata.RegInfo;
import com.convenitentmedical.savedata.SharePreferenceUtil;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

/**
 * @author Mr.Codey 挂号fragment
 */
public class Registration extends Fragment {

	private View registrationView;
	private EditText metSearch;
	private RelativeLayout mrlChooseArea, mSelectHos, mSelectDept, mSelectDoc;
	private Button mbtReservation;
	private TextView mtvSelectHos, mtvSelArea, mtvSelDept, mtvSelDoc;
	private Spinner mspinner;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private HashMap<String, String> mHash;// 存放搜索的方式及数据
	private String searchMethod = "123";
	private String searchContent;
	private SharedPreferences preferences;

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		registrationView = inflater.inflate(R.layout.registration, container,
				false);
		initView();
		setAllText();
		setSpinnerAdapter();
		preferences=getActivity().getSharedPreferences("myshare", 0);
		// 搜索事件
		metSearch.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					searchContent = metSearch.getText().toString().trim();
					if (searchContent.equals("")) {
						Toast.makeText(getActivity(), "请输入内容！",
								Toast.LENGTH_SHORT).show();
					} else {
						/*
						 * mHash = new HashMap<String, String>();
						 * mHash.put("searchmethod", searchMethod);
						 * mHash.put("searchcontent", searchContent);
						 * Log.i("hash", mHash.toString());
						 */
						Intent intent = new Intent(getActivity(),
								DoctorSearchResult.class);
						intent.putExtra("search", new String[] { searchMethod,
								searchContent });
						startActivity(intent);
					}

				}
				return false;
			}
		});
		// 选择地区的点击事件
		mrlChooseArea.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), SelectAreaList.class);
				startActivity(intent);
			}
		});
		// 选择医院的点击事件
		mSelectHos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(RegInfo.getInstanse().getArea().equals(""))
				{
					Toast.makeText(getActivity(), "请先选择地区！", Toast.LENGTH_SHORT).show();
				}
				else {
				Intent intent = new Intent(getActivity(),
						SelectHosActivity.class);
				startActivity(intent);
				}
			}
		});
		// 选择科室的点击事件
		mSelectDept.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(RegInfo.getInstanse().getHospital().equals(""))
				{
					Toast.makeText(getActivity(), "请先选择医院！", Toast.LENGTH_SHORT).show();
				}
				else {
				Intent intent = new Intent(getActivity(),
						SelectDepartmentActivity.class);
				startActivity(intent);
				}
			}
		});
		// 选择医生的点击事件
		mSelectDoc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(RegInfo.getInstanse().getDepartment().equals(""))
				{
					Toast.makeText(getActivity(), "请先选择科室！", Toast.LENGTH_SHORT).show();
				}
				else {
				Intent intent = new Intent(getActivity(),
						SelectDoctorList.class);
				startActivity(intent);
				}
			}
		});
		// 预约挂号的点击事件
		mbtReservation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//更改挂号状态值，以便更改button
				if(RegInfo.getInstanse().getDepartment().equals("")||RegInfo.getInstanse().
						getDoctor().equals("")||RegInfo.getInstanse().getArea().
						equals("")||RegInfo.getInstanse().getHospital().equals(""))
				{
					Toast.makeText(getActivity(), "请确认信息完整", Toast.LENGTH_SHORT).show();
				}
				else {
				SharePreferenceUtil.getInstanse().putIntData(preferences, "pay_success", 0);
				Intent intent = new Intent(getActivity(), MainActivity.class);
				intent.putExtra("info", 1);
				startActivity(intent);}
			}
		});
		return registrationView;
	}

	/**
	 * 选择之后设文字
	 */
	private void setAllText() {
		String area = RegInfo.getInstanse().getArea();
		String hos = RegInfo.getInstanse().getHospital();
		String dept = RegInfo.getInstanse().getDepartment();
		String doc = RegInfo.getInstanse().getDoctor();

		if (!area.equals("")) {
			mtvSelArea.setText(area);
		}
		if (!hos.equals("")) {
			mtvSelectHos.setText(hos);
		}
		if(!dept.equals(""))
		{
			mtvSelDept.setText(dept);
		}
		if(!doc.equals(""))
		{
			mtvSelDoc.setText(doc);
		}
	}

	private void initView() {
		mtvSelectHos = (TextView) registrationView
				.findViewById(R.id.Tv_select_hosptical);
		mtvSelArea = (TextView) registrationView
				.findViewById(R.id.Tv_select_area);
		mtvSelDept = (TextView) registrationView
				.findViewById(R.id.Tv_Select_Department);
		mtvSelDoc = (TextView) registrationView
				.findViewById(R.id.Tv_select_doctor);
		mrlChooseArea = (RelativeLayout) registrationView
				.findViewById(R.id.rl_1);
		mSelectHos = (RelativeLayout) registrationView.findViewById(R.id.rl_2);
		mSelectDept = (RelativeLayout) registrationView.findViewById(R.id.rl_3);
		mSelectDoc = (RelativeLayout) registrationView.findViewById(R.id.rl_4);
		metSearch = (EditText) registrationView
				.findViewById(R.id.Et_Search_doc_Tips);
		mbtReservation = (Button) registrationView
				.findViewById(R.id.bt_reservation);
		mspinner = (Spinner) registrationView.findViewById(R.id.sp_doc_or_hos);
	}

	/**
	 * spinner内容的添加和获取
	 */
	private void setSpinnerAdapter() {
		data_list = new ArrayList<String>();
		data_list.add("    医生");
		data_list.add("    医院");

		// 适配器
		arr_adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.spinner_text, data_list);
		// 设置样式
		arr_adapter.setDropDownViewResource(R.layout.spinner_child_text);
		// 加载适配器
		mspinner.setAdapter(arr_adapter);
		// 设置默认值
		mspinner.setVisibility(View.VISIBLE);
		mspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				searchMethod = parent.getItemAtPosition(position).toString()
						.trim();
				if(searchMethod.equals("医院"))
				{
					metSearch.setHint("医院名称");
				}
				else if(searchMethod.equals("医生"))
				{
					metSearch.setHint("医生姓名");
				}
				Log.i("method", searchMethod);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				searchMethod = "医生";
			}
		});
	}

	/**
	 * 获取搜索方式和内容
	 * 
	 * @param back
	 */
	public void getSearchContent(CallBack back) {
		back.getResult(mHash);
	}

	/**
	 * 回调接口 传输搜索方式和内容
	 * 
	 * @author Mr.Codey
	 *
	 */
	public interface CallBack {
		public void getResult(HashMap<String, String> result);
	}
}
