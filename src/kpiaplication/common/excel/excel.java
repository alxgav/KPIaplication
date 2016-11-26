/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.common.excel;

import com.csvreader.CsvWriter;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import jxl.*;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import kpiaplication.common.CustomDate;
import kpiaplication.common.common;
import kpiaplication.data.db.Order;
import kpiaplication.data.db.Product;
import kpiaplication.data.db.pmk_product_id;
import org.apache.commons.math3.util.Precision;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Алексей
 */
public class excel {
    
   
   // List<yug> y=new ArrayList<>();
    common c; 
    Product p;
    
    public excel() throws SQLException {
        this.c = new common();
    }
    
    public void yug_excel(String file) throws IOException, BiffException, SQLException{
           // c = new common();
            String [] name_sheet ={"КОМПЬЮТЕРНАЯ ТЕХНИКА",
                            "IT-ПЕРИФЕРИЯ","АКСЕССУАРЫ",
                            "ЭЛЕМЕНТЫ ПИТАНИЯ И МЕДИА",
                            "ТЕЛЕФОНИЯ",
                            "ТВ АУДИО ТЕХНИКА",
                            "АВТОЭЛЕКТРОНИКА",
                            "ФЛЕШ ПАМЯТЬ"};
            DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
            deleteBuilder.where().eq("postach", c.postach[0]);
            deleteBuilder.delete();
            
            Workbook workbook = Workbook.getWorkbook(new File(file));
            String type =" ";
            for(int i=0;i<=name_sheet.length-1;i++){
                Sheet sheet = workbook.getSheet(name_sheet[i]);
                System.out.println(sheet.getRows()+"| yug");         
                int row = 10;
                while (row<sheet.getRows()){
                    Cell  A= sheet.getCell(0,row);
                    Cell  B= sheet.getCell(1,row);
                    Cell  E= sheet.getCell(4,row);
                    Cell  F= sheet.getCell(5,row);
                    Cell  K= sheet.getCell(8,row); //u_price
                    Cell  L= sheet.getCell(9,row); //rrc

                    if(A.getCellFormat().getBackgroundColour()== Colour.LIGHT_BLUE){
                        type=A.getContents();
                    }

                if(A.getType()== CellType.NUMBER){
                   // System.out.println(K.getContents()+" L "+L.getContents());
                    NumberCell nc = (NumberCell) A;
                    NumberCell pr = (NumberCell) K;
                   
                    double  kod = nc.getValue();
                    if(pr.getValue()!=0&&!"".equals(L.getContents())){
                         NumberCell pu = (NumberCell) L;
                         
                         p =new Product(Double.toString(kod).substring(0, Double.toString(kod).length()-2),
                            B.getContents(),
                            E.getContents(),
                            F.getContents(),
                             setValue(pu.getValue(), 0),pr.getValue(),
                            c.postach[0],
                            type,
                                 setStatus(""+Double.toString(kod).substring(0, Double.toString(kod).length()-2),pr.getValue()));
                         c.produkt.create(p);
                         
                    }
                   
                }
                    row++;
                   
                }
            }
            
         workbook.close();
       
    }


    public  void RL_excel(String file) throws IOException, BiffException, SQLException{
       // c = new common();
            DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
            deleteBuilder.where().eq("postach", c.postach[1]);
            deleteBuilder.delete();
        Workbook workbook = Workbook.getWorkbook(new File(file));
        String type =" ";
            Sheet sheet = workbook.getSheet(0);
            System.out.println(sheet.getRows()+"| RL"); 
            int row = 4;
            int size_col=1;
            while (row<sheet.getRows()){
                Cell  A= sheet.getCell(1,row);// kod
                Cell  B= sheet.getCell(2,row);//art
                Cell  E= sheet.getCell(3,row);//desk
                Cell  F= sheet.getCell(6,row);//price rrc
                Cell  G= sheet.getCell(5,row);//price_u
                if((A.getCellFormat().getFont().getName().equalsIgnoreCase("Baskerville Old Face"))&&(A.getCellFormat().getFont().getPointSize()==16)){
                    type = A.getContents();
                   
                }
                
                if(A.getContents().startsWith("00")){
                    Double price =0.00;
                    Double price_u =0.00;
                    if(F.getContents().equals("")){
                        price =0.00;
                       // System.out.println("0.00_price");
                    } else{
//                        price = Double.valueOf(F.getContents().replace(",", "."));
                        NumberCell pr = (NumberCell)F;
                        price = pr.getValue();
                    }
                    if(G.getContents().equals("")){
                        price_u=0.00;
                       // System.out.println("0.00_price_u");
                    }else{
//                        price_u = Double.valueOf(G.getContents().replace(",", "."));
                        NumberCell pu = (NumberCell)G;
                        price_u = pu.getValue();
                    }
//                            if(!F.getContents().equals("")&&!G.getContents().equals("")){
//                              NumberCell pr = (NumberCell)F;
//                              price = pr.getValue();
//                              NumberCell pu = (NumberCell)G;
//                              price_u = pu.getValue();
//                            }
//                            if(price!=0){
                            
                               p= new Product(A.getContents(),
                                B.getContents(),
                                E.getContents(),
                                "",
                                setValue(price,0),price_u,
                                c.postach[1],
                                type,
                                setStatus(""+A.getContents(),setValue(price,0)));
                       c.produkt.create(p);  
//                            }
                    size_col++;
                }
                row++;
            }
            System.out.println("RL| "+size_col);
    }
    
