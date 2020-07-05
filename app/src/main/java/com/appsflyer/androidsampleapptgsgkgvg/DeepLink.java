package com.appsflyer.androidsampleapptgsgkgvg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerLibCore;

import java.util.HashMap;
import java.util.Map;

import static com.appsflyer.AppsFlyerLibCore.LOG_TAG;

/** Test this deep link with the link : 5 */
  /** run: $ adb shell am start -a android.intent.action.VIEW -d https://androidsampleapp.onelink.me/Pvqj */



public class DeepLink extends AppCompatActivity {


    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);


        /* Add this call to the tracker on each deep linked activity */

        AppsFlyerLib.getInstance().sendDeepLinkData(this);


        AppsFlyerLib.getInstance().registerConversionListener(this, new AppsFlyerConversionListener() {

            /* Returns the attribution data. Note - the same conversion data is returned every time per install */
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {
                for (String attrName : conversionData.keySet()) {
                    Log.d(LOG_TAG, "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d(LOG_TAG, "error onInstallConversionFailure : " + errorMessage);
            }


            /* Called only when a Deep Link is opened */
            @Override
            public void onAppOpenAttribution(Map<String, String> conversionData) {
                String attributionDataText = "Attribution Data: \n";
                for (String attrName : conversionData.keySet()) {
                    Log.d(LOG_TAG, "attribute: " + attrName + " = " +
                            conversionData.get(attrName));
                    attributionDataText += conversionData.get(attrName) + "\n";

                }
                setAttributionText(attributionDataText);
                goToFruit(conversionData.get("fruit_name"), conversionData);
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d(LOG_TAG, "error onAttributionFailure : " + errorMessage);
            }
        });

    }


    /* Used to display the deep link data returned from onAppOpenAttribution */

    public void setAttributionText(final String data){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView attributionTextView = findViewById(R.id.attributionText);
                attributionTextView.setText(data);
            }
        } , 1500);
    }
      private void goToFruit(String fruitName, Map<String, String> dlData) {
          String fruitClassName = fruitName.concat("Activity");
          try {
              Class fruitClass = Class.forName(this.getPackageName().concat(".").concat(fruitClassName));
              Log.d(LOG_TAG, "Looking for class " + fruitClass);
              Intent intent = new Intent(getApplicationContext(), fruitClass);
//              if (dlData != null) {
//                  // Map is casted HashMap since it is easier to pass serializable data to an intent
//                  HashMap<String, String> copy = new HashMap<String, String>(dlData);
//                  intent.putExtra(DL_ATTRS, copy);
//              }
              startActivity(intent);
          } catch (ClassNotFoundException e) {
              Log.d(LOG_TAG, "Deep linking failed looking for " + fruitName);
              e.printStackTrace();
          }
      }

}
