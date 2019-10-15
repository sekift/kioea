package com.kioea.www.sqlutil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

public class MySqlTest {
	public static void main(String args[]) throws Exception {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/menagerie";
		String user = "root";
		String password = "123456";

		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		String sql = "select * from pstest";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			DatabaseMetaData dmd=conn.getMetaData();
			Enumeration<Driver> d=DriverManager.getDrivers();
			while(d.hasMoreElements()){
			    System.out.println(d.nextElement().getClass().getName());
			}
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getInt("id")+"\t");
				System.out.print(rs.getString("name")+"\t");
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			rs.close();
//			ps.close();
			conn.close();
		}

	}
}
