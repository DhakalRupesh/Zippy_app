package com.example.zippy.model;

public class Advertise {
    private String postedby;
    private String pstedbyName;
    private String contactno;
    private String contactemail;
    private String goodstype;
    private String vehicleneed;
    private String sendfrom;
    private String destinationofdelivery;
    private String priceofdelivery;
    private String negociable;
    private String ad_image;
    private Boolean statusofdelivery;

    public Advertise(String postedby, String contactno, String contactemail, String goodstype, String vehicleneed, String sendfrom, String destinationofdelivery, String priceofdelivery, String negociable, String ad_image, Boolean statusofdelivery) {
        this.postedby = postedby;
        this.contactno = contactno;
        this.contactemail = contactemail;
        this.goodstype = goodstype;
        this.vehicleneed = vehicleneed;
        this.sendfrom = sendfrom;
        this.destinationofdelivery = destinationofdelivery;
        this.priceofdelivery = priceofdelivery;
        this.negociable = negociable;
        this.ad_image = ad_image;
        this.statusofdelivery = statusofdelivery;
    }

    public Advertise(String postedby, String pstedbyName, String contactno, String contactemail, String goodstype, String vehicleneed, String sendfrom, String destinationofdelivery, String priceofdelivery, String negociable, String ad_image) {
        this.postedby = postedby;
        this.pstedbyName = pstedbyName;
        this.contactno = contactno;
        this.contactemail = contactemail;
        this.goodstype = goodstype;
        this.vehicleneed = vehicleneed;
        this.sendfrom = sendfrom;
        this.destinationofdelivery = destinationofdelivery;
        this.priceofdelivery = priceofdelivery;
        this.negociable = negociable;
        this.ad_image = ad_image;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getPstedbyName() {
        return pstedbyName;
    }

    public void setPstedbyName(String pstedbyName) {
        this.pstedbyName = pstedbyName;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }

    public String getVehicleneed() {
        return vehicleneed;
    }

    public void setVehicleneed(String vehicleneed) {
        this.vehicleneed = vehicleneed;
    }

    public String getSendfrom() {
        return sendfrom;
    }

    public void setSendfrom(String sendfrom) {
        this.sendfrom = sendfrom;
    }

    public String getDestinationofdelivery() {
        return destinationofdelivery;
    }

    public void setDestinationofdelivery(String destinationofdelivery) {
        this.destinationofdelivery = destinationofdelivery;
    }

    public String getPriceofdelivery() {
        return priceofdelivery;
    }

    public void setPriceofdelivery(String priceofdelivery) {
        this.priceofdelivery = priceofdelivery;
    }

    public String getNegociable() {
        return negociable;
    }

    public void setNegociable(String negociable) {
        this.negociable = negociable;
    }

    public String getAd_image() {
        return ad_image;
    }

    public void setAd_image(String ad_image) {
        this.ad_image = ad_image;
    }

    public Boolean getStatusofdelivery() {
        return statusofdelivery;
    }

    public void setStatusofdelivery(Boolean statusofdelivery) {
        this.statusofdelivery = statusofdelivery;
    }
}
