/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.model;

import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import kpiaplication.data.db.Product;

/**
 *
 * @author aleksej
 */
public class CheckboxTableCellFactory implements Callback{

    @Override
    public Object call(Object param) {
        CheckBoxTableCell<Product,Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    }

   
    
}
