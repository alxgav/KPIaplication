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
 * @author aleksej
 */
@DatabaseTable(tableName = "product_postach")
public class product_postach {

    @DatabaseField(generatedId = true)
    private Integer id;
//    @DatabaseField (columnName = "pmk_id", foreign = true, foreignAutoRefresh = true)
//    private pmk_product_id pmk_id;
    @DatabaseField
    private String pmk_id;
    @DatabaseField
    private String postach;
    @DatabaseField
    private Double price_postach_rrc;
    @DatabaseField
    private Double price_postach_rrc2; // second pole
    @DatabaseField
    private Double price_postach_ric;
    @DatabaseField
    private String kod_postach;
    @DatabaseField
    private String art_postach;
    public product_postach() {
    }

    public product_postach(String pmk_id, String postach, Double price_postach_rrc,Double price_postach_ric, Double price_postach_rrc2, String kod_postach, String art_postach) {
        this.pmk_id = pmk_id;
        this.postach = postach;
        this.price_postach_rrc = price_postach_rrc;
        this.price_postach_ric = price_postach_ric;
        this.price_postach_rrc2 = price_postach_rrc2;
        this.kod_postach = kod_postach;
        this.art_postach = art_postach;
    }

    public product_postach(String postach, Double price_postach_rrc,Double price_postach_ric, Double price_postach_rrc2, String kod_postach, String art_postach) {
        this.postach = postach;
        this.price_postach_rrc = price_postach_rrc;
        this.price_postach_ric = price_postach_ric;
        this.price_postach_rrc2 = price_postach_rrc2;
        this.kod_postach = kod_postach;
        this.art_postach = art_postach;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice_postach_rrc2() {
        return price_postach_rrc2;
    }

    public void setPrice_postach_rrc2(Double price_postach_rrc2) {
        this.price_postach_rrc2 = price_postach_rrc2;
    }

    public String getPostach() {
        return postach;
    }

    public void setPostach(String postach) {
        this.postach = postach;
    }

    public Double getPrice_postach_rrc() {
        return price_postach_rrc;
    }

    public void setPrice_postach_rrc(Double price_postach_rrc) {
        this.price_postach_rrc = price_postach_rrc;
    }

    public String getKod_postach() {
        return kod_postach;
    }

    public void setKod_postach(String kod_postach) {
        this.kod_postach = kod_postach;
    }

    public String getArt_postach() {
        return art_postach;
    }

    public void setArt_postach(String art_postach) {
        this.art_postach = art_postach;
    }

    public Double getPrice_postach_ric() {
        return price_postach_ric;
    }

    public void setPrice_postach_ric(Double price_postach_ric) {
        this.price_postach_ric = price_postach_ric;
    }
}
