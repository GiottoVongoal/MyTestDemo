package com.example.giotto.mttext.demo.table;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.PageTableData;
import com.bin.david.form.utils.DensityUtils;
import com.example.giotto.mttext.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yly
 * @Data 2019/4/9 10:57
 * @Description  表格展示页面
 */
public class TableActivity extends Activity implements View.OnClickListener {

    private SmartTable<UserInfo> table;
    private PageTableData<UserInfo> tableData;

    private TextView tvback, tvNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        FontStyle.setDefaultTextSize(DensityUtils.sp2px(this, 15)); //设置全局字体大小
        table = (SmartTable<UserInfo>) findViewById(R.id.table);
        tvback = (TextView) findViewById(R.id.tv_page_back);
        tvNext = (TextView) findViewById(R.id.tv_page_next);
        tvback.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        //普通列
        Column<String> name = new Column<>("项目经理", "name");
        name.setFixed(true);
        Column<String> khgs = new Column<>("客户归属", "khgs");
        khgs.setFixed(true);
        Column<String> province = new Column<>("省", "province");
        Column<String> city = new Column<>("市/镇", "city");
        Column<String> asqbh = new Column<>("合同号", "asqbh");
        Column<String> czr = new Column<>("承租人", "czr");
        Column<String> gys = new Column<>("供应商", "gys");
        Column<String> dls = new Column<>("代理商", "dls");
        Column<String> gysdbfs = new Column<>("供应商担保方式", "gysdbfs");
        Column<String> sbhy = new Column<>("设备行业", "sbhy");
        Column<String> sbmc = new Column<>("设备名称", "sbmc");
        Column<String> sjIRR = new Column<>("实际IRR(税前%)", "sjIRR");
        Column<String> sfygyq = new Column<>("是否有过逾期", "sfygyq");
        Column<String> sjqzr = new Column<>("实际起租日", "sjqzr");
        Column<String> dqr = new Column<>("到期日", "dqr");

        //设置单元格内容
        List<UserInfo> list = new ArrayList<>();
        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180012", "二次送审888二次送审888",
                "玉田县昌通电子有限公司", "玉田县昌通电子有限公司", "无", "线切割", "多线切割机", "17.183049",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "", "", "LA180015", "东莞市同进刀具科技有限公司",
                "ANCA Manufacturing (Thailand) Ltd.", "苏美达国际技术贸易有限公司", "无", "磨床", "数控刃磨机床", "15.018985",
                "是", "", ""));

        list.add(new UserInfo("李霞", "苏州一区", "", "", "LA180307001", "二次送审888二次送审888",
                "永准机械（上海）有限公司", "玉田县昌通电子有限公司", "无", "加工中心", "多线切割机", "14.692302",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180312", "二次送审888二次送审888",
                "玉田县昌通电子有限公司", "", "无", "加工中心", "加工", "13.987902",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180307002", "额度测试专用",
                "玉田县昌通电子有限公司", "", "无", "线切割", "加工", "18.003007",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180318002", "额度测试专用",
                "江苏亚威机床股份有限公司", "玉田县昌通电子有限公司", "无", "线切割", "多线切割机", "17.183049",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "", "", "LA180012", "二次送审888二次送审888",
                "江苏亚威机床股份有限公司", "", "无", "线切割", "多线切割机", "14.692302",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180319001", "深圳市杰美晟模具有限公司",
                "江苏亚威机床股份有限公司", "", "无", "车床", "车床", "17.183049",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180318001", "深圳市杰美晟模具有限公司",
                "江苏亚威机床股份有限公司", "测试统一社会信用", "无", "车床", "车床", "13.987902",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180319002", "深圳市杰美晟模具有限公司",
                "江苏亚威机床股份有限公司", "测试统一社会信用", "无", "车床", "车床", "14.692302",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "", "", "LA180308001", "二次送审888二次送审888",
                "江苏亚威机床股份有限公司", "", "无", "车床", "车床", "16.004997",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180320", "二次送审888二次送审888",
                "玉田县昌通电子有限公司", "王氏港建科技设备（深圳）有限公司", "无", "线切割", "多线切割机", "13.987902",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180321001", "额度测试专用",
                "东莞市龙辰晖机械设备有限公司", "", "无", "线切割", "多线切割机", "13.980634",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "", "", "LA180324002", "额度测试专用",
                "东莞市龙辰晖机械设备有限公司", "", "无", "线切割", "多线切割机", "17.183049",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180324003", "交易额度测试A-01",
                "东莞市龙辰晖机械设备有限公司", "", "无", "线切割", "多线切割机", "13.987902",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180324004", "交易额度测试A-01",
                "东莞市龙辰晖机械设备有限公司", "玉田县昌通电子有限公司", "无", "线切割", "多线切割机", "18.003007",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180326001", "二次送审888二次送审888",
                "玉田县昌通电子有限公司", "", "无", "线切割", "多线切割机", "17.183049",
                "是", "2018-06-28", "2020-06-28"));

        list.add(new UserInfo("李霞", "苏州一区", "广东省", "东莞市", "LA180326002", "二次送审888二次送审888",
                "玉田县昌通电子有限公司", "", "无", "线切割", "多线切割机", "16.004997",
                "是", "2018-06-28", "2020-06-28"));


        tableData = new PageTableData<>("起租合同信息统计表", list, name, khgs, province, city, asqbh, czr, gys, dls, gysdbfs,
                sbhy, sbmc, sjIRR, sfygyq, sjqzr, dqr);

        table.getConfig().setShowXSequence(false);//(横向)
//        table.getConfig().setShowYSequence(false);//(纵向)

        tableData.setPageSize(14);//
        table.setTableData(tableData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_page_back:
                tableData.setCurrentPage(tableData.getCurrentPage() - 1);
                table.notifyDataChanged();
                break;
            case R.id.tv_page_next:
                tableData.setCurrentPage(tableData.getCurrentPage() + 1);
                table.notifyDataChanged();
                break;
        }
    }
}
