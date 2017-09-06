package com.tianyu.servlet;

import com.tianyu.mode.AllStatsDataBean;
import org.apache.commons.dbutils.QueryRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by dongqingqing on 17/9/5.
 */
public class AddStatsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AllStatsDataBean stats = (AllStatsDataBean) session.getAttribute("stats");

//        if (stats == null) {
//            resp.sendRedirect("/tianyu/404.html");
//        } else {
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

            try {
                // 通过在context.xml文件，设定的数据源对象的名字，获取数据源对象。
                Context context = new InitialContext();
                ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
//				for(double i=1;i<=100000;i++) {
                String sql = "insert into stats(time,groups,content,result,developer,pd,model,tester,reason) values(?,?,?,?,?,?,?,?,?)";

                // 为SQL语句中的？设定参数。
                String params[] = { time,groups,content,result,developer,pd,model,tester,reason };
                // DButils中核心类，生成对象时传递数据源对象。
                QueryRunner qr = new QueryRunner(ds);
                sql_res = qr.update(sql, params);
//				}
            } catch (Exception e) {
                e.printStackTrace();
            }
            String message = "";
            if (sql_res == 1) {
                message = "添加成功！";
            } else {
                message = "添加失败！";
            }

            req.setAttribute("message", message);
            req.getRequestDispatcher("/addstats.jsp").forward(req, resp);

        }
    }
//}
