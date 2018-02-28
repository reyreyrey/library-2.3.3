-dontwarn org.litepal.**
-keep class org.litepal.**{*;}
-keep class * extends org.litepal.crud.DataSupport{
    *;
}

-dontwarn org.xwalk.core.**
-keep class org.xwalk.core.** { *;}
-dontwarn org.chromium.**
-keep class org.chromium.** { *;}

-dontwarn junit.framework.**
-keep class junit.framework.**{*;}



-keepattributes *JavascriptInterface*


-optimizationpasses 5


-dontusemixedcaseclassnames

-dontskipnonpubliclibraryclasses

-dontskipnonpubliclibraryclassmembers

-dontpreverify

-verbose
-printmapping priguardMapping.txt

-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*



################common###############
 #å®žä½“ç±»ä¸å‚ä¸Žæ··æ·†
-keep class com.jess.arms.widget.** { *; } #è‡ªå®šä¹‰æŽ§ä»¶ä¸å‚ä¸Žæ··æ·†
-keepnames class * implements java.io.Serializable
-keepattributes Signature
-keep class **.R$* {*;}
-ignorewarnings
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclassmembers enum * {  # ä½¿ç”¨enumç±»åž‹æ—¶éœ€è¦æ³¨æ„é¿å…ä»¥ä¸‹ä¸¤ä¸ªæ–¹æ³•æ··æ·†ï¼Œå› ä¸ºenumç±»çš„ç‰¹æ®Šæ€§ï¼Œä»¥ä¸‹ä¸¤ä¸ªæ–¹æ³•ä¼šè¢«åå°„è°ƒç”¨ï¼Œ
    public static **[] values();
    public static ** valueOf(java.lang.String);
}



################alipay###############

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}

-keep class com.sunloto.shandong.bean.** { *; }

################umeng###############
-keep class com.umeng.** { *; }
-keep public class * extends com.umeng.**

################glide###############
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class com.bumptech.glide.** { *; }
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


################androidEventBus###############
-keep class org.simple.** { *; }
-keep interface org.simple.** { *; }
-keepclassmembers class * {
    @org.simple.eventbus.Subscriber <methods>;
}

################autolayout###############
-keep class com.zhy.autolayout.** { *; }
-keep interface com.zhy.autolayout.** { *; }





################nineoldandroids###############
-keep class com.nineoldandroids.animation.** { *; }
-keep interface com.nineoldandroids.animation.** { *; }
-keep class com.nineoldandroids.view.** { *; }
-keep interface com.nineoldandroids.view.** { *; }



################autobahn###############
-keep class de.tavendo.autobahn.** { *; }
-keep interface de.tavendo.autobahn.** { *; }


################shareSdk###############
-keep class cn.sharesdk.** { *; }
-keep interface cn.sharesdk.** { *; }

################crop###############
-keep class com.soundcloud.android.** { *; }
-keep interface com.soundcloud.android.** { *; }


################pickerview###############
-keep class com.bigkoo.pickerview.** { *; }
-keep interface com.bigkoo.pickerview.** { *; }


################carousellayoutmanager###############
-keep class com.azoft.carousellayoutmanager.** { *; }
-keep interface com.azoft.carousellayoutmanager.** { *; }

################messagepack###############
-keep class org.** { *; }
-keep interface org.** { *; }

################javassist###############
-keep class javassist.** { *; }
-keep interface javassist.** { *; }

################RxLifeCycle#################
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }

################RxCache#################
-dontwarn io.rx_cache.internal.**
-keep class io.rx_cache.internal.Record { *; }
-keep class io.rx_cache.Source { *; }

-keep class io.victoralbertos.jolyglot.** { *; }
-keep interface io.victoralbertos.jolyglot.** { *; }



# Marshmallow removed Notification.setLatestEventInfo()
-dontwarn android.app.Notification


#-optimizationpasses 7
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-dontoptimize
-dontusemixedcaseclassnames
-verbose
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontwarn dalvik.**
#-overloadaggressively

#@proguard_debug_start
# ------------------ Keep LineNumbers and properties ---------------- #
-renamesourcefileattribute TbsSdkJava
#@proguard_debug_end

