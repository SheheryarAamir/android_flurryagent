package com.nemogames;

import java.util.Map;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class FlurryAgent 
{
	private Activity	RootActivity;
	
	public void		init()
	{
		this.RootActivity = UnityPlayer.currentActivity;
	}
	
	public void		StartSession()
	{
		RootActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run() 
			{
				com.flurry.android.FlurryAgent.onStartSession(RootActivity, FlurryAgent.this.getFlurryAPIKey());
			}
		});
	}
	
	public void		EndSession()
	{
		RootActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run() 
			{
				com.flurry.android.FlurryAgent.onEndSession(RootActivity);
			}
		});
	}
	
	public void		SetReportLocation(boolean status)
	{
		com.flurry.android.FlurryAgent.setReportLocation(status);
	}
	
	public void		SetCaptureUncouthExceptions(boolean status)
	{
		com.flurry.android.FlurryAgent.setCaptureUncaughtExceptions(status);
	}
	
	public void		SetContinueSessionMilis(long milis)
	{
		com.flurry.android.FlurryAgent.setContinueSessionMillis(milis);
	}
	
	public void		LogEvent(String id)
	{
		com.flurry.android.FlurryAgent.logEvent(id);
	}
	
	public void		LogEvent(String id, Map<String, String> data)
	{
		com.flurry.android.FlurryAgent.logEvent(id, data);
	}
	
	public void		LogEvent(String id, Map<String, String> data, boolean timed)
	{
		com.flurry.android.FlurryAgent.logEvent(id, data, timed);
	}
	
	public void		LogEvent(String id, boolean timed)
	{
		com.flurry.android.FlurryAgent.logEvent(id, timed);
	}
	
	public void		LogPageView()
	{
		com.flurry.android.FlurryAgent.onPageView();
	}
	
	public void		SetLogEnabled(boolean status)
	{
		com.flurry.android.FlurryAgent.setLogEnabled(status);
	}
	
	public void		SetUseHttps(boolean status)
	{
		com.flurry.android.FlurryAgent.setUseHttps(status);
	}
	
	public void		SetAge(int age)
	{
		com.flurry.android.FlurryAgent.setAge(age);
	}
	
	public void		SetGender(int gender)
	{
		com.flurry.android.FlurryAgent.setGender((gender == 1? com.flurry.android.Constants.FEMALE:com.flurry.android.Constants.MALE));
	}
	
	public void		SetUserID(String id)
	{
		com.flurry.android.FlurryAgent.setUserId(id);
	}
	
	public String	getFlurryAPIKey()
	{
		try {
		    ApplicationInfo ai = RootActivity.getPackageManager().getApplicationInfo(RootActivity.getPackageName(), PackageManager.GET_META_DATA);
		    Bundle bundle = ai.metaData;
		    String myApiKey = bundle.getString("flurry_app_key");
		    return myApiKey;
		} catch (NameNotFoundException e) {
		    Log.e("Nemo - FlurryAgent", "Failed to load meta-data, NameNotFound: " + e.getMessage());
		} catch (NullPointerException e) {
		    Log.e("Nemo - FlurryAgent", "Failed to load meta-data, NullPointer: " + e.getMessage());         
		}
		return "";
	}
}
