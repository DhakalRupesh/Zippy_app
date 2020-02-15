package com.example.zippy.model;

public class Postm {
    private int ad_image;
    private String negociable;
    private String priceofdelivery;
    private String sendfrom;
    private String vehicleneed;
    private String goodstype;

    public Postm(int ad_image, String negociable, String priceofdelivery, String sendfrom, String vehicleneed, String goodstype) {
        this.ad_image = ad_image;
        this.negociable = negociable;
        this.priceofdelivery = priceofdelivery;
        this.sendfrom = sendfrom;
        this.vehicleneed = vehicleneed;
        this.goodstype = goodstype;
    }

    public int getAd_image() {
        return ad_image;
    }

    public void setAd_image(int ad_image) {
        this.ad_image = ad_image;
    }

    public String getNegociable() {
        return negociable;
    }

    public void setNegociable(String negociable) {
        this.negociable = negociable;
    }

    public String getPriceofdelivery() {
        return priceofdelivery;
    }

    public void setPriceofdelivery(String priceofdelivery) {
        this.priceofdelivery = priceofdelivery;
    }

    public String getSendfrom() {
        return sendfrom;
    }

    public void setSendfrom(String sendfrom) {
        this.sendfrom = sendfrom;
    }

    public String getVehicleneed() {
        return vehicleneed;
    }

    public void setVehicleneed(String vehicleneed) {
        this.vehicleneed = vehicleneed;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }
}
