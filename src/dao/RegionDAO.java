/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitas.Country;
import entitas.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class RegionDAO {
    private Connection koneksi;

    public RegionDAO() {
    }
    
    public RegionDAO(Connection koneksi) {
        this.koneksi = koneksi;
    }
    
    public List<Region> getAll(){
        List<Region> regions = new ArrayList();
        try {
            String query = "SELECT * FROM REGIONS";
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Region region = new Region();
                region.setRegionId(resultSet.getInt("region_id"));
                region.setRegionName(resultSet.getString("region_name"));
                regions.add(region);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return regions;
    }
    
    public List<Region> getById(){
        List<Region> regions = new ArrayList();
        Scanner input = new Scanner(System.in);
        System.out.println("Id : ");
        int id = input.nextInt();
        
        try {
            String query = "SELECT * FROM REGIONS WHERE REGION_ID = "+id;
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Region region = new Region();
                region.setRegionId(resultSet.getInt("region_id"));
                region.setRegionName(resultSet.getString("region_name"));
                regions.add(region);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return regions;
    }
    
    public List<Region> getBySearch(){
        List<Region> regions = new ArrayList();
        String search,name,namesr,query = "";
        int id = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Pick Search : Id or Name");
        search = input.nextLine();
        
        if(search.equals("ID") || search.equals("Id") || search.equals("id")){
            System.out.println("Id Number : ");
            id = input.nextInt();
            query = "SELECT * FROM REGIONS WHERE REGION_ID LIKE '%"+ id + "%'";
        }
        if(search.equals("NAME") || search.equals("Name") || search.equals("name")){
            System.out.println("Name : ");
            name = input.nextLine();
            namesr = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            query = "SELECT * FROM REGIONS WHERE REGION_NAME LIKE '%"+ namesr +"%'";
            //query = "SELECT * FROM REGIONS WHERE REGEXP_LIKE(REGION_NAME, '"+ name +"','i')";
        }  
        
        try {
//            String query = "SELECT * FROM REGIONS WHERE REGION_ID = '1'";
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Region region = new Region();
                region.setRegionId(resultSet.getInt("region_id"));
                region.setRegionName(resultSet.getString("region_name"));
                regions.add(region);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return regions;
    }
    
    
    //=============================================== VERSI PENDEKNYA =========================================================
    
    /**
     * function untuk menjalankan segala input query
     * @param query input query yang akan dijalankan
     * @return mengembalikan nilai tabel
     */
    public List<Region> getData(String query){
        List<Region> regions = new ArrayList();
        try {
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Region region = new Region();
                region.setRegionId(resultSet.getInt("region_id"));
                region.setRegionName(resultSet.getString("region_name"));
                regions.add(region);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return regions;
    }
    
    /**
     * Function untuk menjalankan segala input query (versi gabung country)
     * @param query input query yang akan dijalankan
     * @return mengembalikan nilai tabel
     */
    public List<Region> getData2(String query){
        List<Region> regions = new ArrayList();
        try {
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int regionId = resultSet.getInt(1);
                String regionName = resultSet.getString(2);             
                String countryId = resultSet.getString(3);
                String countryName = resultSet.getString(4);
                
                Country country = new Country(countryId, countryName);
                Region region = new Region(regionId, regionName, country);
                                
                regions.add(region);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return regions;
    }
    
    /**
     * Function untuk menampilkan semua data tabel
     * @return mengembalikan query yang ingin dijalankan
     */
    public List<Region> getAllDatas(){
        return this.getData("SELECT * FROM REGIONS ORDER BY 1");
    }
    
    /**
     * Function untuk mencari data berdasarkan ID
     * @param regionId id ke berapa / ID dari data tersebut
     * @return 
     */
    public List<Region> getById(int regionId){
        return this.getData("SELECT * FROM REGIONS WHERE REGION_ID = " + regionId +"ORDER BY 1");
    }
    
    /**
     * Function untuk mencari data berdasarkan ID dengan menjalankan getById
     * @param regionId id ke berapa / ID dari data tersebut
     * @return 
     */
    public Region getByIds(int regionId){
        return (Region) this.getById(regionId).get(0);
    }
    
    
    /**
     * Function untuk menjalankan query yang ingin dicari
     * @param category tabel yang ingin dicari
     * @param cari data apa yang ingin dicari
     * @return 
     */
    public List<Region> search(String category, String cari){
        return this.getData("SELECT * FROM REGEXP_LIKE(" + category + ", " + cari + " ORDER BY 1");
    }
    
    
    public List<Region> getRegions(){
        return this.getData2("SELECT * from regions NATURAL JOIN countries ORDER BY 1");
    }
    
}
