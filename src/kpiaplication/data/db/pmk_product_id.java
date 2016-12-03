/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.data.db;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 *
 * @author aleksej
 */
@DatabaseTable(tableName = "pmk_product_id")
public class pmk_product_id {
    
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String pmk_id;
    @DatabaseField
    private String pmk_kateg;
    @DatabaseField
    private String pmk_deskr;
    @DatabaseField
    private Double pmk_price;
//    @DatabaseField
//    private String pmk_garant;
//    @ForeignCollectionField
//    private ForeignCollection<product_postach> product_postach;

    

    public pmk_product_id() {
    }



    public pmk_product_id(String pmk_id, String pmk_kateg, String pmk_deskr, Double pmk_price) {
        this.pmk_id = pmk_id;
        this.pmk_kateg = pmk_kateg;
        this.pmk_deskr = pmk_deskr;
        this.pmk_price = pmk_price;
    }

    //    public pmk_product_id(String pmk_id, String pmk_kateg, Double price_pmk, ForeignCollection<kpiaplication.data.db.product_postach> product_postach) {
//        this.pmk_id = pmk_id;
//        this.pmk_kateg = pmk_kateg;
//        this.price_pmk = price_pmk;
//        this.product_postach = product_postach;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPmk_id() {
        return pmk_id;
    }

    public void setPmk_id(String pmk_id) {
        this.pmk_id = pmk_id;
    }


    public Double getPmk_price() {
        return pmk_price;
    }

    public void setPmk_price(Double pmk_price) {
        this.pmk_price = pmk_price;
    }

    public String getPmk_kateg() {
        return pmk_kateg;
    }

    public void setPmk_kateg(String pmk_kateg) {
        this.pmk_kateg = pmk_kateg;
    }

    public String getPmk_deskr() {
        return pmk_deskr;
    }

    public void setPmk_deskr(String pmk_deskr) {
        this.pmk_deskr = pmk_deskr;
    }

//    public ForeignCollection<kpiaplication.data.db.product_postach> getProduct_postach() {
//        return product_postach;
//    }
//
//    public void setProduct_postach(ForeignCollection<kpiaplication.data.db.product_postach> product_postach) {
//        this.product_postach = product_postach;
//    }
}
