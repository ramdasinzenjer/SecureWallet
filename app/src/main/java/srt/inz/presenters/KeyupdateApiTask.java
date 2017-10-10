package srt.inz.presenters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import srt.inz.connnectors.Connectivity;
import srt.inz.connnectors.Constants;
import srt.inz.connnectors.OnKeyupdateTaskCompleted;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class KeyupdateApiTask extends AsyncTask<String, String, String>{
	
	Context ctx;
	String sky1,sky2,sky3,sky4,sky5,sky6,sky7,sky8,sky9,sky10;
    OnKeyupdateTaskCompleted listerner;

    String result;
    LinearLayout linlaHeaderProgress;
    
    public KeyupdateApiTask(Context context,String sk1,String sk2,
    		String sk3,String sk4,String sk5,String sk6,
    		String sk7,String sk8,String sk9,String sk10,
    		LinearLayout linlaHeaderProgress, 
    		OnKeyupdateTaskCompleted onkeyupdateTaskCompleted) {
        this.ctx=context;
        this.sky1=sk1;  this.sky2=sk2;	this.sky3=sk3;	this.sky4=sk4;	this.sky5=sk5;
        this.sky6=sk6;  this.sky7=sk7;	this.sky8=sk8;	this.sky9=sk9;	this.sky10=sk10;
        
        this.linlaHeaderProgress=linlaHeaderProgress;
        this.listerner=onkeyupdateTaskCompleted;

    }
    
    

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		String urlParameters = null;
        try {
            urlParameters =  "id=" + URLEncoder.encode("2", "UTF-8") + "&&"
            		+"k1=" + URLEncoder.encode(sky1, "UTF-8") + "&&"
                    + "k2=" + URLEncoder.encode(sky2, "UTF-8") + "&&"
                    + "k3=" + URLEncoder.encode(sky3, "UTF-8") + "&&"
                    + "k4=" + URLEncoder.encode(sky4, "UTF-8") + "&&"
                    + "k5=" + URLEncoder.encode(sky5, "UTF-8") + "&&"
                    + "k6=" + URLEncoder.encode(sky6, "UTF-8") + "&&"
                    + "k7=" + URLEncoder.encode(sky7, "UTF-8") + "&&"
                    + "k8=" + URLEncoder.encode(sky8, "UTF-8") + "&&"
                    + "k9=" + URLEncoder.encode(sky9, "UTF-8") + "&&"
                    + "k10=" + URLEncoder.encode(sky10, "UTF-8") + "&&";
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        result = Connectivity.excutePost(Constants.KEYUPDATE_URL,
                urlParameters);
        Log.e("You are at", "" + result);

        return result;
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		linlaHeaderProgress.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onPostExecute(String s) {
		// TODO Auto-generated method stub
		super.onPostExecute(s);
		
		listerner.OnTaskCompleted(s);
        /* progressDialog.dismiss();*/
        linlaHeaderProgress.setVisibility(View.GONE);
	}

}
