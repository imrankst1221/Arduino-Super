package nullpointer.imrankst1221.arduinosuper;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Fragment {
	
	public Settings(){}
	EditText port8,port9,port10,port11,port12,port13;
	Button btnSave;
	public View rootView;
	DataBase db = new DataBase();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        btnSave = (Button)rootView.findViewById(R.id.buttonSave);
        port8 = (EditText)rootView.findViewById(R.id.editPort8);
        port9 = (EditText)rootView.findViewById(R.id.editPort9);
        port10 = (EditText)rootView.findViewById(R.id.editPort10);
        port11 = (EditText)rootView.findViewById(R.id.editPort11);
        port12 = (EditText)rootView.findViewById(R.id.editPort12);
        port13 = (EditText)rootView.findViewById(R.id.editPort13);
        
        port8.setText(db.getPotr8());
        port9.setText(db.getPotr9());
        port10.setText(db.getPotr10());
        port11.setText(db.getPotr11());
        port12.setText(db.getPotr12());
        port13.setText(db.getPotr13());
        
        
        btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db.updateSetting(port8.getText().toString(), port9.getText().toString(), port10.getText().toString(), port11.getText().toString(), port12.getText().toString(), port13.getText().toString());
				Toast msg = Toast.makeText(rootView.getContext(),
		                  "New setting saved", Toast.LENGTH_LONG);
		              msg.show();
			}
		});
        
        
        return rootView;
    }
}
