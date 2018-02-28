# bmobLibrary
[![](https://jitpack.io/v/reyreyrey/library.svg)](https://jitpack.io/#reyreyrey/library)
</br>

##引入
<p>在项目的build.gradle中添加如下代码</p>
<pre>
  <code>
    allprojects{
      repositories {
          jcenter()
          maven { url "https://jitpack.io" }
      }
    }
  </code>
</pre>

<p>在Module的build.gradle中添加如下代码</p>
<pre>
  <code>
  //加号替换为jitpack版本号
    compile 'com.github.reyreyrey:library:+'
  </code>
</pre>

##使用
<p>
	1.复制<a href="https://github.com/reyreyrey/library/blob/master/keys/"
		title="">签名文件</a>到Module，复制<a
		href="https://github.com/reyreyrey/library/blob/master/gradle/common_gradle.gradle"
		title="">common.gradle</a>、<a
		href="https://github.com/reyreyrey/library/blob/master/gradle/global.gradle"
		title="">global.gradle</a>和<a
		href="https://github.com/reyreyrey/library/blob/master/gradle/simple.gradle"
		title="">simple.gradle</a>到项目的gradle文件夹中
</p>
<p>2.修改Module的build.gradle</p>
<pre>
  <code>
    apply from: '../gradle/common_gradle.gradle'
    dependencies {
        compile fileTree(include: ['*.jar'], dir: 'libs')
        //加号替换为jitpack版本号
        compile 'com.github.reyreyrey:library:+'
    }
  </code>
</pre>
<p>3.在项目的Application中添加代码</p>
<pre>
  <code>
    @Override
    public void onCreate() {
        super.onCreate();
        Library.init(this, BuildConfig.DEBUG);
    }
  </code>
</pre>
<p>4.新建SplashActivity继承SplashBaseActivity,并复写以下方法</p>
<pre>
  <code>
    public class SplashActivity extends SplashBaseActivity{
        @Override
            protected void toMain() {
                //跳转到主界面
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
            @Override
            protected String getAppID() {
                //返回在后台添加的appid
                return "2";
            }
            @Override
            protected int getSplashImageRes() {
                //无需修改
                return ResourceUtil.getDrawableId(this, BuildConfig.SPLASH_PIC);
            }
            @Override
            protected boolean isShowGuide() {
                //是否显示引导页（开关开启的时候才会读这个配置）
                return false;
            }
            @Override
            protected int[] guideRess() {
                //引导页图片资源数组
                return null;
            }
    }
  </code>
</pre>
<p>5.配置AndroidManifest.xml</p>
<pre>
  <code>
        //配置应用的图标
       android:icon="${app_icon}"
       //将你的SplashActivity配置为启动Activity
  </code>
</pre>
<p>6.删除string.xml中的app_name和colors.xml中的colorPrimary</p>
<p>7.编译运行</p>

##其他
UIManager类中的方法
<p>static void	art(android.content.Context context)
艺术列表（使用默认标题）</p>
<p>static void	art(android.content.Context context, java.lang.String title)
艺术家列表</p>
<p>static void	caipiaoHistory(android.content.Context context)
彩票开奖记录</p>
<p>static void	caipiaoHistory(android.content.Context context, java.lang.String title)
彩票开奖记录</p>
<p>static void	chat(android.content.Context context, java.lang.String userName)
与某个用户聊天</p>
<p>static void	chatList(android.content.Context context)
会话列表 页面</p>
<p>static void	fuli(android.content.Context context)
福利页面（图片瀑布流）</p>
<p>static void	fuli(android.content.Context context, java.lang.String title)
福利页面（图片瀑布流）</p>
<p>static void	guigushi(android.content.Context context)
鬼故事页面</p>
<p>static void	guigushi(android.content.Context context, java.lang.String title)
鬼故事页面</p>
<p>static void	login(android.content.Context context)
登录</p>
<p>static void	lotteryChar(android.content.Context context)
走势图</p>
<p>static void	lotteryChar(android.content.Context context, java.lang.String title)
走势图</p>
<p>static void	lotteryChar1(android.content.Context context)
走势图2</p>
<p>static void	lotteryChar1(android.content.Context context, java.lang.String title)
走势图2</p>
<p>static void	luck(android.content.Context context)
鬼故事页面</p>
<p>static void	luck(android.content.Context context, java.lang.String title)
幸运转盘页面</p>
<p>static void	manhua(android.content.Context context)
漫画列表页面</p>
<p>static void	manhua(android.content.Context context, java.lang.String title)
漫画列表页面</p>
<p>static void	mine(android.content.Context context)
个人中心</p>
<p>static void	mine(android.content.Context context, java.lang.String title)
个人中心</p>
<p>static void	myAttention(android.content.Context context)
我关注的人（必须是登录状态）</p>
<p>static void	myAttention(android.content.Context context, java.lang.String title)
我关注的人（必须是登录状态）</p>
<p>static void	myFabu(android.content.Context context)
我发布的帖子（必须是登录状态）</p>
<p>static void	myFabu(android.content.Context context, java.lang.String title)
我发布的帖子（必须是登录状态）</p>
<p>static void	mySave(android.content.Context context)
我保存的帖子（必须是登录状态）</p>
<p>static void	mySave(android.content.Context context, java.lang.String title)
我保存的帖子（必须是登录状态）</p>
<p>static void	mySave(android.content.Context context, java.lang.String userid, java.lang.String username)
其他用户的个人中心</p>
<p>static void	postList(android.content.Context context)
帖子列表</p>
<p>static void	postList(android.content.Context context, java.lang.String title)
帖子列表</p>
<p>static void	reg(android.content.Context context)
注册</p>
<p>static void	searchUser(android.content.Context context)
搜索用户</p>
<p>static void	searchUser(android.content.Context context, java.lang.String title)
搜索用户</p>
<p>static void	showBigImage(android.support.v7.app.AppCompatActivity appCompatActivity, java.lang.String imagePath)
查看大图页面</p>
<p>static void	systemNotify(android.content.Context context)
系统通知</p>
<p>static void	systemNotify(android.content.Context context, java.lang.String title)
系统通知</p>
<p>static void	wx(android.content.Context context)
微信精选页面</p>
<p>static void	wx(android.content.Context context, java.lang.String title)
微信精选页面</p>
<p>static void	xiaohua(android.content.Context context)
笑话列表页面（显示默认标题）</p>
<p>static void	xiaohua(android.content.Context context, java.lang.String title)
笑话列表页面</p>

<p>10.webview</p>
<pre>
    <code>
        //isShowBack： false:不显示toolbar,点击返回键退出页面  true:显示toolbar，可以返回之前的页面
        WebViewActivity.load(context, url, isShowBack);
        //isShowBack是false
        WebViewActivity.load(context, url);
        //isShowBack是true
        WebViewActivity.openWebViewUrl(context, url, title);
   </code>
</pre>
<p>11.toast</P>
<pre>
     <code>
        ToastUtils.toastError(context, message);
    </code>
</pre>
<p>12.Progress</P>
<pre>
    <code>
        ProgressDialogUtils.showProgress(context);
        ProgressDialogUtils.dismissProgress();
    </code>
</pre>

<p>
    13.下拉刷新使用参考<a
		href="https://github.com/reyreyrey/library/blob/master/app/src/main/java/com/android/bmoblibrary/ui/TrendChartActivity.java"
		title="TrendChartActivity">TrendChartActivity</a>
</P>
<p>
	14.library提供<a
		href="https://github.com/reyreyrey/library/blob/master/app/src/main/java/com/android/bmoblibrary/base/UIActivity.java"
		title="UIActivity">UIActivity</a>和<a
		href="https://github.com/reyreyrey/library/blob/master/app/src/main/java/com/android/bmoblibrary/base/UIBaseFragment.java"
		title="UIBaseFragment">UIBaseFragment</a>
</p>
<p>
	15.如果需要混淆，请将<a
		href="https://github.com/reyreyrey/library/blob/master/app/proguard-rules.pro"
		title="proguard-rules.pro">proguard-rules.pro</a>文件拷贝到项目中
</p>
