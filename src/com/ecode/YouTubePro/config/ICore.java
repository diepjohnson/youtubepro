package com.ecode.YouTubePro.config;

import java.util.Set;

import android.content.Context;

import com.ecode.YouTubePro.network.IProtocol;
import com.ecode.YouTubePro.utils.YoutubeListener;

public interface ICore {

	IProtocol getIProtocol();

	Context getContext();

	public boolean isNetworkFail();

	Set<YoutubeListener> copyAndReconstructListeners(YoutubeListener listener);

	void stopThreads();

	public void stopCoreAndDestroy();

}
