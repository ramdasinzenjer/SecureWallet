package srt.inz.connnectors;


public interface Constants {

    //Progress Message
    String LOGIN_MESSAGE="Logging in...";
    String REGISTER_MESSAGE="Register in...";

    //Urls
 
    String REGISTER_URL="http://192.168.1.10/door_lock/mRegister.php?";
    String LOGIN_URL="http://192.168.1.10/door_lock/mLogin.php?";
    String KEYUPDATE_URL="http://192.168.1.10/door_lock/mKeyupdate.php?";
    String KEYGETTER_URL="http://192.168.1.10/door_lock/mKeygetter.php?";
    String OTPGETTER_URL="http://192.168.1.10/door_lock/mOtpreciever.php?";
    
    //Details

    String ID="id";
    String NAME="Name";
    String PASSWORD="Password";
    String EMAIL="Email";
    
    String LOGINSTATUS="LoginStatus";
    String USERID="id";

    //sharedpreference

    String PREFERENCE_PARENT="Parent_Pref";

   
}
