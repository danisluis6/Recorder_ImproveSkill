package tutorial.lorence.started.local.storage.access;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import tutorial.lorence.started.local.storage.DbContract;
import tutorial.lorence.started.local.storage.DbHelper;
import tutorial.lorence.started.local.storage.entities.RecordingItem;
import tutorial.lorence.started.local.storage.listeners.OnDatabaseChangedListener;
import tutorial.lorence.started.other.Constants;
import tutorial.lorence.started.other.Utils;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class DARecorder {

    private static OnDatabaseChangedListener mOnDatabaseChangedListener;

    public static void setOnDatabaseChangedListener(OnDatabaseChangedListener listener) {
        mOnDatabaseChangedListener = listener;
    }

    private RecordingItem getFromCursor(final Cursor cursor) {
        int recordId = cursor.getInt(cursor.getColumnIndex(DbContract.DBHelperItem._ID));
        String name = cursor.getString(cursor.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME));
        String file_path = cursor.getString(cursor.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH));
        int length = cursor.getInt(cursor.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH));
        Long added = cursor.getLong(cursor.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED));
        return new RecordingItem(recordId, name, file_path, length, added);
    }

    public long add(RecordingItem item, Context context) {
        long id = 0;
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME, item.getName());
        values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, item.getFilePath());
        values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, item.getLength());
        values.put(DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED, item.getTime());
        if (db != null && db.isOpen()) {
            id = db.insert(DbContract.DBHelperItem.TABLE_NAME, null, values);
        }
        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener.onNewDatabaseEntryAdded();
        }
        return id;
    }

    public boolean edit(RecordingItem item, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.DBHelperItem._ID, item.getId());
        cv.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME, item.getName());
        cv.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, item.getFilePath());
        cv.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, item.getLength());
        cv.put(DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED, item.getTime());
        try {
            db.update(DbContract.DBHelperItem.TABLE_NAME, cv, DbContract.DBHelperItem._ID + "=" + item.getId(), null);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void addAll(List<RecordingItem> items, Context context) {
        if (items.size() > 0) {
            DbHelper dbHelper = DbHelper.getInstance(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null && db.isOpen()) {
                for (RecordingItem item : items) {
                    ContentValues values = new ContentValues();
                    values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME, item.getName());
                    values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH, item.getFilePath());
                    values.put(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH, item.getLength());
                    values.put(DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED, item.getTime());
                    db.insert(DbContract.DBHelperItem.TABLE_NAME, null, values);
                }
            }
        }
    }

    public ArrayList<RecordingItem> getAll(Context context) {
        ArrayList<RecordingItem> recordings = new ArrayList<>();
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (db != null && db.isOpen()) {
            String[] projection = {
                    DbContract.DBHelperItem._ID,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH,
                    DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED
            };
            Cursor cursor = db.query(DbContract.DBHelperItem.TABLE_NAME, projection,
                    null, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                RecordingItem recordingItem;
                do {
                    recordingItem = getFromCursor(cursor);
                    recordings.add(recordingItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return recordings;
    }

    public List<RecordingItem> searchForRecording(Context context, String data, String type) {
        List<RecordingItem> recordingslist = new ArrayList<>();
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(Utils.equalsIgnoreCase(type, "name") ? DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME : DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH)
                .append(Constants.LIKE)
                .toString();

        String[] selectionArgs = {data + Constants.LIKE_SEP};
        if (db != null && db.isOpen()) {
            String[] projection = {
                    DbContract.DBHelperItem._ID,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH,
                    DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH,
                    DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED
            };
            Cursor cursor = db.query(DbContract.DBHelperItem.TABLE_NAME, projection,
                    selection, selectionArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                RecordingItem recordingItem;
                do {
                    recordingItem = getFromCursor(cursor);
                    recordingslist.add(recordingItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return recordingslist;
    }

    public boolean deleteAll(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db != null && db.isOpen()
                && db.delete(DbContract.DBHelperItem.TABLE_NAME, null, null) > 0;
    }

    public boolean deleteById(int index, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(DbContract.DBHelperItem._ID).append(Constants.EQUAL)
                .toString();
        String[] selectionArgs = {String.valueOf(index)};
        return db != null && db.isOpen()
                && db.delete(DbContract.DBHelperItem.TABLE_NAME, selection, selectionArgs) > 0;
    }

    public boolean deleteByIds(List<Integer> recordingIds, Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String recordingIDs = TextUtils.join(",", recordingIds.toArray());
        String selection = new StringBuilder(Constants.EMPTY_STRING)
                .append(DbContract.DBHelperItem._ID).append(Constants.IN)
                .append(Constants.LEFT_BRACKET_SEP).append(recordingIDs).append(Constants.RIGHT_BRACKET_SEP).toString();
        return db != null && db.isOpen()
                && db.delete(DbContract.DBHelperItem.TABLE_NAME, selection, null) > 0;
    }

    public int getCount(Context context) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DbContract.DBHelperItem._ID};
        Cursor c = db.query(DbContract.DBHelperItem.TABLE_NAME, projection, null, null, null, null, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public RecordingItem getItemAt(Context context, int position) {
        DbHelper dbHelper = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DbContract.DBHelperItem._ID,
                DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME,
                DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH,
                DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH,
                DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED
        };
        Cursor c = db.query(DbContract.DBHelperItem.TABLE_NAME, projection, null, null, null, null, null);
        if (c.moveToPosition(position)) {
            RecordingItem item = new RecordingItem();
            item.setId(c.getInt(c.getColumnIndex(DbContract.DBHelperItem._ID)));
            item.setName(c.getString(c.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_NAME)));
            item.setFilePath(c.getString(c.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH)));
            item.setLength(c.getInt(c.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_RECORDING_LENGTH)));
            item.setTime(c.getLong(c.getColumnIndex(DbContract.DBHelperItem.COLUMN_NAME_TIME_ADDED)));
            c.close();
            return item;
        }
        return null;
    }
}
