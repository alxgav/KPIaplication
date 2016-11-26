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
@DatabaseTable(tableName = "users")
public class Users {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String user_name;
     @DatabaseField
    private String password;
     @DatabaseField
    private boolean status;
      @DatabaseField
    private String shop;

    public Users() {
    }
    
      

    public Users(String user_name, String password, boolean status, String shop) {
        this.user_name = user_name;
        this.password = password;
        this.status = status;
        this.shop = shop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return  password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
   
    
}
