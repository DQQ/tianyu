package com.tianyu.utils;

import org.apache.commons.dbutils.QueryRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Created by dongqingqing on 17/9/6.
 */
public class DbHelper {
    public static QueryRunner getQueryRunner() {

        DataSource ds = null;

        try {
            // 通过在context.xml文件，设定的数据源对象的名字，获取数据源对象。
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
        } catch (Exception e) {
            e.printStackTrace();
        }
        QueryRunner qr = new QueryRunner(ds);
        return qr;
    }


}
