package com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 检查工具，检查获取的字符串是否符合特定格式
 * @author lubbe
 *
 */
public class CheckUtils {
	/**
	 * 判断是否符合中国大陆手机号码规范
	 * @param PhoneNum 需要验证的号码字符串
	 * @return 符合返回true，否则返回false
	 */
	public static boolean isChinaPhoneLegal(String PhoneNum){
			String regExp ="^1((3[0-9]|4[57]|5[0-35-9]|7[0678]|8[0-9])\\d{8}$)";
			Pattern p=Pattern.compile(regExp);
			Matcher m=p.matcher(PhoneNum);
			return m.matches();
		}
	/**
	 *
	 *
	 */
	public static boolean isChinaIDCardNum(String IDNum) {
		String regExp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(IDNum);
		return m.matches();
	}
}
 