
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
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {
	private WindowManager wm;
	
	private ImageView iv_1, iv_2;
	private Bitmap afterbitmap;
	private Canvas canvas;
	private Paint paint;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		
		iv_1 = (ImageView)findViewById(R.id.iv_1);
		iv_2 = (ImageView)findViewById(R.id.iv_2);
		
		
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		iv_1.setImageBitmap(bitmap1);
		
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap1.getWidth() * 2, bitmap1.getHeight(), bitmap1.getConfig());
		
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		
		canvas = new Canvas(bitmap2);
//		canvas.drawColor(Color.WHITE);
		Matrix m = new Matrix();
		m.setValues(new float[]{
			2.0f, 0, 0,
			0, 1, 0,
			0, 0, 1,
		});
		//m.setScale(2.0f, 1.0f);
		canvas.drawBitmap(bitmap1, m, paint);
		
		iv_2.setImageBitmap(bitmap2);

 
		

	}

	
	
	
	
}
