/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kpiaplication.controller.CategoryController;
import kpiaplication.controller.addToPMKController;
import kpiaplication.data.db.Product;
import kpiaplication.data.db.pmk_category;
import kpiaplication.data.db.pmk_product_id;
import kpiaplication.data.db.product_postach;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Алексей
 */
public class KPIaplication extends Application {

    public KPIaplication() {
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        Scene scene = new Scene(root,width-150,height-150);
        
        stage.setScene(scene);
       
        //stage.setFullScreen(true);
        stage.show();
       // openLoginDialog();
       
    }
    

//insert
    public void showaddToPMK(Product product, java.util.List<product_postach> pp){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KPIaplication.class.getResource("/kpiaplication/view/addToPMK.fxml"));
        try {
            VBox page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Додати до ПМК");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setIconified(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            addToPMKController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPMKID(product,pp);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //edit
    public void showaddToPMK(ObservableList<pmk_product_id> pmk, ObservableList<product_postach> postach){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KPIaplication.class.getResource("/kpiaplication/view/addToPMK.fxml"));

        try {
            VBox page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Додати до ПМК");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setResizable(false);
            dialogStage.setIconified(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            addToPMKController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPMKID(pmk,postach);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCategory(pmk_category category) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(KPIaplication.class.getResource("/kpiaplication/view/category.fxml"));
        BorderPane page = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Додати Категорію");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.setIconified(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        CategoryController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCategory(category);
        dialogStage.showAndWait();
        }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

    
}
