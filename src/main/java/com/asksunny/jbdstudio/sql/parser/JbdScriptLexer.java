package com.asksunny.jbdstudio.sql.parser;

import java.io.IOException;
import java.io.Reader;

public class JbdScriptLexer 
{
	
	int readAHead = -1;
	Reader reader = null;
	boolean eof = false;
	int line = 1;
	int column = 0;

	public JbdScriptLexer(Reader reader) {
		super();
		this.reader = reader;
	}
	
	public JbdScriptToken nextToken() throws IOException
	{
		
		if(eof) return null;		
		boolean r = true;
		StringBuilder buf = new StringBuilder();
		while(r){				
			int ci = reader.read();
			column++;
			if(ci==-1){
				reader.close();
				eof=true;
				r =false;
				if(buf.length()==0) return null; else break;					
			}
			char c = (char)ci;
			if(c ==';'){
				JbdScriptToken t = new JbdScriptToken();
				t.image = ";";
				t.kind = JbdScriptConstants.SEMICOLON;
				r = false;
				return t;					
			}else if(c =='"' || c =='\''){
				readTo(c, buf);
				r = false;
				JbdScriptToken t = new JbdScriptToken();
				t.image = buf.toString();
				t.kind = (c=='"')?JbdScriptConstants.DOUBLE_QUOTE_LITERAL:JbdScriptConstants.SINGLE_QUOTE_LITERAL;
				return t;
			}else if(Character.isSpaceChar(c)){
				if(c=='\n'){
					line++;
					column=0;
				}
				
				if(buf.length()==0){
					continue;
				}else{
					r = false;
					break;
				}
			}else{
				buf.append(c);
			}			
		}
		JbdScriptToken t = new JbdScriptToken();
		t.image = buf.toString();
		t.kind = JbdScriptConstants.PLAIN_LITERAL_TOKEN;
		return t;
		
	}
	
	protected void readTo(char c1, StringBuilder buf) throws IOException
	{
		for(;;){
			int ci = reader.read();
			column++;
			if(ci==-1){
				reader.close();
				eof=true;				
				break;					
			}
			char c = (char)ci;
			if(c==c1){
				break;
			}else if(c=='\\'){
				char c2 = (char)reader.read();
				column++;
				switch(c2)
				{
				case 't':
					buf.append("\t");
					break;
				case 'b':
					buf.append("\b");
					break;
				case 'n':
					buf.append("\n");
					break;
				case 'r':
					buf.append("\r");
					break;
				case '\'':
					buf.append("'");
					break;
				case '"':
					buf.append("\"");
					break;
				case '\\':
					buf.append("\\");
					break;
				default:
					throw new IOException(String.format("Unknow character at line %d column %d", line, column));
				}
			}else{
				buf.append(c);
			}
			
		}
		
	}
	
}
