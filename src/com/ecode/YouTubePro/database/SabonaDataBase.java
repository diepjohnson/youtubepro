package com.ecode.YouTubePro.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SabonaDataBase {
	private SQLiteDatabase db;
	SQLiteDatabase rdb;
	private static SabonaDataBase self;

	public SabonaDataBase(SabonaDBHelper helper) {
		if(db==null){
			db = (helper.getWritableDatabase());
		}
		rdb = helper.getReadableDatabase();
	}

	public static SabonaDataBase getInstance(SabonaDBHelper helper) {
		self = new SabonaDataBase(helper);
		return self;
	}

	public Cursor rawQuery(String sql, String[] selectionArgs) {
		return rdb.rawQuery(sql, selectionArgs);
	}

	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy) {
		return rdb.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy);
	}

	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) {
		return rdb.query(table, columns, selection, selectionArgs, groupBy,
				having, orderBy, limit);
	}

	public Cursor query(boolean distinct, String table, String[] columns,
			String selection, String[] selectionArgs, String groupBy,
			String having, String orderBy, String limit) {
		return rdb.query(distinct, table, columns, selection, selectionArgs,
				groupBy, having, orderBy, limit);
	}

	public long insert(String table, String nullColumnHack, ContentValues values) {
		return getDb().insert(table, nullColumnHack, values);

	}

	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		return getDb().update(table, values, whereClause, whereArgs);

	}

	public void execSQL(String sql) {
		getDb().execSQL(sql);
	}

	public int delete(String table, String whereClause, String[] whereArgs) {
		return getDb().delete(table, whereClause, whereArgs);
	}

	public SQLiteDatabase getDb() {
		return db;
	}
	
	public void close(){
		db.close();
		rdb.close();
	}

}
