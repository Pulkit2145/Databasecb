package com.moncledjogger.pro.databasecb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


import static com.moncledjogger.pro.databasecb.TableTask.*;


/**
 * Created by user 3 on 16-07-2017.
 */

public class DatabaseTask extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tasks.db";

    public static final int VERSION = 1;


    public DatabaseTask(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = CREATE + TABLE_NAME
                + LBR + COLUMN_ID + INT_PK_AUTOIC
                + COMMA
                + COLUMN_TASK + TYPE_TEXT + COMMA
                + COLUMN_IS_DONE + TYPE_INTEGER + RBR
                + TERMINATE;

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }


    public long insertTask(Task task) {

        SQLiteDatabase sqlDb = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK, task.getTaskName());
        cv.put(COLUMN_IS_DONE, task.isDone());

        return sqlDb.insert(
                TABLE_NAME,
                null,
                cv
        );

    }

    public Task getTask(long id) {
        //// TODO: 14/07/17
        return null;
    }



    public ArrayList<Task> getAllTasks() {

        ArrayList<Task> tasks = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        Cursor c = database.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_ID + " DESC ");


        while (c.moveToNext()){
            String task;
            int id;
            boolean status;

            int taskColumn = c.getColumnIndex(COLUMN_TASK);
            int idColumn = c.getColumnIndex(COLUMN_ID);
            int idStatus = c.getColumnIndex(COLUMN_IS_DONE);

            task = c.getString(taskColumn);
            id = c.getInt(idColumn);
            status = (1 == c.getInt(idStatus));

            tasks.add(new Task(task,id,status));

        }
        c.close();
        return tasks;
    }

    public void deleteTask(long id){


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(WHEN_UPGRADING_FROM_1_2);

    }
}
