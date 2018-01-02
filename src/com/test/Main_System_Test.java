package com.test;

import com.menu.StartMenu;

import java.sql.SQLException;

public class Main_System_Test {
	/**
	 * 测试主函数
	 */
	public static void main(String[] args) throws SQLException {
		StartMenu menu=new StartMenu();
		menu.start();
	}

}
