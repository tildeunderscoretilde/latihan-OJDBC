/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitas;
import java.util.List;

/**
 *
 * @author Martin
 */
public class Region {
    private int regionId;
    private String regionName;
    private Country country;
    private List<Country> countries;

    public Region() {
    }

    public Region(int regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Region(int regionID, String regionName, List<Country> countries) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.countries = countries;
    }

    public Region(int regionId, String regionName, Country country) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.country = country;
    }

    public Region(int regionId, String regionName, Country country, List<Country> countries) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.country = country;
        this.countries = countries;
    }
    
    
    
    /**
     * @return the regionID
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionID to set
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName the regionName to set
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return the countries
     */
    public List<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }
    
    
}
