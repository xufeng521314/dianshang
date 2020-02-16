package com.example.dianshangxiangmu.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dianshangxiangmu.dao.DaoMaster;


public class MyDaoMaster extends DaoMaster.OpenHelper {
    public MyDaoMaster(Context context, String name) {
        super(context, name);
    }

    /**
     * 为预留数据库升级做准备
     * @param context
     * @param name
     * @param factory
     */
    public MyDaoMaster(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
