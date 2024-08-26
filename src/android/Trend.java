package in.rtac.Trend;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.zcs.sdk.DriverManager;
import com.zcs.sdk.Printer;
import com.zcs.sdk.SdkResult;
import com.zcs.sdk.print.PrnTextFont;
import com.zcs.sdk.print.PrnTextStyle;
import java.io.IOException;
import java.io.InputStream;

public class Trend {

    private Activity mActivity;
    private DriverManager mDriverManager;
    private Printer mPrinter;
    private Sys mSys;

    public Trend(Activity callingActivity) {
        mActivity = callingActivity;
        mDriverManager = new DriverManager(mActivity);
       
    }

  

    public void printBitmap(String imageString) {
        mSys = mDriverManager.getBaseSysDevice();
        mPrinter = mDriverManager.getPrinter();
        int status = mSys.sdkInit();
        Log.e("status", "status "+status);
        if(status != SdkResult.SDK_OK) {
            mSys.sysPowerOn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = mSys.sdkInit();
        if(status != SdkResult.SDK_OK) {
            Log.e("PrintTrend", "SDK Initialization failed. Status: " + status);
        }else{
            try {
                int printStatus = mPrinter.getPrinterStatus();
                if (printStatus == SdkResult.SDK_PRN_STATUS_PAPEROUT) {
                    Log.e("PrintTrend", "Printer status indicates paper is out");
                } else {
                    byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                    Log.e("imageBytes", "imageBytes"+imageBytes);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    if (bitmap == null) {
                        Log.e("PrintTrend", "Bitmap is null");
                        return false;
                    }

                    mPrinter.setPrintAppendBitmap(bitmap, Layout.Alignment.ALIGN_CENTER);
                    printStatus = mPrinter.setPrintStart();
                }

            }catch (Exception e){
                editText.setText("printStatus exr-"+e);
                Log.e("Exception", String.valueOf(e));
            }
        }
    
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Setup preferences if needed
    }
}

