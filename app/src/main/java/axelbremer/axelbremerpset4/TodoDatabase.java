package axelbremer.axelbremerpset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by axel on 20-11-17.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete("todos", "_id = " + id, new String[] {});
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("completed", completed);
        db.insert("todos", null, cv);
    }

    public void update(long id, int completed) {
        Log.d("UPDATE", "update: " + id);
        SQLiteDatabase db = instance.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("completed", completed);
        db.update("todos", cv, "_id = " + id, new String[] {});
    }

    public static TodoDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new TodoDatabase(context, "name", null,1);
        }
        return instance;
    }

    public Cursor selectAll() {
        SQLiteDatabase db = instance.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos", new String[] {});
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table todos (_id INTEGER PRIMARY KEY, title TEXT, completed BOOLEAN);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('kabouterdans leren', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('kaboutermeisje uitkiezen', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('met je voeten op de grond stampen', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "todos");
    }
}
