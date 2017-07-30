package com.maslick.ai.klassy;

public class House {

    private double houseSize;
    private double lotSize;
    private int bedrooms;
    private int granite;
    private double bathroom;

    public House() {
    }

    public House(double houseSize, double lotSize, int bedrooms, int granite, double bathroom) {
        this.houseSize = houseSize;
        this.lotSize = lotSize;
        this.bedrooms = bedrooms;
        this.granite = granite;
        this.bathroom = bathroom;
    }

    public double getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(double houseSize) {
        this.houseSize = houseSize;
    }

    public double getLotSize() {
        return lotSize;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getGranite() {
        return granite;
    }

    public void setGranite(int granite) {
        this.granite = granite;
    }

    public double getBathroom() {
        return bathroom;
    }

    public void setBathroom(double bathroom) {
        this.bathroom = bathroom;
    }
}
