/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kpiaplication.common.error;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Алексей
 */
public class error_log {

    public error_log() {
    }
    
    public void write_log_error(String text) throws IOException{
        FileWriter fstream = new FileWriter("error.log", true);
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(text);
            out.newLine();
        }
    }
    
}
