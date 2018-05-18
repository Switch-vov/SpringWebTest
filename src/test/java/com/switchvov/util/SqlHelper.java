package com.switchvov.util;

import java.io.InputStream;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Switch
 * @function 提供数据库访问方法
 */
public class SqlHelper {
    // 定义需要的变量
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static CallableStatement cs = null;

    // 连接数据库参数
    private static String url = "";
    private static String user = "";
    private static String password = "";
    private static String driver = "";

    private static Properties pp = null;
    private static InputStream fis = null;

    // 静态代码块加载驱动
    static {
        try {
            // 从dbInfo.properties文件中读取配置信息
            pp = new Properties();
            try {
                fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbInfo.properties"); //new FileInputStream("");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                pp.load(fis);
                url = pp.getProperty("url");
                user = pp.getProperty("user");
                password = pp.getProperty("password");
                driver = pp.getProperty("driver");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fis = null;
        }
    }

    // 得到连接
    public static Connection getConnection() {
        try {
            ct = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ct;
    }

    // 获得PreparedStatement
    public static PreparedStatement getPreparedStatement() {
        return ps;
    }

    // 得到Connection
    public static Connection getCt() {
        return ct;
    }

    // 得到PreparedStatement
    public static PreparedStatement getPs() {
        return ps;
    }

    // 得到CallableStatement
    public static CallableStatement getCs() {
        return cs;
    }

    // 得到ResultSet
    public static ResultSet getRs() {
        return rs;
    }

    // 调用存储过程，无返回值
    // sql {call 过程名(?,?,?)}
    public static void callPro1(String sql, String[] parameters) {
        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            // ?赋值
            if (parameters != null && !parameters.equals("")) {
                for (int i = 0; i < parameters.length; i++) {
                    cs.setObject(i + 1, parameters[i]);
                }
            }
            cs.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, cs, ct);
        }
    }

    // 调用存储过程，有返回值
    public static CallableStatement callPro2(String sql, String[] inParameters,
                                             Integer[] outParameters) {

        try {
            ct = getConnection();
            cs = ct.prepareCall(sql);
            // 输入参数
            if (inParameters != null && !inParameters.equals("")) {
                for (int i = 0; i < inParameters.length; i++) {
                    cs.setObject(i + 1, inParameters[i]);
                }
            }
            // 输出参数
            if (outParameters != null) {
                for (int i = 0; i < outParameters.length; i++) {
                    cs.registerOutParameter(inParameters.length + i + 1,
                            outParameters[i]);
                }
            }
            cs.execute();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return cs;
    }

    // select方法
    public static ResultSet executeQuery(String sql, String[] parameters) {
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return rs;
    }

    // select方法2(更安全)----规则：哪里使用资源，哪里关闭资源
    public static ArrayList executeQuery2(String sql, String[] parameters) {
        ArrayList result = null;
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
            // 获取结果原始信息
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int column = resultSetMetaData.getColumnCount();

            result = new ArrayList();
            // 一行数据
            while (rs.next()) {
                // 对象数组，表示一行数据
                Object[] ob = new Object[column];
                for (int i = 1; i <= column; i++) {
                    // 一项数据
                    ob[i - 1] = rs.getObject(i);
                }
                result.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            SqlHelper.close(rs, ps, ct);
        }
        return result;
    }

    // 考虑事务的update/delete/insert(DML)方法
    public static void executeUpdate(String[] sql, String[][] parameters) {
        try {
            // 获得连接
            ct = getConnection();
            // 将自动提交设置为false
            ct.setAutoCommit(false);
            // 设置事务级别为Serializable
            ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // 用户传入的可能是多个SQL语句
            for (int i = 0; i < sql.length; i++) {
                if (parameters[i] != null && !parameters[i].equals("")) {
                    ps = ct.prepareStatement(sql[i]);
                    for (int j = 0; j < parameters[i].length; j++) {
                        // 给?赋值
                        ps.setString(j + 1, parameters[i][j]);
                    }
                }
                // 执行操作
                ps.executeUpdate();
            }

            // 提交
            ct.commit();
        } catch (Exception e) {
            // TODO: handle exception
            // 回滚
            try {
                ct.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
    }

    // 不考虑事务的update/delete/insert(DML)方法
    public static void executeUpdate(String sql, String[] parameters) {
        // 创建ps
        try {
            ct = getConnection();
            ps = ct.prepareStatement(sql);
            if (parameters != null && !parameters.equals("")) {
                // 给?赋值
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            // 执行操作
            ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            // 开发阶段使用
            e.printStackTrace();

            // 抛出运行异常,可以给调用该方法的方法一个选择
            // 可以处理也可以放弃处理
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, ct);
        }
    }

    // 关闭资源的方法
    public static void close(ResultSet rs, Statement ps, Connection ct) {
        // 先开后闭
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 使用垃圾回收机制回收
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 使用垃圾回收机制回收
            ps = null;
        }
        if (ct != null) {
            try {
                ct.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 使用垃圾回收机制回收
            ct = null;
        }
    }
}