package com.example.assignmet_ph43159.Model;

public class Cay {
    private String _id;
    private String anh;
    private String ten;
    private double gia;
    private String kichthuoc;

    public Cay() {
    }

    public Cay(String ten, double gia, String kichthuoc) {
        this.ten = ten;
        this.gia = gia;
        this.kichthuoc = kichthuoc;
    }

    public Cay(String anh, String ten, double gia, String kichthuoc) {
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.kichthuoc = kichthuoc;
    }

    public Cay(String _id, String anh, String ten, double gia, String kichthuoc) {
        this._id = _id;
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.kichthuoc = kichthuoc;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }
}
