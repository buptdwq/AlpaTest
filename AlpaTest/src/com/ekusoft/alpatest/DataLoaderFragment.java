package com.ekusoft.alpatest;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;


public class DataLoaderFragment extends Fragment {

	public DataLoaderFragment() {
		// TODO Auto-generated constructor stub
	}

	public interface ProgressListener
	{
		public void onCompletion( Double result);
		public void onProgressUpdate( int progress);
	}
	
	private ProgressListener mProgressListener;
	
	private Double mResult = Double.NaN;
	private LoadingTask mTask;
	
	
	@Override
	public void onAttach(Activity activity) 
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
		setRetainInstance(true);
		
	}
	
	public Double getResult()
	{
		return mResult;
	}
	
	public boolean hasResult()
	{
		return !Double.isNaN(mResult);
	}
	
	public void removeProgressListener()
	{
		mProgressListener = null;
	}
	
	public void  setProgressListener( ProgressListener listener)
	{
		mProgressListener = listener;
	}
	
	public void startLoading()
	{
		mTask = new LoadingTask();
		mTask.execute();
	}
	
	private class LoadingTask extends AsyncTask<Void, Integer, Double>
	{
		@Override
		protected Double doInBackground(Void... params) {
			// TODO Auto-generated method stub
			double result = 0;
			
			try 
			{
				for (int i = 0; i < 100; i++) 
				{
					result += Math.sqrt(50);
					Thread.sleep(50);
					this.publishProgress(i);
				}
				
			} catch (Exception e) 
			{
				// TODO: handle exception
				
			}
			
			return Double.valueOf(result);
		}
		
		@Override
		protected void onPostExecute(Double result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			mResult = result;
			mTask = null;
			if ( mProgressListener != null) 
			{
				mProgressListener.onCompletion(mResult);
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
			if ( mProgressListener != null) 
			{
				mProgressListener.onProgressUpdate(values[0]);
			}
		}
	}
	
}
