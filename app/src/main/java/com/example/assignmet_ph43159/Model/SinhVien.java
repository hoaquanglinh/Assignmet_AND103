package com.example.assignmet_ph43159.Model;

public class SinhVien {
    private String tensv;
    private String masv;
    private Double diemtb;

    public SinhVien() {
    }

    public SinhVien(String tensv, String masv, Double diemtb) {
        this.tensv = tensv;
        this.masv = masv;
        this.diemtb = diemtb;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public Double getDiemtb() {
        return diemtb;
    }

    public void setDiemtb(Double diemtb) {
        this.diemtb = diemtb;
    }
}
