package com.kioea.www.sqlutil;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author:Administrator
 * @time:2016-4-22 下午03:16:47
 * @version:
 */
public class Data2Page {
	private static Logger logger = LoggerFactory.getLogger(Data2Page.class);

	/**
	 * @Description:(分页数据查询)
	 * @param <T>
	 * @param dbId
	 * @param pageInfo
	 * @param sql
	 * @param rsh
	 * @param params
	 * @return
	 */
	public static <T> T query(Connection conn, PageInfo pageInfo, String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		if (pageInfo != null) {
			if (sql.toLowerCase().indexOf(" limit ") > -1) {
				logger.error("[分页查询]含有关键词 limit");
				return null;
			}
			String pageSql = sql;
			int o = sql.toLowerCase().indexOf(" order by ");
			if (o > -1) {
				pageSql = sql.substring(0, o);
			}
			if (pageInfo.getTotalCount() < 0) {
				String sumSql = "SELECT COUNT(1) FROM (" + pageSql + ") T";
				Number totalCounts = (Number) queryRunner.query(conn, sumSql, new ScalarHandler(), params);
				if (totalCounts == null) {
					logger.error("[分页查询]查询不到记录：sql=" + pageSql + ",parmas=" + params);
					return null;
				}
				pageInfo.setTotalCount(totalCounts.longValue());
			}
			long begRowNum = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();
			sql += " LIMIT " + begRowNum + "," + pageInfo.getPageSize();
		}
		return queryRunner.query(conn, sql, rsh, params);
	}

}
