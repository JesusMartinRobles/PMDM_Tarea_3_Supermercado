package com.example.PR_Tarea3MartinRoblesJesus.models;

import java.io.Serializable;

public class Producto implements Serializable {

    public  Producto(){

    }
    public  Producto(String name, double price, int available,double weight,String ingredient,String url){
        this.price=price;
        this.name=name;
        this.available =available;
        this.ingredient = ingredient;
        this.url = url;
        this.weight=weight;
    }
    public  Producto(int id,String name, double price, int avilable,double weight,String ingredient,String url){
        this._id=id;
        this.price=price;
        this.name=name;
        this.available =avilable;
        this.ingredient = ingredient;
        this.url = url;
        this.weight=weight;
    }
    private int _id;
    private String name;
    private double price;
    private int available;
    private double weight;
    private String ingredient;
    private String url;

    public int getId(){
        return this._id;
    }
    public void setId(int id){
        this._id=id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public int isAvilable(){
        return this.available;
    }
    public void setAvailable(int aviable){
        this.available = aviable;
    }
    public String getIngredient(){
        return this.ingredient;
    }
    public void setIngredient(String ingredient){
        this.ingredient=ingredient;
    }
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(double weight){
        this.weight=weight;
    }
    public String getURL(){
        return this.url;
    }
    public void setUrl(String url){
        this.url=url;
    }
}
