package com.example.zippy.model;

public class AdvertiseMy {
    private String _id;
    private String postedby;
    private String acceptedby;
    private String goodstype;
    private String vehicleneed;
    private String sendfrom;
    private String destinationofdelivery;
    private String priceofdelivery;
    private String negociable;
    private String ad_image;
    private Boolean statusofdelivery;

    public AdvertiseMy(String _id, String postedby, String acceptedby, String goodstype, String vehicleneed, String sendfrom, String destinationofdelivery, String priceofdelivery, String negociable, String ad_image, Boolean statusofdelivery) {
        this._id = _id;
        this.postedby = postedby;
        this.acceptedby = acceptedby;
        this.goodstype = goodstype;
        this.vehicleneed = vehicleneed;
        this.sendfrom = sendfrom;
        this.destinationofdelivery = destinationofdelivery;
        this.priceofdelivery = priceofdelivery;
        this.negociable = negociable;
        this.ad_image = ad_image;
        this.statusofdelivery = statusofdelivery;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getAcceptedby() {
        return acceptedby;
    }

    public void setAcceptedby(String acceptedby) {
        this.acceptedby = acceptedby;
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
