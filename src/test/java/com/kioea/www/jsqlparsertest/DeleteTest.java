package com.kioea.www.jsqlparsertest;

import java.io.StringReader;

import junit.framework.TestCase;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.delete.Delete;

import org.junit.Test;

public class DeleteTest extends TestCase {
	CCJSqlParserManager parserManager = new CCJSqlParserManager();

	public DeleteTest(String arg0) {
		super(arg0);
	}

	@Test
	public void testDelete() throws JSQLParserException {
		String statement = "DELETE FROM mytable WHERE mytable.col = 9";

		Delete delete = (Delete) parserManager.parse(new StringReader(statement));
		assertEquals("mytable", delete.getTable().getName());
		assertEquals(statement, "" + delete);
	}
}
