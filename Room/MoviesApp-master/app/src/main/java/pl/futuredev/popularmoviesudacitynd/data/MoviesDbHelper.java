package pl.futuredev.popularmoviesudacitynd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favouriteMovieList.db";

    private static final int DATABASE_VERSION = 10;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAV_TABLE = "CREATE TABLE " + MoviesContract.MoviesDateBase.TABLE_NAME + " (" +
                MoviesContract.MoviesDateBase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,   " +
                MoviesContract.MoviesDateBase.MOVIE_ID + " INTEGER NOT NULL, " +
                MoviesContract.MoviesDateBase.MOVIE_TITLE + " TEXT NOT NULL, " +
                MoviesContract.MoviesDateBase.MOVIE_POSTER_PATCH + " TEXT NOT NULL, " +
                MoviesContract.MoviesDateBase.RELEASE_DATE + " TEXT NOT NULL, " +
                MoviesContract.MoviesDateBase.VOTE_AVERAGE + " TEXT NOT NULL, " +
                MoviesContract.MoviesDateBase.VOTE_COUNT + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_FAV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.MoviesDateBase.TABLE_NAME);
        onCreate(db);
    }
}
