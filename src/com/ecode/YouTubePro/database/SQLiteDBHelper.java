package com.ecode.YouTubePro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDBHelper extends SQLiteOpenHelper {

	public static final String tblOrders = "Orders";
	public static final String[] cOrders = { "ProductId", "ProductName", "Amount", "Cost", "OtherCosts", "Seller", "CrDateTime","Discount" };

	private static final String dbName = "IAOrders.db";
	private static final int dbVersion = 1;

	public SQLiteDBHelper(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String dbCreateCmd = genCreateCmd(tblOrders, cOrders);
		db.execSQL(dbCreateCmd);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + tblOrders);
		onCreate(db);
	}

	private String genCreateCmd(String tblName, String... cColumns) {
		String cmdCreate = "create table " + tblName + " (";
		for (String colName : cColumns) {
			cmdCreate += colName + " text null, ";
		}
		cmdCreate = cmdCreate.substring(0, cmdCreate.length() - 2);
		cmdCreate += ");";
		Log.d(tblName, cmdCreate);
		return cmdCreate;
	}
}
