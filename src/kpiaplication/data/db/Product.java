/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author Алексей
 */
 @DatabaseTable(tableName = "product")
public class Product  {

   
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String kod;
    @DatabaseField
    private String artPost;
    @DatabaseField
    private String deskr;
    @DatabaseField
    private String magazin;
    
    @DatabaseField
    private Double price;
    @DatabaseField
    private Double price_u;
    @DatabaseField
    private String postach;
    @DatabaseField
    private String kateg;
    @DatabaseField
    private String status;
    @DatabaseField
    private String garant;
    @DatabaseField
    private String kateg2;
  
   

    public Product() {
    }

    public Product(String kod, String artPost, String deskr, String magazin, Double price, Double price_u, String postach, String kateg, String status, String garant) {
        this.kod = kod;
        this.artPost = artPost;
        this.deskr = deskr;
        this.magazin = magazin;
        this.price = price;
        this.price_u = price_u;
        this.postach = postach;
        this.kateg = kateg;
        this.status = status;
        this.garant = garant;
    }

    public Product(String kod, String artPost, String deskr, String magazin, Double price, Double price_u, String postach, String kateg, String status, String garant, String kateg2) {
        this.kod = kod;
        this.artPost = artPost;
        this.deskr = deskr;
        this.magazin = magazin;
        this.price = price;
        this.price_u = price_u;
        this.postach = postach;
        this.kateg = kateg;
        this.status = status;
        this.garant = garant;
        this.kateg2 = kateg2;
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getArtPost() {
        return artPost;
    }

    public void setArtPost(String artPost) {
        this.artPost = artPost;
    }

    

    public String getMagazin() {
        return magazin;
    }

    public void setMagazin(String magazin) {
        this.magazin = magazin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
     public String getDeskr() {
        return deskr;
    }

    public void setDeskr(String deskr) {
        this.deskr = deskr;
    }

    public Double getPrice_u() {
        return price_u;
    }

    public void setPrice_u(Double price_u) {
        this.price_u = price_u;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGarant() {
        return garant;
    }

    public void setGarant(String garant) {
        this.garant = garant;
    }

    public String getKateg2() {
        return kateg2;
    }

    public void setKateg2(String kateg2) {
        this.kateg2 = kateg2;
    }
}
