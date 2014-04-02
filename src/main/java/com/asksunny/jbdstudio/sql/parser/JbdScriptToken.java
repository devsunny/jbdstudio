package com.asksunny.jbdstudio.sql.parser;

public class JbdScriptToken {
	String image;
	int kind;	
	int line = 0;
	int column = 0;
	
	
	
	
	public JbdScriptToken(String image, int kind, int line, int column) {
		super();
		this.image = image;
		this.kind = kind;
		this.line = line;
		this.column = column;
	}
	public JbdScriptToken() {
		super();		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	@Override
	public String toString() {
		return "JbdScriptToken [image=" + image + ", kind=" + kind + ", line="
				+ line + ", column=" + column + "]";
	}
	
	
	
}
