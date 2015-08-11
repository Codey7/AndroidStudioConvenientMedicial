package com.convenientmedical.frag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.convenientmedical.adapter.IllDetailsListAdapter;
import com.convenientmedical.main.R;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author Mr.Codey 资讯fragment
 */
public class Diagnoses extends Fragment implements OnClickListener {
	private View diagView;
	// private LinearLayout mLinClick1,mLinClick2,mLinClick3;
	private ListView mCheckListView, mMedicalListView;
	private List<HashMap<String, Object>> mCheckList, mMedicalList;
	private HashMap<String, Object> mCheckHash, mMedicalHash;
	private LinearLayout mlIllDetails, mlMedicalDetails;
	private RelativeLayout mlCheckDetails;
	private RelativeLayout mrlIllDetails, mrlCheckDetails, mrlMedicalDetails;
	private ImageView arrow1, arrow2, arrow3;
	private TextView mtvQuene,mtvNow;

	// private ExpandableListView mexpandList;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		diagView = inflater.inflate(R.layout.diagnoses, container, false);
		mCheckList = new ArrayList<HashMap<String, Object>>();
		mMedicalList = new ArrayList<HashMap<String, Object>>();

		return diagView;
	}

	private void initView() {
		/*
		 * mLinClick1=(LinearLayout)diagView.findViewById(R.id.lin_click1);
		 * mLinClick2=(LinearLayout)diagView.findViewById(R.id.lin_click2);
		 * mLinClick3=(LinearLayout)diagView.findViewById(R.id.lin_click3);
		 * mLinClick1.setOnClickListener(this);
		 * mLinClick2.setOnClickListener(this);
		 * mLinClick3.setOnClickListener(this);
		 */
		arrow1 = (ImageView) diagView.findViewById(R.id.arrow);
		arrow2 = (ImageView) diagView.findViewById(R.id.arrow_2);
		arrow3 = (ImageView) diagView.findViewById(R.id.arrow_3);
		mCheckListView = (ListView) diagView.findViewById(R.id.check_list);
		mMedicalListView = (ListView) diagView.findViewById(R.id.medical_list);
		mlIllDetails = (LinearLayout) diagView.findViewById(R.id.ill_details);
		mlCheckDetails = (RelativeLayout) diagView
				.findViewById(R.id.check_details);
		mlMedicalDetails = (LinearLayout) diagView
				.findViewById(R.id.medical_details);
		/*
		 * mlIllDetails.setOnClickListener(this);
		 * mlCheckDetails.setOnClickListener(this);
		 * mlMedicalDetails.setOnClickListener(this);
		 * 
		 * 
		 */
		
		mtvQuene=(TextView)diagView.findViewById(R.id.quene_ing);
		mtvNow=(TextView)diagView.findViewById(R.id.now_ing);
		
		mrlIllDetails = (RelativeLayout) diagView.findViewById((R.id.rl_t1));
		mrlCheckDetails = (RelativeLayout) diagView.findViewById((R.id.rl_t2));
		mrlMedicalDetails = (RelativeLayout) diagView
				.findViewById((R.id.rl_t3));
		mrlIllDetails.setOnClickListener(this);
		mrlCheckDetails.setOnClickListener(this);
		mrlMedicalDetails.setOnClickListener(this);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		mlIllDetails.setVisibility(View.GONE);
		mlCheckDetails.setVisibility(View.GONE);
		mlMedicalDetails.setVisibility(View.GONE);
		setCheckData();
		setMedicalData();
		setCheckListAdapter();
		setMedicalListAdapter();
		
		//设置
		setQuene();
		
		

	}
	
	private void setQuene()
	{
		
		Handler  handler=new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mtvNow.setText("您当前不在队列中");
				mtvQuene.setVisibility(View.GONE);
			}
		}, 15000);
		
		
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(5000);
					mtvNow.setText("您当前不在队列中");
					mtvQuene.setVisibility(View.GONE);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();*/
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_t1:
			if (mlIllDetails.getVisibility() == View.VISIBLE) {
				mlIllDetails.setVisibility(View.GONE);
				arrow1.setImageResource(R.drawable.arrow_black_right);

			} else if (mlIllDetails.getVisibility() == View.GONE) {
				mlIllDetails.setVisibility(View.VISIBLE);
				arrow1.setImageResource(R.drawable.arrow_black_down);
			}

			break;
		case R.id.rl_t2:
			if (mlCheckDetails.getVisibility() == View.VISIBLE) {
				mlCheckDetails.setVisibility(View.GONE);
				arrow2.setImageResource(R.drawable.arrow_black_right);
			} else if (mlCheckDetails.getVisibility() == View.GONE) {
				mlCheckDetails.setVisibility(View.VISIBLE);
				arrow2.setImageResource(R.drawable.arrow_black_down);
			}
			break;
		case R.id.rl_t3:
			if (mlMedicalDetails.getVisibility() == View.VISIBLE) {
				mlMedicalDetails.setVisibility(View.GONE);
				arrow3.setImageResource(R.drawable.arrow_black_right);
			} else if (mlMedicalDetails.getVisibility() == View.GONE) {
				mlMedicalDetails.setVisibility(View.VISIBLE);
				arrow3.setImageResource(R.drawable.arrow_black_down);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 设置检查单的数据源
	 */
	private void setCheckData() {
		mCheckHash = new HashMap<String, Object>();
		mCheckHash.put("project", "CT");
		mCheckHash.put("time", "7/19 9:00");
		mCheckHash.put("queue", "8");
		mCheckList.add(mCheckHash);

		mCheckHash = new HashMap<String, Object>();
		mCheckHash.put("project", "验血");
		mCheckHash.put("time", "7/19 9:00");
		mCheckHash.put("queue", "1");
		mCheckList.add(mCheckHash);

		mCheckHash = new HashMap<String, Object>();
		mCheckHash.put("project", "B超");
		mCheckHash.put("time", "7/19 9:00");
		mCheckHash.put("queue", "10");
		mCheckList.add(mCheckHash);

		mCheckHash = new HashMap<String, Object>();
		mCheckHash.put("project", "X光");
		mCheckHash.put("time", "7/19 9:01");
		mCheckHash.put("queue", "12");
		mCheckList.add(mCheckHash);
	}

	/**
	 * 设置药品的数据源
	 */
	private void setMedicalData() {

		mMedicalHash = new HashMap<String, Object>();
		mMedicalHash.put("name", "雷尼替丁");
		mMedicalHash.put("quantity", "1");
		mMedicalHash.put("price", "￥15");
		mMedicalList.add(mMedicalHash);

		mMedicalHash = new HashMap<String, Object>();
		mMedicalHash.put("name", "吗丁啉");
		mMedicalHash.put("quantity", "1");
		mMedicalHash.put("price", "￥8");
		mMedicalList.add(mMedicalHash);
		
		mMedicalHash = new HashMap<String, Object>();
		mMedicalHash.put("name", "霍香正气丸");
		mMedicalHash.put("quantity", "5");
		mMedicalHash.put("price", "￥5");
		mMedicalList.add(mMedicalHash);
		
		mMedicalHash = new HashMap<String, Object>();
		mMedicalHash.put("name", "东科胃复宁胶囊");
		mMedicalHash.put("quantity", "1");
		mMedicalHash.put("price", "￥20");
		mMedicalList.add(mMedicalHash);
	}

	private void setCheckListAdapter() {
		IllDetailsListAdapter adapter = new IllDetailsListAdapter(
				getActivity(), mCheckList);
		mCheckListView.setAdapter(adapter);
		setListViewHeightBasedOnChildren(mCheckListView);
		/*
		 * SimpleAdapter adapter= new SimpleAdapter(getActivity(), mCheckList,
		 * R.layout.ill_details_list, new String[]{"project","time","queue"},
		 * new int[]{R.id.tv_project,R.id.tv_time,R.id.tv_queue});
		 * mCheckListView.setAdapter(adapter);
		 */
	}

	private void setMedicalListAdapter() {
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), mMedicalList,
				R.layout.medical_details_list, new String[] { "name",
						"quantity", "price" }, new int[] { R.id.tv_name,
						R.id.tv_quantity, R.id.tv_price });
		mMedicalListView.setAdapter(adapter);
		setListViewHeightBasedOnChildren(mMedicalListView);
	}

	public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}

}
