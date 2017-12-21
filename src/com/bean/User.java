package com.bean;

public class User {
	private String name="";                   //姓名
	private String id="";
	private String password="";              //密码
	private String age="";                   //年龄
    private String ID_num="";               //身份证号
    private String sex="";                  //性别
    private String phone_num="";           //电话号码
    private String registered_city="";    //注册城市
    private double balance=0;              //余额
    public void setAge(String age) {
        this.age = age;
    }

    public void setID_num(String ID_num) {
        this.ID_num = ID_num;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setRegistered_city(String registered_city) {
        this.registered_city = registered_city;
    }

    public String getAge() {
        return age;
    }

    public String getID_num() {
        return ID_num;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getRegistered_city() {
        return registered_city;
    }

	//构造器
	public User() {
		super();
	}
	
	public User(String name, String id, String password, double balance) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.balance = balance;
		
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(balance);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		return result;
//	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", password=" + password + ", balance=" + balance + "]";
	}
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
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
