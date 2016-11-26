/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.data;

/**
 *
 * @author Алексей
 */
public class yug {
    
   private String kod;
   private String art;
   private String desk;
   private String magazin;
   private double price;
   private String postach;
   private String kateg;
    

    public yug() {
    }

//    public yug(String kod, String art, String desk, String magazin, double price) {
//        this.kod = kod;
//        this.art = art;
//        this.desk = desk;
//        this.magazin = magazin;
//        this.price = price;
//    }

    public yug(String kod, String art, String desk, String magazin, double price, String postach, String kateg) {
        this.kod = kod;
        this.art = art;
        this.desk = desk;
        this.magazin = magazin;
        this.price = price;
        this.postach = postach;
        this.kateg = kateg;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getMagazin() {
        return magazin;
    }

    public void setMagazin(String magazin) {
        this.magazin = magazin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPostach() {
        return postach;
    }

    public void setPostach(String postach) {
        this.postach = postach;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }
}
