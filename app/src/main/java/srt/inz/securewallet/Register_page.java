package srt.inz.securewallet;

import srt.inz.connnectors.OnRegisterTaskCompleted;
import srt.inz.presenters.RegisterApiTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_page  extends Activity{
	
	Button brg;
	
	EditText etn,etun,etpas,etcpas,etph,etmail;
	String sn,sun,spas,scpas,sphn,smail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		
		//brg=(Button)findViewById(R.id.btnregister);
		
		etn=(EditText)findViewById(R.id.etname);
		etun=(EditText)findViewById(R.id.etuname);
		etpas=(EditText)findViewById(R.id.etpass);
		etcpas=(EditText)findViewById(R.id.etcps);
		etph=(EditText)findViewById(R.id.etphn);
		etmail=(EditText)findViewById(R.id.etemail);
		
		/*brg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});*/
		
	}
	public void regist(View v)
	{
		sn=etn.getText().toString();
		sun=etun.getText().toString();
		spas=etpas.getText().toString();
		scpas=etcpas.getText().toString();
		sphn=etph.getText().toString();
		smail=etmail.getText().toString();


              RegisterApiTask task=new RegisterApiTask(getApplicationContext(),smail,sn,spas,sphn,sun,
            		  new OnRegisterTaskCompleted() {
				
				@Override
				public void OnTaskCompleted(String result) {
					// TODO Auto-generated method stub
					
					if(result!=null)
                      {

                          if(result.contains("User Exists"))
                          {
                              Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
                          }
                          else{
                            //  profileDetailsResuilt= ApiParserClass.Parentdetails(result);
                              Intent intent =new Intent(getApplicationContext(),Mainpage.class);
                              startActivity(intent);
                        	  Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
                          }
                      }
					
				}
			});
              task.execute();
	}
/*
	private boolean setValidationText() {
        
      
        if(smail.indexOf("@")==-1)
        {
            return false;
        }
       
       
  return  true;
}*/
}
