/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.controller;

import com.github.sarxos.webcam.Webcam;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import javafx.util.StringConverter;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import kpiaplication.KPIaplication;
import kpiaplication.common.CustomDate;
import kpiaplication.common.common;
import kpiaplication.common.error.error_log;
import kpiaplication.common.excel.excel;
import kpiaplication.common.messages.message;
import kpiaplication.data.db.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Алексей
 */
public class MainController implements Initializable {


    @FXML
    public TabPane tabMain;
    //private KPIaplication kpi;

    @FXML
    public TableColumn <Product, String> kategColumn;
    @FXML
    public TableColumn <Product, String> postachColumn;
    public TableView postachTable;
    public TableColumn postachTableCol1;
    public TableColumn postachTableCol2;
    public TableColumn postachTableCol3;
    public TableColumn postachTableCol4;
    public Button edit_pmk;
    public Button save_pmk;
    public Button delete_pmk;
    public TextField searchPmk;
    public Region region;
    public Button addUserButton;
    public Button deleteShopButton;
    public TableColumn kategColumn2;
    public TableColumn garantColumn;
    public TreeTableView  <pmk_category> kategTree;
    public TreeTableColumn<pmk_category,String> categoryColumn;
    public TreeTableColumn categoryTest;
    public Button addCategoryButton;
    public Button delCategoryButton;
    public TableColumn postachTableCol22;
    private Button clearButton = new Button();
    @FXML
    private Button testButton;
    @FXML
    private TableView<Product> mainTable;
    @FXML
    private TableColumn<Product, Double> kodColumn;
    @FXML
    private TableColumn<Product, String> artColumn;
    @FXML
    private TableColumn<Product, String> deskColumn;
    @FXML
    private TableColumn<Product, String> magColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
   
    common c;
   public  Order order;
   public Users  users;
   public  pmk_product_id pmk_product;
    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<Product> produktData = FXCollections.observableArrayList();
    private final ObservableList<Order> orderData = FXCollections.observableArrayList();
    private final ObservableList<product_postach> product_postachData = FXCollections.observableArrayList();
    private final ObservableList kateg = FXCollections.observableArrayList();
    private final ObservableList postach = FXCollections.observableArrayList();
    private final ObservableList shop = FXCollections.observableArrayList();
    private final ObservableList user = FXCollections.observableArrayList();
    private final ObservableList user_list = FXCollections.observableArrayList();
    private final ObservableList pmk_list = FXCollections.observableArrayList();
    private final ObservableList<pmk_category> pmk_category = FXCollections.observableArrayList();

   // private final ObservableList<String> postach = FXCollections.observableArrayList(c.postach);
    @FXML
    private TextField seachtext;
    @FXML
    private Button SettingButton;
    @FXML
    private ComboBox postachBox;
    @FXML
    private ComboBox<?> comboBoxKateg;
    @FXML
    private TableColumn<Product, Double> price1Col;
    @FXML
    private TableColumn<Product, Double> price2Col;
    @FXML
    private Label sizeLabel;
    @FXML
    private CheckBox postachCheck;
    @FXML
    private CheckBox kategCheck;
    @FXML
    private TableColumn<Order, String> kodOrderColumn;
    @FXML
    private TableColumn<Order, String> DeskrOrderColumn;
    @FXML
    private TableColumn<Order, Double> PriceOrderColumn;
    @FXML
    private TableColumn<Order, String> PostachOrderColumn;
    @FXML
    private TableColumn<Order, Integer> stOrderColumn;
    @FXML
    private TableColumn<Order, Double> SumaOrderColumn;
    @FXML
    private TableColumn<Order, String> ShopOrderColumn;
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private MenuItem googleMenu;
    @FXML
    private MenuItem rozetkam;
    @FXML
    private DatePicker orderDate;
    @FXML
    private TableColumn<Order, CustomDate> dateOrderColumn;
    private String  seach_item ;
    @FXML
    private Button seachButton;
    @FXML
    private Tab settingTab;
    @FXML
    private MenuItem mcopeArt;
    @FXML
    private MenuItem mcopyKod;
    @FXML
    private MenuItem mcopyDeskr;
    
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();
    
