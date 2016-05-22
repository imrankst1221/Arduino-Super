package nullpointer.imrankst1221.arduinosuper;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Home extends Fragment {
	private static final String TAG = "------------------";
	  
	  ToggleButton btn8, btn9,btn10,btn11,btn12,btn13;
	  public View rootView;
	  private static final int REQUEST_ENABLE_BT = 1;
	  private BluetoothAdapter btAdapter = null;
	  private BluetoothSocket btSocket = null;
	  private OutputStream outStream = null;
	  
	  //get voice need var
	  protected static final int RESULT_SPEECH = 1;
	  private ImageButton btnSpeak;
	  private TextView txtText;
	  
	  // Well known SPP UUID
	  private static final UUID MY_UUID =
	      UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	  // Insert your bluetooth devices MAC address
	  private static String address = "20:15:03:16:15:66";
	  
	  DataBase db = new DataBase();
	  
	public Home(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        rootView = inflater.inflate(R.layout.fragment_home, container, false);       

        btn8 = (ToggleButton) rootView.findViewById(R.id.btn8);
        btn9 = (ToggleButton) rootView.findViewById(R.id.btn9);
        btn10 = (ToggleButton) rootView.findViewById(R.id.btn10);
        btn11 = (ToggleButton) rootView.findViewById(R.id.btn11);
        btn12 = (ToggleButton) rootView.findViewById(R.id.btn12);
        btn13 = (ToggleButton) rootView.findViewById(R.id.btn13);
        
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        checkBTState();

        btn8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("a");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 8", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("b");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 8", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        
        btn9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("c");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 9", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("d");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 9", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        btn10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("e");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 10", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("f");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 10", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        
        btn11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("g");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 11", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("h");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 11", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        btn12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("i");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 12", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("j");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 12", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        
        btn13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if (isChecked) {
        	  sendData("k");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked On at port 13", Toast.LENGTH_SHORT);
              msg.show();
          } else {
        	  sendData("l");
              Toast msg = Toast.makeText(rootView.getContext(),
                  "You have clicked Off at port 13", Toast.LENGTH_SHORT);
              msg.show();
          }
            }
        });
        //for get google voice to text 
        txtText = (TextView) rootView.findViewById(R.id.txtText);

    	btnSpeak = (ImageButton) rootView.findViewById(R.id.btnSpeak);

    	btnSpeak.setOnClickListener(new View.OnClickListener() {

    		@Override
    		public void onClick(View v) {

    			Intent intent = new Intent(
    					RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

    			try {
    				startActivityForResult(intent, RESULT_SPEECH);
    				txtText.setText("");
    			} catch (ActivityNotFoundException a) {
    				Toast t = Toast.makeText(v.getContext(),
    						"Ops! Your device doesn't support Speech to Text",
    						Toast.LENGTH_SHORT);
    				t.show();
    			}
    		}
    	});
    	
        return rootView;
    }
	
	    private void connectBlutooth() {
	    // Set up a pointer to the remote node using it's address.
	    BluetoothDevice device = btAdapter.getRemoteDevice(address);
	  
	    // Two things are needed to make a connection:
	    //   A MAC address, which we got above.
	    //   A Service ID or UUID.  In this case we are using the
	    //     UUID for SPP.
	    try {
	      btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
	    } catch (IOException e) {
	      Log.e(TAG+" Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
	    }
	  
	    // Discovery is resource intensive.  Make sure it isn't going on
	    // when you attempt to connect and pass your message.
	    btAdapter.cancelDiscovery();
	  
	    // Establish the connection.  This will block until it connects.
	    Log.e(TAG, "...Connecting to Remote...");
	    try {
	      btSocket.connect();
	      Log.d(TAG, "...Connection established and data link opened...");
	    } catch (IOException e) {
	      try {
	        btSocket.close();
	      } catch (IOException e2) {
	        Log.e(TAG+" Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
	      }
	    }
	    
	    // Create a data stream so we can talk to server.
	    Log.e(TAG, "...Creating Socket...");

	    try {
	      outStream = btSocket.getOutputStream();
	    } catch (IOException e) {
	      Log.e(TAG+" Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
	    }
	}
  
		  @Override
		  public void onResume() {
		    super.onResume();
		    Log.e(TAG, "...In onResume - Attempting client connect...");
		    connectBlutooth();
		  }
		
		  @Override
		  public void onPause() {
		    super.onPause();
		
		    Log.e(TAG, "...In onPause()...");
		
		    if (outStream != null) {
		      try {
		        outStream.flush();
		      } catch (IOException e) {
		        Log.e(TAG+" Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
		      }
		    }
		
		    try     {
		      btSocket.close();
		    } catch (IOException e2) {
		      Log.e(TAG+" Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
		    }
		  }
		  private void checkBTState() {
		    // Check for Bluetooth support and then check to make sure it is turned on
		
		    // Emulator doesn't support Bluetooth and will return null
		    if(btAdapter==null) { 
		    	Log.e(TAG+"Fatal Error", "Bluetooth Not supported. Aborting.");
		    } else {
		      if (btAdapter.isEnabled()) {
		    	  Log.e(TAG, "...Bluetooth is enabled...");
		      } else {
		        //Prompt user to turn on Bluetooth
		        Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
		        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		      }
		    }
		  }
		
		  private void errorExit(String title, String message){
		    //Toast msg = Toast.makeText(getBaseContext(),
		    //    title + " - " + message, Toast.LENGTH_SHORT);
		    Toast msg = Toast.makeText(rootView.getContext(),
		            title + "Bluetooth hc-06 not found", Toast.LENGTH_SHORT);
		    msg.show();
		    //finish();
		  }
		  
		  private void sendData(String message) {
		    byte[] msgBuffer = message.getBytes();
		
		    Log.e(TAG, "...Sending data: " + message + "...");
		
		    try {
		      outStream.write(msgBuffer);
		    } catch (IOException e) {
		      String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
		      if (address.equals("00:00:00:00:00:0")) 
		        msg = msg + ".\n\nUpdate your server address from 00:00:00:00:00:00 to the correct address on line 37 in the java code";
		      msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";
		      Log.e(TAG, "... " + msg + "...");
		      errorExit("Fatal Error", msg);       
		    }
		  }
		String voiceText="";
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case RESULT_SPEECH: {
				if (resultCode == android.app.Activity.RESULT_OK && null != data) {

					ArrayList<String> text = data
							.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

					txtText.setText(text.get(0).toUpperCase());
					voiceText = text.get(0);
					//connectBlutooth();
					checkBTState();
					
					new Handler().postDelayed(new Runnable() {
			        	@Override
			            public void run() {
			        		if(voiceText.toUpperCase().equals(db.getPotr8()+" ON")){
								sendData("a");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 8", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr8()+" OFF")){
								sendData("b");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 8", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else if(voiceText.toUpperCase().equals(db.getPotr9()+" ON")){
								sendData("c");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 9", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr9()+" OFF")){
								sendData("d");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 9", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else if(voiceText.toUpperCase().equals(db.getPotr10()+" ON")){
								sendData("e");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 10", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr10()+" OFF")){
								sendData("f");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 10", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else if(voiceText.toUpperCase().equals(db.getPotr11()+" ON")){
								sendData("g");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 11", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr11()+" OFF")){
								sendData("h");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 11", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else if(voiceText.toUpperCase().equals(db.getPotr12()+" ON")){
								sendData("i");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 12", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr12()+" OFF")){
								sendData("j");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 12", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else if(voiceText.toUpperCase().equals(db.getPotr13()+" ON")){
								sendData("k");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked on at port 13", Toast.LENGTH_SHORT);
						              msg.show();
							}else if(voiceText.toUpperCase().equals(db.getPotr13()+" OFF")){
								sendData("l");
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "You have clicked off at port 13", Toast.LENGTH_SHORT);
						              msg.show();
							}
							
							else {
								Toast msg = Toast.makeText(rootView.getContext(),
						                  "Voice not match. No port available for this voice.", Toast.LENGTH_SHORT);
						              msg.show();
							}
			        		
			            }
			        }, 5000); // wait for 5 seconds
					
				}
				break;
			}
			}
		}
}
