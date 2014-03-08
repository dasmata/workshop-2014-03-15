package com.zitec.workshop2preps.utils;

import java.io.IOException;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String SQL_DIR = "db";
	private static final String CREATEFILE = "create.sql";
	private static final String UPGRADEFILE_PREFIX = "upgrade-";
	private static final String UPGRADEFILE_SUFFIX = ".sql";
	private static final String DATABASE_NAME = "workshopz";
	private Context context;
	
	public DatabaseHelper(Context context, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		try {
			execSqlFile(CREATEFILE, database);
		} catch (IOException e) {

		} catch (SQLException e) {
			
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		try {	
            for( String sqlFile : AssetUtils.list(SQL_DIR, this.context.getAssets())) {
                if ( sqlFile.startsWith(UPGRADEFILE_PREFIX)) {
                    int fileVersion = Integer.parseInt(sqlFile.substring( UPGRADEFILE_PREFIX.length(),  sqlFile.length() - UPGRADEFILE_SUFFIX.length())); 
                    if ( fileVersion > oldVersion && fileVersion <= newVersion ) {
                        execSqlFile( sqlFile, database );
                    }
                }
            }
        } catch( IOException exception ) {
            throw new RuntimeException("Database upgrade failed", exception );
        }	
	}
	
	protected void execSqlFile(String sqlFile, SQLiteDatabase db)
			throws SQLException, IOException {
		for (String sqlInstruction : SqlParser.parseSqlFile(SQL_DIR + "/"
				+ sqlFile, this.context.getAssets())) {
			db.execSQL(sqlInstruction);
		}
	}
	
	
}
