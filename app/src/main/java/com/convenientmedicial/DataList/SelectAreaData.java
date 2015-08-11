package com.convenientmedicial.DataList;

import java.util.ArrayList;
import java.util.List;

public class SelectAreaData {
	public SelectAreaData() {
	}

	public static List<String> getDataSource(int i) {
		List<String> list = new ArrayList<String>();
		if (i == 0) {
			list.add("杭州");
			list.add("宁波");
			list.add("温州");
			list.add("嘉兴");
			list.add("湖州");
			list.add("绍兴");
			list.add("金华");
			list.add("衢州");
			list.add("舟山");
			list.add("台州");
			list.add("丽水");
		} else if (i == 1) {

			list.add("杭州市第一人民医院");
			list.add("杭州市中医院");
			list.add("杭州师范大学附属医院（杭州市二医院）");
			list.add("浙江绿城心血管病医院");
			list.add("杭州市第三人民医院");
			list.add("杭州市妇产科医院");
			list.add("杭州市西溪医院(市六医院)");
			list.add("杭州市儿童医院医院");
			list.add("杭州市第七人民医院");
			list.add("杭州市红十字会医院");
			list.add("杭州疗养院");
			list.add("杭州市肿瘤医院(市一集团吴山院区)");
			list.add("杭州口腔医院城西分院");
			list.add("桐君堂(武林馆)");
			list.add("杭州孙泰和中医门诊部");
			list.add("杭州万承志堂高银街中医门诊部");
			list.add("杭州天目山妇产医院");
			list.add("桐君堂(城站馆)");
			list.add("杭州虹桥医院");
			list.add("杭州市余杭区第一人民医院");
			list.add("杭州市萧山区第一人民医院");
			list.add("杭州钢铁集团公司职工医院");
			list.add("杭州市余杭区第三人民医院");
			list.add("桐庐县中医院");
			list.add("桐庐县妇幼保健院");
			list.add("杭州整形医院");
			list.add("浙江萧山医院");
			list.add("杭州市余杭区妇幼保健院");
			list.add("杭州市萧山区中医院");
			list.add("杭州市萧山区第四人民医院");
			list.add("杭州市余杭区中医院");
		}
		else if (i == 2) {

			list.add("王医生");
			list.add("李医生");
			list.add("陈医生");
			list.add("曹医生");
			list.add("张医生");
			list.add("戴医生");
			list.add("戴医生");
			list.add("史医生");
			list.add("赵医生");
			list.add("钱医生");
			list.add("孙医生");
			list.add("周医生");
		}
		return list;
	}
}
