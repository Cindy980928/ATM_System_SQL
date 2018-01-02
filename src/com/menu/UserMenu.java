package com.menu;

import com.bean.User;
import com.dao.Bank;
import com.tools.Input;

import java.sql.SQLException;

public class UserMenu {
	/**
	 * 进入用户菜单
	 */
	public void UserStart(User user_LoginNow) throws SQLException {
		Bank bank=new Bank();
	boolean flag=true;

	//若登录不成功返回上一级菜单
	if(user_LoginNow == null)
		flag=false;
	int key=0;

	while(flag){
		System.out.println("********用户菜单*************");
		System.out.println("当前账户为:"+user_LoginNow.getId()+"  :");
		System.out.print("1.查询余额");
		System.out.print("  2.取款");
		System.out.print("  3.存款");
		System.out.print("  4.转账");
		System.out.print("  5.查询日志");
		System.out.print("  6.查询信息");
		System.out.print("  7.修改信息");
		System.out.print("  8.修改密码");
		System.out.print("  9.注销账户");
		System.out.print("  10.退出\n");
		System.out.println("**************************");
		System.out.print("请输入操作功能:");
		//键入操作功能
		key=Input.get(1,10);
		
		switch(key){
		case 1:
			bank.search(user_LoginNow);//查询余额
			break;
		case 2:
			bank.draw(user_LoginNow);//取款
			break;
		case 3:
			bank.deposit(user_LoginNow);//存款
			break;
		case 4:
			bank.transfer(user_LoginNow);//转账
			break;
		case 5:
			bank.showLogs(user_LoginNow);//查询日志
			break;
        case 6:
            bank.showMessage(user_LoginNow);//查询信息
            break;
		case 7:
				bank.changeMessage(user_LoginNow);//修改信息
				break;
		case 8:
				bank.changePassword(user_LoginNow);//修改密码
				break;
		case 9:
				bank.unsubscribe(user_LoginNow);//注销账户
			    bank.Logout(user_LoginNow);//退出
			    flag=false;
				break;
		case 10:
			bank.Logout(user_LoginNow);//退出
			flag=false;
			break;
		}
	}
	}

}
