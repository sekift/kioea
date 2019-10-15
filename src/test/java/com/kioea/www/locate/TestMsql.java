package com.kioea.www.locate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kioea.www.CloseUtil;
import com.kioea.www.encryptutil.SeqUUIDUtil;
import com.kioea.www.sqlutil.ConsistentHashTable;
import com.kioea.www.sqlutil.ConsistentHashTable.TableItem;

public class TestMsql {

	private static TableItem getTableItem(String ip) {
		return ConsistentHashTable.getTable("info", ip);
	}

	public boolean insert(String ip) throws Exception {
		if (ip == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/menagerie", "root", "123456");

			StringBuffer sql = new StringBuffer();
			TableItem ti = getTableItem(ip);
			sql.append("INSERT INTO ").append(ti.getTable()).append(" values(0,?)");
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, ip);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			CloseUtil.closeSilently(ps);
			CloseUtil.closeSilently(conn);
		}
	}

	public static void main(String args[]) throws SQLException {
		// TestMsql tm=new TestMsql();
		// try {
		// tm.insert("demo4");
		// tm.insert("demo5");
		// tm.insert("demo6");
		// tm.insert("demo7");
		// tm.insert("demo8");
		// tm.insert("demo9");
		// tm.insert("demo10");
		// tm.insert("demo11");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		int j = 0;
		int k = 0;
		int l=0;
		for (int i = 0; i < 10000; i++) {
			TableItem ti = ConsistentHashTable.getTable("info", SeqUUIDUtil.toSequenceUUID());
			if (ti.getNo() == 0) {
				j++;
			}else if(ti.getNo() == 1400000000) {
				k++;
			}else{
				l++;
			}
		}
		System.out.println("j=" + j + " ; k=" + k + " ; l=" + l);
	}
}
