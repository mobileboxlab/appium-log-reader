package com.mobilebox.reader.core;

import java.util.ArrayList;
import java.util.List;

import com.mobilebox.reader.model.Log;
import com.mobilebox.reader.model.Responses;

public class LogAccess {

	private List<Log> logContent = new ArrayList<Log>();
	public final static LogAccess INSTANCE = new LogAccess();

	private LogAccess() {
	}

	public List<Log> getLogContent() {
		return logContent;
	}

	public void setLogContent(List<Log> logContent) {
		this.logContent = logContent;
	}

	/**
	 * Retrieve a collection of log messages.
	 * 
	 * @return A <code>Responses</code> instance.
	 */
	public Responses<Log> getLogs() {
		Responses<Log> logs = new Responses<Log>(logContent);
		if (getLogContent().size() > 0) {
			logs = new Responses<Log>(getLogContent());
		}
		return logs;
	}

	/**
	 * Appends the given {@link Log} to the end of this list.
	 * 
	 * @param log
	 *            A {@link Log} instance.
	 */
	public void add(Log log) {
		logContent.add(log);
	}

	/**
	 * Removes all of the elements from the log list.
	 */
	public void clear() {
		logContent.clear();
	}

	/*
	 * Retrieve last lines of log.
	 * 
	 * @return A <code>Log</code> instance.
	 */
	public Log getLastLine() {
		Log lastLog = new Log();
		if (getLogContent().size() > 0) {
			lastLog = getLogContent().get(getLogContent().size() - 1);
		}
		return lastLog;
	}

	/**
	 * Retrieve N lines of log.
	 * 
	 * @param lines
	 *            Number of lines to retrieve.
	 * @return A <code>Responses</code> instance.
	 * 
	 */
	public Responses<Log> getLines(int lines) {
		return new Responses<Log>(getNLogs(lines));
	}

	/**
	 * Retrieve the total of a given log level.
	 * 
	 * @param level
	 *            Should be: error, info or warn.
	 * @return A <code>Responses</code> instance.
	 * 
	 */
	public int getTotalByLevel(String level) {
		int total = 0;
		List<Log> logs = getLogs().getItems();
		if (logs.size() > 0) {
			for (Log l : logs) {
				if (l != null) {
					if (l.getLevel().equalsIgnoreCase(level)) {
						total = total + 1;
					}
				}
			}
		}
		return total;
	}

	private List<Log> getNLogs(int logQuantity) {
		if (getLogContent().size() >= logQuantity) {
			return getLogContent().subList(getLogContent().size() - logQuantity, getLogContent().size());
		} else {
			return getLogContent();
		}
	}

}
