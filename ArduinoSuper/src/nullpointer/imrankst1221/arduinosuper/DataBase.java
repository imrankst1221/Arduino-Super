package nullpointer.imrankst1221.arduinosuper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DataBase extends Activity{
	
	public static SharedPreferences userPrefs;
	//all get method
	
	public String getPotr8(){
		 return ""+ userPrefs.getString("port8","");
	}
	public String getPotr9(){
		 return ""+ userPrefs.getString("port9","");
	}
	public String getPotr10(){
		 return ""+ userPrefs.getString("port10","");
	}
	public String getPotr11(){
		 return ""+ userPrefs.getString("port11","");
	}
	public String getPotr12(){
		 return ""+ userPrefs.getString("port12","");
	}
	public String getPotr13(){
		 return ""+ userPrefs.getString("port13","");
	}
	
	public void updateSetting(String port8,String port9,String port10,String port11,String port12,String port13){
		SharedPreferences.Editor editor = userPrefs.edit();
		editor.putString("port8", ""+port8.toUpperCase());
		editor.putString("port9", ""+port9.toUpperCase());
		editor.putString("port10", ""+port10.toUpperCase());
		editor.putString("port11", ""+port11.toUpperCase());
		editor.putString("port12", ""+port12.toUpperCase());
		editor.putString("port13", ""+port13.toUpperCase());
		editor.commit();
	}
}