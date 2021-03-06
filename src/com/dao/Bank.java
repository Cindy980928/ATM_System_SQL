package com.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.bean.User;
import com.menu.UserMenu;
import com.tools.CheckUtils;
import com.tools.Input;
import com.tools.Time;

public class Bank {
    /**
     * 注册功能
     */
    public User singIn() throws SQLException {
        //创建帐号对象
        User user_new = new User();
        user_new.setID_num(Input.getID());
        //在数据库中插入新空用户，自动生成ID序列号
        UserOperation.createNewUser();
        String id = UserOperation.addUser();
        user_new.setId(id);
        //在数据库中创建该用户的日志表
        UserOperation.createlog(id);

        System.out.println("开始用户注册，请输入你的名字：");
        //创建姓名
        user_new.setName(Input.getString());
        //更新数据库
//        user_new.setID_num(Input.getID());
        user_new.setAge();
        user_new.setSex();
        UserOperation.updataUser(user_new);
        //创建密码
        user_new.setPassword(Input.getPassword());
        //输入身份证号,并自动生成性别和年龄信息

        //输入手机号
        user_new.setPhone_num(Input.getMobileNum());
        user_new.setRegistered_city("杭州");
        //输入家庭住址
        user_new.setAdress(Input.getAdress());
        //在数据库中记录登录日志
        UserOperation.addLogs(id, "于" + Time.getDateTimeNow() + "  创建帐号:" + id);
        //更新数据库
        UserOperation.updataUser(user_new);
        System.out.println("用户创建成功，您的帐号id为:" + id + ",是否进行存款（y/n）:");
        //键入key判断是否预存款
        String key = Input.getY();

        if ("y".equals(key)) {
            System.out.println("请输入存款金额:");
            double prestore = Input.get(0.1);
            //创建预存款
            user_new.setBalance(prestore);
            //更新数据库
            UserOperation.updataUser(user_new);
            System.out.println("预存款：" + prestore + " 元,操作成功!");
            //记录预存款日志
            UserOperation.addLogs(id, "于" + Time.getDateTimeNow() + "  预存:" + prestore + " 元");
        }
        //记录第一次的登录
        UserOperation.addLogs(id, "于" + Time.getDateTimeNow() + "  第一次登录系统。");
        System.out.println("注册完成！感谢使用本银行账户。");
        return user_new;
    }

    /**
     * 登录功能
     */

    public User login() {
        System.out.println("请输入帐号:");
        String id_login = Input.getString();
        User user_login = UserOperation.findUser(id_login);
        System.out.println("请输入密码:");
        String pws_login = Input.getString();
        //判断账户是否存在、密码是否匹配
        if (user_login != null && user_login.getPassword().equals(pws_login)) {
            System.out.println("登录成功！当前帐号为：" + id_login);
            //记录登录日志
            UserOperation.addLogs(user_login.getId(), "于" + Time.getDateTimeNow() + "  登录系统。");
        } else {
            System.out.println("帐号或密码错误，登录失败。");
        }
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
        UserOperation.updataUser(user_LoginNow);
        //记录存款日志
        UserOperation.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  存入:" + money_deposit + " 元。");
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
            UserOperation.updataUser(user_LoginNow);
            //记录取款日志
            UserOperation.addLogs(user_LoginNow.getId(), "与" + Time.getDateTimeNow() + " 取款:" + money_draw + " 元。");
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
        User user_transfer = UserOperation.findUser(id_transfer);
        System.out.println("请输入转账金额：");
        double money_transfer = Input.get(0.1);
        //判断转账id与姓名是否相符
        if (user_transfer != null && user_transfer.getName().equals(name_transfer)) {
            //判断余额
            if (user_LoginNow.getBalance() >= money_transfer) {
                //操作转账
                user_transfer.setBalance(user_transfer.getBalance() + money_transfer);
                //更新数据库(被转账人)
                UserOperation.updataUser(user_transfer);
                //记录被转账人日志
                UserOperation.addLogs(user_transfer.getId(), "于" + Time.getDateTimeNow() + "  由账户:" + user_LoginNow.getId() + " " + user_LoginNow.getName() + "，转帐入：" + money_transfer + " 元。");
                //操作扣款
                user_LoginNow.setBalance(user_LoginNow.getBalance() - money_transfer);
                //更新数据库(本帐号)
                UserOperation.updataUser(user_LoginNow);
                //记录本账号账转账日志
                UserOperation.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  转出账户:" + user_transfer.getId() + " " + user_transfer.getName() + "," + money_transfer + " 元。");


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
        ArrayList<String> logs = UserOperation.getLog(user_LoginNow.getId());
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
        UserOperation.addLogs(user_LoginNow.getId(), "于" + Time.getDateTimeNow() + "  登出系统。");
        //将当前登录清除
        user_LoginNow = null;
    }

    //查询信息
    public void showMessage(User user_LoginNow){
        System.out.println("卡号： "+user_LoginNow.getId());
        System.out.println("姓名： "+user_LoginNow.getName());
        System.out.println("性别： "+user_LoginNow.getSex());
        System.out.println("年龄： "+user_LoginNow.getAge());
        System.out.println("身份证： "+user_LoginNow.getID_num());
        System.out.println("家庭住址： "+user_LoginNow.getAdress());
        System.out.println("注册城市： "+user_LoginNow.getRegistered_city());
        System.out.println("手机号码： "+user_LoginNow.getPhone_num());
    }

    //修改信息
    public void changeMessage(User user_LoginNow){
        System.out.println("请输入要修改的手机号：");
        Scanner in=new Scanner(System.in);
        String num=in.next();
        while(!CheckUtils.isChinaPhoneLegal(num)){
            System.out.println("输入的不是合法的中国大陆号码，请重新输入:");
            num=in.next();
        }
        user_LoginNow.setPhone_num(num);
        System.out.println("请输入要修改的家庭住址：");
        String adress=in.next();
        user_LoginNow.setAdress(adress);
        System.out.println("修改成功！");
        //更新数据库
        UserOperation.updataUser(user_LoginNow);

    }

    /*
    *修改密码
     */
    public void changePassword(User user_LoginNow){
        System.out.println("请输入新密码：");
        Scanner in=new Scanner(System.in);
        String password=in.next();
        while(!CheckUtils.isSix(password)){
            System.out.println("密码必须为六位数字");
            password=in.next();
        }
        user_LoginNow.setPassword(password);
        System.out.println("密码修改成功！");
        //更新数据库
        UserOperation.updataUser(user_LoginNow);
    }

    /*
    *  注销账户
    */
    public void unsubscribe(User user_LoginNow) throws SQLException {
        System.out.println("请输入密码");
        Scanner in=new Scanner(System.in);
        String password=in.next();
        while(!password.equals(user_LoginNow.getPassword()))
        {
            System.out.println("密码错误，请重新输入");
            password=in.next();
        }

        DBUtils dbutil=new DBUtils();
        String id1=user_LoginNow.getId();
        String sql="delete  from User WHERE id='" + id1 + "'; ";//生成一条sql语句
        Statement stmt=dbutil.getConnection().createStatement();//创建Statement对象
        stmt.executeUpdate(sql);//执行sql语句
        System.out.println("注销成功");
        //        String sql = "select * from User WHERE id='" + id + "'; ";
    }
}


