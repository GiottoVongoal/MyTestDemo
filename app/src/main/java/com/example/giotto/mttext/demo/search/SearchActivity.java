package com.example.giotto.mttext.demo.search;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.giotto.mttext.demo.utils.MyUtils.showToast;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo
 * @date 2017-07-13 09:46
 * @description 展示历史查询
 */
public class SearchActivity extends Activity implements View.OnClickListener {
    ImageView search_back_iv;
    EditText search_et;
    TextView search_do_tv;//查询按钮
    TextView history_search_none_tv;//暂无
    TextView history_search_clear_tv;//清空

    ListView history_search_lv;
    private List<String> historyList;
    private SearchHistoryAdapter historyAdapter;
    private String historyString;// 用于存储历史纪录的字符串
    private SPUtils sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        search_back_iv = (ImageView) findViewById(R.id.search_back_iv);
        search_et = (EditText) findViewById(R.id.search_et);
        search_do_tv = (TextView) findViewById(R.id.search_do_tv);
        history_search_none_tv = (TextView) findViewById(R.id.history_search_none_tv);
        history_search_clear_tv = (TextView) findViewById(R.id.history_search_clear_tv);
        history_search_lv = (ListView) findViewById(R.id.history_search_lv);

        // 把软键盘上的回车键改为搜索,布局中android:imeOptions="actionSearch"
        search_et.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键【回车键改为了搜索键】
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 进行搜索操作
                    String etString = search_et.getText().toString();
                    getSearch(etString);
                }
                return false;
            }
        });
        // 获取历史记录
        historyList = new ArrayList<>();
        sp = new SPUtils(this, "searchtrademark");//搜索商标
        initHistory();
        historyAdapter = new SearchHistoryAdapter(this, historyList);
        history_search_lv.setAdapter(historyAdapter);
        //监听点击事件
        setOnClick();
    }


    private void initHistory() {
        historyString = sp.getString("historylist", "");
        Log.i("---本地搜索历史-->", historyString);
        if (historyString.length() != 0) {
            stringToList(historyString);
            history_search_clear_tv.setVisibility(View.VISIBLE);
            history_search_none_tv.setVisibility(View.GONE);
        } else {
            history_search_clear_tv.setVisibility(View.GONE);
            history_search_none_tv.setVisibility(View.VISIBLE);
        }
    }

    private void setOnClick() {
        search_do_tv.setOnClickListener(this);
        history_search_clear_tv.setOnClickListener(this);
        search_back_iv.setOnClickListener(this);
        history_search_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSearch(historyList.get(position));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initHistory();
        if (historyAdapter != null) {
            historyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_back_iv:
                onBackClick();
                break;
            case R.id.search_do_tv:
                String etString = search_et.getText().toString();
                getSearch(etString);
                break;
            case R.id.history_search_clear_tv:
                clearHistory();
                break;
        }
    }

    /**
     * 进行查询并将查询内容写入历史记录中
     */
    private void getSearch(String searchString) {
        hideSoftInput(this);
//        String etString = search_et.getText().toString();
        if (!TextUtils.isEmpty(searchString)) {
            putString(searchString);
            showToast(this, "查询" + searchString);
            onBackClick();
        } else {
            showToast(this, "请输入搜索内容");
        }
    }

    /**
     * 存储新的搜索历史(去掉重复的)
     *
     * @param putString 插入的字符串
     */
    private void putString(String putString) {
        String string = sp.getString("historylist", "");
        String replaceString = putString + ",";
        if (string.indexOf(replaceString) != -1) {
            string = string.replaceAll(replaceString, "");
            putString = replaceString + string;
            sp.put("historylist", putString);
        } else {
            putString = putString + "," + sp.getString("historylist", "");
            sp.put("historylist", putString);
        }
    }

    /**
     * 弹出框提示是否清除历史搜索记录
     */
    private void clearHistory() {
//        OnClickListener off = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        };
//        OnClickListener on = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
        sp.put("historylist", "");
        historyList.clear();
        historyAdapter.notifyDataSetChanged();
        history_search_clear_tv.setVisibility(View.GONE);
        history_search_none_tv.setVisibility(View.VISIBLE);
        showToast(this, "清除成功");
//            }
//        };
//        new NewViewTools(TradingHallSearchActivity.this).dia_log(
//                new String[]{"确定清除历史搜索记录?"}, "提示", off, on, new String[]{
//                        "取消", "确定"});
    }

    private void stringToList(String string) {
        historyList.clear();
        String[] myStrings = string.split(",");
        for (int i = 0; i < myStrings.length; i++) {
            historyList.add(myStrings[i]);
        }
    }


    public static void onBackClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }).start();
    }

    /*
      避免输入法面板遮挡
      <p>在manifest.xml中activity中设置</p>
      <p>android:windowSoftInputMode="adjustPan"</p>
     */

    /**
     * 动态隐藏软键盘
     *
     * @param activity activity
     */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
