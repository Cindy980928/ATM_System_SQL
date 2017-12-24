package com.dao;

import java.util.ArrayList;

import com.bean.User;
import com.tools.AcountNum;
import com.tools.Input;
import com.tools.Time;

public class Bank {
    /**
     * 注册功能
     */
    public User singIn() {
        //在数据库中插入新空用户，自动生成ID序列号
        Userdao.createNewUser();
        //创建帐号对象
        User user_new = new User();
//		int serialnum=Userdao.getSerial();
        //使用ID序列号生成ID帐号
//		String id=AcountNum.get(serialnum);
        //创建ID
        //在数据库中添加帐号的ID
//		Userdao.addUser(id,serialnum);
        String id = Userdao.addUser();
        user_new.setId(id);
        //在数据库中创建该用户的日志表
        Userdao.createlog(id);

        System.out.println("开始用户注册，请输入你的名字：");
        //创建姓名
        user_new.setName(Input.getString());
        //更新数据库
        Userdao.updataUser(user_new);
        //创建密码
        user_new.setPassword(Input.getPassword());
        //输入身份证号
        System.out.println("开始用户注册，请输入你的名字：");
        //输入手机号
        user_new.setPhone_num(Input.getMobileNum());
        //在数据库中记录登录日志
        Userdao.addLogs(id, "于" + Time.getDateTimeNow() + "  创建帐号:" + id);
        //更新数据库
        Userdao.updataUser(user_new);
        System.out.println("用户创建成功，您的帐号id为:" + id + ",是否进行存款（y/n）:");
        //键入key判断是否预存款
        String key = Input.getY();

        if ("y".equals(key)) {
            System.out.println("请输入存款金额:");
            double prestore = Input.get(0.1);
            //创建预存款
            user_new.setBalance(prestore);
            //更新数据库
            Userdao.updataUser(user_new);
            System.out.println("预存款：" + prestore + " 元,操作成功!");
            //记录预存款日志
            Userdao.addLogs(id, "于" + Time.getDateTimeNow() + "  预存:" + prestore + " 元");
        }
        //记录第一次的登录
        Userdao.addLogs(id, "于" + Time.getDateTimeNow() + "  第一次登录系统。");
        System.out.println("注册完成！感谢使用本银行账户。");
        return user_new;
    }

    /**
     * 登录功能
     */
    public User login() {

        System.out.println("请输入帐号:");
        String id_login = Input.getString();
        User user_login = Userdao.findUser(id_login);
        System.out.println("请输入密码:");
        String pws_login = Input.getString();
        //判断账户是否存在、密码是否匹配
        if (user_login != null && user_login.getPassword().equals(pws_login)) {

            System.out.println("登录成功！当前帐号为：" + id_login);
            //记录登录日志
            Userdao.addLogs(user_login.getId(), "于" + Time.getDateTimeNow() + "  登录系统。");
        } else System.out.println("帐号或密码错误，登录失败。");
        return user_login;
    }

    /**
     * 存款功能
     */
    public void deposit(User user_LoginNow) {

        System.out.println("请输入存款金额：");
        double money_deposit = Input.get(0.1);
        //操作存款
        user_LoginNow.setBalance(user_LoginNow.getBalance() + money_deposit);
        //更新数据库
        Userdao.updataUser(user_LoginNow);
        //记录存款日志
        Userdao.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  存入:" + money_deposit + " 元。");
        System.out.println("存款成功");

    }

    /**
     * 取款功能
     */
    public void draw(User user_LoginNow) {

        System.out.println("请输入取款金额：");
        double money_draw = Input.get(0.1);
        //判断当前账户余额是否大于等于取款额
        if (user_LoginNow.getBalance() >= money_draw) {
            //取款操作
            user_LoginNow.setBalance(user_LoginNow.getBalance() - money_draw);
            //更新数据库
            Userdao.updataUser(user_LoginNow);
            //记录取款日志
            Userdao.addLogs(user_LoginNow.getId(), "与" + Time.getDateTimeNow() + " 取款:" + money_draw + " 元。");
            System.out.println("取款成功!");
        } else System.out.println("取款失败！余额不足。");

    }

    /**
     * 查询余额功能
     */
    public void search(User user_LoginNow) {
        System.out.println("您的余额为：" + user_LoginNow.getBalance() + " 元。");
    }

    /**
     * 转账功能
     */
    public void transfer(User user_LoginNow) {

        System.out.println("请输入转账的账户id:");
        String id_transfer = Input.getString();
        System.out.println("请输入转账账户的姓名:");
        String name_transfer = Input.getString();
        //从数据库查找转账帐号
        User user_transfer = Userdao.findUser(id_transfer);
        System.out.println("请输入转账金额：");
        double money_transfer = Input.get(0.1);
        //判断转账id与姓名是否相符
        if (user_transfer != null && user_transfer.getName().equals(name_transfer)) {
            //判断余额
            if (user_LoginNow.getBalance() >= money_transfer) {
                //操作转账
                user_transfer.setBalance(user_transfer.getBalance() + money_transfer);
                //更新数据库(被转账人)
                Userdao.updataUser(user_transfer);
                //记录被转账人日志
                Userdao.addLogs(user_transfer.getId(), "于" + Time.getDateTimeNow() + "  由账户:" + user_LoginNow.getId() + " " + user_LoginNow.getName() + "，转帐入：" + money_transfer + " 元。");
                //操作扣款
                user_LoginNow.setBalance(user_LoginNow.getBalance() - money_transfer);
                //更新数据库(本帐号)
                Userdao.updataUser(user_LoginNow);
                //记录本账号账转账日志
                Userdao.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  转出账户:" + user_transfer.getId() + " " + user_transfer.getName() + "," + money_transfer + " 元。");


                System.out.println("转账成功！向" + user_transfer.getName() + "转账:" + money_transfer + " 元。");
            } else System.out.println("转账失败，本账户余额不足!");
        } else System.out.println("转账帐号不存在或账户id与姓名不符。");

    }

    /**
     * 查询日志功能
     */
    public void showLogs(User user_LoginNow) {
        System.out.println("操作日志如下：");
        //获取日志
        ArrayList<String> logs = Userdao.getLog(user_LoginNow.getId());
        for (int i = 0; i < logs.size(); i++) {
            //按序号输出日志
            System.out.println(i + 1 + "." + logs.get(i));
        }
    }

    /**
     * 登出功能
     */
    public void Logout(User user_LoginNow) {
        //记录登出系统时间
        Userdao.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  登出系统。");
        //将当前登录清除
        user_LoginNow = null;
    }
}
