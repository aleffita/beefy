package com.beefy;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.react.PackageList;
import com.facebook.react.modules.network.NetworkingModule;
import com.reactnativenavigation.NavigationApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.reactnativenavigation.react.NavigationReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class MainApplication extends NavigationApplication {

  private final ReactNativeHost mReactNativeHost =
      new NavigationReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    initializeInspector(this);
  }

  private void initializeInspector(Context context) {
    if (BuildConfig.DEBUG)
      try {
        Class<?> inspector = Class.forName("com.beefy.inspector.ReactInspector");
        inspector.getMethod("initializeInspector", Context.class).invoke(null, context);

      } catch (Exception e) {
        Log.d("Inspector", e.toString());
      }
    else
      NetworkingModule.setCustomClientBuilder(builder -> {
        builder.cookieJar(new InspectorCookieJar());
      });
  }

}
