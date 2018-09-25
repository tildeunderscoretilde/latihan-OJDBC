/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.sql.Connection;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Martin
 */
public class Koneksi {
    private Connection koneksi = null;
    
    public Connection getKoneksi(){
        try{
            OracleDataSource ods = new OracleDataSource();
            ods.setDriverType("thin");
            ods.setServerName("localhost");
            ods.setPortNumber(1521);
            ods.setServiceName("XE");
            ods.setUser("system");
            ods.setPassword("admin");
            koneksi = ods.getConnection();
            koneksi.createStatement().execute("alter session" + " set current_schema=hr");
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return koneksi;
    }
}
