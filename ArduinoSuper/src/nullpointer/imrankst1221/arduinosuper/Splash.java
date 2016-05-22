package nullpointer.imrankst1221.arduinosuper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Splash extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        DataBase db = new DataBase();
   	    db.userPrefs = getSharedPreferences("setting",MODE_PRIVATE);	    	
        new Handler().postDelayed(new Runnable() {
        	@Override
            public void run() {
        		//open home set time 2000ms 
            	    Intent i;
            		i = new Intent(Splash.this, MainActivity.class);
	                startActivity(i);
	                finish();
            }
        }, 2000); 
    }
	
}
