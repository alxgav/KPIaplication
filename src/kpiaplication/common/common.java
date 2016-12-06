package kpiaplication.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

import kpiaplication.data.db.*;
import kpiaplication.data.dbo.dbConnection;

/**
 * Created by Алексей on 29.05.2016.
 */
public class common {
   public final String [] postach ={"ЮГ-КОНТРАКТ","РАДИОЛАЙН","ЦИФРОТЕХ","КТС","MYTAB","НЕОСЕРВІС","ALL"};
   public final String [] groups ={"Материнськи плати",};
   private ConnectionSource con = new dbConnection().getConnection();
   public Dao<Product, String> produkt;
   public Dao<Order,String> order;
   public Dao<Users,String> user;
   public Dao<Shop,String> shop;
   public Dao<pmk_product_id,String> pmk_product_id;
   public Dao<product_postach,String> product_postach;
   public Dao<pmk_category,String> pmk_category;
   public common() throws SQLException{
      this.produkt = DaoManager.createDao(con, Product.class);
      this.order = DaoManager.createDao(con, Order.class);
      this.user = DaoManager.createDao(con, Users.class);
      this.shop = DaoManager.createDao(con, Shop.class);
      this.pmk_product_id = DaoManager.createDao(con, pmk_product_id.class);
      this.product_postach = DaoManager.createDao(con, kpiaplication.data.db.product_postach.class);
      this.pmk_category = DaoManager.createDao(con, kpiaplication.data.db.pmk_category.class);
   }
   


}
