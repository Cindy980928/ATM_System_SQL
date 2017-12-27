package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bean.User;


public class UserOperation {
    /**
     * 根据id创建log表
     *
     * @param id 指定要创建的的id号
     */
    public static void createlog(String id) {
        //获取连接
        Connection conn = DBUtils.getConnection();
        //创建sql语句
        String sql = "CREATE TABLE IF NOT EXISTS " + id + "Logs" + "(log varchar(255));";
        //创建statement
        Statement stat = null;
        try {
            //获取statement
            stat = conn.createStatement();
            //执行
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入空帐号,用于注册
     */
    public static void createNewUser() {
        Connection conn = DBUtils.getConnection();
//        String sql = "INSERT User (id,name,password,balance) VALUE(null,null,null,null);";
        String sql = "INSERT User (id,name,sex,ID_num,age,registered_city,adress,phone_num,password,balance) VALUE(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查找数据库中的帐号
     *
     * @return 返回对应id帐号的User对象，如果不存在返回null
     */
    public static User findUser(String id) {
        if (id == null) {
            return null;
        }
        Connection conn = DBUtils.getConnection();
        String sql = "select * from User WHERE id='" + id + "'; ";
        Statement stat = null;
        User u = new User();
        try {
            stat = conn.createStatement();
            //执行SQL语句
            ResultSet rs = stat.executeQuery(sql);//executeQuery 该方法用来执行查询语句

            while (rs.next()) {

                u.setId(rs.getString("id"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setBalance(rs.getDouble("balance"));
                u.setAge(rs.getString("age"));
                u.setAdress(rs.getString("adress"));
                u.setRegistered_city(rs.getString("registered_city"));
                u.setID_num(rs.getString("ID_num"));
                u.setPhone_num(rs.getString("phone_num"));
                u.setSex(rs.getString("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (u.getId().equals(" ")) return null;
        else return u;
    }

    /**
     * 设置空帐号ID，用于注册，正式添加用户
     */
    public static String addUser() {
        Connection conn = DBUtils.getConnection();
        String sql_data = "SELECT * FROM User WHERE name IS NULL;";
        Statement stat = null;
        String id = "";
        try {
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql_data);
            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 更新指定user的所有数据
     *
     * @param user 已设定好数据的user对象
     */
    public static void updataUser(User user) {
        Connection conn = DBUtils.getConnection();
        String sql_data =
                "UPDATE User SET name='" + user.getName() +
                        "' ,password='" + user.getPassword() +
                        "' ,balance='" + user.getBalance() +
                        "' ,ID_num ='" + user.getID_num() +
                        "' ,phone_num ='" + user.getPhone_num() +
                        "' ,registered_city ='" + user.getRegistered_city() +
                        "' ,age ='" + user.getAge() +
                        "' ,sex='" + user.getSex() +
                        "' WHERE id = " + user.getId() + ";";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(sql_data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为指定Id帐号添加一条日志记录
     *
     * @param id  要添加日志的id
     * @param str 日志内容
     */
    public static void addLogs(String id, String str) {
        Connection conn = DBUtils.getConnection();
        String sql = "INSERT " + id + "logs VALUES('" + str + "');";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id 获取日志内容
     *
     * @param id 要获取日志的ID
     * @return 返回储存字符串类型的List集合
     */
    public static ArrayList<String> getLog(String id) {
        ArrayList<String> logList = new ArrayList<String>();
        Connection conn = DBUtils.getConnection();
        String sql = "select * from " + id + "logs ;";
        Statement stat = null;
        try {
            stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                String log = rs.getString("log");
                logList.add(log);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logList;
    }

    /**
     * 获取空账号的ID序列，用于注册，定位空帐号
     *
     * @return 空账号的ID序列号
     */
    public static int getSerial() {
        int serialNum = 0;
        Connection conn = DBUtils.getConnection();
        String sql = "SELECT * FROM User WHERE id IS NULL ;";
        Statement stat = null;
        try {
            stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

//                serialNum = rs.getInt("serialNum");
                serialNum = rs.getInt("id");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serialNum;
    }


}
