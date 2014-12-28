package com.mulani.androidui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "AndroidUI-Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
		final List<TextView> colorPanes = getColorPanes();

		// Storing the initial colors. We shall XOR each color with the progress
		// made through seekbar.
		final List<Integer> initialColors = new ArrayList<>();

		for (TextView colorPane : colorPanes) {
			ColorDrawable drawable = (ColorDrawable) colorPane.getBackground();
			initialColors.add(drawable.getColor());
		}

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "Progress made : " + progress);
				for (int i = 0; i < colorPanes.size(); i++) {
					TextView colorPane = colorPanes.get(i);

					int color = initialColors.get(i);

					if (!isWhite(color)) {
						int prevAlpha = (color >> 24) & 0xFF;
						int red = redComponent(color);
						int green = greenComponent(color);
						int blue = blueComponent(color);

						int newColor = (prevAlpha << 24)
								| ((red ^ progress) << 16)
								| ((green ^ progress) << 8) | (blue ^ progress);
						colorPane.setBackgroundColor(newColor);

						Log.i(TAG, "{ Previous color : " + color
								+ ", New color : " + newColor + "}");
					}

				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				this.progress = progress;
			}
		});
	}

	private int redComponent(int color) {
		return (color >> 16) & 0xFF;
	}

	private int greenComponent(int color) {
		return (color >> 8) & 0xFF;
	}

	private int blueComponent(int color) {
		return color & 0xFF;
	}

	private boolean isWhite(int color) {
		return redComponent(color) == 0xFF && greenComponent(color) == 0xFF
				&& blueComponent(color) == 0xFF;
	}

	private List<TextView> getColorPanes() {
		List<TextView> colorPanes = Arrays.asList(
				(TextView) findViewById(R.id.colorPane1),
				(TextView) findViewById(R.id.colorPane2),
				(TextView) findViewById(R.id.colorPane3),
				(TextView) findViewById(R.id.colorPane4),
				(TextView) findViewById(R.id.colorPane5));

		return colorPanes;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			AlertDialogFragment dialogFragment = AlertDialogFragment.getInstance();
			dialogFragment.show(getFragmentManager(), "Alert");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