# --------------------------------------------------------------------------
# Addidional for x5.sdk classes for apps

-keep class com.tencent.smtt.export.external.**{
    *;
}

-keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
	*;
}

-keep class com.tencent.smtt.sdk.CacheManager {
	public *;
}

-keep class com.tencent.smtt.sdk.CookieManager {
	public *;
}

-keep class com.tencent.smtt.sdk.WebHistoryItem {
	public *;
}

-keep class com.tencent.smtt.sdk.WebViewDatabase {
	public *;
}

-keep class com.tencent.smtt.sdk.WebBackForwardList {
	public *;
}

-keep public class com.tencent.smtt.sdk.WebView {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
	public static final <fields>;
	public java.lang.String getExtra();
	public int getType();
}

-keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebView$PictureListener {
	public <fields>;
	public <methods>;
}

-keep public enum com.tencent.smtt.sdk.WebSettings$** {
    *;
}

-keep public enum com.tencent.smtt.sdk.QbSdk$** {
    *;
}

-keep public class com.tencent.smtt.sdk.WebSettings {
    public *;
}


-keepattributes Signature
-keep public class com.tencent.smtt.sdk.ValueCallback {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebViewClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
	public <fields>;
	public <methods>;
}

-keep class com.tencent.smtt.sdk.SystemWebChromeClient{
	public *;
}
# 1. extension interfaces should be apparent
-keep public class com.tencent.smtt.export.external.extension.interfaces.* {
	public protected *;
}

