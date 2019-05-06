package com.example.dil.admin.Model;

public class Product {

    private String ProductName, Battery, Camera, Sim, Color, Display, Fingerprint, ROM, Network, Others, Price, Processor, RAM, YoutubeVideoLink, pid, date, time;

    private Images ImageUris;


    public Product() {

    }

    public Product(String productName, String battery, String camera, String Sim, String color, String display, String fingerprint, String ROM, String network, String others, String price, String processor, String RAM, String youtubeVideoLink, String pid, String date, String time ) {
        this.ProductName = productName;
        this.Battery = battery;
        this.Camera = camera;
        this.Color = color;
        this.Display = display;
        this.Fingerprint = fingerprint;
        this.ROM = ROM;
        this.Sim = Sim;
        this.Network = network;
        this.Others = others;
        this.Price = price;
        this.Processor = processor;
        this.RAM = RAM;
        this.YoutubeVideoLink = youtubeVideoLink;
        this.pid = pid;
        this.date = date;
        this.time = time;
    }

    public Product(Images imageUris) {
        ImageUris = imageUris;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public String getCamera() {
        return Camera;
    }

    public void setCamera(String camera) {
        Camera = camera;
    }

    public String getSim() {
        return Sim;
    }

    public void setSim(String sim) {
        Sim = sim;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String display) {
        Display = display;
    }

    public String getFingerprint() {
        return Fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        Fingerprint = fingerprint;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getNetwork() {
        return Network;
    }

    public void setNetwork(String network) {
        Network = network;
    }

    public String getOthers() {
        return Others;
    }

    public void setOthers(String others) {
        Others = others;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProcessor() {
        return Processor;
    }

    public void setProcessor(String processor) {
        Processor = processor;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getYoutubeVideoLink() {
        return YoutubeVideoLink;
    }

    public void setYoutubeVideoLink(String youtubeVideoLink) {
        YoutubeVideoLink = youtubeVideoLink;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Images getImageUris() {
        return ImageUris;
    }

    public void setImageUris(Images imageUris) {
        ImageUris = imageUris;
    }
}