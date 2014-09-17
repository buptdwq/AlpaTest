package com.ekusoft.alpatest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


public class SplashScreenFragment extends Fragment 
{
	private ProgressBar mProgressBar;
	
	public SplashScreenFragment() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		final View  view = inflater.inflate(R.layout.splash_screen, container, false);
		
		mProgressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
		
	//	return super.onCreateView(inflater, container, savedInstanceState);
		return view;
	}
	
	public void  setProgress( int progress) 
	{
		mProgressBar.setProgress(progress);
	}
	
	
	

}
