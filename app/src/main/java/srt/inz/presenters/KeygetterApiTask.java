package srt.inz.presenters;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import srt.inz.connnectors.Connectivity;
import srt.inz.connnectors.Constants;
import srt.inz.connnectors.OnKeygetterTaskCompleted;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class KeygetterApiTask extends AsyncTask<String, String, String> {
	
	
	Context ctx;
	String id;
    OnKeygetterTaskCompleted listerner;

    String result;
    LinearLayout linlaHeaderProgress;
    
    public KeygetterApiTask(Context context,String mid,
    		LinearLayout linlaHeaderProgress, 
    		OnKeygetterTaskCompleted onkeygetterTaskCompleted) {
        this.ctx=context;
        this.id=mid;  
        
        this.linlaHeaderProgress=linlaHeaderProgress;
        this.listerner=onkeygetterTaskCompleted;

    }
    
    

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		String urlParameters = null;
        try {
            urlParameters =  "id=" + URLEncoder.encode("3", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        result = Connectivity.excutePost(Constants.KEYGETTER_URL,
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
