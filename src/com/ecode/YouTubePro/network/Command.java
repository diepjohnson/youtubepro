package com.ecode.YouTubePro.network;

import com.ecode.YouTubePro.config.YoutubeCore;
import com.ecode.YouTubePro.utils.YoutubeListener;



public class Command implements Comparable<Command> {
	public static final int DEFAULT_RETRY_TIMES = 3;
	public IRunable runable;
	public YoutubeListener listener;
	public String action;
	int priority = YoutubeCore.PRIORITY_MEDIUM;
	int sequence = YoutubeCore.sequencing.getAndIncrement();
	long time = System.currentTimeMillis();
	public int retryTime = DEFAULT_RETRY_TIMES;
	public boolean sendByUI = false;
	public Command(IRunable runable, YoutubeListener listener, String action,
			int priority, boolean sendByUI) {
		super();
		this.runable = runable;
		this.listener = listener;
		this.action = action;
		this.priority = priority;
		this.sendByUI = sendByUI;
	}

	@Override
	public int compareTo(Command another) {
		// TODO Auto-generated method stub
		int result = priority - another.priority;
		if (result == 0) {
			result = (int) (time - another.time);
		}
		return result;
	}

}
