package tutorial.lorence.started.local.storage;

import android.provider.BaseColumns;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public final class DbContract {
    /**
     * Text data type.
     */
    private static final String TEXT_TYPE = " TEXT";
    /**
     * Integer data type.
     */
    private static final String INTEGER_TYPE = " INTEGER";
    /**
     * Comma symbol.
     */
    private static final String COMMA_SEP = ",";
    /**
     * Left bracket symbol.
     */
    private static final String LEFT_BRACKET_SEP = " (";
    /**
     * Right bracket symbol.
     */
    private static final String RIGHT_BRACKET_SEP = " );";
    /**
     * Primary key.
     */
    private static final String PRIMARY_AUTOINCREMENT = " PRIMARY KEY AUTOINCREMENT";
    /**
     * Create table statement.
     */
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    /**
     * Create query for USER table.
     */
    static final String SQL_CREATE_SCHEDULE = new StringBuilder(CREATE_TABLE)
            .append(DBHelperItem.TABLE_NAME).append(LEFT_BRACKET_SEP)
            .append(DBHelperItem._ID).append(INTEGER_TYPE)
            .append(PRIMARY_AUTOINCREMENT)
            .append(COMMA_SEP)
            .append(DBHelperItem.COLUMN_NAME_RECORDING_NAME).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(DBHelperItem.COLUMN_NAME_RECORDING_FILE_PATH).append(TEXT_TYPE)
            .append(COMMA_SEP)
            .append(DBHelperItem.COLUMN_NAME_RECORDING_LENGTH).append(INTEGER_TYPE)
            .append(COMMA_SEP)
            .append(DBHelperItem.COLUMN_NAME_TIME_ADDED).append(INTEGER_TYPE)
            .append(RIGHT_BRACKET_SEP)
            .toString();
    /**
     * Drop table statement.
     */
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    /**
     * Drop query for User table.
     */
    static final String SQL_DELETE_SCHEDULE = new StringBuilder(DROP_TABLE)
            .append(DBHelperItem.TABLE_NAME).toString();

    /**
     * Constructor. Prevents the DbUser class from being instantiated.
     */
    private DbContract() {
    }

    public abstract static class DBHelperItem implements BaseColumns {
        public static final String TABLE_NAME = "recordings";
        public static final String COLUMN_NAME_RECORDING_NAME = "recording_name";
        public static final String COLUMN_NAME_RECORDING_FILE_PATH = "file_path";
        public static final String COLUMN_NAME_RECORDING_LENGTH = "length";
        public static final String COLUMN_NAME_TIME_ADDED = "time_added";
    }
}
