package com.gudigudigudi.appdemojetpack.room;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.commonlib.constants.LogTag;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import static android.content.Context.MODE_PRIVATE;

@Slf4j
public class DataStoreFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "DataStoreFragment";
    private static final String TMP_FILE_NAME = "tmp.txt";
    private EditText mEditText;

    private Button mBtnSaveDataBySharedpreferences;
    private Button mBtnRestoreDataBySharedpreferences;

    private AppDatabase mAppDatabase;
    private BookDao mBookDao;
    private Button mBtnInsertRoom;

    private Button mBtnQueryRoom;
    private Button mBtnUpdateRoom;
    private Button mBtnDeleteRoom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_store, container, false);

        mEditText = view.findViewById(R.id.edit);

        mBtnSaveDataBySharedpreferences = view.findViewById(R.id.btn_save_data_by_sharedpreferences);
        mBtnRestoreDataBySharedpreferences = view.findViewById(R.id.btn_restore_data_by_sharedpreferences);

        mBtnInsertRoom = view.findViewById(R.id.btn_insert_room);
        mBtnQueryRoom = view.findViewById(R.id.btn_query_room);
        mBtnUpdateRoom = view.findViewById(R.id.btn_update_room);
        mBtnDeleteRoom = view.findViewById(R.id.btn_delete_room);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String inputText = FileIOUtils.readFile2String(PathUtils.getDataPath() + TMP_FILE_NAME);

        if (!TextUtils.isEmpty(inputText)) {
            mEditText.setText(inputText);
            mEditText.setSelection(inputText.length());
            ToastUtils.showShort("Restoring succeeded");
        }

        mAppDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "book").build();
        mBookDao = mAppDatabase.bookDao();

        mBtnSaveDataBySharedpreferences.setOnClickListener(this);
        mBtnRestoreDataBySharedpreferences.setOnClickListener(this);

        mBtnInsertRoom.setOnClickListener(this);
        mBtnQueryRoom.setOnClickListener(this);
        mBtnUpdateRoom.setOnClickListener(this);
        mBtnDeleteRoom.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        FileIOUtils.writeFileFromString(PathUtils.getDataPath() + TMP_FILE_NAME, mEditText.getText().toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_save_data_by_sharedpreferences:
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
                break;
            case R.id.btn_restore_data_by_sharedpreferences:
                SharedPreferences prefs = getActivity().getSharedPreferences("data", MODE_PRIVATE);
                String name = prefs.getString("name", "");
                int age = prefs.getInt("age", 0);
                boolean married = prefs.getBoolean("married", false);

                Log.d(TAG, "name is " + name);
                Log.d(TAG, "age is " + age);
                Log.d(TAG, "married is " + married);
                break;
            case R.id.btn_insert_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        Book book = new Book();
                        book.setName("The Dan Vinci Code");
                        book.setAuthor("Dan Brown");
                        book.setPages(454);
                        book.setPrice(16.96);
                        book.setPress("Unknown");

                        Log.d(TAG, "insert book into Sqlite db: " + book.toString());

                        mBookDao.insert(book);

                        return null;
                    }
                }.execute();
                break;
            case R.id.btn_query_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<Book> bookList = mBookDao.getAll();
                        for (Book book : bookList) {
                            Log.d(TAG, "book is:" + book.toString());
                        }
                        return null;
                    }
                }.execute();
                break;
            case R.id.btn_update_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        double price = 1.01;
                        String name = "The Dan Vinci Code";
                        Log.d(TAG, "book to be updated: " + mBookDao.getBookByName(name).toString());
                        mBookDao.updatePriceByName("The Dan Vinci Code", price);
                        Log.d(TAG, "book is updated: " + mBookDao.getBookByName(name).toString());
                        return null;
                    }
                }.execute();

                break;
            case R.id.btn_delete_room:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        String name = "The Dan Vinci Code";
                        mBookDao.deleteBook(mBookDao.getBookByName(name));
                        Log.d(TAG, "book number: " + mBookDao.getAll().size());
                        return null;
                    }
                }.execute();
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }

    }
}
