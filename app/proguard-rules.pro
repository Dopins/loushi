# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\Android/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}


-ignorewarnings						# 忽略警告，避免打包时某些警告出现
-optimizationpasses 5				# 指定代码的压缩级别
-dontusemixedcaseclassnames			# 是否使用大小写混合
-dontskipnonpubliclibraryclasses	# 是否混淆第三方jar
-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法
 #混淆前后的映射
 -printmapping mapping.txt

-dontwarn android.support.v4.**     #缺省proguard 会检查每一个引用是否正确，但是第三方库里面往往有些不会用到的类，没有正确引用。如果不配置的话，系统就会报错。
-dontwarn android.os.**
-keep class android.support.v4.** { *; } 		# 保持哪些类不被混淆
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class android.os.**{*;}

-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.widget
-keep public class * extends com.sqlcrypt.database
-keep public class * extends com.sqlcrypt.database.sqlite
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends com.treecore.**
-keep public class * extends de.greenrobot.dao.**
#model
#-keep class * com.android.loushi.loushi.jsonbean
#-keep class * com.android.loushi.loushi.util
#-keep class * com.android.loushi.loushi.callback
-keep class com.android.loushi.loushi.jsonbean.** { *; }
-keep class com.android.loushi.loushi.util.** { *; }
-keep class com.android.loushi.loushi.callback.** { *; }
-keepclasseswithmembernames class * {		# 保持 native 方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * {			 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {			 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity { #保持类成员
   public void *(android.view.View);
}

#webview
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers enum * {					# 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {	# 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}


#eventbus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#picasso
-dontwarn com.squareup.**
-keep class com.squareup.** { *;}

#淘宝
-keepattributes Signature
    -keep class sun.misc.Unsafe { *; }
    -keep class com.taobao.** {*;}
    -keep class com.alibaba.** {*;}
    -keep class com.alipay.** {*;}
    -keep class com.alibaba.nb.** {*;}
    -dontwarn com.alibaba.nb.**
    -dontwarn com.taobao.**
    -dontwarn com.alibaba.**
    -dontwarn com.alipay.**
    -keep class com.ut.** {*;}
    -dontwarn com.ut.**
    -keep class com.ta.** {*;}
    -dontwarn com.ta.**

-keepclasseswithmembers class * {
    void onClick*(...);
}
-keepclasseswithmembers class * {
    *** *Callback(...);
}
 #gson

 #-libraryjars libs/gson-2.2.2.jar
 -keepattributes Signature
     # Gson specific classes
     -keep class sun.misc.Unsafe { *; }
     # Application classes that will be serialized/deserialized over Gson
     -keep class com.google.gson.examples.android.model.** { *; }
 #okhttp
  #okhttputils
     -dontwarn com.lzy.okhttputils.**
     -keep class com.lzy.okhttputils.**{*;}
     -dontwarn okhttp3.**
     -keep class okhttp3.**{*;}
     -dontwarn com.squareup.okhttp3.**
     -keep class com.squareup.okhttp3.** { *;}
     -dontwarn okio.**

     -dontwarn com.squareup.**
     -dontwarn okio.**
     -keep public class org.codehaus.* { *; }
     -keep public class java.nio.* { *; }

#youmeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class [com.loushi.android].R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class cn.smssdk.**{*;}
-keep class com.mob.**{*;}

-keep class com.sina.**{*;}
