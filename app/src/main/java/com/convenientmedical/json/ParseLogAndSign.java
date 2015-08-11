package com.convenientmedical.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
/**
 * 注册登录的状态值解析
 * @author Mr.Codey
 *
 */
public class ParseLogAndSign {
	private String jsonString;
	private JSONTokener jsonParser;
	private JSONObject object;

	/**
	 * 获取状态值
	 * 
	 * @param json
	 * @return
	 */
	public String getContent(String json) {
		jsonParser = new JSONTokener(json);
		try {
			object = (JSONObject) jsonParser.nextValue();
			return object.getString("status");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
