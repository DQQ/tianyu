package com.tianyu.servlet;

import com.tianyu.mode.StatsBean;
import org.apache.commons.dbutils.QueryRunner;

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

/**
 * Created by dongqingqing on 17/9/5.
 */

@WebServlet(name = "AddStatsServlet")
public class AddStatsServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String time = req.getParameter("time");
        String groups = req.getParameter("group");
        String content = req.getParameter("content");
        String developer = req.getParameter("developer");
        String pd = req.getParameter("pd");
        String apkversion = req.getParameter("apkversion");
        String tester = req.getParameter("tester");
        String reason = req.getParameter("reason");
        String apkplugs = req.getParameter("apkplugs");
        String isrequirement = req.getParameter("isrequirement");
        String isproducts = req.getParameter("isproducts");
        String isboning = req.getParameter("isboning");
        String result = req.getParameter("ispass");

        DataSource ds = null;
        int sql_res = 0;
        String message = "";
        String sql = "insert into stats(time,groups,content,result,developer,pd,apkversion,apkplugs,isrequirement,isproducts,isboning,tester,reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        // 为SQL语句中的？设定参数。
        String params[] = {time, groups, content, result, developer, pd, apkversion, apkplugs, isrequirement, isproducts, isboning, tester, reason};

        try {
            // 通过在context.xml文件，设定的数据源对象的名字，获取数据源对象。
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (content.length() != 0 && tester.length() != 0) {
            try {
                // DButils中核心类，生成对象时传递数据源对象。
                QueryRunner qr = new QueryRunner(ds);
                sql_res = qr.update(sql, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (sql_res == 1) {
            message = "添加成功！";
        } else {
            message = "添加失败！";
        }
        if ("".equals(content)) {
            message = "提测邮件不能为空！";
        }
        if ("".equals(tester)) {
            message = "记录人不能为空！";
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("/addstats.jsp").forward(req, resp);

    }
}