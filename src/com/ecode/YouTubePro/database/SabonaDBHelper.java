/**
 * 
 */
package com.ecode.YouTubePro.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ecode.YouTubePro.config.IConfig;

/**
 * @author daining
 * 
 */
public class SabonaDBHelper extends SQLiteOpenHelper {

	private boolean isMeta = false;

	static volatile SabonaDBHelper metaDbHelper = null;
	static volatile SabonaDBHelper dbHelper = null;

	private SabonaDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	private SabonaDBHelper(Context context, String name, CursorFactory factory,
			int version, boolean isMeta) {
		super(context, name, factory, version);
		this.isMeta = isMeta;
	}

	public static SabonaDBHelper getInstance(Context context, boolean isMeta) {
		if (isMeta) {
			if (null == metaDbHelper) {
				synchronized (SabonaDBHelper.class) {
					if (null == metaDbHelper) {
						metaDbHelper = new SabonaDBHelper(
								context.getApplicationContext(),
								DBString.YT_META_DB_NAME, null,
								DBString.YT_META_DB_VERSION, true);
					}
				}
			}
			return metaDbHelper;
		} else {
			if (null == dbHelper) {
				synchronized (SabonaDBHelper.class) {
					if (null == dbHelper) {
						dbHelper = new SabonaDBHelper(
								context.getApplicationContext(),
								DBString.DB_NAME, null, DBString.DB_VERSION);
					}

				}
			}
			return dbHelper;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("db version bf", "" + db.getVersion());
		if (!isMeta) { // Meta Data will copy data and schema from assets
			if (IConfig.DEBUG_MODE)
				System.out.println("on Create DB: ..........");
			if (SQLiteDatabase.findEditTable("Setting") != null) {
				Log.e("Find table", "Delete tables");
				String dropString = "DROP TABLE IF EXISTS ";
				for (String sql : DBString.TABLES_NEWEST) {
					sql = dropString + sql;
					db.execSQL(sql);
				}
			}
			for (String sql : DBString.CREATE_TABLES) {
				db.execSQL(sql);
				if (IConfig.DEBUG_MODE)
					System.out.println(sql);
			}
		}
		// db.setVersion(db.getVersion()+1);
		Log.e("db version af", "" + db.getVersion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dbPath = db.getPath();
		if (dbPath.contains(DBString.DB_NAME)) {
			if (!isMeta) {
			}
		}
	}
}
