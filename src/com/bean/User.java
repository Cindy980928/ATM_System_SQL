package com.bean;

import java.util.Objects;

public class User {
	private String name="";                   //姓名
	private String id="";                     //卡号
	private String password="";              //密码
	private String age="";                   //年龄
    private String ID_num="";               //身份证号
    private String sex="";                  //性别
    private String phone_num="";           //电话号码
    private String registered_city="";    //注册城市
    private double balance=0;              //余额
	//getter and setter


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getID_num() {
		return ID_num;
	}

	public void setID_num(String ID_num) {
		this.ID_num = ID_num;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getRegistered_city() {
		return registered_city;
	}

	public void setRegistered_city(String registered_city) {
		this.registered_city = registered_city;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	//构造器
	public User() {
	}
	
//	public User(String name, String id, String password, double balance) {
//		this.name = name;
//		this.id = id;
//		this.password = password;
//		this.balance = balance;
//	}

    public User(String name, String id, String password, String age, String ID_num, String sex, String phone_num, String registered_city, double balance) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
        this.ID_num = ID_num;
        this.sex = sex;
        this.phone_num = phone_num;
        this.registered_city = registered_city;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 &&
                Objects.equals(name, user.name) &&
                Objects.equals(id, user.id) &&
                Objects.equals(password, user.password) &&
                Objects.equals(age, user.age) &&
                Objects.equals(ID_num, user.ID_num) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(phone_num, user.phone_num) &&
                Objects.equals(registered_city, user.registered_city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, password, age, ID_num, sex, phone_num, registered_city, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", ID_num='" + ID_num + '\'' +
                ", sex='" + sex + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", registered_city='" + registered_city + '\'' +
                ", balance=" + balance +
                '}';
    }
}
