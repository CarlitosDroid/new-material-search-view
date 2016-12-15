package pe.comercio.materialsearchview.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pe.comercio.materialsearchview.util.ConstantDB;

/**
 * Created by Ricardo Bravo on 30/06/16.
 * Modified by CarlitosDroid on 30/11/2016
 * Requirement: Se agrega nueva tabla LastSearch de version 1 a version 2
 *
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler mInstance;

    protected DatabaseHandler(Context context){
        super(context, ConstantDB.DATABASE_NAME, null, ConstantDB.DATABASE_VERSION);
    }

    protected void getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHandler(context);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_LASTSEARCH_TABLE= "CREATE TABLE " + ConstantDB.TABLE_LAST_SEARCH + "("
                + ConstantDB.KEY_NAME + " TEXT, "
                + ConstantDB.KEY_DATETIME + " TEXT)";

        db.execSQL(CREATE_LASTSEARCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}