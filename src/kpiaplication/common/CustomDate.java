/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.common;

/**
 *
 * @author Алексей
 */
import java.text.SimpleDateFormat;

public class CustomDate extends java.sql.Date {

    public CustomDate(long date) {
        super(date);
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(this);
    }
}
