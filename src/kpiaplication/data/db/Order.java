/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.data.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;
import kpiaplication.common.CustomDate;

/**
 *
 * @author Алексей
 */
 @DatabaseTable(tableName = "order")
public class Order {
   @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String kod;
    @DatabaseField
    private String deskr;
    @DatabaseField
    private String shop;
    @DatabaseField
    private String seler;
    
    @DatabaseField
    private Double price;
    @DatabaseField
    private Double summa;
    @DatabaseField
    private String postach;
    @DatabaseField
    private Integer st;  
    @DatabaseField
    private Date order_date;

    public Order() {
    }

    public Order( String kod, String deskr, String shop, Double price, Double summa, String postach, Integer st, Date order_date, String seler) {
        
        this.kod = kod;
        this.deskr = deskr;
        this.shop = shop;
        this.price = price;
        this.summa = summa;
        this.postach = postach;
        this.st = st;
        this.order_date = order_date;
        this.seler = seler;
    }

    public String getSeler() {
        return seler;
    }

    public void setSeler(String seler) {
        this.seler = seler;
    }

    
    public Order(Double summa, Integer st) {
        this.summa = summa*st;
        this.st = st;
    }
    
    

    public Date getOrder_date() {
        return new CustomDate(order_date.getTime());
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
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

    public String getDeskr() {
        return deskr;
    }

    public void setDeskr(String deskr) {
        this.deskr = deskr;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public String getPostach() {
        return postach;
    }

    public void setPostach(String postach) {
        this.postach = postach;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }
    
    
    
    
}
