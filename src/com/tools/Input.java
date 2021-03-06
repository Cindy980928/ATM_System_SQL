package com.tools;

import com.bean.User;
import com.dao.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 各种控制台输入方法包装类
 *
 * @author lubbe
 */
public class Input {
    /**
     * 获取[start,end] 范围内的整数
     */
    public static int get(int start, int end) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int num = 0;
        while (true) {
            while (true) {
                String str = sc.next();
                try {
                    num = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("输入的不是数字!，请重新输入");
                    continue;
                }
                break;
            }
            if (num > end || num < start) {
                System.out.println("无效操作，输入数字必须在 " + start + " 与 " + end + " 之间，请重新出入 :");
                continue;
            }
            break;//拿到了正确的区间值ֵ
        }

        return num;
    }

    /**
     * 键入"Y"或"N"
     *
     * @return 返回一个y或n
     */
    public static String getY() {
        @SuppressWarnings("resource")
        Scanner sc1 = new Scanner(System.in);
        String key = "y";
        while (true) {
            key = sc1.next();
            if (!key.equals("y") && !key.equals("n")) {
                System.out.println("无效操作，键入必须是 y 或  n ，请重新输入 :");
                continue;
            }
            break;
        }

        return key;
    }

    /**
     * 获取[start,end] 范围内的浮点数
     *
     * @param start 范围的起始
     * @param end   范围的结束值
     * @return 输入的范围内的浮点数
     */
    public static double get(double start, double end) {
        double num = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while (true) {
            while (true) {
                String str = sc.next();
                try {
                    num = Double.parseDouble(str);
                } catch (NumberFormatException e) {
                    System.out.println("输入的不是一位数字!，请重新输入");
                    continue;
                }
                break;
            }
            if (num > end || num < start) {
                System.out.println("输入有误，数字必须是" + start + " 与 " + end + " 之间，请重新选择操作:");
                continue;
            }
            break;
        }

        return num;
    }

    /**
     * 获取无范围浮点数
     *
     * @param a a等于0.1时可调用
     * @return 输入的浮点数
     */
    public static double get(double a) {
        double num = 0;
        if (a == 0.1) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            while (true) {
                String str = sc.next();
                try {
                    num = Double.parseDouble(str);
                } catch (NumberFormatException e) {
                    System.out.println("输入的不是数字!，请重新输入");
                    continue;
                }
                break;
            }
            return num;
        }
        return 0;
    }

    /**
     * 获取无范围整数
     *
     * @param a a等于1时可调用
     * @return 输入的整数
     */
    public static int get(int a) {
        int num = 0;
        if (a == 1) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            while (true) {
                String str = sc.next();
                try {
                    num = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                    System.out.println("输入的不是数字!，请重新输入");
                    continue;
                }
                break;
            }
            return num;
        }
        return 0;
    }

    /**
     * 获取输入字符串
     *
     * @return 字符串sc.next()；
     */
    public static String getString() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(true) {
            String name = sc.nextLine();
            if (name.equals(""))
                System.out.println("姓名不能为空！");
            else
                return name;
        }
    }

    /**
     * 获取合法中国大陆手机号码，需要使用到CheckUtils类
     *
     * @return 号码字符串
     */
    public static String getMobileNum() {
        System.out.println("请输入您的手机号码：");
        while (true) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            String num = sc.nextLine();
            if (num.equals(""))
                System.out.println("手机号不能为空！");
            else if (CheckUtils.isChinaPhoneLegal(num))
                return num;
            else System.out.println("输入的不是合法的中国大陆号码，请重新输入:");
        }
    }


    /**
     * 创建密码，带有重复验证功能
     *
     * @return 返回密码串
     */
    public static String getPassword() {
        String pwd = "";
        String pwd_rept = "";
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入密码:");
            while(true) {
                pwd = sc.nextLine();
                if (pwd.equals(""))
                    System.out.println("密码不能为空！");
                else if(!CheckUtils.isSix(pwd))
                    System.out.println("密码必须为6位数字！");
                else
                    break;
            }
            System.out.println("请再次输入密码:");
            pwd_rept = sc.nextLine();
            if (!pwd.equals(pwd_rept)&&!pwd.equals("")) {
                System.out.println("两次输入的密码不一致，请重新输入:");
            } else
                return pwd_rept;
        }
    }

    /**
     *获取合法身份证号
     *
     */
    public static String getID() throws SQLException {
        String num;
        System.out.println("请输入您的身份证号：");
        while (true) {
            Scanner sc = new Scanner(System.in);
            num = sc.nextLine();
            DBUtils dbutil=new DBUtils();
            String sql = "select ID_num from User WHERE ID_num= "+num+";";
            Statement statement = dbutil.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (!res.next()) {
                //res is null
                if (num.equals(""))
                    System.out.println("身份证号不能为空！请重新输入");
                else if (CheckUtils.isChinaIDCardNum(num))
                    return num;
                else
                    System.out.println("身份证号有误，请重新输入");
            } else {
                // res is not null
                System.out.println("该账号已被注册，请重新输入");
            }

        }
    }
    /*
    String sql = "select ID_num from User; ";
        DBUtils dbutil=new DBUtils();
        String id1=user_LoginNow.getId();
        String sql="delete  from User WHERE id='" + id1 + "'; ";//生成一条sql语句
        Statement stmt=dbutil.getConnection().createStatement();//创建Statement对象
        stmt.executeUpdate(sql);//执行sql语句
    *
    * Statement statement = conn.createStatement();
ResultSet res = statement.executeQuery(selectSql);
if (!res.next()) {
    //res is null
} else {
    // res is not null
}

    * */

    /**
     * 获取家庭住址
     */
    public static String getAdress(){
        String adress;
        System.out.println("请输入您的家庭住址");
        while(true) {
            Scanner sc = new Scanner(System.in);
            adress = sc.nextLine();
            if (adress.equals(""))
                System.out.println("地址不能为空！");
            else
                return adress;
        }
    }

    /**
     * 登录功能，直接提示输入帐号和密码，并返回一个String的数组，大小为2，下标为0的为帐号，为1的为密码
     *
     * @return 登录帐号和密码组成的String数组
     */
    public static String[] logIn() {
        String[] logIn = new String[2];
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入卡号:");
        logIn[0] = sc.next();
        System.out.println("请输入密码:");
        logIn[1] = sc.next();
        return logIn;
    }

    /**
     * 输入要注销的卡号
     */
    public static String getCard() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String card = sc.next();
            Statement statement = null;
            ResultSet rs = statement.executeQuery("select id FROM user");
            if (!rs.equals(card)) {
                System.out.println("卡号不存在");
            } else
                return card;
        }
    }
}
