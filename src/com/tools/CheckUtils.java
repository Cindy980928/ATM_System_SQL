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
			String regExp ="^((12[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
			Pattern p=Pattern.compile(regExp);
			Matcher m=p.matcher(PhoneNum);
			return m.matches();
		}
	/**
	 * 判断是否符合email规范
	 * @param email 需要衍生的email字符串
	 * @return 符合返回true，否则返回false
	 */
	public static boolean isEmailLegal(String email){
		String regExp ="\\w+\\x40\\w+\\x2e\\w+";
		Pattern p=Pattern.compile(regExp);
		Matcher m=p.matcher(email);
		return m.matches();
	}
}
 