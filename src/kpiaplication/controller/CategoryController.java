package kpiaplication.controller;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kpiaplication.common.common;
import kpiaplication.common.messages.message;
import kpiaplication.data.db.pmk_category;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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
//        try {
//            setCategoryTree();
//        } catch (SQLException e) {
//            new message().messgaeDLG("Помила відкриття категорій","ERROR",e.toString());
//        }


    }

    public void addButtonCategoryAction(ActionEvent actionEvent) {
        TreeItem<pmk_category> item = new TreeItem<>(new pmk_category(2,"new category"));
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        int rowIndex = sm.getSelectedIndex();
        TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);
        selectedItem.getChildren().add(item);
    }

    public void delButtonCategoryAction(ActionEvent actionEvent) {
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        if (sm.isEmpty())
        {
            new message().messgaeDLG("","","not selected");
            return;
        }
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

    public  void setCategory(pmk_category category) throws SQLException {
        this.category = category;
        setCategoryTree();

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void setCategoryTree() throws SQLException {
        common com = new common();
        pmk_category.clear();
        QueryBuilder<pmk_category,String> qb = com.pmk_category.queryBuilder();
        PreparedQuery<pmk_category> pq = qb.prepare();
        List<pmk_category> category = com.pmk_category.query(pq);
        category.forEach((r)->{
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

    public void kategTreeMouseClick(MouseEvent mouseEvent) {
        int click = mouseEvent.getClickCount();

        if(click==2){
            TreeItem<pmk_category> kateg = kategTree.getSelectionModel().getSelectedItem();
            addToPMKController addCategory = new addToPMKController();
            String k = kateg.getValue().getCategory();
            addCategory.setCategory(k);
            dialogStage.close();
        }
    }


}
