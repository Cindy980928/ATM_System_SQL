package com.tools;

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
     *
     * @param start 范围的起始
     * @param end   范围的结束值
     * @return Int型的所输入的值
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
                System.out.println("无效操作，键入必须是 y 或  n ，请重新出入 :");
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
                    System.out.println("输入的不是数字!，请重新输入");
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
        return sc.next();
    }

    /**
     * 获取合法中国大陆手机号码，需要使用到CheckUtils类
     *
     * @return 号码字符串
     */
    public static String getMobileNum() {
        String num = null;
        System.out.println("请输入你的手机号码：");
        while (true) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            num = sc.next();
            if (CheckUtils.isChinaPhoneLegal(num))
                break;
            else System.out.println("输入的不是合法的中国大陆号码，请重新输入:");
        }
        return num;
    }


    /**
     * 创建密码，带有重复验证功能
     *
     * @return 返回密码串
     */
    @SuppressWarnings("resource")
    public static String getPassword() {
        String pwd = null;
        String pwd_rept = null;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入密码:");
            pwd = sc.next();
            System.out.println("请再次输入密码:");
            pwd_rept = sc.next();
            if (!pwd.equals(pwd_rept)) {
                System.out.println("两次输入的密码不一致，请重新输入:");
            } else
                return pwd_rept;
        }
    }

    /**
     *
     *
     */
    public static String getID() {
        String num;
        System.out.println("请输入你的身份证号：");
        while (true) {
            Scanner sc = new Scanner(System.in);
            num = sc.next();
            if (CheckUtils.isChinaIDCardNum(num))
                return num;
            System.out.println("身份证号有误，请重新输入");
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
        System.out.println("请输入帐号:");
        logIn[0] = sc.next();
        System.out.println("请输入密码:");
        logIn[1] = sc.next();
        return logIn;
    }
}
