package com.aapbd.visiorace.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

public class BitmapUtils {

	public static Bitmap rescaledBitmap(final Bitmap bit, final int x,
			final int y, final boolean flag) {

		return Bitmap.createScaledBitmap(bit, x, y, true);

	}

	public static Bitmap resizeMatrix(final Bitmap BitmapOrg, int x, int y,
			final boolean flag) {

		final int origHeight = BitmapOrg.getHeight();
		final int origWidth = BitmapOrg.getWidth();
		float scaleWidth;
		float scaleHeight;
		if (origWidth >= origHeight) {
			scaleWidth = (float) x / origWidth;
			scaleHeight = scaleWidth;
		} else {
			scaleHeight = (float) y / origHeight;
			scaleWidth = scaleHeight;
		}
		x = Math.round(origWidth * scaleWidth);
		y = Math.round(origHeight * scaleWidth);
		;

		// create a matrix for the manipulation
		final Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		return Bitmap.createBitmap(BitmapOrg, 0, 0, x, y, matrix, flag);

	}

	public void function() {
		final int origWidth = 327;
		final int origHeight = 496;
		int newWidth = 320;
		final int newHeight = 455;
		float scaleWidth;
		float scaleHeight;
		float scaleFactor;
		long start;
		long end;
		start = System.nanoTime();

		scaleWidth = (float) newWidth / origWidth;
		scaleHeight = (float) newHeight / origHeight;
		scaleFactor = Math.min(scaleWidth, scaleHeight);

		newWidth = Math.round(origWidth * scaleFactor);
		newWidth = Math.round(origHeight * scaleFactor);

		end = System.nanoTime();
		Log.e("TIME", "" + (end - start));

	}

	public static BitmapDrawable getRoundedCornerBitmap(
			final Bitmap smallBitmap, final int width, final int height) {
		final Bitmap output = Bitmap.createBitmap(smallBitmap.getWidth(),
				smallBitmap.getHeight(), Config.ARGB_8888);
		final Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, smallBitmap.getWidth(),
				smallBitmap.getHeight());
		// final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);
		final float roundPx = 12;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(smallBitmap, rect, rect, paint);

		return new BitmapDrawable(output);
	}

	public static Bitmap getRoundedCornerBitmap2(final Bitmap bitmap) {
		final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		final Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = 12;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
	
	public static Bitmap getBitmapFromUrl(String url) {
	    Bitmap bitmap = null;
	    HttpGet httpRequest = null;
	    httpRequest = new HttpGet(url);
	    HttpClient httpclient = new DefaultHttpClient();

	    HttpResponse response = null;
	    try {
	        response = httpclient.execute(httpRequest);
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    if (response != null) {
	        HttpEntity entity = response.getEntity();
	        BufferedHttpEntity bufHttpEntity = null;
	        try {
	            bufHttpEntity = new BufferedHttpEntity(entity);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        InputStream instream = null;
	        try {
	            instream = bufHttpEntity.getContent();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        bitmap = BitmapFactory.decodeStream(instream);
	    }
	    return bitmap;
	}

	public static byte[] saveAsJpeg(final Bitmap bitmap) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		return out.toByteArray();
	}

	public static InputStream bitmapToInputStream(Bitmap bitmap) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		byte[] bitmapdata=out.toByteArray();
		ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
	    return bs;
	}
	public static Bitmap decodeScaled(final byte data[]) {
		final int MAX_SIZE = 512;
		if (data.length > 0) {
			try {
				final BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length, options);
				final int fullWidth = options.outWidth;
				final int fullHeight = options.outHeight;
				int w = 0;
				int h = 0;
				if (fullWidth > fullHeight) {
					w = MAX_SIZE;
					h = w * fullHeight / fullWidth;
				} else {
					h = MAX_SIZE;
					w = h * fullWidth / fullHeight;
				}
				options.inJustDecodeBounds = false;
				options.inSampleSize = fullWidth / w;
				bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
						options);
				return bitmap;
			} catch (final Exception e) {
			}
		}
		return null;
	}

}
