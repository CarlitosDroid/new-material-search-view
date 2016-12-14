package pe.comercio.materialsearchview.storage.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.util.ConstantDB;

/**
 * Created by Ricardo Bravo on 3/08/16.
 *
 */

public class LastSearchDB extends DatabaseHandler {

    public LastSearchDB(Context context) {
        super(context);
        getInstance(context);
    }

    public void addLastSearch(String lastSearchName, String numberTouch){
        String sql = "INSERT INTO "+ ConstantDB.TABLE_LAST_SEARCH +"("
                +ConstantDB.KEY_NAME+", "
                +ConstantDB.KEY_DATETIME+") VALUES (?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        statement.clearBindings();
        statement.bindString(1, lastSearchName);
        statement.bindString(2, numberTouch);
        statement.execute();
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<UserEntity> getOrderedListLastSearch(){

        List<UserEntity> lastSearchList = new ArrayList<>();

        String selectQuery = "SELECT "+ConstantDB.KEY_NAME+", "
                +ConstantDB.KEY_DATETIME
                +" FROM " + ConstantDB.TABLE_LAST_SEARCH
                + " ORDER BY " + ConstantDB.KEY_DATETIME + " DESC ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    UserEntity lastSearchEntity = new UserEntity();
                    lastSearchEntity.setName(cursor.getString(0));
                    lastSearchEntity.setDateTime(cursor.getString(1));
                    lastSearchList.add(lastSearchEntity);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return lastSearchList;
    }

    public UserEntity getLastSearchByName(String name){

        UserEntity lastSearchEntity = new UserEntity();

        String selectQuery = "SELECT "+ConstantDB.KEY_NAME+", "
                +ConstantDB.KEY_DATETIME
                +" FROM " + ConstantDB.TABLE_LAST_SEARCH
                + " WHERE " + ConstantDB.KEY_NAME + " = '"+ name+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    lastSearchEntity.setName(cursor.getString(0));
                    lastSearchEntity.setName(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }
        return lastSearchEntity;
    }

    public void deleteLastSearchByName(String lastSearchName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + ConstantDB.TABLE_LAST_SEARCH+ " WHERE "+ConstantDB.KEY_NAME+"='"+lastSearchName+"'");
        db.close();

    }

    public void deleteLastSearch(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ConstantDB.TABLE_LAST_SEARCH;
        db.execSQL(query);
    }
}
