package com.asksunny.jbdstudio.sql.parser;

import java.io.StringReader;


public class JbdScriptParser {
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		String test = "SELECT *, 'my \\ntext' as \"my column\" FROM test;\n insert into my table; ";
		StringReader reader = new StringReader(test);
		JbdScriptLexer lexer = new JbdScriptLexer(reader);
		JbdScriptToken t = null;
		while((t=lexer.nextToken())!=null){
			System.out.println(t.toString());			
		}
		reader.close();
		
	}
	
}
