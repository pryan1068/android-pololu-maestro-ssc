package org.pololu.maestro;

import com.pololu.maestro.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Main Activity with sliders for controlling servos.
 * 
 * This class simply translates UI actions into MaestroSSC calls.
 * 
 * @author  Patrick H. Ryan
 * 
 */
public class MaestroSSCActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
	private static final String TAG = "MaestroSSCActivity";
	private Button homeButton;
	private SeekBar channel1PositionBar, channel2PositionBar, channel3PositionBar, channel4PositionBar, channel5PositionBar, channel6PositionBar;
	private TextView errorsTextView;
	private MaestroSSC maestroSSC; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = getLayoutInflater().inflate(R.layout.ssc_activity, null);
		setContentView(view);
		
		homeButton = (Button) view.findViewById(R.id.homeButton);
		homeButton.setOnClickListener(this);
		
		channel1PositionBar = (SeekBar) view.findViewById(R.id.channel1PositionBar);
		channel1PositionBar.setOnSeekBarChangeListener(this);
		channel2PositionBar = (SeekBar) view.findViewById(R.id.channel2PositionBar);
		channel2PositionBar.setOnSeekBarChangeListener(this);
		channel3PositionBar = (SeekBar) view.findViewById(R.id.channel3PositionBar);
		channel3PositionBar.setOnSeekBarChangeListener(this);
		channel4PositionBar = (SeekBar) view.findViewById(R.id.channel4PositionBar);
		channel4PositionBar.setOnSeekBarChangeListener(this);
		channel5PositionBar = (SeekBar) view.findViewById(R.id.channel5PositionBar);
		channel5PositionBar.setOnSeekBarChangeListener(this);
		channel6PositionBar = (SeekBar) view.findViewById(R.id.channel6PositionBar);
		channel6PositionBar.setOnSeekBarChangeListener(this);
		
		errorsTextView = (TextView) view.findViewById(R.id.errorsTextView);
		
		maestroSSC = new MaestroSSC();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Intent intent = getIntent();
//		Log.d(TAG, "onResume(" + intent + ")");
		String action = intent.getAction();

		if (action.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
			UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
			if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
				UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
				maestroSSC.setDevice(usbManager, device);
			} else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
				maestroSSC.setDevice(null, null);
			} else {
				Log.d(TAG, "Unexpected Action=" + action.toString());
			}
		}
	}
	
	/**
	 * Handle button clicks
	 */
	@Override
	public void onClick(View v) {
//		Log.d(TAG,"onClick(" + v.getId() + ")");
		
		if (v.equals(homeButton)) {
			// Have not figured out how to do goHome() yet. Just center the sliders for now.
			//maestroSSC.goHome();
			channel1PositionBar.setProgress(channel1PositionBar.getMax() / 2);
			channel2PositionBar.setProgress(channel2PositionBar.getMax() / 2);
			channel3PositionBar.setProgress(channel3PositionBar.getMax() / 2);
			channel4PositionBar.setProgress(channel4PositionBar.getMax() / 2);
			channel5PositionBar.setProgress(channel5PositionBar.getMax() / 2);
			channel6PositionBar.setProgress(channel6PositionBar.getMax() / 2);
		}
	}

	/**
	 * SeekBar Listener
	 */
	public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser) {
//		Log.d(TAG,"onProgressChanged(" + seekBar.getId() + ", " + progress + ")");
		
		if (seekBar.equals(channel1PositionBar)) {
			maestroSSC.setTarget(0, progress);
		} else if (seekBar.equals(channel2PositionBar)) {
			maestroSSC.setTarget(1, progress);
		} else if (seekBar.equals(channel3PositionBar)) {
			maestroSSC.setTarget(2, progress);
		} else if (seekBar.equals(channel4PositionBar)) {
			maestroSSC.setTarget(3, progress);
		} else if (seekBar.equals(channel5PositionBar)) {
			maestroSSC.setTarget(4, progress);
		} else if (seekBar.equals(channel6PositionBar)) {
			maestroSSC.setTarget(5, progress);
		}
	}

	/**
	 * SeekBar Listener
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	/**
	 * SeekBar Listener
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}	
}
