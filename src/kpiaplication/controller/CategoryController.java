package kpiaplication.controller;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import kpiaplication.common.common;
import kpiaplication.common.messages.message;
import kpiaplication.data.db.pmk_category;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    public TreeTableColumn idColumn;
    public TreeTableColumn parent_idColumn;
    public Button editButtonCategory;


    pmk_category category;
    private int id;

    private Stage dialogStage;
    private final ObservableList<pmk_category> pmk_category = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
        percentColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("percent"));
        idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        parent_idColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("parent_id"));


    }

    public void addButtonCategoryAction(ActionEvent actionEvent) throws SQLException {

        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        if(!sm.isEmpty()) {
            TreeItem<pmk_category> item = new TreeItem<>(getNewCategory("parent_id"));
            int rowIndex = sm.getSelectedIndex();

            TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);

            id = selectedItem.getValue().getId();
            selectedItem.getChildren().add(item);
        } else{
            TreeItem<pmk_category> newItem = new TreeItem<>(getNewCategory("id"));
            kategTree.getRoot().getChildren().add(newItem);
        }
        setCategoryTree(category);

    }

    private int getId(){
        return id;
    }
    private void setId(TreeItem<pmk_category> selectedItem){
        id = selectedItem.getValue().getId();

    }

    private pmk_category getNewCategory(String kateg) throws SQLException {
        common com = new common();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Додати/редагувати категорію");
        dialog.setHeaderText("");
        ButtonType loginButtonType = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
        ButtonType canselButtonType = new ButtonType("Відмінити", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, canselButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField new_category = new TextField();
        new_category.setPromptText("Категорія");
        TextField new_percent = new TextField();
        new_percent.setPromptText("Відсоток");
        grid.add(new_category, 0, 0);
        grid.add(new_percent, 0, 1);
        dialog.getDialogPane().setContent(grid);
        Optional<Pair<String, String>> result = dialog.showAndWait();
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        if (new_category.getText().isEmpty() || new_percent.getText().isEmpty()) {
            return null;
        }
        if (kateg.equals("id")) {
            com.pmk_category.create(new pmk_category(0, new_category.getText(), Double.valueOf(new_percent.getText())));
            return new pmk_category(0, new_category.getText(), Double.valueOf(new_percent.getText()));
        }
        if (kateg.equals("parent_id")) {
            int rowIndex = sm.getSelectedIndex();
            TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);
          //  new message().messgaeDLG("","",""+selectedItem.getValue().getId());
            com.pmk_category.create(new pmk_category(selectedItem.getValue().getId(), new_category.getText(), Double.valueOf(new_percent.getText())));
            return new pmk_category(selectedItem.getValue().getId(), new_category.getText(), Double.valueOf(new_percent.getText()));
        }
        return null;
    }



    public void delButtonCategoryAction(ActionEvent actionEvent) throws SQLException {
        common com = new common();
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
                DeleteBuilder<pmk_category,String> db = com.pmk_category.deleteBuilder();
                db.where().eq("id",selectedItem.getValue().getId());
                db.delete();
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

    public void editButtonCategoryAction(ActionEvent actionEvent) throws SQLException {
        common com = new common();
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("редагувати категорію");
        dialog.setHeaderText("");
        ButtonType loginButtonType = new ButtonType("Редагувати", ButtonBar.ButtonData.OK_DONE);

        ButtonType canselButtonType = new ButtonType("Відмінити", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, canselButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField new_category = new TextField(sm.getSelectedItem().getValue().getCategory());
        new_category.setPromptText("Категорія");
        TextField new_percent = new TextField(""+sm.getSelectedItem().getValue().getPercent());
        new_percent.setPromptText("Відсоток");
        grid.add(new_category, 0, 0);
        grid.add(new_percent, 0, 1);
        dialog.getDialogPane().setContent(grid);
        Optional<Pair<String, String>> result = dialog.showAndWait();
        UpdateBuilder<pmk_category,String> ub = com.pmk_category.updateBuilder();
        ub.where().eq("id",sm.getSelectedItem().getValue().getId());
        ub.updateColumnValue("category",new_category.getText());
        ub.updateColumnValue("percent",new_percent.getText());
        ub.update();
        pmk_category.get(sm.getFocusedIndex()).setCategory(new_category.getText());
        pmk_category.get(sm.getFocusedIndex()).setPercent(Double.valueOf(new_percent.getText()));
        sm.getSelectedItem().setValue(pmk_category.get(sm.getFocusedIndex()));
        kategTree.refresh();
    }
}
