/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entitas.Region;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tools.Koneksi;

/**
 *
 * @author Martin
 */
public class RegionDAOTest {
    
    public RegionDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getAllRegions() {
        System.out.println("Get All Region");
        Connection koneksi = new Koneksi().getKoneksi();
        RegionDAO rdao = new RegionDAO(koneksi);
        List<Region> regions = rdao.getAll();
        Assert.assertNotNull(regions);
    }
    
}
