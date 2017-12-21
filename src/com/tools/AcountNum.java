package com.tools;


public class AcountNum {
	/**
	 * 按照序列号生成6位帐号
	 * @param serialNum
	 * @return
	 */
	public static String get(int serialNum){
		return String.format("%06d", serialNum);
	}
}
