package com.soris.SORIS_planing.sp.Service.models;


import com.soris.SORIS_planing.admin.dashboard.models.topServiceModel;
import com.soris.SORIS_planing.dbUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class servicesSummaryModel {
    /*private int isSuccess;*/
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs;

    //get services count
    public int getServiceCount(String spID){
        try{
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();

            String sql = "SELECT COUNT(*) FROM service WHERE spid = '"+spID+"'";
            rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getInt(1);

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public HashMap<String, Integer> getSummeryOfServices(String spID){

        try {
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();
            int total = 0;

            String sql = "SELECT status, COUNT(*) FROM service WHERE spid = '"+spID+"' GROUP BY status";
            rs = stmt.executeQuery(sql);
            HashMap<String,Integer> statusSet = new HashMap<String,Integer>();

            while(rs.next()) {
                statusSet.put(rs.getString("status"), rs.getInt(2));
                total+=rs.getInt(2);
            }

            statusSet.put("total", total);
            return statusSet;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }


    public HashMap<String, Integer> getcatSumOfServices(String spID){

        try {
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();
            int total = 0;

            String sql = "SELECT category, COUNT(*) FROM service WHERE spid = '"+spID+"' GROUP BY category";
            rs = stmt.executeQuery(sql);
            HashMap<String,Integer> statusSet = new HashMap<String,Integer>();

            while(rs.next()) {
                statusSet.put(rs.getString("category"), rs.getInt(2));
                total+=rs.getInt(2);
            }

            statusSet.put("total", total);
            return statusSet;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public List<topServiceModel> getTopServices(String spID){
        List<topServiceModel> sList = new ArrayList<topServiceModel>();

        try{
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();

            String sql = "SELECT service.sid, name, services.summ, category, price, discount, description, status FROM service INNER JOIN \n" +
                    "(SELECT sid, sum(count) AS summ FROM soris.eventservices WHERE spid = '"+spID+"' group by sid) AS services ON services.sid = service.sid ORDER BY services.summ DESC;";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                topServiceModel topService = new topServiceModel();
                topService.setName(rs.getString("name"));
                topService.setCount(rs.getInt("summ"));
                topService.setCategory(rs.getString("category"));
                topService.setPrice((float)rs.getInt("price"));
                topService.setDiscount( (float)rs.getInt("discount"));
                sList.add(topService);

            }
            return sList;
        }
        catch (Exception ex){
            return null;
        }
    }

    public String getAddress(String spID){
        try{
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();

            String sql = "SELECT address FROM serviceprovider WHERE spid = '"+spID+"'";
            rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString(1);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getPhoneNum(String spID){
        try{
            con = dbUtil.initializeDatabase();
            stmt = con.createStatement();

            String sql = "SELECT contactno FROM serviceprovider WHERE spid = '"+spID+"'";
            rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString(1);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