     public  void CYFRO_excel(String file) throws IOException, BiffException, SQLException{
       // c = new common();
            DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
            deleteBuilder.where().eq("postach", c.postach[2]);
            deleteBuilder.delete();
            String desk;
            String kod;
            String art;
            Double price;
            Double price_u;
            String type;
            //read xlsx via poi
            FileInputStream fis = new FileInputStream(new File(file));
            XSSFWorkbook workbook = new XSSFWorkbook (fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator(); 
            System.out.println(sheet.getLastRowNum());
            int r=10;
            
            while(r<sheet.getLastRowNum()){
                XSSFRow row = sheet.getRow(r);
                 if(!row.getCell(3).getStringCellValue().equals("")){
                          
                           kod = Double.toString(row.getCell(2).getNumericCellValue());
                           System.out.println(kod);
                           price =row.getCell(6).getNumericCellValue(); 
                           price_u =row.getCell(5).getNumericCellValue(); 
                           if(price !=0){
                               p= new Product(kod.substring(0, kod.length()-2),//kod
                                row.getCell(1).getStringCellValue(),//art
                                row.getCell(3).getStringCellValue(),//desk
                                "",//magazin
                                setValue(price,0),price_u,//price
                                c.postach[2],
                                row.getCell(0).getStringCellValue(),
                                       setStatus(""+kod,setValue(price,0)));//type
                                c.produkt.create(p); 
                           }
                          
                       }
                r++;
            }

        
    fis.close();
     }

      public  void MYTAB_excel(String file) throws IOException, BiffException, SQLException{
    //    c = new common();
            DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
            deleteBuilder.where().eq("postach", c.postach[4]);
            deleteBuilder.delete();
            String desk;
            String kod;
            String art;
            Double price;
            Double price_u;
            String type="";
            String magazin;
            //read xlsx via poi
            FileInputStream fis = new FileInputStream(new File(file));
            XSSFWorkbook workbook = new XSSFWorkbook (fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator(); 
           // XSSFRow r = sheet.getRow(0);
           System.out.println(sheet.getLastRowNum());
            int r=2;
            while(r<sheet.getLastRowNum()){
                XSSFRow row = sheet.getRow(r);
                 short fontInd = row.getCell(2).getCellStyle().getFontIndex();
                        if(workbook.getFontAt(fontInd).getBold()){
                             type=  row.getCell(2).getStringCellValue(); 
                         }
                        
                       if(!workbook.getFontAt(fontInd).getBold()){
                           kod = Double.toString(row.getCell(1).getNumericCellValue());
                           price = row.getCell(3).getNumericCellValue();
                            price_u = row.getCell(7).getNumericCellValue();
                           if(price!=0){
                                p= new Product(kod.substring(0, kod.length()-2),//kod
                                row.getCell(0).getStringCellValue(), //art
                                row.getCell(2).getStringCellValue(),//desk
                                row.getCell(9).getStringCellValue(),//magazin
                                setValue(price,0),price_u,//price
                                c.postach[4],
                                type,
                                        setStatus(""+kod.substring(0, kod.length()-2),setValue(price,0)));
                                c.produkt.create(p);
                           }
                       }
                r++;
            }
//          
        
    fis.close();
     }

    public void KTS_excel(String file) throws IOException, BiffException, SQLException {
        //    c = new common();
            DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
            deleteBuilder.where().eq("postach", c.postach[3]);
            deleteBuilder.delete();   
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file),"Windows-1251"),';');
            String[] row;
            int r=0;
            while((row = reader.readNext()) != null) {
                if(r!=0){
                  String kod = row[0];
                  String dekr = row[3];
                  if(dekr.length()>249){
                      dekr = dekr.substring(0,249);
                  }
                  Double price = Double.valueOf(row[4].replace(",", "."));
                  double price_u=0.00;
                  if(price!=0){
                       p =new Product(row[0],
                            row[1],
                            dekr,
                            row[8],
                            Double.valueOf(row[4].replace(",", ".")),price_u,
                               c.postach[3],
                               row[2],
                               "");   
                     c.produkt.create(p);
                  }
                   
                }    
                    r++;
            } 
            System.out.println(r);

    }
    
    public void neo_excel(String file) throws SQLException, IOException, BiffException{
        
        
        String  groups [] ={"CPU",
                        "Пам'ять",
                        "MB AMD-Socket AM1",
                        "MB AMD-Socket AM2, AM3",
                        "MB AMD-Socket FM2",
                        "MB Intel -Socket 1150",
                        "MB Intel -Socket 1151",
                        "MB Intel -Socket 1155",
                        "MB Intel -Socket 2011",
                        "MB Intel -Socket 479",
                        "MB Intel -Socket LGA775",
                        "Відеокарти-ATI based",
                        "Відеокарти-Nvidia based",
                        "HDD Mobile",
                        "HDD",
                        "HDD Ext",
                        "SSD диски",
                        "CD-ROM/CDRW/DVD",
                        "Дисководи",
                        "Корпуси",
                        "Звукові карти",
                        "Колонки",
                        "Принтери",
                        "Мультифункціональні пристрої",
                        "Сканери",
                        "Монітори",
                        "Витратні матеріали",
                        "Охолоджувачі",
                        "Мультимедія",
                        "Клавіатури",
                        "Миші",
                        "Килимки",
                        "Мережні фільтри",
                        "Блоки живлення",
                        "Інтерфейсні плати",
                        "Notebook",
                        "Notebook - Аксесуари",
                        "PC-monoblock",
                        "USB Flash drive",
                        "USB MP3, MP4 плеєри",
                        "Акумулятори і батарейки",
                        "ББЖ",
                        "Відеореєстратори",
                        "Електронні книги",
                        "Зарядні пристрої",
                        "Кабельна продукція, перехіді з"+"\""+"єднання",
                        "Носії інформації",
                        "Папір",
                        "Планшети",
                        "Смартфони",
                        "Смартфони-Аксесуари",
                        "Сумки для ноутбуків /нетбуків",};
        
          DecimalFormat df = new DecimalFormat("#.##");
          DeleteBuilder<Product,String> deleteBuilder = c.produkt.deleteBuilder();
          deleteBuilder.where().eq("postach", c.postach[5]);
          deleteBuilder.delete();
          Workbook workbook = Workbook.getWorkbook(new File(file));
          Sheet sheet = workbook.getSheet(0);
          String kategorija ="";
          int row =  5;
          while (row<sheet.getRows()){
             Cell  A= sheet.getCell(0,row);// kategorija
             Cell  B= sheet.getCell(1,row);//firma
             Cell  K= sheet.getCell(10, row);//kod
             Cell  C= sheet.getCell(2,row);//art
             Cell  D= sheet.getCell(3, row);//desk
             String dekr = D.getContents();
             if(dekr.length()>249){
                      dekr = dekr.substring(0,249);
                  }
             Cell  I= sheet.getCell(8,row);//magazin
             Cell  G= sheet.getCell(6, row);//price
             Cell  F= sheet.getCell(5, row);//price_u
             Cell  E= sheet.getCell(4, 3);//valuta
           
             if(!B.getContents().equals("")&&(I.getContents().equals("+")||I.getContents().equals("r"))){
                 
                 for(String s:groups){
                     if(A.getContents().equals(s)){
                     //   Double price = Double.valueOf(G.getContents().replace(",", "."));
                         NumberCell p1 = (NumberCell)G;
                         NumberCell v = (NumberCell)E;
                         NumberCell p2 = (NumberCell)F;
                         Double price = p1.getValue();
                         if(A.getContents().startsWith("MB")){
                            kategorija = c.groups[0]; 
                         } else{
                             kategorija = A.getContents();
                         }
                         if(price!=0){
                           // Double price_u= Double.valueOf(E.getContents().replace(",", "."))*Double.valueOf(F.getContents().replace(",", "."));
                            Double price_u=v.getValue()*p2.getValue();
                             p =new Product(K.getContents(), //kod
                            C.getContents(), 
                            dekr,
                            I.getContents(), Precision.round(price, 0, BigDecimal.ROUND_HALF_UP), Precision.round(price_u, 2, BigDecimal.ROUND_HALF_UP),
                            c.postach[5],kategorija,
                                     setStatus(K.getContents(), Precision.round(price, 0, BigDecimal.ROUND_HALF_UP)));
                         c.produkt.create(p);
                         }
                        
                     }
                 }
                
             }
             row++;
          }
    }
    
    ///export to excel
    
    public void make_excel(List postach,LocalDate order_date) throws IOException, BiffException, WriteException, SQLException{
      Workbook wb = Workbook.getWorkbook(excel.class.getResourceAsStream("tmp/template.xls")); 
      QueryBuilder<Order,String> qb = c.order.queryBuilder();
      Instant instant  = order_date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
      
      
      for(Object s:postach){
          qb.where().eq("order_date", new CustomDate(Date.from(instant).getTime())).and().eq("postach", s);
         // qb.orderBy("postach", true);
          PreparedQuery<Order> preparedQuery = qb.prepare();
          List <Order> order = c.order.query(preparedQuery);
          WritableWorkbook new_wb = Workbook.createWorkbook(new File("out_excel/"+s+".xls"),wb);
          WritableSheet sheet =new_wb.getSheet(0);
          WritableFont wf = new WritableFont(WritableFont.ARIAL,10);
          WritableCellFormat cell_left = new WritableCellFormat(wf);
          WritableCellFormat cell_left_color = new WritableCellFormat(wf);
          cell_left.setWrap(true);
          cell_left.setBorder(Border.ALL, BorderLineStyle.THIN);
          cell_left_color.setWrap(true);
          cell_left_color.setBackground(Colour.GRAY_25);
          cell_left_color.setBorder(Border.ALL, BorderLineStyle.THIN);
          Double suma =0.00;
          int row=8; 
          for(int i=0;i<=order.size()-1;i++){
              sheet.addCell(new Label(0, row, order.get(i).getKod() , cell_left)); 
              sheet.addCell(new Label(1, row, order.get(i).getDeskr() , cell_left)); 
              sheet.addCell(new  jxl.write.Number(3, row, order.get(i).getPrice() , cell_left)); 
              sheet.addCell(new jxl.write.Number(2, row, order.get(i).getSt() , cell_left)); 
              sheet.addCell(new jxl.write.Number(4, row, order.get(i).getSumma() , cell_left)); 
              suma = suma + order.get(i).getSumma();
              row++;
          }
          sheet.addCell(new Label(3, row, "Всього, грн." , cell_left_color)); 
          sheet.addCell(new jxl.write.Number(4, row, suma , cell_left)); 


          new_wb.write();
          new_wb.close();
      }
      wb.close();
      
      
    }
    
    public void writeCSV() throws IOException, SQLException{
        String outputFile = "products_import.csv";
        boolean alreadyExists = new File(outputFile).exists();
        CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
        
        QueryBuilder<Product,String> qb = c.produkt.queryBuilder();
        qb.where().eq("postach", "РАДИОЛАЙН");
        PreparedQuery<Product> preparedQuery = qb.prepare();
        List <Product> g = c.produkt.query(preparedQuery);
        int id = 1;
        for(int i=0;i<=g.size()-1;i++){
            csvOutput.write(Integer.toString(id));
            csvOutput.write("1");
            csvOutput.write(g.get(i).getDeskr());
            csvOutput.write(g.get(i).getKateg());
            csvOutput.write(Double.toString(g.get(i).getPrice()));
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("РАДИОЛАЙН");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("10");
            csvOutput.write("1");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("0");
            csvOutput.write("");
            csvOutput.write("0");
            csvOutput.write("");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("0");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.write("");
            csvOutput.endRecord();
            id++;
        }
        csvOutput.close();
       
    }
    
    private Double setValue(Double value,int scale){
        return Precision.round(value, scale, BigDecimal.ROUND_HALF_UP);
    }
    
 
    private String setStatus(String kod,Double price) throws SQLException{
       String b="";
       GenericRawResults<String[]> rawResults = c.pmk_product_id.queryRaw("SELECT kod_postach from pmk_product_id where kod_postach='"+kod+"'");
       GenericRawResults<String[]> priceResults = c.pmk_product_id.queryRaw("SELECT price from pmk_product_id where kod_postach='"+kod+"'");
       if(rawResults.getFirstResult()==null){
           b="НОВЕ ПОСТУПЕЛННЯ"; 
       } 
       else{

           for(final String result[]:priceResults){
               if(result[0]!=null){
                   String s =Double.valueOf(result[0]).toString();
                   if(s.equals(price.toString())){
                       b="БЕЗ ЗМІН";
                       System.out.println(b);
                   }else{
                       b="ЦІНА НЕСПІВПАДАЄ";
                       System.out.println(b);
                   }
               }
           }
       }
       return b;
    }
}
