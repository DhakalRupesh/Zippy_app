package com.example.zippy.model;

public class Vehicles {
   private String brandName;
   private String vehicleType;
   private String vehicle_no;
   private String license_no;
   private String license_Image;
   private Boolean verified;

    public Vehicles(String brandName, String vehicleType, String vehicle_no, String license_no, String license_Image, Boolean verified) {
        this.brandName = brandName;
        this.vehicleType = vehicleType;
        this.vehicle_no = vehicle_no;
        this.license_no = license_no;
        this.license_Image = license_Image;
        this.verified = verified;
    }

    public Vehicles(String brandName, String vehicleType, String vehicle_no, String license_no, String license_Image) {
        this.brandName = brandName;
        this.vehicleType = vehicleType;
        this.vehicle_no = vehicle_no;
        this.license_no = license_no;
        this.license_Image = license_Image;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public String getLicense_Image() {
        return license_Image;
    }

    public void setLicense_Image(String license_Image) {
        this.license_Image = license_Image;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
