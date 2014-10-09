package com.ecode.YouTubePro.database;

import java.util.ArrayList;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecode.YouTubePro.config.IConfig;
import com.ecode.YouTubePro.config.IConstant;

public class BuiltinServiceManager implements IConstant {
	// private boolean isLogon;
	SabonaDBHelper dbHelper;
	// db object for write.
	SabonaDataBase db;
	// db object for read.
	// SQLiteDatabase dbr;
	static BuiltinServiceManager self = null;
	Context context;

	public Hashtable<String, String> getAccountInfo() {
		Hashtable<String, String> info = new Hashtable<String, String>();
		Cursor cursor = db.query("Account", null, null, null, null, null,
				null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			String key = cursor.getString(cursor.getColumnIndex("key"));
			String value = cursor.getString(cursor.getColumnIndex("info"));
			info.put(key, value);
		}
		cursor.close();
		return info;
	}

	public void saveAccountInfo(String info) {
		SQLiteDatabase sdb = db.getDb();
		sdb.beginTransaction();
		try {
			sdb.delete("Account", null, null);
				ContentValues cv = new ContentValues();
				cv.put("info", info);
				cv.put("lastUpdate", -1);
				sdb.insert("Account", null, cv);
			sdb.setTransactionSuccessful();
		} finally {
			sdb.endTransaction();
		}
	}

	public BuiltinServiceManager(Context context) {
		// setLogon(false);
		this.context = context;
		dbHelper = SabonaDBHelper.getInstance(context, false);
		db = SabonaDataBase.getInstance(dbHelper);
		// dbr = dbHelper.getReadableDatabase();
	}

	public static BuiltinServiceManager getInstance(Context context) {
		if (self == null) {
			self = new BuiltinServiceManager(context);
		}
		return self;
	}

	public BuiltinService getBuitinService() {
		BuiltinService service = null;
		if (db != null) {
			Cursor cursor = db.rawQuery("SELECT * FROM BuitinService", null);
			if (cursor.getCount() == 0) {
				// Log.i("DDAI", "no record in BuitinService");
				cursor.close();
			} else {
				service = new BuiltinService();
				cursor.moveToFirst();
				long serviceId = cursor.getLong(cursor
						.getColumnIndex("serviceLocalId"));
				service.setLocalId(cursor.getLong(cursor
						.getColumnIndex("localId")));
				service.setUserId(cursor.getString(cursor
						.getColumnIndex("userId")));
				service.setDisplayName(cursor.getString(cursor
						.getColumnIndex("DisplayName")));
				service.setUserEmail(cursor.getString(cursor
						.getColumnIndex("UserEmail")));
				cursor.close();
				cursor = db.query("Service", null, "localId=?",
						new String[] { String.valueOf(serviceId) }, null, null,
						null);
				if (cursor.getCount() == 0) {
					Log.d("DB", "no record in Service");
					cursor.close();
					return null;
				} else {
					cursor.moveToFirst();
					service.setDisplayName(cursor.getString(cursor
							.getColumnIndex("displayName")));
					service.setUserName(cursor.getString(cursor
							.getColumnIndex("username")));
					cursor.close();
				}
			}

		}
		return service;
	}

	public boolean saveBuitinService(BuiltinService service) {
		boolean result = false;
		if (db != null) {
			Cursor cursor = db.rawQuery("SELECT * FROM buitinService", null);
			if (cursor.getCount() == 0) {
				cursor.close();
				try {
					ContentValues cv = new ContentValues();
					cv.put("displayName", service.getDisplayName());
					cv.put("username", service.getUserName());

					long serviceId = db.insert("service", "username", cv);
					if (serviceId != -1) {
						// insert table:buitinService
						cv = new ContentValues();
						cv.put("serviceLocalId", serviceId);
						cv.put("DisplayName", service.getDisplayName());
						cv.put("userId", service.getUserId());
						cv.put("UserEmail", service.getUserEmail());
						long id = db.insert("BuitinService", "serviceLocalId",
								cv);
					} else {
						if (IConfig.DEBUG_MODE)
							Log.d("DDAI", "insert Table:service fail");
					}
					result = true;
				} catch (SQLException e) {
					if (IConfig.DEBUG_MODE)
						Log.e("SQLException", e.getMessage());
				}
			} else {
				// update record
				try {
					cursor.moveToFirst();
					long serviceId = cursor.getLong(cursor
							.getColumnIndex("serviceLocalId"));
					cursor.close();
					ContentValues cv = new ContentValues();
					cv.put("displayName", service.getDisplayName());
					cv.put("username", service.getUserName());
					int rows = db.update("service", cv, "localId=?",
							new String[] { String.valueOf(serviceId) });
					if (rows < 1) {
						serviceId = db.insert("service", null, cv);

					}
					ContentValues idcv = new ContentValues();
					idcv.put("serviceLocalId", serviceId);
					idcv.put("userId", service.getUserId());
					rows = db.update("BuitinService", idcv, null, null);
					if (rows < 1) {
						db.insert("BuitinService", null, idcv);
					}
				} catch (Exception e) {
					if (IConfig.DEBUG_MODE)
						Log.e("SQLException", e.getMessage());
				}
			}
			cursor.close();
		} else {
			if (IConfig.DEBUG_MODE)
				Log.e("DDAI", "can't get the SQLiteDatabase");
		}

		return result;
	}

