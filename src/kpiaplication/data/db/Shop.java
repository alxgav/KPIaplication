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
@DatabaseTable(tableName = "shop")

public class Shop {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String shop_name;

    public Shop() {
    }
    
    

    public Shop(String shop_name) {
        this.shop_name = shop_name;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String user_name) {
        this.shop_name = user_name;
    }
    
    
}
