package com.zitec.workshopz.utils;

import java.io.IOException;

import com.zitec.workshop2preps.utils.SqlParser;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	public static String createFile = "create.sql";
	public Context ctx;

	
	public DbHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		try {
			if(AssetUtils.exists(DbHelper.createFile, "db", this.ctx.getAssets())){
				this.runSql(DbHelper.createFile, arg0);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void runSql(String sqlFile, SQLiteDatabase db) throws SQLException, IOException {
		for (String sqlInstruction : SqlParser.parseSqlFile(SQL_DIR + "/" + sqlFile, this.context.getAssets())) {
			db.execSQL(sqlInstruction);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
