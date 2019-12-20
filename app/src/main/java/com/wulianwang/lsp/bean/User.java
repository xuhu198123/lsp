package com.wulianwang.lsp.bean;

import java.io.Serializable;

public class User implements Serializable {
    //用户名
    private String userName;
    //密码
    private String password;
    //注册时手机号
    private String phone;
    //邮箱
    private String email;
    //昵称
    private String nickName;
    //性别
    private String sex;
    //年龄
    private int age;
    //头像路径
    private String headImg;
    //职业(多职业用逗号隔开)
    private String profession;
    //是否实名认证 0未认证1已认证
    private String realName;
    //是否企业认证  0未认证1已认证
    private String realEnterprise;
    //是否用电信息认证  0未认证1已认证
    private String realElectro;
    //是否职业认证(职业) 0未认证1已认证
    private String realOccupation;

    private String beginTime;

    private String updateTime;

    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealEnterprise() {
        return realEnterprise;
    }

    public void setRealEnterprise(String realEnterprise) {
        this.realEnterprise = realEnterprise;
    }

    public String getRealElectro() {
        return realElectro;
    }

    public void setRealElectro(String realElectro) {
        this.realElectro = realElectro;
    }

    public String getRealOccupation() {
        return realOccupation;
    }

    public void setRealOccupation(String realOccupation) {
        this.realOccupation = realOccupation;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
