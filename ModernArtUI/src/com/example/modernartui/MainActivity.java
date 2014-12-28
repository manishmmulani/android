package com.example.modernartui;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends ActionBarActivity {
	
	private ImageView iv1;
	private ImageView iv2;
	private ImageView iv3;
	private ImageView iv4;
	private ImageView iv5;
	private ImageView iv6;
	
	private SeekBar seekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv1 = (ImageView)findViewById(R.id.imageView1);
		iv2 = (ImageView)findViewById(R.id.imageView2);
		iv3 = (ImageView)findViewById(R.id.imageView3);
		iv4 = (ImageView)findViewById(R.id.imageView4);
		iv5 = (ImageView)findViewById(R.id.imageView5);
		iv6 = (ImageView)findViewById(R.id.imageView6);
		seekBar = (SeekBar)findViewById(R.id.seekBar1);
		
		seekBar.setMax(100);
		
		iv2.setBackgroundColor(Color.WHITE);
		
		iv1.setBackgroundColor(Color.argb(0xFF, 0x10000, 0x100, 0xff000000));
		iv3.setBackgroundColor(Color.argb(0xFF, 0x00, 0xFF, 0x00));
		iv4.setBackgroundColor(Color.argb(0xFF, 0xFF, 0x00, 0x00));
		iv5.setBackgroundColor(Color.argb(0xFF, 0xFF, 0xFF, 0x00));
		iv6.setBackgroundColor(Color.argb(0xFF, 0x00, 0x00, 0xFF));
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				progress = progresValue;
				if (progress == 0) {
					return;
				}
				int lower = Color.argb(0xFF, progress * 0x10000,
						progress * 0x100, progress) + 0xff000000;
				iv1.setBackgroundColor(lower);
				
				int lower3 = Color.argb(0xFF, progress * 0x00,
						progress * 0xff, progress);
				iv3.setBackgroundColor(lower3);
				
				
				int lower4 = Color.argb(0xFF, 0xff,
						(int)(((double)progress / (double)100) * 0xff), 0);
				iv4.setBackgroundColor(lower4);
				
				int lower5 = Color.argb(0xFF, (int)(((double)(100 - progress) / (double)100) * 0xFF), 0xFF, 0);
				iv5.setBackgroundColor(lower5);
				
				int lower6 = Color.argb(0xFF, (int)(((double)progress / (double)100) * 0xFF), 0, 0xFF);
				iv6.setBackgroundColor(lower6);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				return;
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				return;
			}
		});
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
//			Toast.makeText(getApplicationContext(), "hello world, haaaaa",
//					Toast.LENGTH_SHORT).show();
			AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setTitle("Modern Art UI")
				.setMessage("please make your choice!");
			setPositiveButton(builder);
			setNegativeButton(builder)
				.create()
				.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
		return builder.setPositiveButton("Visit", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Uri webpage = Uri.parse("http://www.baidu.com");
				Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
				
				Intent chooserIntent = null;
				chooserIntent = Intent.createChooser(intent, "Choose An App");	        

				if (chooserIntent.resolveActivity(getPackageManager()) != null) {
				    startActivity(chooserIntent);
				}
			}
		});
	}
	
	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
		return builder.setNegativeButton("Not now", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
	}
}