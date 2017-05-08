package com.wenzhiguo.greendao;


import android.app.Application;

import org.greenrobot.greendao.database.Database;

/**
 * Created by dell on 2017/5/8.
 * action :进行初始化GreenDao
 */

public class App extends Application {
    private  static DaoSession daoSession = null;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper wen = new DaoMaster.DevOpenHelper(this, "WEN");
        Database writableDb = wen.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(writableDb);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession daoSession() {
        return daoSession;
    }
}
