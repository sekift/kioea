package com.kioea.www.berkeleydb;

import java.io.File;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;

/**
 * 
 * @author:Administrator
 * @time:2015-10-19 下午02:45:08
 * @version:
 */
/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 1997, 2015 Oracle and/or its affiliates.  All rights reserved.
 *
 * $Id$
 */

/*
 * An example of a program configuring a database environment.
 * 
 * For comparison purposes, this example uses a similar structure as
 * examples/ex_env.c and examples_cxx/EnvExample.cpp.
 */
public class EnvExample {

	String path = "D:/BerkeleyDB6.1.26/export/dbEnv";

	public void testExample() {
		Environment myDbEnvironment = null;
		Database myDatabase = null;

		try {
			// 打开环境
			EnvironmentConfig envConfig = new EnvironmentConfig();
			envConfig.setAllowCreate(true);
			myDbEnvironment = new Environment(new File(path), envConfig);

			// 打开数据库
			DatabaseConfig dbConfig = new DatabaseConfig();
			dbConfig.setAllowCreate(true);
			// 打开一个数据库，数据库名为sampleDatabase,数据库的配置为dbConfig
			myDatabase = myDbEnvironment.openDatabase(null, "dbtest", dbConfig);
			
			//List<String> myDbNames = myDbEnvironment.getDatabaseNames();
			//for(int i=0; i < myDbNames.size(); i++) {
			  //  System.out.println("Database Name: " + myDbNames.get(i));
			//}
			
			//使用dbtest作为测试
			String aKey = "keya";
			String aData = "dataa";
			//保存
			try {
			    DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes("UTF-8"));
			    DatabaseEntry theData = new DatabaseEntry(aData.getBytes("UTF-8"));
			    myDatabase.put(null, theKey, theData);
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			//读取
			try {
				DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes("UTF-8"));
				DatabaseEntry theData = new DatabaseEntry();
				if (myDatabase.get(null, theKey, theData, LockMode.DEFAULT) ==
					OperationStatus.SUCCESS) {
					byte[] retData = theData.getData();
					String foundData = new String(retData, "UTF-8");
					System.out.println("For key: '" + aKey + "' found data: '" +
							foundData + "'.");
				} else {
					System.out.println("No record found for key '" + aKey + "'.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//读取
			aKey = "keyf";
			try {
			    DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes("UTF-8"));
			    DatabaseEntry theData = new DatabaseEntry();
			if (myDatabase.get(null, theKey, theData, LockMode.DEFAULT) ==
			        OperationStatus.SUCCESS) {
			        byte[] retData = theData.getData();
			        String foundData = new String(retData, "UTF-8");
			        System.out.println("For key: '" + aKey + "' found data: '" +
			                            foundData + "'.");
			    } else {
			        System.out.println("No record found for key '" + aKey + "'.");
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			try {
			    DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes("UTF-8"));
			    myDatabase.delete(null, theKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} catch (DatabaseException dbe) {
			dbe.getStackTrace();
		} finally {
			try {
				if (myDatabase != null) {
					myDatabase.close();
				}
				if (myDbEnvironment != null) {
					myDbEnvironment.close();
				}
			} catch (DatabaseException dbe) {
				dbe.getStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		EnvExample ee = new EnvExample();
		ee.testExample();
	}

}
