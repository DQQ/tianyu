package com.tianyu.servlet;

import com.tianyu.mode.CountBean;
import com.tianyu.mode.StatsBean;
import com.tianyu.utils.ConnectDb;
import com.tianyu.utils.DbHelper;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dongqingqing on 17/9/6.
 */

@WebServlet(name = "AllStatsServlet")
public class AllStatsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (method == null) {
            list(request,response);
        } else if (method.equals("noPass")) {
            noPass(request,response);
        }
//        else if (method.equals("requirQuery")){
//            requirQuery(request,response);
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
//        String selectedgroup = "CM";
//        String dayfrom = "2017-09-09";
//        String dayto = "2017-09-19";
        String selectedgroup = request.getParameter("selectedgroup");
        String dayfrom = request.getParameter("dayfrom");
        String dayto = request.getParameter("dayto");

        if (dayfrom.length() !=0 && dayto.length() != 0) {
            String sql_repuir = "select id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning,reason from stats " +
                    "where groups='" + selectedgroup + "' and time between '" + dayfrom + "' and '" + dayto + "'order by id desc";

            QueryRunner qr = DbHelper.getQueryRunner();
            List list = null;
            try {
                list = (List) qr.query(sql_repuir, new BeanListHandler(StatsBean.class));
                System.out.println("===sql_repuir size=====" + list.size());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("list", list);
            request.getRequestDispatcher("/allstats.jsp").forward(request, response);
        } else {
            String sql_repuir = "select id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning,reason from stats " +
                    "where groups='" + selectedgroup + "'order by id desc";

            QueryRunner qr = DbHelper.getQueryRunner();
            List list = null;
            try {
                list = (List) qr.query(sql_repuir, new BeanListHandler(StatsBean.class));
                System.out.println("===sql_repuir size=====" + list.size());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("list", list);
            request.getRequestDispatcher("/allstats.jsp").forward(request, response);
        }
    }

    protected void noPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sql_nopass = "select id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning," +
                "reason from stats where result='否'";

        QueryRunner qr = DbHelper.getQueryRunner();
        List list = null;
        try {
            list = (List) qr.query(sql_nopass, new BeanListHandler(StatsBean.class));
            System.out.println("===list size====="+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("/allstats.jsp").forward(request, response);

    }


    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String groups = request.getParameter("all");
        String sql = "select id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning,reason from stats order by id desc";
//        if (groups.equals("null")) {
//            sql = "select id,time, groups,content,result,developer,pd,model,tester,reason from stats order by id desc limit 0,50";
//        } else {
//            sql = "select id,time, groups,content,result,developer,pd,model,tester,reason from stats where groups="+groups;
//        }
        String sql_conut_all = "select count(*) as conut_all from stats";
        String sql_conut_pass = "select count(*) as conut_pass from stats where result='是'";
        String sql_count_fail = "select count(*) as count_fail from stats where result='否'";


        QueryRunner qr = DbHelper.getQueryRunner();
        List list = null;
        List list_conut_all = null;
        List list_conut_pass = null;
        List list_conut_fail = null;
        CountBean sball = null;
        CountBean sbpass =null;
        CountBean sbfail = null;
        try {
            list = (List) qr.query(sql, new BeanListHandler(StatsBean.class));
            list_conut_all = (List)qr.query(sql_conut_all,new BeanListHandler(CountBean.class));
            list_conut_pass = (List)qr.query(sql_conut_pass,new BeanListHandler(CountBean.class));
            list_conut_fail = (List)qr.query(sql_count_fail,new BeanListHandler(CountBean.class));

            sball = (CountBean) list_conut_all.get(0);
            sbpass = (CountBean) list_conut_pass.get(0);
            sbfail = (CountBean) list_conut_fail.get(0);

//            System.out.println("===list size====="+list.size());
//            System.out.println("===all====="+list_conut_all.get(0));
//            System.out.println("===pass====="+list_conut_pass.get(0));
//            System.out.println("===fail====="+list_conut_fail.get(0));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("list", list);
        request.setAttribute("sball", sball);
        request.setAttribute("sbpass", sbpass);
        request.setAttribute("sbfail", sbfail);
        request.getRequestDispatcher("/allstats.jsp").forward(request, response);
    }
}