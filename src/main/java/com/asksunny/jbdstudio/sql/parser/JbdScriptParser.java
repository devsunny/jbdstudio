package com.asksunny.jbdstudio.sql.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JbdScriptParser {

	JbdScriptLexer lexer = null;

	public JbdScriptParser(String script) {
		lexer = new JbdScriptLexer(new StringReader(script));
	}

	public String nextStatement() {
		List<JbdScriptToken> tokens = new ArrayList<JbdScriptToken>();

		try {
			JbdScriptToken t = null;
			boolean stop = false;
			do {
				t = lexer.nextToken();
				if (t == null || t.kind == JbdScriptConstants.SEMICOLON) {
					stop = true;
					break;
				} else {
					tokens.add(t);
				}
			} while (!stop);
		} catch (IOException e) {
			// should never be here;
			e.printStackTrace();
		}
		int size = tokens.size();
		if (size == 0)
			return null;
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < size; i++) {
			JbdScriptToken t = tokens.get(i);
			switch (t.kind) {
			case JbdScriptConstants.DOUBLE_QUOTE_LITERAL:
				buf.append('"').append(t.image).append('"');
				break;
			case JbdScriptConstants.SINGLE_QUOTE_LITERAL:
				buf.append('\'').append(t.image).append('\'');
				break;
			default:
				buf.append(tokens.get(i).image);
				break;
			}
			if (i < size - 1)
				buf.append(" ");
		}
		return buf.toString();
	}

	
}
