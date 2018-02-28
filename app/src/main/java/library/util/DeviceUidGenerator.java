package library.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.android.library.BuildConfig;
import com.android.library.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class DeviceUidGenerator {

    private static class Monitor {
        private static StringBuilder log;

        private static void start() {
            log = new StringBuilder("start:\n");
        }

        private static void log(String how) {
            log.append(how).append("\n");
        }

        private static void finish(String uid) {
            log.append("end. device uid is:" + uid);
        }

        private static String getLog() {
            return log.toString();
        }
    }

    public static String trace() {
        return Monitor.getLog();
    }


    private static final String INSTALLATION = "install";

    private synchronized static String fromUuid(Context context) {
        String uid = null;
        if (uid == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                uid = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return uid;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }
}
