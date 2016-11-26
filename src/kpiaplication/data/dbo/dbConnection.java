/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.data.dbo;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author Алексей
 */
public class dbConnection {
    
    //final String databaseUrl = "jdbc:sqlite:db/kpidb.db"; 
    final String databaseUrl = "jdbc:mysql://10.33.0.2:3306/kpidb";
    ConnectionSource con;
     public dbConnection() {
         try {
            con = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e) {
            new   lib.messages.error.errorMessage().error(e.toString());
        }
    }
    public ConnectionSource getConnection(){

            ((JdbcConnectionSource)con).setUsername("root");
            ((JdbcConnectionSource)con).setPassword("123456");
           
        return con;
    }
    
    
}
