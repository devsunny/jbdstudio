package com.asksunny.jbdstudio.util;

import java.util.ArrayList;
import java.util.Hashtable;

public final class CLIOptions extends Hashtable<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<String> opts;

	public boolean hashOption(String optName) {
		return this.containsKey(optName);
	}

	public String getOption(String optName) {
		return this.get(optName);
	}

	public int getIntOption(String optName) {
		return Integer.valueOf(getOption(optName));
	}

	public long getLongOption(String optName) {
		return Long.valueOf(getOption(optName));
	}

	public double getDoubleOption(String optName) {
		return Double.valueOf(getOption(optName));
	}

	public boolean getBooleanOption(String optName) {
		String argv = getOption(optName);
		return argv != null
				&& (argv.equalsIgnoreCase("Y") || argv.equalsIgnoreCase("YES")
						|| argv.equalsIgnoreCase("TRUE")
						|| argv.equalsIgnoreCase("ON") || argv
							.matches("[1-9]\\d*"));
	}
	

	public CLIOptions(String[] args) {
		super();
		this.opts = new ArrayList<String>();
		parseArgs(args);
	}

	private void parseArgs(String[] args) {
		int argc = args.length;
		for (int i = 0; i < argc; i++) {
			String argv = args[i];
			if (argv.startsWith("--")) {
				String optKey = argv.substring(2);
				if (i < argc - 1 && !args[i + 1].startsWith("-")) {
					String optVal = args[++i];
					this.put(optKey, optVal);
				} else {
					this.put(optKey, Boolean.TRUE.toString());
				}
			} else if (argv.startsWith("-")) {
				String optKey = argv.substring(1);
				if (i < argc - 1 && !args[i + 1].startsWith("-")) {
					String optVal = args[++i];
					this.put(optKey, optVal);
				} else {
					this.put(optKey, Boolean.TRUE.toString());
				}
			} else {
				opts.add(argv);
			}
		}

	}

}
