package kpiaplication.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kpiaplication.common.common;
import kpiaplication.common.messages.message;
import kpiaplication.data.db.pmk_category;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import lib.file.ini.PropIni;

/**
 * Created by alxga on 10.12.2016.
 */
public class CategoryController implements Initializable {

    public Button addButtonCategory;
    public Button delButtonCategory;
    public TreeTableColumn categoryColumn;
    public TreeTableColumn percentColumn;
    public TreeTableView<pmk_category> kategTree;


    pmk_category category;

    private Stage dialogStage;
    private final ObservableList<pmk_category> pmk_category = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        percentColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("percent"));


    }

    public void addButtonCategoryAction(ActionEvent actionEvent) {
        TreeItem<pmk_category> item = new TreeItem<>(new pmk_category(2,"new category"));
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();

        if(!sm.isEmpty()) {
            int rowIndex = sm.getSelectedIndex();
            TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);
            selectedItem.getChildren().add(item);
        } else{

        }
    }

    public void delButtonCategoryAction(ActionEvent actionEvent) {

        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        if (sm.isEmpty())
        {
            new message().messgaeDLG("","","not selected");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYES){
            int rowIndex = sm.getSelectedIndex();
            TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);
            TreeItem<pmk_category> parent = selectedItem.getParent();
            if (parent != null)
            {
                parent.getChildren().remove(selectedItem);
            }
            else
            {
                kategTree.setRoot(null);
            }
        }

    }

    public  void setCategory(pmk_category category) throws SQLException {
        this.category = category;
        setCategoryTree(category);


    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void setCategoryTree(pmk_category category) throws SQLException {
        this.category = category;
        common com = new common();
        pmk_category.clear();
        QueryBuilder<pmk_category,String> qb = com.pmk_category.queryBuilder();
        PreparedQuery<pmk_category> pq = qb.prepare();
        List<pmk_category> categ = com.pmk_category.query(pq);
        categ.forEach((r)->{
            pmk_category.add(r);
        });
        TreeItem<pmk_category> root = new TreeItem<>();
        root.setExpanded(true);
        kategTree.setShowRoot(false);

        pmk_category.forEach((r)->{

            TreeItem<pmk_category> categoryRoot = new TreeItem<>(r);
            int id = r.getId();

            if(r.getParent_id()==0) {
                root.getChildren().addAll(categoryRoot);
                pmk_category.forEach((n)->{
                    TreeItem<pmk_category> parentCategory = new TreeItem<>(n);
                    if(n.getParent_id()==id){
                        categoryRoot.getChildren().add(parentCategory);
                    }
                });
            }else{

            }
        });

        kategTree.setRoot(root);

    }

    public void kategTreeMouseClick(MouseEvent mouseEvent) throws SQLException {
        int click = mouseEvent.getClickCount();

        if(click==2){
           // category = new pmk_category();
            common com= new common();
            TreeItem<pmk_category> kateg =  kategTree.getSelectionModel().getSelectedItem();
            com.ini.WriteString("category",kateg.getValue().getCategory());
            com.ini.WriteString("percent",kateg.getValue().getPercent().toString());
            com.ini.WriteString("parent_id",kateg.getValue().getParent_id().toString());
            dialogStage.close();
        }
    }

}
