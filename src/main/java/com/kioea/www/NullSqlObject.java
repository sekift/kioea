package com.kioea.www;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 
 * @author:sekift
 * @time:2015-9-30 下午04:26:06
 * @version:
 */
public class NullSqlObject {
	
	/**
	 * sql基本对象
	 */
    private InputStream nullAsciiStream = null;
    private BigDecimal nullBigDecimal = null;
    private InputStream nullBinaryStream = null;
    private Blob nullBlob = null;
    private boolean nullBoolean = false;
    private byte nullByte = 0;
    private byte[] nullBytes = null;
    private Reader nullCharacterStream = null;
    private Clob nullClob = null;
    private Date nullDate = null;
    private double nullDouble = 0.0;
    private float nullFloat = 0.0f;
    private int nullInt = 0;
    private long nullLong = 0;
    private Object nullObject = null;
    private Ref nullRef = null;
    private short nullShort = 0;
    private String nullString = null;
    private Time nullTime = null;
    private Timestamp nullTimestamp = null;
    private URL nullURL = null;
    
    public InputStream getNullAsciiStream() {
		return nullAsciiStream;
	}
	public void setNullAsciiStream(InputStream nullAsciiStream) {
		this.nullAsciiStream = nullAsciiStream;
	}
	public BigDecimal getNullBigDecimal() {
		return nullBigDecimal;
	}
	public void setNullBigDecimal(BigDecimal nullBigDecimal) {
		this.nullBigDecimal = nullBigDecimal;
	}
	public InputStream getNullBinaryStream() {
		return nullBinaryStream;
	}
	public void setNullBinaryStream(InputStream nullBinaryStream) {
		this.nullBinaryStream = nullBinaryStream;
	}
	public Blob getNullBlob() {
		return nullBlob;
	}
	public void setNullBlob(Blob nullBlob) {
		this.nullBlob = nullBlob;
	}
	public boolean isNullBoolean() {
		return nullBoolean;
	}
	public void setNullBoolean(boolean nullBoolean) {
		this.nullBoolean = nullBoolean;
	}
	public byte getNullByte() {
		return nullByte;
	}
	public void setNullByte(byte nullByte) {
		this.nullByte = nullByte;
	}
	public byte[] getNullBytes() {
		return nullBytes;
	}
	public void setNullBytes(byte[] nullBytes) {
		this.nullBytes = nullBytes;
	}
	public Reader getNullCharacterStream() {
		return nullCharacterStream;
	}
	public void setNullCharacterStream(Reader nullCharacterStream) {
		this.nullCharacterStream = nullCharacterStream;
	}
	public Clob getNullClob() {
		return nullClob;
	}
	public void setNullClob(Clob nullClob) {
		this.nullClob = nullClob;
	}
	public Date getNullDate() {
		return nullDate;
	}
	public void setNullDate(Date nullDate) {
		this.nullDate = nullDate;
	}
	public double getNullDouble() {
		return nullDouble;
	}
	public void setNullDouble(double nullDouble) {
		this.nullDouble = nullDouble;
	}
	public float getNullFloat() {
		return nullFloat;
	}
	public void setNullFloat(float nullFloat) {
		this.nullFloat = nullFloat;
	}
	public int getNullInt() {
		return nullInt;
	}
	public void setNullInt(int nullInt) {
		this.nullInt = nullInt;
	}
	public long getNullLong() {
		return nullLong;
	}
	public void setNullLong(long nullLong) {
		this.nullLong = nullLong;
	}
	public Object getNullObject() {
		return nullObject;
	}
	public void setNullObject(Object nullObject) {
		this.nullObject = nullObject;
	}
	public Ref getNullRef() {
		return nullRef;
	}
	public void setNullRef(Ref nullRef) {
		this.nullRef = nullRef;
	}
	public short getNullShort() {
		return nullShort;
	}
	public void setNullShort(short nullShort) {
		this.nullShort = nullShort;
	}
	public String getNullString() {
		return nullString;
	}
	public void setNullString(String nullString) {
		this.nullString = nullString;
	}
	public Time getNullTime() {
		return nullTime;
	}
	public void setNullTime(Time nullTime) {
		this.nullTime = nullTime;
	}
	public Timestamp getNullTimestamp() {
		return nullTimestamp;
	}
	public void setNullTimestamp(Timestamp nullTimestamp) {
		this.nullTimestamp = nullTimestamp;
	}
	public URL getNullURL() {
		return nullURL;
	}
	public void setNullURL(URL nullURL) {
		this.nullURL = nullURL;
	}
}
