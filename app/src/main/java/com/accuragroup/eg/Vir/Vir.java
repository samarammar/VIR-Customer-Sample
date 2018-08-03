package com.accuragroup.eg.Vir;

import android.app.Application;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.accuragroup.eg.Vir.models.Entities.DaoMaster;
import com.accuragroup.eg.Vir.models.Entities.DaoSession;
import com.accuragroup.eg.Vir.utils.Utils;
import com.crashlytics.android.Crashlytics;
import com.onesignal.OneSignal;

import org.greenrobot.greendao.database.Database;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Apex on 7/9/2017.
 */

public class Vir extends Application {

    private DaoSession daoSession;
    public static final boolean ENCRYPTED = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "vir-db-encrypted" : getString(R.string.appName));
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        MultiDex.install(this);


        // override default font
        Utils.overrideFont(this, "MONOSPACE", "fonts/app_font.ttf");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/opensans-regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
