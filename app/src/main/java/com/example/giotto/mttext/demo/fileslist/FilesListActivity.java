package com.example.giotto.mttext.demo.fileslist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.giotto.mttext.demo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yly
 * @Data 2019/2/13 10:50
 * @Description  Android获取文件夹下的所有子文件名称
 */
public class FilesListActivity extends Activity {
    private RecyclerView files_list_rv;
    private List<FilesBean> list;
    private FilesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.files_list_layout);
        init();
    }

    private void init() {
        files_list_rv = (RecyclerView) findViewById(R.id.files_list_rv);
        list = new ArrayList<>();
        files_list_rv.setLayoutManager(new LinearLayoutManager(this));
        list = getFilesAllName(getSDPath() + "/tencent/micromsg/download/");//微信地址
        adapter = new FilesListAdapter(this, list);
        files_list_rv.setAdapter(adapter);
    }

    public static List<FilesBean> getFilesAllName(String path) {
        List<FilesBean> list = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {//存在
            File[] files = file.listFiles();
            if (files == null) {
                Log.e("error", "空目录");
                return null;
            }
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
//            if (fileName.endsWith(".zip") || fileName.endsWith(".txt")
//               || fileName.endsWith(".docx")|| fileName.endsWith(".xlsx")) {
                FilesBean bean = new FilesBean();
                bean.setFilePath(files[i].getAbsolutePath());
                bean.setName(files[i].getName());
                list.add(bean);
//            }
            }
        }
        return list;
    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
