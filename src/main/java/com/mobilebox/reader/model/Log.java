package com.mobilebox.reader.model;

import static java.lang.String.format;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Log implements IResponse<Log> {

	@SerializedName("message")
	private String message = "";

	@SerializedName("level")
	private String level = "";

	public Log(String message, String level) {
		this.setMessage(message);
		this.setLevel(level);
	}

	public Log() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	public String toHtml() {
		String prefix = format("[%s]%s", level, message);
		if (level.equalsIgnoreCase("error")) {
			prefix = format("<a href='https://duckduckgo.com/?q=%s' target='_blank'><img src='img/duck.png'></a> %s",
					message, prefix);
		}
		return format("<tr><td>%s</td><td class='%s'>%s</td></tr>", prefix, level, level);
	}

	@Override
	public Log me() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
