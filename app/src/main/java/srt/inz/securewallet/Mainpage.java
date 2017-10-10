package srt.inz.securewallet;

import srt.inz.connnectors.OnLoginTaskCompleted;
import srt.inz.presenters.LoginApiTask;
import srt.inz.services.OtpNService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class  Mainpage extends Activity {
	
	Button bl,br; EditText etun,etpas; String sun,spas;
	LinearLayout linlaHeaderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        
        bl=(Button)findViewById(R.id.btnlog);
        br=(Button)findViewById(R.id.btnreg);
        etun=(EditText)findViewById(R.id.edituid);
        etpas=(EditText)findViewById(R.id.editpass); 
        linlaHeaderProgress=(LinearLayout)findViewById(R.id.linlaHeaderProgress);
        
        
        
        
        
        bl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startService(new Intent(getApplicationContext(), OtpNService.class));
				
				sun=etun.getText().toString();
				spas=etpas.getText().toString();
				
				if (isNetworkConnected()) {
		        	
		        	Toast.makeText(getApplicationContext(), "Network Connectivity Avialable", Toast.LENGTH_SHORT).show();
				
		        	LoginApiTask task=new LoginApiTask(getApplicationContext(), sun, spas,linlaHeaderProgress, new OnLoginTaskCompleted() {

						@Override
						public void OnTaskCompleted(String result) {
							// TODO Auto-generated method stub
							if(result!=null)
		                      {

		                          if(result.contains("success"))
		                          {
		                        	  Intent intent =new Intent(getApplicationContext(),Secure_home.class);
		                              startActivity(intent);
		                              Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
		                          }
		                          else{
		                            //  profileDetailsResuilt= ApiParserClass.Parentdetails(result);
		                              
		                        	  Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
		                          }
		                      }
						}
					});
					task.execute();
		        	
				}
		        else
		        {
		        	Toast.makeText(getApplicationContext(), "Network Unavialable. Please Connect to the network", Toast.LENGTH_SHORT).show();
		        }

				
				
			}
		});
        
        br.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i=new Intent(getApplicationContext(),Register_page.class);
				startActivity(i);
			}
		});
    }
    private boolean isNetworkConnected() {
    	  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    	  return cm.getActiveNetworkInfo() != null;
    	 }
}
