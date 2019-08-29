package com.db1;

import java.sql.*;

public class db1 {
        public static Connection getConn()
        {
            Connection con=null;
            try{
                Class.forName("org.gjt.mm.mysql.Driver");
                String url="jdbc:mysql://localhost/testshop?useUnicode=true&characterEncoding=UTF-8";
                con = DriverManager.getConnection(url, "root", "");
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return con;
        }
    public String CheckLogin(){
        String id = null;
        String name=null;
        String sql="select * from role ";
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = new db1().getConn();
      /*  try{
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            System.out.println("role_id role_name");
            while (rs.next()) {
                id = rs.getString("role_id");
                name=rs.getString("role_name");
                System.out.println(id+"       "+name);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                rs.close();
                stat.close();
                conn.close();
            }catch(Exception e){e.printStackTrace();}
        }*/
        ///////////////////////////////////////////////////////////////
        try{
        System.out.println("使用元数据自动遍历每一行的列的值：");
        stat = conn.createStatement();
        rs = stat.executeQuery(sql);
        // 将ResultSet的遍历的下标位置从最后，重置到初始位置
        rs.beforeFirst();
        // 使用元数据获取每一行的类信息数据
        ResultSetMetaData rsmd = rs.getMetaData();
        // 获取列数
        int columnCount = rsmd.getColumnCount();
        // 自动遍历每一行数据的的各个列的值
        while (rs.next()) {
            // 使用Resultset的元数据遍历每一行的列数据
            for (int i = 1; i <= columnCount; i++) {
                // 获取当前列的名字
                String strColmnName = rsmd.getColumnLabel(i);
                System.out.print(" " + rs.getObject(strColmnName));
            }
            // 换行
            System.out.println();
        }
        ///////////////////////////////////////////////////////////////////
        // 关闭资源
        rs.close();
        stat.close();
        conn.close();

    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        return id;
    }

        public static void main(String[] args)
        {
            new db1().getConn();
            new db1().CheckLogin();
            System.out.println("连接成功！");

        }
}


