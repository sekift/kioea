package com.kioea.www.csvutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;
/**
 * csv paser
 * 
 * @author:sekift
 * @time:2014-8-8 下午03:22:14
 */
public class CsvParser implements Iterator<List<String>>{
	
	private static final Logger log = LoggerFactory.getLogger(CsvParser.class);
			
	private CsvListReader reader = null;
	private List<String> row = null;
	
	public CsvParser(String csvFile, String encoding) {
		super();
		try {
			reader = new CsvListReader(new InputStreamReader(new FileInputStream(csvFile), encoding), CsvPreference.EXCEL_PREFERENCE);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean hasNext(){
		try {
			if(reader.getLineNumber() == 0){
				row = reader.read();
			}
			row = reader.read();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return row != null;
	}
	
	@Override
	public List<String> next(){
		return row;
	}
	
	@Override
	public void remove(){
		throw new UnsupportedOperationException("本CSV解析器是只读的."); 
	}
	
	public void close(){
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 当前行号,从1开始
	 * @return int
	 */
	public int getLineNumber(){
		return reader.getLineNumber() - 1;
	}

}
