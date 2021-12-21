package cn.ty.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;


public class User implements Serializable {
    public User() {
    }

    public User(String uname, String upass) {
        this.uname = uname;
        this.upass = upass;
    }

    private int id;

    private String uname;

    private String upass;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdate;

    private String photo;

    private String phone;

    private String address;

    private String data1;

    private String data2;

    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass == null ? null : upass.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1 == null ? null : data1.trim();
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2 == null ? null : data2.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uname=").append(uname);
        sb.append(", upass=").append(upass);
        sb.append(", createdate=").append(createdate);
        sb.append(", photo=").append(photo);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", data1=").append(data1);
        sb.append(", data2=").append(data2);
        sb.append("]");
        return sb.toString();
    }
}