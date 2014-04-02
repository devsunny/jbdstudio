package com.asksunny.jbdstudio.sql.parser;

import java.io.IOException;
import java.io.Reader;

public class JbdScriptLexer {

	int peek = -1;
	boolean eof = false;
	int line = 1;
	int column = 0;
	Reader reader = null;

	public JbdScriptLexer(Reader reader) {
		super();
		this.reader = reader;
	}

	public JbdScriptToken nextToken() throws IOException {

		if (eof)
			return null;
		StringBuilder buf = new StringBuilder();
		boolean r = true;
		while (r) {
			int ci = peek();
			// System.out.println(">>>>" + ci);
			column++;
			if (ci == -1) {
				reader.close();
				eof = true;
				r = false;
				if (buf.length() == 0)
					return null;
				else
					break;
			}
			char c = (char) ci;
			if (c == ';') {
				JbdScriptToken t = null;
				if (buf.length() > 0) {
					t = new JbdScriptToken(buf.toString(),
							JbdScriptConstants.PLAIN_LITERAL_TOKEN, line,
							column);
				} else {
					read();
					t = new JbdScriptToken(buf.toString(),
							JbdScriptConstants.SEMICOLON, line, column);
				}
				r = false;
				return t;
			} else if (c == '"' || c == '\'') {
				read();
				readTo(c, buf);
				r = false;
				int k = (c == '"') ? JbdScriptConstants.DOUBLE_QUOTE_LITERAL
						: JbdScriptConstants.SINGLE_QUOTE_LITERAL;
				JbdScriptToken t = new JbdScriptToken(buf.toString(), k, line,
						column);
				return t;
			} else if (Character.isWhitespace(c)) {
				read();
				if (c == '\n') {
					line++;
					column = 0;
				}
				if (buf.length() == 0) {
					continue;
				} else {
					r = false;
					break;
				}
			} else {
				read();
				buf.append(c);
			}
		}
		JbdScriptToken t = new JbdScriptToken(buf.toString(),
				JbdScriptConstants.PLAIN_LITERAL_TOKEN, line, column);
		return t;

	}

	protected void readTo(char c1, StringBuilder buf) throws IOException {
		for (;;) {
			int ci = read();
			column++;
			if (ci == -1) {
				reader.close();
				eof = true;
				break;
			}
			char c = (char) ci;
			if (c == c1) {
				break;
			} else if (c == '\\') {
				char c2 = (char) read();
				column++;
				switch (c2) {
				case 't':
					buf.append("\\t");
					break;
				case 'b':
					buf.append("\\b");
					break;
				case 'n':
					buf.append("\\n");
					break;
				case 'r':
					buf.append("\\r");
					break;
				case '\'':
					buf.append("\\'");
					break;
				case '"':
					buf.append("\\\"");
					break;
				case '\\':
					buf.append("\\\\");
					break;
				default:
					throw new IOException(String.format(
							"Unknow character at line %d column %d", line,
							column));
				}
			} else {
				buf.append(c);
			}

		}

	}

	protected int peek() throws IOException {
		if (peek == -1 && !eof) {
			peek = reader.read();
			if (peek == -1)
				eof = true;
		}
		return peek;
	}

	protected int read() throws IOException {

		int r = -1;
		if (peek != -1) {
			r = peek;
			peek = reader.read();
			if (peek == -1)
				eof = true;
		} else if (peek == -1 && !eof) {
			r = reader.read();
			if (r == -1)
				eof = true;
		} else {
			r = -1;
		}
		return r;

	}

}
