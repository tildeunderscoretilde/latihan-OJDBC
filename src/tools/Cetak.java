package tools;

import dao.CountryDAO;
import dao.RegionDAO;
import entitas.Country;
import entitas.Region;
import java.util.Scanner;

/**
 *
 * @author Martin
 */
public class Cetak {
    public static void main(String[] args){
        
        
//        RegionDAO rdao = new RegionDAO(new Koneksi().getKoneksi());
//        for (Region region : rdao.getAll()) {
//            System.out.println(region.getRegionId() + " - " + region.getRegionName());
//        }
        
        RegionDAO rdaoall = new RegionDAO(new Koneksi().getKoneksi());
        int temp = 0;
        for (Region region : rdaoall.getRegions()) {
            int id = region.getRegionId();
            if(id != temp) 
            {
                System.out.println("\n" + id + ".  " + region.getRegionName() + " -> ");
                temp = id;
            }
            else{
            System.out.println(region.getCountry().getCountryId() + " - " 
                    + region.getCountry().getCountryName());
            }
        }
        
//        RegionDAO rdaoid = new RegionDAO(new Koneksi().getKoneksi());
//        for (Region region : rdaoid.getById()) {
//            System.out.println(region.getRegionId() + " - " + region.getRegionName());
//        }
        
//        RegionDAO rdaosr = new RegionDAO(new Koneksi().getKoneksi());
//        for (Region region : rdaosr.getBySearch()) {
//            System.out.println(region.getRegionId() + " - " + region.getRegionName());
//        }
        
//        CountryDAO cdao = new CountryDAO(new Koneksi().getKoneksi());
//        for (Country country : cdao.getAll()) {
//            System.out.println(country.getCountryId() + " - " + country.getCountryName());
//        }
        
//        CountryDAO cdaoid = new CountryDAO(new Koneksi().getKoneksi());
//         for (Country country : cdaoid.getById()) {
//            System.out.println(country.getCountryId() + " - " + country.getCountryName());
//        }

//        CountryDAO cdaosr = new CountryDAO(new Koneksi().getKoneksi());
//        for (Country country : cdaosr.getBySearch()) {
//            System.out.println(country.getCountryId() + " - " + country.getCountryName());
//        }
        
//        CountryDAO cdaoall = new CountryDAO(new Koneksi().getKoneksi());
//        for (Country country : cdaoall.getCountries()) {
//            System.out.println(country.getCountryId() + " - " + country.getCountryName() + " - " + country.getRegion().getRegionId());
//        }
    }
}
