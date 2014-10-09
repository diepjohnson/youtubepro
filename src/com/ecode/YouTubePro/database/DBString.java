package com.ecode.YouTubePro.database;

public class DBString {
	
	public static String[] CREATE_TABLES = {
		"CREATE TABLE Service (localId  integer primary key autoincrement, "
				+ "status text not null, displayName text not null,username text not null, password text not null, presence text not null);",
			"CREATE TABLE Account (Id  integer primary key autoincrement, "
					+ "Account text not null);",
			"CREATE TABLE BuitinService (localId  integer primary key autoincrement, "
					+ "serviceLocalId integer not null, Authkey text not null,DisplayName text not null,Status text not null, SuperUser text not null,userId text,UserPhone text not null, UserEmail text not null,AreaID text not null,displayAvatar blob not null, Sex integer not null);",	
			"CREATE TABLE HistorySearch (Id  integer primary key autoincrement,request text not null);"
		 };

	/**
	 * @category the table witch version is 23
	 * */
	public static String[] TABLES_23 = {"Service","Account","BuitinService","HistorySearch"};
	
	/**
	 * @category the table witch version is the newest
	 * */
	public static String[] TABLES_NEWEST = {"Service","Account","BuitinService","HistorySearch"};

	public final static String DB_NAME = "User_DataBase.db";
	/**
	 * @category Start version is 23, 2012-06-21 version is 24, 2012-06-23
	 *           version is 25, 2012-07-30 version is 26, 2012-08-06 version is
	 *           27, 2012-08-10 version is 28,2012-09-06 version is 29
	 * */
	public static final int DB_VERSION = 30;

	public static final String YT_META_DB_NAME = "YoutubeAccount.db";

	/**
	 * when regenerate IA_DataBase.db and import new db file to the im project,
	 * you need add 1 to IA_META_DB_VERSION manually, and thus new ia db is
	 * automatically cover old ia db.
	 * 
	 * e.g. { (1)old IA_META_DB_VERSION = 9 (2)import new IA_DataBase.db, and
	 * delete old IA_DataBase.db (3)add 1, IA_META_DB_VERSION = 10 (4)run im
	 * 
	 */
	public static int YT_META_DB_VERSION = 16;

	public static String SP_ACK_LOGON = "AckLogon";
}
