package com.tools;


public class AcountNum {
	/**
	 * �������к�����6λ�ʺ�
	 * @param serialNum
	 * @return
	 */
	public static String get(int serialNum){
		return String.format("%06d", serialNum);
	}
}
