package com.soris.SORIS_planing.host.services.models;

import com.soris.SORIS_planing.dbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class serviceHandlerModel {
    private Connection con;


    public serviceHandlerModel() throws SQLException, ClassNotFoundException {
        Connection con = dbUtil.initializeDatabase();
        this.con = con;
    }

    public List<serviceModel> serviceListByCategory(String category){
        List<serviceModel> serviceList =  new ArrayList<>();
        try {

            String query = String.format("SELECT * FROM service WHERE category='%s' AND status='approved'",category);
            Statement st = this.con.createStatement();
            ResultSet res = st.executeQuery(query);


            while(res.next()) {
                serviceModel service = new serviceModel();
                service.setSid(res.getString("sid"));
                service.setSpid(res.getString("spid"));
                service.setName(res.getString("name"));
                service.setCategory(res.getString("category"));
                service.setPrice(res.getString("price"));
                service.setDiscount(res.getString("discount"));
                service.setDescription(res.getString("description"));
                service.setStatus(res.getString("status"));
                service.setImages(res.getString("images"));

                serviceList.add(service);

            }
            return serviceList;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public serviceModel getServiceById(String sid){

        try {

            String query = String.format("SELECT * FROM service WHERE  sid='%s'",sid);
            Statement st = this.con.createStatement();
            ResultSet res = st.executeQuery(query);
            res.next();
            serviceModel service = new serviceModel();

            service.setSid(res.getString("sid"));
            service.setSpid(res.getString("spid"));
            service.setName(res.getString("name"));
            service.setCategory(res.getString("category"));
            service.setPrice(res.getString("price"));
            service.setDiscount(res.getString("discount"));
            service.setDescription(res.getString("description"));
            service.setStatus(res.getString("status"));
            service.setImages(res.getString("images"));

            return service;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

    public spModel getServiceProviderById(String spid){
        try {

            String query = String.format("SELECT * FROM serviceprovider WHERE  spid='%s'",spid);
            Statement st = this.con.createStatement();
            ResultSet res = st.executeQuery(query);
            res.next();
            spModel serviceProvider = new spModel();

            serviceProvider.setSpid(Integer.parseInt(res.getString("spid")));
            serviceProvider.setBrandname(res.getString("brandname"));
            serviceProvider.setContactno(res.getString("contactno"));
            serviceProvider.setAddress(res.getString("address"));
            serviceProvider.setEmail(res.getString("email"));
            serviceProvider.setPassword(res.getString("password"));

            return serviceProvider;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }
}
