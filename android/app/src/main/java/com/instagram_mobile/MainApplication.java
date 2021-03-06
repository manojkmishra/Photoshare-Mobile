package com.instagram_mobile;
import com.facebook.react.ReactPackage;


import com.reactnativenavigation.NavigationApplication;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.BV.LinearGradient.LinearGradientPackage;
import com.facebook.CallbackManager;
import com.facebook.reactnative.androidsdk.FBSDKPackage;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.FacebookSdk;

import java.util.Arrays;
import java.util.List;
import com.oblador.vectoricons.VectorIconsPackage;
import com.reactnativenavigation.controllers.ActivityCallbacks;
import android.content.Intent;

public class MainApplication extends NavigationApplication 
{   private static CallbackManager mCallbackManager = CallbackManager.Factory.create();
    protected static CallbackManager getCallbackManager() {   return mCallbackManager; }
    
    @Override
    public boolean isDebug() 
    {  // Make sure you are using BuildConfig from your own application
        return BuildConfig.DEBUG;
    }

    protected List<ReactPackage> getPackages() 
    {   // Add additional packages you require here
        // No need to add RnnPackage and MainReactPackage
        return Arrays.<ReactPackage>asList(
             new VectorIconsPackage(),
             new RNGestureHandlerPackage(),
             new LinearGradientPackage(),
             new FBSDKPackage(mCallbackManager)
        );
    }

    @Override
    public List<ReactPackage> createAdditionalReactPackages() {   return getPackages();  }

    @Override
    public String getJSMainModuleName() { return "index";  }

    public void onCreate() 
    {    super.onCreate();
        setActivityCallbacks(new ActivityCallbacks() 
                {   @Override
                    public void onActivityResult(int requestCode, int resultCode, Intent data) 
                    {  mCallbackManager.onActivityResult(requestCode, resultCode, data);
                    }
                });
        FacebookSdk.sdkInitialize(getApplicationContext());
        // If you want to use AppEventsLogger to log events.
        AppEventsLogger.activateApp(this);
      }
}