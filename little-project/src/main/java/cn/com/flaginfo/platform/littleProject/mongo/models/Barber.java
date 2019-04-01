package cn.com.flaginfo.platform.littleProject.mongo.models;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Barber")
public class Barber extends BaseMongoDbModel{

    @Indexed
    private String wxId;
    private String wxName;
    @Indexed
    private String mobile;
    private String name;
    private String nickName;
    private String password;
    // 0 女  1 男
    private Boolean gender;
    //字典项数据
    private String experience;
    //字典项数据
    private List<String> positionList;
    //字典项数据
    private List<String> metroList;
    private String address;
    //字典项数据
    private String currentSalary;
    //字典项数据
    private String expectSalary;
    private int age;
    private String remark;
    //0 离职 1 在职
    private Boolean isOnjob;

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<String> positionList) {
        this.positionList = positionList;
    }

    public Boolean getOnjob() {
        return isOnjob;
    }

    public void setOnjob(Boolean onjob) {
        isOnjob = onjob;
    }

    public List<String> getMetroList() {
        return metroList;
    }

    public void setMetroList(List<String> metroList) {
        this.metroList = metroList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(String currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(String expectSalary) {
        this.expectSalary = expectSalary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Barber{" +
                "wxId='" + wxId + '\'' +
                ", wxName='" + wxName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", experience='" + experience + '\'' +
                ", positionList=" + positionList +
                ", metroList=" + metroList +
                ", address='" + address + '\'' +
                ", currentSalary='" + currentSalary + '\'' +
                ", expectSalary='" + expectSalary + '\'' +
                ", age=" + age +
                ", remark='" + remark + '\'' +
                ", isOnjob=" + isOnjob +
                ", id='" + id + '\'' +
                '}';
    }
}
