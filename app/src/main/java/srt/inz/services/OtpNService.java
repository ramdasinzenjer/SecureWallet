package srt.inz.services;

import org.json.JSONArray;
import org.json.JSONObject;

import srt.inz.connnectors.OnOtprecieverTaskCompleted;
import srt.inz.presenters.OtprecieverApiTask;
import srt.inz.securewallet.Mainpage;
import srt.inz.securewallet.R;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("NewApi") public class OtpNService extends Service{
	
	private MyThread mythread;
    public boolean isRunning = false;
    private static String TAG = OtpNService.class.getSimpleName();
	
	public OtpNService()
	{
		
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		 Log.d(TAG, "onCreate");     
	        mythread  = new MyThread();
		Toast.makeText(getApplicationContext(), "Service created", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	//	new Myasync().execute();
		
		if(!isRunning){
            mythread.start();
            isRunning = true;
        }
		
		
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class MyThread extends Thread{
        static final long DELAY = 5000;
        @Override
        public void run(){          
            while(isRunning){
                Log.d(TAG,"Running");
                try {                   
                    getOTPfromdB();
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    isRunning = false;
                    e.printStackTrace();
                }
            }
        }
         
    }
	
	
	public void notification(Intent inte,String otp)
	{
		
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); 	
		PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, inte, 0);	
		// this is it, we'll build the notification!
		// in the addAction method, if you don't want any icon, just set the first param to 0
		Notification mNotification = new Notification.Builder(getApplicationContext())		
			.setContentTitle("OTP Recieved")
			.setContentText("Your OTP code is "+otp+" .Please enter code in Door Security.")
			.setTicker("New Message Alert!")
			/*.setNumber(++numMessages)*/
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentIntent(pIntent)
			.setSound(soundUri)		
			//.addAction(0, "View", pIntent)
			//.addAction(0, "Stop", pIntent)			
			.build();
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		// If you want to hide the notification after it was selected, do the code below
		// myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notificationManager.notify(0, mNotification);
		
		// Toast.makeText(getApplicationContext(), "Message recieved", Toast.LENGTH_LONG).show();
	}
	
	
	public void otpparser(String result)
	{
		try
		{
			JSONObject  jObject = new JSONObject(result);
			JSONObject  jObject1 = jObject.getJSONObject("Event");
			JSONArray ja = jObject1.getJSONArray("Details"); 
			int length=ja.length();
			for(int i=0;i<length;i++)
			{
				JSONObject data1	= ja.getJSONObject(i);
				
				String sotp=data1.getString("OTP");
				
				if (sotp.equals("0")) {
					
					
				} else {

					Intent intentAlarm= new Intent(getBaseContext(), Mainpage.class);
					notification(intentAlarm,sotp);
				}
				
				
			}
		}
			catch (Exception e)         
		{
				System.out.println("Error:"+e);
		}
	}
	
	public void getOTPfromdB()
	{
		

		OtprecieverApiTask task=new OtprecieverApiTask(getApplicationContext(),
				"3", new OnOtprecieverTaskCompleted() {
					
					@Override
					public void OnTaskCompleted(String result) {
						// TODO Auto-generated method stub
						
						if(result!=null)
	                      {

							
	                          if(result.contains("success"))
	                          {
	                        	  otpparser(result);
	                             // Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
	                          }
	                          else{
	                            //  profileDetailsResuilt= ApiParserClass.Parentdetails(result);
	                              
	                        	//  Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
	                          }
	                      }
						
						
					}
				});
		task.execute();
		 
		
	}

}
