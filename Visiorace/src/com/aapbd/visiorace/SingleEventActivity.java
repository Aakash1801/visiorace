package com.aapbd.visiorace;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aapbd.visiorace.utils.BitmapUtils;

public class SingleEventActivity extends Activity {
	// private TextView register;
	private EditText comment;
	private Context con;
	private String mcomment;
	private ImageView camera, star;

	private byte photoFileData[] = new byte[0];
	Bitmap defBitmap, smallBitmap;

	final int DIALOG_PHOTO = 1;

	final int SELECT_IMAGE = 1;
	final int CAPTURE_IMAGE = 2;

	private InputStream imageStream = null;
	private ImageView previewImage = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.singleeventview);
		con = this;
		// register = (TextView) findViewById(R.id.Register);
		comment = (EditText) findViewById(R.id.Comments);
		camera = (ImageView) findViewById(R.id.camera);
		star = (ImageView) findViewById(R.id.starIcon);

		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showCameradialog();
			}
		});

		star.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showRatingdialog();
			}
		});
	}

	public void setBack(View v) {
		finish();
	}

	// public void setForget(View v) {
	// forgetPassVaildation();
	// }
	//
	// public void forgetPassVaildation() {
	// if (TextUtils.isEmpty(comment.getText())) {
	// AlertMessage.showMessage(con, "Error",
	// "Please Enter Nome utente o email");
	// return;
	// }
	//
	// }

	private void showCameradialog() {
		final Dialog dialog = new Dialog(con,
				android.R.style.Theme_Translucent_NoTitleBar);

		// Setting dialogview
		Window window = dialog.getWindow();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		window.setGravity(Gravity.CENTER);

		// window.setLayout(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT);
		dialog.setTitle(null);
		dialog.setContentView(R.layout.cameradialog);
		dialog.setCancelable(true);
		final ImageView camera = (ImageView) dialog
				.findViewById(R.id.camera_icon);
		final ImageView album = (ImageView) dialog
				.findViewById(R.id.album_icon);

		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				capturePhotoByCamera();

			}
		});

		album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				selectPhotoFromGallery();
			}
		});

		dialog.show();
	}

	public void selectPhotoFromGallery() {
		startActivityForResult(new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
				SELECT_IMAGE);

	}

	public void capturePhotoByCamera() {

		try {

			final Intent i = new Intent("android.media.action.IMAGE_CAPTURE");

			startActivityForResult(i, CAPTURE_IMAGE);

		} catch (final Exception e) {

			Log.e("Edit Player", e.getMessage());

		}

	}

	@Override
	public void onActivityResult(final int requestCode, final int resultCode,
			final Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CAPTURE_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {

				try {
					final Bundle extras = data.getExtras();

					final Bitmap b = (Bitmap) extras.get("data");

					smallBitmap = BitmapUtils.decodeScaled(BitmapUtils
							.saveAsJpeg(b));

					// smallBitmap = RoundedCornerBitmap.getRoundedCornerBitmap(
					// con, b, 1000, 1000, 100, 100, 200, 200);
					// previewImage.setImageBitmap(smallBitmap);

					/*
					 * convert to inputstream from byte array
					 */
					// imageStream =
					// BitmapUtils.bitmapToInputStream(smallBitmap);

				} catch (final Exception e) {
					Log.w("EditPlayer", e.toString());
				}

			}
		} else if (requestCode == SELECT_IMAGE) {

			Log.d("this is at on activity result",
					"this is select image from gallery");
			if (resultCode == Activity.RESULT_OK) {

				final Uri selectedImage = data.getData();

				try {
					final InputStream is = getContentResolver()
							.openInputStream(selectedImage);
					try {
						if (is.available() > 0) {

							photoFileData = new byte[is.available()];
							is.read(photoFileData, 0, is.available());
							is.close();

							smallBitmap = BitmapUtils
									.decodeScaled(photoFileData);
							// smallBitmap = BitmapUtils
							// .getRoundedCornerBitmap2(smallBitmap);
							// smallBitmap = RoundedCornerBitmap
							// .getRoundedCornerBitmap(con, b, 80,
							// 80, 80, 80, 80, 80);

							// smallBitmap = RoundedCornerBitmap
							// .getRoundedCornerBitmap(con, smallBitmap,
							// 1000, 1000, 100, 100, 200, 200);
							// previewImage.setImageBitmap(smallBitmap);
							final ByteArrayOutputStream out = new ByteArrayOutputStream();
							smallBitmap.compress(Bitmap.CompressFormat.JPEG,
									100, out);

							// imageStream =
							// BitmapUtils.bitmapToInputStream(smallBitmap);

						}

						/*
						 * convert to inputstream from byte array
						 */

					} catch (final IOException e) {
					}

				} catch (final FileNotFoundException e) {
				}

			}

		}
	}

	private void showRatingdialog() {
		final Dialog dialog = new Dialog(con,
				android.R.style.Theme_Translucent_NoTitleBar);

		// Setting dialogview
		Window window = dialog.getWindow();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		window.setGravity(Gravity.CENTER);

		// window.setLayout(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT);
		dialog.setTitle(null);
		dialog.setContentView(R.layout.ratingdialog);
		dialog.setCancelable(true);
		final Button ok = (Button) dialog.findViewById(R.id.OkButton);

		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.cancel();
			}
		});

		dialog.show();
	}

}
