package com.soris.SORIS_planing.sp.Service.controllers;

import com.soris.SORIS_planing.sp.Service.models.serviceModel;
import com.soris.SORIS_planing.sp.Service.models.servicesSummaryModel;
import com.soris.SORIS_planing.sp.Service.models.topServicesModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "servicesSummaryServlet", value = "/servicesSummaryServlet")
public class servicesSummaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);

        if(session.getAttribute("userid") != null && session.getAttribute("role") == "sp"){
            String spID = (String) session.getAttribute("userid");

            try{
                servicesSummaryModel summarySer = new servicesSummaryModel();
                int servicesCount = summarySer.getServiceCount(spID);
                HashMap<String,Integer> summeryOfServices= summarySer.getSummeryOfServices(spID);
                HashMap<String,Integer> catSumOfServices= summarySer.getcatSumOfServices(spID);
                List<topServicesModel> _topservices = summarySer.getTopServices(spID);
                String address = summarySer.getAddress(spID);
                String contactno = summarySer.getPhoneNum(spID);
                String email = summarySer.getMail(spID);

                request.setAttribute("serviceCount",servicesCount);
                request.setAttribute("serviceSummery",summeryOfServices);
                request.setAttribute("catSumOfServices",catSumOfServices);
                request.setAttribute("topServices",_topservices);
                request.setAttribute("address", address);
                request.setAttribute("contactno", contactno);
                request.setAttribute("email",email);

                request.getRequestDispatcher("/sp-dashboard/servicesSummary.jsp").forward(request, response);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
