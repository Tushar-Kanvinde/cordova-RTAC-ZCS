package in.rtac.RTACTrend;

import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.rtac.Trend.Trend;
import android.app.Activity;
import android.view.WindowManager;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class RTACTrend  extends CordovaPlugin {
    private in.rtac.RTACTrend.RTACTrend mContext;
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        switch(action){
            case "printBitmap":
                String imageString = args.getString(0);
                printTrendBitmap(callbackContext, imageString);
                return true;
            default:
                return false;
        }
    }

    void printTrendBitmap(CallbackContext callbackContext,String base64) throws JSONException {
        Trend TREND = new Trend(this.cordova.getActivity());
        TREND.printBitmap(base64);
        callbackContext.success();
        return;
    }
}