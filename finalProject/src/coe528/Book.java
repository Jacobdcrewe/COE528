/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Jacob
 */
public class Book {
    private String bookName;
    private double bookPrice;
    private CheckBox select;
    
    public Book(String bookName, double bookPrice){
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.select = new CheckBox();
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public double getBookPrice() {
        return bookPrice;
    }
    
    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
    
    public CheckBox getSelect() {
        return select;
    }
    
    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    
}
