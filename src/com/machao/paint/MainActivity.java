
package com.machao.paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity {
	private ImageView iv_1;
	private Canvas canvas;
	private Paint paint;
	private SeekBar sb1,sb2,sb3,sb4;
	private ColorMatrix cm;
	private Bitmap alterbitmap, bitmap1;
	private Matrix m;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		iv_1 = (ImageView)findViewById(R.id.iv_1);
		sb1 = (SeekBar)findViewById(R.id.sb1);
		sb2 = (SeekBar)findViewById(R.id.sb2);
		sb3 = (SeekBar)findViewById(R.id.sb3);
		sb4 = (SeekBar)findViewById(R.id.sb4);
		
		cm = new ColorMatrix();
		
		bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.pre);
		alterbitmap = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), bitmap1.getConfig());
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		canvas = new Canvas(alterbitmap);
		m = new Matrix();
		canvas.drawBitmap(bitmap1, m, paint);
		
		iv_1.setImageBitmap(alterbitmap);
		
		sb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				int progress = sb1.getProgress();
				cm.set(new float[]{
						progress/128.0f, 0, 0, 0, 0,
						0, 1, 0, 0, 0,
						0, 0, 1, 0, 0,
						0, 0, 0, 1, 0,
				});
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap1, m, paint);
				iv_1.setImageBitmap(alterbitmap);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				int progress = sb2.getProgress();
				cm.set(new float[]{
						1, 0, 0, 0, 0,
						0, progress/128.0f, 0, 0, 0,
						0, 0, 1, 0, 0,
						0, 0, 0, 1, 0,
				});
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap1, m, paint);
				iv_1.setImageBitmap(alterbitmap);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sb3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				int progress = sb3.getProgress();
				cm.set(new float[]{
						1, 0, 0, 0, 0,
						0, 1, 0, 0, 0,
						0, 0, progress/128.0f, 0, 0,
						0, 0, 0, 1, 0,
				});
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap1, m, paint);
				iv_1.setImageBitmap(alterbitmap);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//调节白的程度
		sb4.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				int progress = sb4.getProgress();
/*				cm.set(new float[]{
						progress/128.0f, 0, 0, 0, 0,
						0, progress/128.0f, 0, 0, 0,
						0, 0, progress/128.0f, 0, 0,
						0, 0, 0, 1, 0,
				});*/
				//或者用上面的方法 都可以 下面到一行简洁
				cm.setSaturation(progress/128.0f);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap1, m, paint);
				iv_1.setImageBitmap(alterbitmap);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	
	
	
	
}
