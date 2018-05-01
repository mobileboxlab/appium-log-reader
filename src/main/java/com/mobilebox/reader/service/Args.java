package com.mobilebox.reader.service;

import com.github.jankroken.commandline.annotations.LongSwitch;
import com.github.jankroken.commandline.annotations.Option;
import com.github.jankroken.commandline.annotations.Required;
import com.github.jankroken.commandline.annotations.ShortSwitch;
import com.github.jankroken.commandline.annotations.SingleArgument;

public class Args {
	private String port;
	private String ip;

	@Option
	@LongSwitch("port")
	@ShortSwitch("p")
	@SingleArgument
	@Required
	public void setPort(String port) {
		this.port = port;
	}

	@Option
	@LongSwitch("ip")
	@ShortSwitch("i")
	@SingleArgument
	@Required
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}
}