    @FXML
    private TextField userText;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private Button createeditButton;
    @FXML
    private ComboBox shopBox;
    @FXML
    private CheckBox status;
    @FXML
    private Button addShopButton;
    @FXML
    private ListView<Users> userList;
    @FXML
    private Button addOrderButton;
    @FXML
    private Button deleteOrderButton;
    @FXML
    private Button excelOrderButton;
    @FXML
    private TitledPane loadPrice;
    
    private String shop_name;
    private String user_name;
    private boolean status_user =false;
    @FXML
    private TableView<pmk_product_id> pmkTable;
    @FXML
    private TableColumn<pmk_product_id, String> pmkTable_pmk_idColumn;
    @FXML
    private TableColumn<pmk_product_id, String> pmkTable_kod_postachColumn;
    @FXML
    private TableColumn<pmk_product_id, String> pmkTable_art_postachColumn;
    @FXML
    private TableColumn<pmk_product_id, String> pmkTable_deskr_postachColumn;
    @FXML
    private TableColumn<pmk_product_id, Double> pmkTable_priceColumn;
    @FXML
    private TableColumn<pmk_product_id, String> pmkTable_pmk_kategColumn;
    @FXML
    private TableColumn<Product, String> statusColumn;
    @FXML
    private MenuItem addToPMKMenu;
    @FXML
    private TableColumn<pmk_product_id, Double> pmkTable_pmk_priceColumn;
    
   // private BooleanProperty checked = new SimpleBooleanProperty(true);
  //  private TableColumn<Product,Boolean> new_boolColumn;
     public MainController() throws SQLException {
        this.c = new common();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
     //   searchPmk.getChildrenUnmodifiable().addAll(clearButton);

       StringConverter sc = new StringConverter() {
            @Override
            public String toString(Object t) {
                return t == null ? null : t.toString();
            }
 
            @Override
            public Object fromString(String string) {
                return string;
            }
        };  


       kodOrderColumn.setCellValueFactory(new PropertyValueFactory<>("kod"));
       DeskrOrderColumn.setCellValueFactory(new PropertyValueFactory<>("deskr"));
       PriceOrderColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
       PostachOrderColumn.setCellValueFactory(new PropertyValueFactory<>("postach"));
       stOrderColumn.setCellValueFactory(new PropertyValueFactory<>("st"));
       SumaOrderColumn.setCellValueFactory(new PropertyValueFactory<>("summa"));
       ShopOrderColumn.setCellValueFactory(new PropertyValueFactory<>("shop"));
       dateOrderColumn.setCellValueFactory(new PropertyValueFactory<Order,CustomDate>("order_date"));
       categoryColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("category"));
       categoryTest.setCellValueFactory(new TreeItemPropertyValueFactory<>("percent"));

       
       orderTable.setPlaceholder(new Label("Немає замовлень :("));
       mainTable.setPlaceholder(new Label(""));
       orderDate.setValue(LocalDate.now());
       
