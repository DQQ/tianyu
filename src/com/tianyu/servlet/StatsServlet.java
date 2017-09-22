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
 * Created by dongqingqing on 17/9/13.
 */
@WebServlet(name = "StatsServlet")
public class StatsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 解决从jsp页面表单接受参数出现的中文乱码问题。
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if (method.equals("add")) {
            add(request, response);
        } else if (method.equals("delete")) {
            delete(request, response);
        } else if (method.equals("edit")) {
            perEdit(request, response);
        } else if (method.equals("update")) {
            update(request, response);
        } else if (method.equals("list")) {
            list(request, response);
        } else {
            list(request, response);
        }
    }


    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String sql = "select id,time, groups,content,result,developer,pd,model,tester,reason from stats order by id desc limit 0,50;";

        QueryRunner qr = DbHelper.getQueryRunner();
        List list = null;
        try {
            list = (List) qr.query(sql, new BeanListHandler(StatsBean.class));
            System.out.println("===list size====="+list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("/allstats.jsp").forward(request, response);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String time = req.getParameter("time");
        String groups = req.getParameter("group");
        String content = req.getParameter("content");
        String result = req.getParameter("ispass");
        String developer = req.getParameter("developer");
        String pd = req.getParameter("pd");
        String model = req.getParameter("model");
        String tester = req.getParameter("tester");
        String reason = req.getParameter("reason");

        DataSource ds = null;
        int sql_res = 0;
        String message = "";
        String sql = "insert into stats(time,groups,content,result,developer,pd,model,tester,reason) values(?,?,?,?,?,?,?,?,?)";
        // 为SQL语句中的？设定参数。
        String params[] = {time, groups, content, result, developer, pd, model, tester, reason};


        try {
            // 通过在context.xml文件，设定的数据源对象的名字，获取数据源对象。
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // DButils中核心类，生成对象时传递数据源对象。
            QueryRunner qr = new QueryRunner(ds);
            sql_res = qr.update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (sql_res == 1) {
            message = "添加成功！";
        } else {
            message = "添加失败！";
        }if ("".equals(content)) {
            message = "提测邮件不能为空！";
        }if ("".equals(tester)) {
            message = "记录人不能为空！";
        }

        req.setAttribute("message", message);
        req.getRequestDispatcher("/addstats.jsp").forward(req, resp);

    }

    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String sql = "delete from stats where id=" + id;

        QueryRunner qr = DbHelper.getQueryRunner();
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/servlet/categoryServlet?method=list").forward(request, response);
    }

    public void perEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String sql = "select id,time, groups,content,result,developer,pd,model,tester,reason from stats where id=" + id;

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

    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        String groups = request.getParameter("groups");
        String content = request.getParameter("content");
        String developer = request.getParameter("developer");
        String pd = request.getParameter("pd");
        String model = request.getParameter("model");
        String tester = request.getParameter("tester");
        String reason = request.getParameter("reason");

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
            String sql = "update stats set time=?, groups=?,content=?,developer=?,pd=?,model=?,tester=?,reason=? where id="+id;
            // 为SQL语句中的？设定参数。
            String params[] = {time, groups, content, developer, pd, model, tester, reason};

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
        request.getRequestDispatcher("/tianyu").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
