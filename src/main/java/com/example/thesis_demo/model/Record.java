package com.example.thesis_demo.model;

import com.opencsv.bean.CsvBindByPosition;

public class Record {

    @CsvBindByPosition(position = 0)
    private String nhomNganh;

    @CsvBindByPosition(position = 1)
    private String tenNganh;

    @CsvBindByPosition(position = 2)
    private String maNganh;

    @CsvBindByPosition(position = 3)
    private String tenTruong;

    @CsvBindByPosition(position = 4)
    private String maTruong;

    @CsvBindByPosition(position = 5)
    private String tohopMon;

    @CsvBindByPosition(position = 6)
    private String diemChuan;

    //getters setters
    public String getNhomNganh() {
        return nhomNganh;
    }

    public void setNhomNganh(String nhomNganh) {
        this.nhomNganh = nhomNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public String getTohopMon() {
        return tohopMon;
    }

    public void setTohopMon(String tohopMon) {
        this.tohopMon = tohopMon;
    }

    public String getDiemChuan() {
        return diemChuan;
    }

    public void setDiemChuan(String diemChuan) {
        this.diemChuan = diemChuan;
    }
}
