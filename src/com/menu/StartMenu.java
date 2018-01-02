package com.menu;

import com.bean.Admin;
import com.dao.Bank;
import com.tools.Input;

import java.sql.SQLException;

public class StartMenu {

	public void start() throws SQLException {
		
		boolean flag=true;
		Bank bank=new Bank();
		UserMenu menu=new UserMenu();
		int key=0;
		while(flag){
		System.out.println("******************************");
		System.out.println();
		System.out.println("      欢迎使用本行ATM");
		System.out.println();
		System.out.println("******************************\n");
		System.out.println("         1.注册");
		System.out.println("         2.登录");
		System.out.println("         3.退出");
		System.out.println("******************************");
		System.out.println("请输入操作功能：");
		//键入选择
		key=Input.get(1, 4);
		
		switch(key){
		case 1: 
			menu.UserStart(bank.singIn());// 注册
			break;
		case 2: 
			menu.UserStart(bank.login());//登录
			break;
		case 3: 
			flag=false;
			System.out.println("感谢使用本银行系统，系统关闭！");//退出
			break;
		}
			
		}
	}
}
