package quiniela.tesis.com.mundial2018.tools;

/**
 * Clase con métodos estáticos miscelaneos
 * */


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import quiniela.tesis.com.mundial2018.R;

public class Tools {

	public static String getCurrentTimeStamp() {
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

			return currentTimeStamp;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static void showMessage(final String msj, Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setMessage(msj)
				.setTitle(context.getString(R.string.app_name))
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		AlertDialog alertDialog = builder.create();
		alertDialog.show();

	}


	public static String getIMEI(Context context) {
		TelephonyManager mngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return "";
		}
		String imei = mngr.getDeviceId();
	    return imei;
	}
	
	/*Ejemplo de implementación
	 * 
	 * Tools.exportDB2(getActivity(),
    		getActivity().getPackageName(),
    		DatabaseHandler.DATABASE_NAME
    		);*/
	public static void exportDataBase(Context context, String packageName, String databaseName){
		File sd = Environment.getExternalStorageDirectory();
     	File data = Environment.getDataDirectory();
     	FileChannel source=null;
     	FileChannel destination=null;
     	// ejemplo "/data/"+ "com.authorwjf.sqliteexport" +"/databases/"+SAMPLE_DB_NAME;
     	String currentDBPath = String.format("/data/%s/databases/%s",
				packageName,// paquete de la aplicaciòn com.example.app
				databaseName // nombre de la base de datos
		);
    		   
     	String backupDBPath = String.format("%s.db", databaseName); //Nombre del archivo backup, en la raiz de la sd
     	File currentDB = new File(data, currentDBPath);
     	File backupDB = new File(sd, backupDBPath);
     	try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(context, "BD exportada con éxito", Toast.LENGTH_LONG).show();
            //Después de crear el archivo se envía por correo electrónico
            	enviarCorreo(context, sd+"/"+backupDBPath, "Exportación base de datos");
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}
	public static void enviarCorreo(Context context, String path,String subject) {
		Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE); // it's not ACTION_SEND
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, "Sqlite 3");

	 	String file1 =path;
	 		//Se mantiene array por si fuese necesario exportar varias bases de datos
	 	ArrayList<Uri> uris = new ArrayList<Uri>();
	 	File fileIn = new File(file1);
	 	Uri u = Uri.fromFile(fileIn);
	 	uris.add(u);
		intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		context.startActivity(Intent.createChooser(intent, "Send mail..."));

	}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
            int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
	}
    
    public static int calculateInSampleSize(
	            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	    return inSampleSize;
	}
    public static void saveImageViewOnSD(String path, String namefile, ImageView feedImg){
        BitmapDrawable drawable = (BitmapDrawable) feedImg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        if(bitmap!=null){
	        File sdCardDirectory = Environment.getExternalStorageDirectory();
	        File dir = new File(sdCardDirectory.getAbsolutePath() + path);
	        dir.mkdirs();
	        File image = new File(dir, namefile);
	        // Encode the file as a PNG image.
	        FileOutputStream outStream = null;
	        try {
	            outStream = new FileOutputStream(image);
	            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
	        /* 100 para mantener la calidad de la imagen */
	            outStream.flush();
	            outStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }else{
        	Log.e(Tools.class.getName(), "Error al cargar imagen");
        	//Toast.makeText(getApplicationContext(), "Error al cargar imagen", Toast.LENGTH_LONG).show();
        }
    }
    public static Bitmap getCircleBitmap(Bitmap bitmap) {

	    //crop to circle 
	    Bitmap output;
	    //check if its a rectangular image
	    if (bitmap.getWidth() > bitmap.getHeight()) {
	        output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
	    } else {
	        output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
	    }
	    Canvas canvas = new Canvas(output);

	    float r = 0;

	    if (bitmap.getWidth() > bitmap.getHeight()) {
	        r = bitmap.getHeight() / 2;
	    } else {
	        r = bitmap.getWidth() / 2;
	    }

	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());


	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);

	    canvas.drawCircle(r, r, r, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);

	    return output;
	   }


}