       price1Col.setOnEditCommit((CellEditEvent<Product, Double> event) -> {
           ((Product) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPrice(event.getNewValue());
       });
       
       
        try {
            loginDialog();

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       orderTable.setEditable(true);
        try {
            getDateAll();
            Kategories();
            Postach();
            getOrder();
            getPMK_product();

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            new lib.messages.error.errorMessage().error(ex.toString());
        }
       SeacheChanged();
        tabSelect();
        tableCellRenderer();
       mainTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            showUsers();

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            new lib.messages.error.errorMessage().error(ex.toString());
        }

         userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
           @Override
           public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               try {
                   System.out.println(newValue.toString());
                   QueryBuilder<Users,String> qb_user = c.user.queryBuilder();
                   qb_user.where().eq("user_name",newValue.toString());
                   PreparedQuery<Users> preparedQuery = qb_user.prepare();
                   List <Users> p = c.user.query(preparedQuery);
                   userText.setText(newValue.toString());
                   status.setSelected(p.get(0).isStatus());
                   password1.setText(p.get(0).getPassword());
                   password2.setText(p.get(0).getPassword());
                   shopBox.setValue(p.get(0).getShop());

               } catch (SQLException ex) {
                   Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                   new lib.messages.error.errorMessage().error(ex.toString());
               }
           }

         });

        changePostach_product();

        try {
            setCategoryTree();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   
    @FXML
    private void testButtonAction(ActionEvent event) {
        try {
            getYUG();
        } catch (IOException | BiffException | SQLException  ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            new lib.messages.error.errorMessage().error(ex.toString());
        }
    }
    
   private void getOrder() throws SQLException{
        orderData.clear();
        QueryBuilder<Order,String> qb = c.order.queryBuilder();
        LocalDate order_date = orderDate.getValue();
        Instant instant  = order_date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        if(status_user){
          qb.where().eq("order_date", new CustomDate(Date.from(instant).getTime()));  
        }else{
           qb.where().eq("order_date", new CustomDate(Date.from(instant).getTime())).and().eq("seler", user_name).and().eq("shop", shop_name); 
        }
        
        PreparedQuery<Order> preparedQuery = qb.prepare();
       
        List <Order> p = c.order.query(preparedQuery);
         
            p.stream().forEach((r)->{
               orderData.add(r);
            });
          orderTable.setItems(orderData);
   }
    
    private void getYUG() throws IOException, BiffException, SQLException{
        String dir = "price/";
        produktData.clear();
 //       new excel().yug_excel(dir+"pprice_yugcontract.xls");
        new excel().RL_excel(dir+"RL.xls");
//       // new excel().CYFRO_excel(dir+"Cifrotech.xlsx");
//        new excel().MYTAB_excel(dir+"MYTAB.xlsx");
//       // new excel().KTS_excel(dir+"ktc_price.csv");
//        new excel().neo_excel(dir+"Neo Service.xls");
         QueryBuilder<Product,String> qb = c.produkt.queryBuilder();
         PreparedQuery<Product> preparedQuery = qb.prepare();
         List <Product> p = c.produkt.query(preparedQuery);
         
            p.stream().forEach((r)->{
               produktData.add(r);

            });
          mainTable.setItems(produktData);
        new message().messgaeDLG("Завантаження прайсів", null, "Прайси загружені");
    }
    
    private void getDateAll() throws SQLException{
        QueryBuilder<Product,String> qb = c.produkt.queryBuilder();
        PreparedQuery<Product> preparedQuery = qb.prepare();
        List <Product> p = c.produkt.query(preparedQuery);
            p.stream().forEach((r)->{
               produktData.add(r);

            });
          mainTable.setItems(produktData);
         sizeLabel.setText("Знайдено : "+produktData.size());
    }

    public void getPMK_product() throws SQLException{
        pmk_list.clear();
        QueryBuilder<pmk_product_id,String> qb = c.pmk_product_id.queryBuilder();
        PreparedQuery<pmk_product_id> preparedQuery = qb.prepare();
        List <pmk_product_id> p = c.pmk_product_id.query(preparedQuery);
        p.stream().forEach((r)->{
            pmk_list.add(r);

        });

        pmkTable.setItems(pmk_list);
    }

    private void setCategoryTree() throws SQLException {
        pmk_category.clear();
        QueryBuilder<pmk_category,String> qb = c.pmk_category.queryBuilder();
        PreparedQuery<pmk_category> pq = qb.prepare();
        List<pmk_category> category = c.pmk_category.query(pq);
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



    @FXML
    private void SettingButtonAction(ActionEvent event) throws SQLException, IOException {
        new excel().writeCSV();
    }

 


    private void SeacheChanged(){
         
         seachtext.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
        
             
             String seach = "";
            try {
                    QueryBuilder<Product,String> qb = c.produkt.queryBuilder();
                    if(seachtext.getText() != null ){
                        seach=seachtext.getText();
                        if(postachCheck.isSelected()&&kategCheck.isSelected()){
                           qb.where().like("deskr","%"+seach.replace("'", "''")+"%").and().eq("kpiaplication.data.db.kpiaplication.data.db.kpiaplication.data.db.kateg", ""+comboBoxKateg.getSelectionModel().getSelectedItem()).and().eq("postach", ""+postachBox.getSelectionModel().getSelectedItem());
                        }else{
                           if(postachCheck.isSelected()){
                               qb.where().like("deskr","%"+seach.replace("'", "''")+"%").and().eq("postach", ""+postachBox.getSelectionModel().getSelectedItem());
                             }
                            if(kategCheck.isSelected()){
                                qb.where().like("deskr","%"+seach.replace("'", "''")+"%").and().eq("kpiaplication.data.db.kpiaplication.data.db.kpiaplication.data.db.kateg", ""+comboBoxKateg.getSelectionModel().getSelectedItem());
                            }  
                        }
                     }
                   
                    if(!postachCheck.isSelected()&&!kategCheck.isSelected()){
                       qb.where().like("deskr","%"+seach.replace("'", "''")+"%"); 
                    }
                    else{
                         
                    } 
           
               PreparedQuery<Product> preparedQuery = qb.prepare();
               
               List <Product> g = c.produkt.query(preparedQuery);
                    produktData.clear();
                    g.stream().forEach((r) -> {
                       produktData.add(r);
     
            });
            sizeLabel.setText("Знайдено : "+produktData.size()); 
           } catch (SQLException ex) {
                System.out.println(ex.toString()+"  "+seach);
                 try {
                     new error_log().write_log_error(ex.toString());
                 } catch (IOException ex1) {
                     Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
                 }
               Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
              
           }
         
        mainTable.setItems(produktData);
       });
    }

    //postachTable
    private void changePostach_product(){
        pmkTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TableView.TableViewSelectionModel selectionModel =pmkTable.getSelectionModel();
            pmk_product_id pp = (pmk_product_id) newValue;

            QueryBuilder<product_postach,String> qb = c.product_postach.queryBuilder();
            try {
                if(pp.getPmk_id()!=null){
                    qb.where().eq("pmk_id",pp.getPmk_id());
                    PreparedQuery<product_postach> pq;
                    pq = qb.prepare();
                    List<product_postach> product_post= c.product_postach.query(pq);
                    product_postachData.clear();
                    product_post.forEach((r->{
                        product_postachData.add(r);
                    }));
                    postachTable.setItems(product_postachData);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void Kategories() throws SQLException{
        GenericRawResults<String[]> rawResults = c.produkt.queryRaw("SELECT distinct kateg from product");
         for(String res[]:rawResults){
                         kateg.add(res[0]);
                    }
         comboBoxKateg.setItems(kateg);
        
    }
     private void Postach() throws SQLException{
        GenericRawResults<String[]> rawResults = c.produkt.queryRaw("SELECT distinct postach from product");
        
         for(String res[]:rawResults){
                         postach.add(res[0]);
                    }
         postachBox.setItems(postach);
         System.out.println(kateg.size());
    }

    @FXML
    private void googleMenuAction(ActionEvent event) throws IOException, URISyntaxException  {
        openBrowser("http://www.google.com/search?q="); 
   
    }

    @FXML
    private void seachtextProcess(KeyEvent event) {
      
      
    }

    @FXML
    private void rozetkamAction(ActionEvent event) throws IOException, URISyntaxException {
        TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
        Desktop.getDesktop().browse(new URL("http://rozetka.com.ua/search/?section=%2F&text="+URLEncoder.encode( t.get(0).getArtPost() ,java.nio.charset.StandardCharsets.UTF_8.toString())).toURI());
    }
    
    private void  openBrowser(String url) throws IOException, URISyntaxException{
        TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
        
        Desktop.getDesktop().browse(new URL(url+URLEncoder.encode( t.get(0).getDeskr() ,java.nio.charset.StandardCharsets.UTF_8.toString())).toURI());
    }
    // login dialog
    

    @FXML
    private void orderDateAction(ActionEvent event) throws SQLException {
        getOrder();
    }


    @FXML
    private void seachButtonAction(ActionEvent event) throws SQLException {
        produktData.clear();
        QueryBuilder<Product,String> qb = c.produkt.queryBuilder();
      if(postachCheck.isSelected()&&kategCheck.isSelected()){
          qb.where().eq("postach",""+postachBox.getSelectionModel().getSelectedItem()).and().eq("kpiaplication.data.db.kpiaplication.data.db.kpiaplication.data.db.kateg",""+ comboBoxKateg.getSelectionModel().getSelectedItem());
      }else{
         if(postachCheck.isSelected()){
          qb.where().eq("postach",""+postachBox.getSelectionModel().getSelectedItem()); 
        }
        if(kategCheck.isSelected()){
           qb.where().eq("kpiaplication.data.db.kpiaplication.data.db.kpiaplication.data.db.kateg",""+ comboBoxKateg.getSelectionModel().getSelectedItem());
        } 
      }
        PreparedQuery<Product> preparedQuery = qb.prepare();
            List <Product> p = c.produkt.query(preparedQuery);
             p.stream().forEach((r)->{
               produktData.add(r);

            });
            mainTable.setItems(produktData);
            sizeLabel.setText("Знайдено : "+produktData.size()); 
            mainTable.setPlaceholder(new Label("Нічого не знайдено :("));
            
      
    }

    @FXML
    private void orderTableClicked(MouseEvent event) throws SQLException {
         TableView.TableViewSelectionModel selectionModel =  orderTable.getSelectionModel();
         ObservableList<Order> t = selectionModel.getSelectedItems();
        if(event.getClickCount()==2){
            TextInputDialog dialog = new TextInputDialog(""+t.get(0).getSt());
            dialog.setTitle("Змінити кількість в замовленні");
            dialog.setHeaderText("Введіть кількість");
            dialog.setContentText("ШТ");
            Optional<String> result = dialog.showAndWait();
            int st = Integer.valueOf(result.get());
            order = new Order(t.get(0).getSumma(),st);
            UpdateBuilder<Order,String> ub = c.order.updateBuilder();
            ub.where().eq("kod", t.get(0).getKod());
            ub.updateColumnValue("st", st);
            ub.updateColumnValue("summa", st*t.get(0).getPrice());
            ub.update();
//            orderData.addAll(order);
//            orderTable.setItems(orderData);
            getOrder();
        }
    }

  private void statusUsers(){
      settingTab.setDisable(true);
      
  } 

    @FXML
    private void mcopeArtAction(ActionEvent event) {
        TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
        copy_produkt(t.get(0).getArtPost());
    }

    @FXML
    private void mcopyKodAction(ActionEvent event) {
        TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
       // System.out.println(t.get(0).getKod());
        copy_produkt(t.get(0).getKod());
    }

    @FXML
    private void mcopyDeskrAction(ActionEvent event) {
         TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
        copy_produkt(t.get(0).getDeskr());
    }
    
    private void copy_produkt(String value){
        // System.out.println(value);      
         content.putString(value);
         clipboard.setContent(content);
    }

    @FXML
    private void createeditButtonAction(ActionEvent event) throws SQLException {
        String user_login = userText.getText();
        String password = password1.getText();
        String password_sec = password2.getText();
        boolean stat = false;

        if(password.equals(password_sec)){
           if (status.isSelected()){
               stat = true;
           } 
           users = new Users(user_login,password,stat,""+shopBox.getSelectionModel().getSelectedItem());
           if(user_login.equals(userList.getSelectionModel().getSelectedItem())){
               UpdateBuilder<Users,String> ub = c.user.updateBuilder();
               ub.where().eq("user_name",user_login);
               ub.updateColumnValue("user_name",user_login);
               ub.updateColumnValue("password",password);
               ub.updateColumnValue("status",stat);
               ub.updateColumnValue("shop",shopBox.getSelectionModel().getSelectedItem());
               ub.update();
               //c.user.update(users);
           } else{
               user.add(users);
               user_list.add(user_login);
               userList.setItems(user_list);
               c.user.create(user);
           }

        }else{
            new message().messgaeDLG("Неможливо записати", "", "Паролі не співпадають");
        }
    }

    @FXML
    private void addShopButtonAction(ActionEvent event) throws SQLException {
        
        TextInputDialog dialog = new TextInputDialog("Назва магазину");
        dialog.setTitle("Додати магазин");
        dialog.setHeaderText("Додати магазин");
        dialog.setContentText("Введіть назву магазину");
        Optional<String> result = dialog.showAndWait();
        shop.add(result.get());
        shopBox.setItems(shop);
        Shop s =new Shop();
        s.setShop_name(result.get());
        c.shop.create(s);
        result.ifPresent(name -> System.out.println("Your name: " + name));

    }
    
    private void showShop() throws SQLException{
        GenericRawResults<String[]> rawResults =  c.shop.queryRaw("SELECT shop_name from shop");
        for(String res[]:rawResults){
            shop.add(res[0]);
        }
        shopBox.setItems(shop);
    }
    
    private void showUsers() throws SQLException{
        GenericRawResults<String[]> rawResults =  c.user.queryRaw("SELECT user_name from users"); 
        for(String res[]:rawResults){
            user_list.add(res[0]);
        }
        userList.setItems(user_list);
        
    }
    

    @FXML
    private void addOrderButtonAction(ActionEvent event) throws SQLException {
        TableView.TableViewSelectionModel selectionModel =  mainTable.getSelectionModel();
        ObservableList<Product> t = selectionModel.getSelectedItems();
        String kod = t.get(0).getKod();
        String deskr = t.get(0).getDeskr();
        String shop = shop_name;
        Double price = t.get(0).getPrice_u();
        String postach_order = t.get(0).getPostach();
        Double price_st =0.0;
        LocalDate order_date = orderDate.getValue();
        Instant instant  = order_date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
       
        //dialog stuke
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Додати замовлення");
        dialog.setHeaderText("Введіть кількість");
        dialog.setContentText("ШТ");
        Integer st = 1;
        
        Optional<String> result = dialog.showAndWait();
        st = Integer.valueOf(result.get());
        price_st=price*st; 
       
        order = new Order(kod,
                deskr, 
                shop,
                price, 
                price_st, 
                postach_order,
                st,  
                new CustomDate(Date.from(instant).getTime()),
                user_name);
        
        orderData.add(order);
        orderTable.setItems(orderData);
        c.order.create(order);
    }

    @FXML
    private void deleteOrderButtonAction(ActionEvent event) throws SQLException {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Видалити запис");
            alert.setHeaderText("Видалити запис");
            alert.setContentText("Ви впевнені?");
            ButtonType buttonTypeYES = new ButtonType("ТАК");
            ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYES){
           
           DeleteBuilder<Order,String> deleteBuilder = c.order.deleteBuilder();
           Order o = orderTable.getSelectionModel().getSelectedItem();
           deleteBuilder.where().eq("kod",o.getKod()).and().eq("order_date", o.getOrder_date());
           deleteBuilder.delete();
           orderData.remove(o);
    }
    
}

    @FXML
    private void excelOrderButtonAction(ActionEvent event) throws SQLException, IOException, BiffException, WriteException {
        new excel().make_excel(postach,orderDate.getValue());
        new message().messgaeDLG("EXCEL", "", "Файли завантажені в папку");
    }
    
    //login dialog
     private void loginDialog() throws SQLException{
        Dialog <Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle("Авторизація");
        dialog.setHeaderText("Введіть логін та пароль");
        
        dialog.setGraphic(new ImageView(this.getClass().getResource("img/Login.png").toString()));
        ButtonType loginButtonType = new ButtonType("Логін",ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,ButtonType.CANCEL);
       
        
        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

         ObservableList d_login = FXCollections.observableArrayList();
         GenericRawResults<String[]> rawResults = c.user.queryRaw("select user_name from users");
        
         for(String res[]:rawResults){
                         d_login.add(res[0]);
                    }
      
        ComboBox username = new ComboBox();
        username.setItems(d_login);
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
             loginButton.setDisable(newValue.toString().trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {

                return new Pair<>(username.getSelectionModel().getSelectedItem().toString(), password.getText());
            }else{
                System.exit(0);
            }
            return null;
        });
       
        Optional<Pair<String, String>> result = dialog.showAndWait();
        String login = username.getSelectionModel().getSelectedItem().toString();
        QueryBuilder<Users,String> qb_user = c.user.queryBuilder();
        qb_user.where().eq("user_name",login);
        PreparedQuery<Users> preparedQuery = qb_user.prepare();
        ObservableList<Users> user_pass = FXCollections.observableArrayList();
        List <Users> p = c.user.query(preparedQuery);
         
        String pass = p.get(0).getPassword();
        status_user = p.get(0).isStatus();
        shop_name = p.get(0).getShop();
        user_name = p.get(0).getUser_name();
        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
            if(login.equals(usernamePassword.getKey())&&pass.equals(usernamePassword.getValue())){
              
                try {
                    if(status_user){
                      settingTab.setDisable(false);
                      loadPrice.setDisable(false);
                    }
                    showShop();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                new message().messgaeDLG("", "", "Login or password incorrect");
                try {
                    loginDialog();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
   
        }



    private void tabSelect(){
        tabMain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                if(tabMain.getSelectionModel().getSelectedItem().getText().equals("ПМК")){
                    try {
                        getPMK_product();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

   private void tableCellRenderer(){
       statusColumn.setCellFactory(column->{
           return new TableCell<Product, String>() {
               @Override
		protected void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   if (item == null || empty) {
			setText(null);
			setStyle("");
			} else {
                       setText(item);
                          if(item.equals("ЦІНА НЕСПІВПАДАЄ")){
                              setTextFill(Color.CHOCOLATE);
                              setStyle("-fx-background-color: yellow");
                          }
                          if(item.equals("НОВЕ ПОСТУПЕЛННЯ")){
                              setTextFill(Color.BLACK);
                              setStyle("-fx-background-color: red");
                          }
                           if(item.equals("БЕЗ ЗМІН")){
                              setTextFill(Color.BLACK);
                              setStyle("-fx-background-color: BEIGE");
                          }
                          
                   }
                }
           };
       });
   }

    public void edit_pmk_action(ActionEvent actionEvent) throws IOException, SQLException {

        KPIaplication kpi = new KPIaplication();
        pmk_product_id pmk = new pmk_product_id();
        kpi.showaddToPMK(pmkTable.getSelectionModel().getSelectedItems(),postachTable.getItems());
        getPMK_product();
    }



    public void delete_pmk_action(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        String pmk_id = pmkTable.getSelectionModel().getSelectedItem().getPmk_id();
        if (result.get() == buttonTypeYES){
/// dodelat
            DeleteBuilder<pmk_product_id,String> deleteBuilder = c.pmk_product_id.deleteBuilder();
            deleteBuilder.where().eq("pmk_id",pmk_id);
            pmk_list.remove(pmkTable.getSelectionModel().getSelectedItem());
            deleteBuilder.delete();

            DeleteBuilder<product_postach,String> dl = c.product_postach.deleteBuilder();
            dl.where().eq("pmk_id",pmk_id);
            product_postachData.remove(postachTable.getSelectionModel().getSelectedItems());
            dl.delete();

        }
    }

    public void mainTableKeyPressed(KeyEvent keyEvent) {
        Product product = mainTable.getSelectionModel().getSelectedItem();
        KPIaplication kpi = new KPIaplication();
        product_postach pp= new product_postach();
        mainTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<TablePosition> selectedCells = FXCollections.observableArrayList();
        ObservableList<Product> si = mainTable.getSelectionModel().getSelectedItems();

        if (keyEvent.getCode() == KeyCode.INSERT){
            selectedCells.addAll(mainTable.getSelectionModel().getSelectedCells().get(mainTable.getSelectionModel().getSelectedCells().size()-1));// .add(mainTable.getSelectionModel().getSelectedCells().get(mainTable.getSelectionModel().getSelectedCells().size()-1));
            List<product_postach> plist = new ArrayList<>();
            for(int i=0;i<=si.size()-1;i++){
                pp = new product_postach(si.get(i).getPostach(),
                        si.get(i).getPrice(),
                        si.get(i).getPrice_u(),
                        si.get(i).getKod(),
                        si.get(i).getArtPost());
                plist.add(pp);
            }
            kpi.showaddToPMK(product,plist);

        }


    }

    public void addUserButtonAction(ActionEvent actionEvent) {
        userText.setText("");
        password1.setText("");
        password2.setText("");
        shopBox.getSelectionModel().selectFirst();
    }

    public void deleteShopButtonAction(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Видалити запис");
        alert.setHeaderText("Видалити запис");
        alert.setContentText("Ви впевнені?");
        ButtonType buttonTypeYES = new ButtonType("ТАК");
        ButtonType buttonTypeNO = new ButtonType("НІ",ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYES, buttonTypeNO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYES){

            DeleteBuilder<Shop,String> deleteBuilder = c.shop.deleteBuilder();
            deleteBuilder.where().eq("shop_name",shopBox.getSelectionModel().getSelectedItem());
            deleteBuilder.delete();
            shop.remove(shopBox.getSelectionModel().getSelectedItem());
        }
    }


    public void addCategoryButtonaction(ActionEvent actionEvent) {
        TreeItem<pmk_category> item = new TreeItem<>(new pmk_category(2,"new category"));
        TreeTableView.TreeTableViewSelectionModel<pmk_category> sm = kategTree.getSelectionModel();
        int rowIndex = sm.getSelectedIndex();
        TreeItem<pmk_category> selectedItem = sm.getModelItem(rowIndex);
        selectedItem.getChildren().add(item);

    }

    public void delCategoryButton(ActionEvent actionEvent) {
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



}