# 2. interfaces should be apparent
-keep public class com.tencent.smtt.export.external.interfaces.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebIconDatabase {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.WebStorage {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.DownloadListener {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
	public <fields>;
	public <methods>;
}
-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.Tbs* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.LogFileUtils {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLog {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.TbsLogClient {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.CookieSyncManager {
	public <fields>;
	public <methods>;
}

# Added for game demos
-keep public class com.tencent.smtt.sdk.TBSGamePlayer {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
	public <fields>;
	public <methods>;
}

-keep public class com.tencent.smtt.utils.Apn {
	public <fields>;
	public <methods>;
}
# end


-keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {
	public <fields>;
	public <methods>;
}

-keep class MTT.ThirdAppInfoNew {
	*;
}

-keep class com.tencent.mtt.MttTraceEvent {
	*;
}

# Game related
-keep public class com.tencent.smtt.gamesdk.* {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBooter {
        public <fields>;
        public <methods>;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {
	public protected *;
}

-keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {
	public protected *;
}


-keep public class com.tencent.**{*;}
-keep public class com.hybridapp.tools.**{*;}


-keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {
	public *;
}
#---------------------------------------------------------------------------


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
	public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
	public <init>(android.content.Context, android.util.AttributeSet, int);
}

#------------------  ä¸‹æ–¹æ˜¯å…±æ€§çš„æŽ’é™¤é¡¹ç›®         ----------------
# æ–¹æ³•åä¸­å«æœ‰â€œJNIâ€å­—ç¬¦çš„ï¼Œè®¤å®šæ˜¯Java Native Interfaceæ–¹æ³•ï¼Œè‡ªåŠ¨æŽ’é™¤
# æ–¹æ³•åä¸­å«æœ‰â€œJRIâ€å­—ç¬¦çš„ï¼Œè®¤å®šæ˜¯Java Reflection Interfaceæ–¹æ³•ï¼Œè‡ªåŠ¨æŽ’é™¤

-keepclasseswithmembers class * {
    ... *JNI*(...);
}

-keepclasseswithmembernames class * {
	... *JRI*(...);
}

-keep class **JNI* {*;}

#------------------log4j-------------------
#-downwarn org.apache.log4j.*
#-keep class org.apache.log4j.** {*;}
#-downwarn de.mindpipe.android.logging.log4j.*
#-keep de.mindpipe.android.logging.log4j.** {*;}

#---------------umeng


# # -------------------------------------------
# # ############### volleyæ··æ·† ###############
# # -------------------------------------------
-keep class com.android.volley.** {*;}
-keep class com.android.volley.toolbox.** {*;}
-keep class com.android.volley.Response$* { *; }
-keep class com.android.volley.Request$* { *; }
-keep class com.android.volley.RequestQueue$* { *; }
-keep class com.android.volley.toolbox.HurlStack$* { *; }
-keep class com.android.volley.toolbox.ImageLoader$* { *; }

# universal-image-loader æ··æ·†

-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader.** { *; }

#  ç™¾åº¦å®šä½
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**

# ormlite
-keepattributes *DatabaseField*
-keepattributes *DatabaseTable*
-keepattributes *SerializedName*
-keep class com.j256.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }
-dontwarn com.j256.ormlite.android.**
-dontwarn com.j256.ormlite.logger.**
-dontwarn com.j256.ormlite.misc.**

#live800
-keep class com.goldarmor.** {*;}


# Required to preserve the Flurry SDK
-keep class com.flurry.** { *; }
-dontwarn com.flurry.**
-keepattributes *Annotation*,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
 -keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
  public static final *** NULL;
 }

 -keepnames @com.google.android.gms.common.annotation.KeepName class *
 -keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
  }






# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhy/android/sdk/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#shrinkï¼Œæµ‹è¯•åŽå‘çŽ°ä¼šå°†ä¸€äº›æ— æ•ˆä»£ç ç»™ç§»é™¤ï¼Œå³æ²¡æœ‰è¢«æ˜¾ç¤ºè°ƒç”¨çš„ä»£ç ï¼Œè¯¥é€‰é¡¹ è¡¨ç¤º ä¸å¯ç”¨ shrinkã€‚
-dontshrink
#æŒ‡å®šé‡æ–°æ‰“åŒ…,æ‰€æœ‰åŒ…é‡å‘½å,è¿™ä¸ªé€‰é¡¹ä¼šè¿›ä¸€æ­¥æ¨¡ç³ŠåŒ…åï¼Œå°†åŒ…é‡Œçš„ç±»æ··æ·†æˆnä¸ªå†é‡æ–°æ‰“åŒ…åˆ°ä¸€ä¸ªä¸ªçš„packageä¸­
-flattenpackagehierarchy
#ä¼˜åŒ–æ—¶å…è®¸è®¿é—®å¹¶ä¿®æ”¹æœ‰ä¿®é¥°ç¬¦çš„ç±»å’Œç±»çš„æˆå‘˜
-allowaccessmodification
#æ··æ·†å‰åŽçš„æ˜ å°„
-printmapping map.txt
#ä¸è·³è¿‡(æ··æ·†) jarsä¸­çš„ éžpublic classes   é»˜è®¤é€‰é¡¹
-dontskipnonpubliclibraryclassmembers
#å¿½ç•¥è­¦å‘Š
-ignorewarnings
#æŒ‡å®šä»£ç çš„åŽ‹ç¼©çº§åˆ«
-optimizationpasses 5
#ä¸ä½¿ç”¨å¤§å°å†™æ··åˆç±»å
-dontusemixedcaseclassnames
#ä¸åŽ»å¿½ç•¥éžå…¬å…±çš„åº“ç±»
-dontskipnonpubliclibraryclasses
#ä¸å¯ç”¨ä¼˜åŒ–  ä¸ä¼˜åŒ–è¾“å…¥çš„ç±»æ–‡ä»¶
-dontoptimize
#ä¸é¢„æ ¡éªŒ
-dontpreverify
#æ··æ·†æ—¶æ˜¯å¦è®°å½•æ—¥å¿—
-verbose
#æ··æ·†æ—¶æ‰€é‡‡ç”¨çš„ç®—æ³•
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#ä¿æŠ¤æ³¨è§£
-renamesourcefileattribute SourceFile
#ä¿æŒæºæ–‡ä»¶å’Œè¡Œå·çš„ä¿¡æ¯,ç”¨äºŽæ··æ·†åŽå®šä½é”™è¯¯ä½ç½®
#ä¿æŒå«æœ‰Annotationå­—ç¬¦ä¸²çš„ attributes
-keepattributes InnerClasses

-dontwarn org.apache.**
#ä¿æŒ ä»»æ„åŒ…å.Rç±»çš„ç±»æˆå‘˜å±žæ€§ã€‚  å³ä¿æŠ¤Ræ–‡ä»¶ä¸­çš„å±žæ€§åä¸å˜
-keepclassmembers class **.R$* {
    public static <fields>;
}

#MPermission
-dontwarn com.zhy.m.**
-keep class com.zhy.m.** {*;}
-keep interface com.zhy.m.** { *; }
-keep class **$$PermissionProxy { *; }


#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-keep interface com.zhy.http.**{*;}

#phonelive
 #-keep class com.xianggang2.phonelive.ui.**{ *;}


#org
-dontwarn org.**
-keep class org.**{*;}
-keep interface org.**{*;}

-keep class com.xianggang2.phonelive.bean.** { *; }

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}


#astuetz
-keep class com.astuetz.**{ *;}
-dontwarn com.astuetz.**
#github
-keep class com.github.**{ *;}
-dontwarn com.github.**

#ksy player
 -keep class com.ksyun.media.player.annotations.**{ *;}
 -keep class com.ksyun.media.player.exceptions.**{ *;}
 -keep class com.ksyun.media.player.ffmpeg.**{ *;}
 -keep class com.ksyun.media.player.misc.**{ *;}
 -keep class com.ksyun.media.player.pragma.**{ *;}
 -keep class com.ksyun.media.player.stats.**{ *;}
 -keep class com.ksyun.media.player.util.**{ *;}
 -keep class com.ksyun.media.player.**{ *;}

-keep class com.ksy.recordlib.service.streamer.**{ *;}
-keep class com.ksy.recordlib.service.preview.** { *;}
-keep class com.ksy.recordlib.service.streamer.FFStreamer { *;}
-keep class com.ksy.recordlib.service.streamer.StreamerException { *;}
-keep class com.ksy.recordlib.service.streamer.Frame { *;}
-keep class com.ksy.recordlib.service.streamer.KSlImage { *;}
-keep class com.ksy.recordlib.service.KSYStreamer {
    private <methods>;
}

## ----------------------------------

##      sharesdk

## ----------------------------------

-keep class cn.sharesdk.**{*;}

-keep class com.sina.**{*;}

-keep class **.R$* {*;}

-keep class **.R{*;}

-dontwarn cn.sharesdk.**

-dontwarn **.R$*

##  huanxin

-keep class com.hyphenate.** {*;}
-dontwarn  com.hyphenate.**

#jiguang

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** { *; }

-keepclassmembers class * {
  void initData();
  void initView();
}

-keep  class com.ksy.recordlib.** { *;}
-keep  class java.com.dd.** { *;}


-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**


-keep  class com.android.tedcoder.wkvideoplayer.** { *;}
-dontwarn com.android.tedcoder.wkvideoplayer.**

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

-keep class com.ksyun.** {
  *;
}

-keep class com.ksy.statlibrary.** {
  *;
}



# glide çš„æ··æ·†ä»£ç 
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner çš„æ··æ·†ä»£ç 
-keep class com.youth.banner.** {
    *;
 }

# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

-dontwarn com.facebook.infer.**
-keep class com.ivi.hybrid.core.modules.**{*;}
-keep class com.gyf.barlibrary.*{*;}


-keepclasseswithmembernames class * {
    native <methods>;
}

-keep public class android.support.v4.content.FileProvider { *; }

-dontwarn org.jetbrains.annotations.**
-dontwarn javax.annotation.**
-keep class java.lang.Object

-dontwarn java.nio.file.*
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8

-keepattributes Signature
-keepattributes Exceptions
-dontwarn sun.misc.**
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer



-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
#==================gson && protobuf==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}


-keep class org.xmlpull.** {*;}
-keep class com.baidu.** {*;}
-keep public class * extends com.umeng.**
-keep class com.umeng.** { *; }
-keep class com.squareup.picasso.* {*;}

-keep class com.hyphenate.** {*;}
-keep class com.hyphenate.chat.** {*;}
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
#不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
-keep class com.hyphenate.chatuidemo.utils.SmileUtils {*;}

#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
