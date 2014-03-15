package com.zitec.workshopz.utils;

import java.io.IOException;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	public static String createFile = "create.sql";
	public static String sqlDir = "db";
	public Context ctx;
	public static String upgradePrefix = "upgrade-";

	
	public DbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		try {
			if(AssetUtils.exists(DbHelper.createFile, DbHelper.sqlDir, this.ctx.getAssets())){
				this.runSql(DbHelper.createFile, arg0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void runSql(String sqlFile, SQLiteDatabase db) throws SQLException, IOException {
		for (String sqlInstruction : SqlParser.parseSqlFile(DbHelper.sqlDir + "/" + sqlFile, this.ctx.getAssets())) {
			db.execSQL(sqlInstruction);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			for(String file : AssetUtils.list(DbHelper.sqlDir, this.ctx.getAssets())){
				int fileVersion = Integer.parseInt(
						file.substring(
								upgradePrefix.length(),
								(file.length()-upgradePrefix.length())
						)
				);
				if(fileVersion > oldVersion && fileVersion <= newVersion){
					this.runSql(file, db);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
