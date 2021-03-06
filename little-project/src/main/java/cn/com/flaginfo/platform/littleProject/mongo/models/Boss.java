package cn.com.flaginfo.platform.littleProject.mongo.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Boss")
public class Boss extends BaseMongoDbModel{
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
    private int leftCount;
    private List<String> barberList;
    private List<String> cartList;

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

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public List<String> getBarberList() {
        return barberList;
    }

    public void setBarberList(List<String> barberList) {
        this.barberList = barberList;
    }

    public List<String> getCartList() {
        return cartList;
    }

    public void setCartList(List<String> cartList) {
        this.cartList = cartList;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "wxId='" + wxId + '\'' +
                ", wxName='" + wxName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", leftCount=" + leftCount +
                ", barberList=" + barberList +
                ", cartList=" + cartList +
                ", id='" + id + '\'' +
                '}';
    }
}
