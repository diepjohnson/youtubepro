package com.ecode.YouTubePro.config;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Process;

import com.ecode.YouTubePro.network.Command;
import com.ecode.YouTubePro.network.IProtocol;
import com.ecode.YouTubePro.network.IRunable;
import com.ecode.YouTubePro.network.JSONProtocol;
import com.ecode.YouTubePro.utils.YoutubeListener;

public class YoutubeCore implements ICore, IConfig, Runnable {
	public static final int PRIORITY_BACKGROUND = 1;
	public static final int PRIORITY_LOW = 2;
	public static final int PRIORITY_MEDIUM = 3;
	public static final int PRIORITY_HIGH = 4;
	public static final int PRIORITY_TOP = 5;

	private static YoutubeCore instance = null;
	private Context mContext;
	private IProtocol protocol;
	private static Set<YoutubeListener> mListener = new CopyOnWriteArraySet<YoutubeListener>();
	private static boolean networkFailed = false;
	private BlockingQueue<Command> mCommands = new PriorityBlockingQueue<Command>();
	private Thread mThread;
	boolean coreFlag = false;
	public static Boolean httpPollingBusy = false;
	private boolean connected = true;
	private byte[] connectionLock = new byte[0];
	public int sleepTime = 10 * 1000;
	private ConnectionChangeReceiver ccReceiver;

	public YoutubeCore(Context context) {
		this.mContext = context;
		this.protocol = new JSONProtocol(context, this);
		mThread = new Thread(this);
		mThread.setName("CORE");
		mThread.start();
	}

	public synchronized static YoutubeCore getInstance(Context context) {
		if (instance == null) {
			instance = new YoutubeCore(context);
		}
		return instance;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public byte[] getConnectionLock() {
		return connectionLock;
	}
	public boolean isConnected() {
		return connected;
	}
	public void registerReceiver() {
		IntentFilter filter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		ccReceiver = new ConnectionChangeReceiver(this);
		mContext.registerReceiver(ccReceiver, filter);
	}

	public void unregisterReceiver() {
		mContext.unregisterReceiver(ccReceiver);
	}

	public void addListener(YoutubeListener listener) {
		if (listener != null) {
			mListener.add(listener);
		}
	}

	public void removeListener(YoutubeListener listener) {
		mListener.remove(listener);
	}

	@Override
	public IProtocol getIProtocol() {
		// TODO Auto-generated method stub
		return protocol;
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return mContext;
	}

	@Override
	public Set<YoutubeListener> copyAndReconstructListeners(
			YoutubeListener listener) {
		// TODO Auto-generated method stub
		if (listener == null) {
			return mListener;
		}
		Set<YoutubeListener> listeners = new HashSet<YoutubeListener>(mListener);
		listeners.add(listener);
		return listeners;
	}

	@Override
	public boolean isNetworkFail() {
		// TODO Auto-generated method stub
		return networkFailed;
	}

	private void restartCore() {
		coreFlag = false;
		mThread = new Thread(this);
		mThread.start();
	}

	public static AtomicInteger sequencing = new AtomicInteger(0);
	public void put(YoutubeListener listener, String action, int priority,
			IRunable runable, boolean sendByUI) {

		putCommand(mCommands, action, listener, runable, priority, sendByUI,
				Command.DEFAULT_RETRY_TIMES);

	}
	private void putCommand(BlockingQueue<Command> queue, String action,
			YoutubeListener listener, IRunable runnable, int priority,
			boolean sendByUI, int retryTimes) {
		int retries = 10;
		Exception e = null;
		while (retries-- > 0) {
			try {

				Command command = new Command(runnable, listener, action,
						priority, sendByUI);
				queue.put(command);
				return;
			} catch (InterruptedException ie) {
				try {
					Thread.sleep(IConfig.PUT_COMMAND_RETRY_INTERVAL);
				} catch (InterruptedException ne) {
				}
				e = ie;
			}
		}
		throw new Error(e);
	}

	public static void setHttpPollingBusy(Boolean value) {
		httpPollingBusy = value;
	}

	public static Boolean getHttpPollingBusy() {
		return httpPollingBusy;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
		coreFlag = true;
		while (coreFlag) {
			String commandDescription = null;
			try {
				final Command command = mCommands.take();
				if (!connected) {
					if (command != null) {
						if (command.sendByUI) {
							command.runable.timeoutCallback();
							continue;
						} else {
							// if (command.action.equals(TaskHeartbeat.class
							// .getName())) {
							// continue;
							// }
							mCommands.add(command);
						}
					}
					synchronized (connectionLock) {
						connectionLock.wait(NETWORK_FAIL_WAIT);
						continue;
					}
				}

				if (command != null) {
					commandDescription = command.action;
					try {
						command.runable.run();
						resetSleepTime();
					} catch (Exception e) {
						if (command.sendByUI) {
							command.runable.timeoutCallback();
						} else {
							if (command.retryTime > 0) {
								command.retryTime--;
								new Thread() {
									@Override
									public void run() {
										try {
											sleep(increaseSleepTime());
											mCommands.put(command);
										} catch (InterruptedException e) {
										}
									}

								}.start();
							}
						}
						e.printStackTrace();
					}
					// callback execution complete
					for (YoutubeListener l : copyAndReconstructListeners(command.listener)) {
						l.commandExecutionComplete(mCommands.size() > 0);
					}
				}
			}
			// prevent the Core thread from crash.
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void resetSleepTime() {
		sleepTime = 10 * 1000;
	}

	private int increaseSleepTime() {
		if (sleepTime == 10 * 1000) {
			// 30 seconds
			sleepTime = 30 * 1000;
		} else if (sleepTime == 30 * 1000) {
			// 3 minutes
			sleepTime = 3 * 60 * 1000;
		} else if (sleepTime == 3 * 60 * 1000) {
			// 5 minutes
			sleepTime = 5 * 60 * 1000;
		}
		return sleepTime;
	}

	@Override
	public void stopThreads() {
		// TODO Auto-generated method stub
		mCommands.clear();
		restartCore();
	}

	@Override
	public void stopCoreAndDestroy() {
		this.stopThreads();
		mThread.interrupt();
		mThread = null;
	}


}
