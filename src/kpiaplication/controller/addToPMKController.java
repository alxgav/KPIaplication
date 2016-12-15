package kpiaplication.controller;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kpiaplication.KPIaplication;
import kpiaplication.common.common;
import kpiaplication.common.messages.message;
import kpiaplication.data.db.Product;
import kpiaplication.data.db.pmk_category;
import kpiaplication.data.db.pmk_product_id;
import kpiaplication.data.db.product_postach;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by alxga on 21.11.2016.
 */
public class addToPMKController implements Initializable {
    public TableColumn postachTableCol31;
    @FXML
    private TextField pmk_id = new TextField() ;
    @FXML
    public TextArea pmk_deskr;
    @FXML
    public ComboBox pmk_kateg = new ComboBox();
    @FXML
    public TableView postachTable;
    @FXML
    public TextField pmk_price;
    @FXML
    public TableColumn postachTableCol1;
    @FXML
    public TableColumn postachTableCol2;
    @FXML
    public TableColumn postachTableCol3;
    @FXML
    public TableColumn postachTableCol4;
    @FXML
    public Button saveButton;
    @FXML
    public Button canselButton;
    public TableColumn postachTableCol5;
    public Button categoryButton;

    private pmk_product_id pmk;
    private Product product;
    private product_postach ppostach;
    private pmk_category category;
    common com;
    private ObservableList pmk_kat = FXCollections.observableArrayList();
    private Stage dialogStage;
    private String kateg;
    private final ObservableList<product_postach> postach = FXCollections.observableArrayList();

    int status = 1;
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
        }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //add new
    public void setPMKID(Product product, List<product_postach> ppostach) {
        this.product = product;
        status=1;
        Double p =(product.getPrice()!=0)?product.getPrice():product.getPrice_u();
        pmk_deskr.setText(product.getDeskr());

        pmk_id.setText(getLastID());
        try {
            com = new common();
            GenericRawResults<String[]> rawResults = com.pmk_product_id.queryRaw("select DISTINCT pmk_kateg from pmk_product_id");
            for(String res[]:rawResults){
                pmk_kat.add(res[0]);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        postach.clear();
        postach.addAll(ppostach);
        postachTable.setItems(postach);
        pmk_kateg.setItems(pmk_kat);

        pmk_price.setText(""+getMinPrice());


    }

    public  void setPMKID(ObservableList<pmk_product_id> pmk,ObservableList<product_postach> postach){
            status=2;
            pmk_id.setText(pmk.get(0).getPmk_id());
            pmk_deskr.setText(pmk.get(0).getPmk_deskr());
            pmk_kateg.setValue(pmk.get(0).getPmk_kateg());
            pmk_price.setText(""+pmk.get(0).getPmk_price());
//            postach.clear();
//            postach.addAll(pp);
            postachTable.setItems(postach);

    }

    private boolean isFound(String pmk_id) throws SQLException {
        com = new common();
        GenericRawResults<String[]> rawResults = com.pmk_product_id.queryRaw("SELECT pmk_id from pmk_product_id where pmk_id='"+pmk_id+"'");
        for(final String[] result:rawResults){
            if(result[0]!=null){
                if(pmk_id.equals(result[0])){
                    return true;
                }
            }
        }
        return false;
    }

    private String getLastID() {
        String result;

        try {
            com = new common();
            QueryBuilder<pmk_product_id,String> builder = com.pmk_product_id.queryBuilder();
            builder.orderBy("id", false);
            pmk = com.pmk_product_id.queryForFirst(builder.prepare());
        } catch (SQLException e) {
            new message().messgaeDLG("Помилка","",e.toString());
        }
        Integer id;
        if(pmk==null){
            id=-1;
        }else{
            id=pmk.getId()+1;
        }
        result = String.format("%06d",id);
        return result;
    }

    private Double getMinPrice(){
            Double min=0.0;
            ArrayList<Double> data = new ArrayList();
            data.clear();
            for(int i=0;i<=postach.size()-1;i++){
               data.add((postach.get(i).getPrice_postach_rrc()!=0)?postach.get(i).getPrice_postach_rrc():postach.get(i).getPrice_postach_rrc2());
            }
            min = Collections.min(data);
            return min;
    }

    public void saveButtonaction(ActionEvent actionEvent) throws SQLException {
        com = new common();

        if(status==1){
            List<product_postach> a =  postachTable.getItems();

            pmk = new pmk_product_id(pmk_id.getText(),
                    ""+pmk_kateg.getSelectionModel().getSelectedItem(),
                    pmk_deskr.getText(),
                    Double.valueOf(pmk_price.getText()));
            ppostach = new product_postach();
            if(!isFound(pmk_id.getText())){
                com.pmk_product_id.create(pmk);
                for(int i=0;i<=a.size()-1;i++){

                    com.product_postach.create(new product_postach(pmk_id.getText(),
                            a.get(i).getPostach(),
                            a.get(i).getPrice_postach_rrc(),
                            a.get(i).getPrice_postach_rrc2(),
                            a.get(i).getKod_postach(),
                            a.get(i).getArt_postach()));
                }


            } else{
                new message().messgaeDLG("Знайдено","Знайдено ПМК","Знайдено співпадіння з ID:"+pmk_id.getText());
            }
        }else{
            UpdateBuilder<pmk_product_id,String> ub = com.pmk_product_id.updateBuilder();
            ub.where().eq("pmk_id",pmk_id.getText());
            ub.updateColumnValue("pmk_kateg",pmk_kateg.getSelectionModel().getSelectedItem());
            ub.updateColumnValue("pmk_deskr",pmk_deskr.getText());
            ub.updateColumnValue("pmk_price",pmk_price.getText());
            ub.updateColumnValue("pmk_id",pmk_id.getText());
            ub.update();
        }

        dialogStage.close();
    }

    public void canselButtonaction(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setCategory(pmk_category category){
        this.category = category;
        pmk_kateg.setValue(category.getCategory());

    }

    public void categoryButtonAction(ActionEvent actionEvent) throws IOException, SQLException {
        KPIaplication kpi = new KPIaplication();
        kpi.showCategory(category);
        com = new common();
        pmk_kateg.setValue(com.ini.ReadString("category"));
        double percent = Double.valueOf(com.ini.ReadString("percent"))/100;
        pmk_price.setText(""+ setValue (getMinPrice()*percent+getMinPrice(),0));

    }

    private Double setValue(Double value,int scale){
        return Precision.round(value, scale, BigDecimal.ROUND_HALF_UP);
    }



}

