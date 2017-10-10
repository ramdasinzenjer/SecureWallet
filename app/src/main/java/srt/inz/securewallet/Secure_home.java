package srt.inz.securewallet;

import org.json.JSONArray;
import org.json.JSONObject;

import srt.inz.connnectors.OnKeygetterTaskCompleted;
import srt.inz.connnectors.OnKeyupdateTaskCompleted;
import srt.inz.presenters.KeygetterApiTask;
import srt.inz.presenters.KeyupdateApiTask;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Secure_home extends Activity{
	
	Button btkeyup;
	
	EditText k1,k2,k3,k4,k5,k6,k7,k8,k9,k10;
	String sk1,sk2,sk3,sk4,sk5,sk6,sk7,sk8,sk9,sk10,res;
	LinearLayout linlaHeaderProgress;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secure_home);
		
		btkeyup=(Button)findViewById(R.id.btCredential);
		
		k1=(EditText)findViewById(R.id.etKey1);
		k2=(EditText)findViewById(R.id.etKey2);
		k3=(EditText)findViewById(R.id.etKey3);
		k4=(EditText)findViewById(R.id.etKey4);
		k5=(EditText)findViewById(R.id.etKey5);
		k6=(EditText)findViewById(R.id.etKey6);
		k7=(EditText)findViewById(R.id.etKey7);
		k8=(EditText)findViewById(R.id.etKey8);
		k9=(EditText)findViewById(R.id.etKey9);
		k10=(EditText)findViewById(R.id.etKey10);
		
		linlaHeaderProgress=(LinearLayout)findViewById(R.id.linlaHeaderProgress2);
		
		getKeysfromdB();
		
		
		btkeyup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sk1=k1.getText().toString();
				sk2=k2.getText().toString();
				sk3=k3.getText().toString();
				sk4=k4.getText().toString();
				sk5=k5.getText().toString();
				sk6=k6.getText().toString();
				sk7=k7.getText().toString();
				sk8=k8.getText().toString();
				sk9=k9.getText().toString();
				sk10=k10.getText().toString();
				
				
				
				KeyupdateApiTask task=new KeyupdateApiTask(getApplicationContext(), 
						sk1, sk2, sk3, sk4, sk5, sk6, sk7, sk8, sk9, sk10, linlaHeaderProgress,
						new OnKeyupdateTaskCompleted() {
							
							@Override
							public void OnTaskCompleted(String result) {
								// TODO Auto-generated method stub
								
								if(result!=null)
			                      {

									res=result;
			                          if(result.contains("success"))
			                          {
			                        	  
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
				// keyparser(res);
			}
		});
	}

	
	public void keyparser(String result)
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
				String s1=data1.getString("k1");
				String s2=data1.getString("k2");
				String s3=data1.getString("k3");
				String s4=data1.getString("k4");
				String s5=data1.getString("k5");
				String s6=data1.getString("k6");
				String s7=data1.getString("k7");
				String s8=data1.getString("k8");
				String s9=data1.getString("k9");
				String s10=data1.getString("k10");
				String stat=data1.getString("status");
				
				k1.setText(s1); k2.setText(s2); k3.setText(s3); k4.setText(s4); k5.setText(s5); 
				k6.setText(s6); k7.setText(s7); k8.setText(s8); k9.setText(s9); k10.setText(s10);
				
				if(stat.contains("0"))
				{
					k1.setEnabled(true); k2.setEnabled(true); k3.setEnabled(true); k4.setEnabled(true);
					k5.setEnabled(true); k5.setEnabled(true); k6.setEnabled(true); k7.setEnabled(true);
					k8.setEnabled(true); k9.setEnabled(true); k10.setEnabled(true);
				}
				else
				{
				k1.setEnabled(false); k2.setEnabled(false); k3.setEnabled(false); k4.setEnabled(false);
				k5.setEnabled(false); k5.setEnabled(false); k6.setEnabled(false); k7.setEnabled(false);
				k8.setEnabled(false); k9.setEnabled(false); k10.setEnabled(false); 
				}
				
			}
		}
			catch (Exception e)         
		{
				System.out.println("Error:"+e);
		}
	}
	
	public void getKeysfromdB()
	{
		

		KeygetterApiTask task=new KeygetterApiTask(getApplicationContext(),
				"3", linlaHeaderProgress,
				new OnKeygetterTaskCompleted() {
					
					@Override
					public void OnTaskCompleted(String result) {
						// TODO Auto-generated method stub
						
						if(result!=null)
	                      {

							res=result;
	                          if(result.contains("success"))
	                          {
	                        	  keyparser(result);
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
	
}
