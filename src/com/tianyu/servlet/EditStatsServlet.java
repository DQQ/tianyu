package com.tianyu.servlet;

import com.tianyu.mode.StatsBean;
import com.tianyu.utils.DbHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dongqingqing on 17/9/8.
 */
@WebServlet(name = "EditStatsServlet")
public class EditStatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (method.equals("delete")) {
            delete(request, response);
        }else if (method.equals("update")) {
            update(request, response);
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String sql = "delete from stats where id=" + id;
        int sql_res = 0 ;

        QueryRunner qr = DbHelper.getQueryRunner();
        try {
            sql_res = qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String message = "";

        if (sql_res == 1) {
            message = "删除成功！";
        } else {
            message = "删除失败！";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        String groups = request.getParameter("groups");
        String content = request.getParameter("content");
        String result = request.getParameter("ispass");
        String developer = request.getParameter("developer");
        String pd = request.getParameter("pd");
        String apkversion = request.getParameter("apkversion");
        String tester = request.getParameter("tester");
        String reason = request.getParameter("reason");

        String apkplugs = request.getParameter("apkplugs");
        String isrequirement = request.getParameter("isrequirement");
        String isproducts = request.getParameter("isproducts");
        String isboning = request.getParameter("isboning");

        DataSource ds = null;
        int sql_res = 0;


        try {
            // 通过在context.xml文件，设定的数据源对象的名字，获取数据源对象。
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
//            id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning,reason
            String sql = "update stats set time=?, groups=?,content=?,developer=?,pd=?,apkversion=?,tester=?,reason=? where id="+id;
            // 为SQL语句中的？设定参数。
            String params[] = {time, groups, content, developer, pd, apkversion, tester, reason};

            // DButils中核心类，生成对象时传递数据源对象。
            QueryRunner qr = new QueryRunner(ds);
            sql_res = qr.update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = "";

        if (sql_res == 1) {
            message = "修改成功！";
        } else {
            message = "修改失败！";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String sql = "select id,time, groups,content,result,developer,pd,apkversion,apkplugs,tester,isrequirement,isproducts,isboning,reason from stats where id=" + id;

        QueryRunner qr = DbHelper.getQueryRunner();
        StatsBean stats = null;

        try {
            List list = (List) qr.query(sql,new BeanListHandler(StatsBean.class));
            stats = (StatsBean) list.get(0);
            System.out.println("stats=======size:"+stats);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("stats", stats);
        request.getRequestDispatcher("/editStats.jsp").forward(request, response);
    }
}