	public boolean saveProductID(String productID, long time) {
		boolean result = false;
		if (db != null) {
			Cursor cursor = db.rawQuery("SELECT * FROM ProductHistory", null);
			if (cursor.getCount() == 0) {
				cursor.close();
				ContentValues cv = new ContentValues();
				cv.put("ProductID", productID);
				cv.put("lastUpdate", time);
				long serviceId = db.insert("ProductHistory", null, cv);
			} else {
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					String product = cursor.getString(cursor
							.getColumnIndex("ProductID"));
					if (product.equals(productID)) {
						ContentValues cv = new ContentValues();
						cv.put("ProductID", productID);
						cv.put("lastUpdate", time);
						db.delete("ProductHistory", "ProductID =?",
								new String[] { String.valueOf(product) });
						long serviceId = db.insert("ProductHistory", null, cv);
						result = true;
						break;
					}
				}
				cursor.moveToFirst();
				if (result != true) {
					ContentValues cv = new ContentValues();
					cv.put("ProductID", productID);
					cv.put("lastUpdate", time);
					long serviceId = db.insert("ProductHistory", null, cv);
					result = true;
				}
				cursor.close();

			}
		} else {
			if (IConfig.DEBUG_MODE)
				Log.e("DDAI", "can't get the SQLiteDatabase");
		}
		Log.e("ProductHistory", "ID: " + productID + " Time  : " + time);
		return result;

	}

	public ArrayList<String> getSearchHistory() {
		ArrayList<String> listID = new ArrayList<String>();
		if (db != null) {
			Cursor cursor = db.rawQuery("SELECT * FROM HistorySearch", null);
			if (cursor.getCount() == 0) {
				// Log.i("DDAI", "no record in BuitinService");
				cursor.close();
			} else {
				Log.e("HistorySearch", "SIZE " + cursor.getCount());
				cursor.moveToFirst();
				if (cursor.getCount() > 20) {
					for (int j = 0; j < (cursor.getCount() - 20); j++) {
						cursor.moveToFirst();
						String productid = cursor.getString(cursor
								.getColumnIndex("Id"));
						db.delete("HistorySearch", "Id =?",
								new String[] { String.valueOf(productid) });
						cursor.moveToNext();
					}
				}
				cursor.close();
				cursor = db.rawQuery("SELECT * FROM HistorySearch", null);
				cursor.moveToFirst();
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
						.moveToNext()) {
					listID.add(cursor.getString(cursor
							.getColumnIndex("Id")));
				}
				cursor.close();
			}
		}

		return listID;

	}

	public void closeDB() {
		db.close();
		dbHelper.close();
	}

	@Override
	protected void finalize() throws Throwable {
		closeDB();
		super.finalize();
	}

	public String getMyID() {
		String result = UNINITIALIZED_STRING;
		Cursor c = db
				.query("BuitinService", null, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			result = c.getString(c.getColumnIndex("Id"));
		}
		c.close();
		return result;
	}

	/**
	 * @return get all Roster's <key,value> in Hashtable<RemoteId, localId>
	 */
	public Hashtable<String, Integer> getRosterIds() {

		Cursor cursor = db.query("BuitinRoster", new String[] { "remoteId",
				"localId" }, null, null, null, null, null);
		Hashtable<String, Integer> idTable = new Hashtable<String, Integer>();
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				idTable.put(cursor.getString(0), cursor.getInt(1));
				cursor.moveToNext();
			}
			Log.i("getRosterIds", "ids:" + idTable);
			cursor.close();
			return idTable;
		} else {
			cursor.close();
			return null;
		}
	}

}
