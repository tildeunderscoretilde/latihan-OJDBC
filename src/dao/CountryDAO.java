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
public class CountryDAO {

    private Connection koneksi;

    public CountryDAO() {
    }

    public CountryDAO(Connection koneksi) {
        this.koneksi = koneksi;
    }
    
    public List<Country> getAll() {
        List<Country> countries = new ArrayList();
        try {
            String query = "SELECT * FROM COUNTRIES";
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountryId(resultSet.getString("country_id"));
                country.setCountryName(resultSet.getString("country_name"));
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return countries;
    }

    public List<Country> getById() {
        List<Country> countries = new ArrayList();
        String id;
        Scanner input = new Scanner(System.in);
        System.out.println("Input ID : ");
        id = input.nextLine();
        try {
            String query = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID LIKE '%" + id.toUpperCase() + "%'";
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountryId(resultSet.getString("country_id"));
                country.setCountryName(resultSet.getString("country_name"));
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return countries;
    }

    public List<Country> getBySearch() {
        List<Country> countries = new ArrayList();
        String search,id,name,namesr,query = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Pick Search : Id or Name");
        search = input.nextLine();
        
        if(search.equals("ID") || search.equals("Id") || search.equals("id")){
            System.out.println("Id : ");
            id = input.nextLine();
            query = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID LIKE '%"+ id.toUpperCase() + "%'";
        }
        if(search.equals("NAME") || search.equals("Name") || search.equals("name")){
            System.out.println("Name : ");
            name = input.nextLine();
            namesr = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            query = "SELECT * FROM COUNTRIES WHERE COUNTRY_NAME LIKE '%"+ namesr +"%'";
            //query = "SELECT * FROM REGIONS WHERE REGEXP_LIKE(REGION_NAME, '"+ name +"','i')";
        }
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = new Country();
                country.setCountryId(resultSet.getString("country_id"));
                country.setCountryName(resultSet.getString("country_name"));
                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return countries;
    }
    
    
    
    //=============================================== VERSI PENDEKNYA =========================================================
    
    /**
     * function untuk menjalankan segala input query
     * @param query input query yang akan dijalankan
     * @return mengembalikan nilai tabel
     */
    public List<Country> getData(String query){
        List<Country> countries = new ArrayList();
        try {
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Country country = new Country();
                country.setCountryId(resultSet.getString("country_id"));
                country.setCountryName(resultSet.getString("country_name"));
                country.add(country);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return countries;
    }
    
    /**
     * Function untuk menjalankan segala input query (versi gabung region)
     * @param query input query yang akan dijalankan
     * @return mengembalikan nilai tabel
     */
    public List<Country> getData2(String query){
        List<Country> countries = new ArrayList();
        try {
            PreparedStatement statement = koneksi.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String countryId = resultSet.getString(2);
                String countryName = resultSet.getString(3);             
                int regionId = resultSet.getInt(1);
                String regionName = resultSet.getString(4);
                
                Region region = new Region(regionId, regionName);
                Country country = new Country(countryId, countryName, region);
                
                countries.add(country);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return countries;
    }
    
    /**
     * Function untuk menampilkan semua data tabel
     * @return mengembalikan query yang ingin dijalankan
     */
    public List<Country> getAllDatas(){
        return this.getData("SELECT * FROM COUNTRIES ORDER BY 1");
    }
    
    /**
     * Function untuk mencari data berdasarkan ID
     * @param countryId id ke berapa / ID dari data tersebut
     * @return mengembalikan query yang ingin dijalankan
     */
    public List<Country> getById(String countryId){
        return this.getData("SELECT * FROM COUNTRIES WHERE REGEXP_LIKE(COUNTRY_ID, '" + countryId + "', 'i'");
    }
    
    /**
     * Function untuk mencari data berdasarkan ID dengan menjalankan getById
     * @param countryId id dari country tersebut
     * @return mengembalikan query yang ingin dijalankan
     */
    public Country getByIds(String countryId){
        return (Country) this.getById(countryId).get(0);
    }
    
    
    /**
     * Function untuk menjalankan query yang ingin dicari
     * @param category kolom yang ingin dicari
     * @param cari data apa yang ingin dicari (id-nya kah, namanya kah)
     * @return mengembalikan query yang ingin dijalankan
     */
    public List<Country> search(String category, String cari){
        return this.getData("SELECT * FROM COUNTRIES REGEXP_LIKE(" + category + ", " + cari + " ORDER BY 1");
    }
    
    /**
     * Function untuk menjalankan query untuk menampilkan gabungan tabel countries dengan reions
     * @return mengembalikan query yang ingin dijalankan
     */
    public List<Country> getCountries(){
        return this.getData2("SELECT * from countries NATURAL JOIN regions ORDER BY 1");
    }
}
