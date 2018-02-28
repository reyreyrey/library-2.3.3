package ticketsystem.ui.trendanalysis;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.android.library.R;

import charting.charts.BarChart;
import charting.components.AxisBase;
import charting.data.BarData;
import charting.data.BarDataSet;
import charting.data.BarEntry;
import charting.data.Entry;
import charting.formatter.IAxisValueFormatter;
import charting.highlight.Highlight;
import charting.interfaces.datasets.IBarDataSet;
import ticketsystem.base.BaseTitleBar;
import ticketsystem.base.BaseTitleBarActivity;
import ticketsystem.bean.TicketOpenData;
import ticketsystem.bean.TicketType;
import ticketsystem.presenter.ParityTrendPresenter;
import ticketsystem.presenter.view.IParityTrendView;
import ticketsystem.ui.chartconfig.BarChartHelper;
import ticketsystem.ui.chartconfig.DataMarkView;

/**
 * @author xiaolong
 * @version v1.0
 * @function <均值分析>
 * @date: 2017/9/15 11:38
 */

public class CodeRateActivity extends BaseTitleBarActivity<ParityTrendPresenter> implements IParityTrendView {
    private TicketType mTicketType;
    private TextView tvTitle;
    private BarChart bcAvgAnalysis;

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        tvTitle = (TextView) titleBar.center;
    }

    public static Bundle buildBundle(TicketType ticketType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("ticketType", ticketType);
        return bundle;
    }

    @Override
    public void getExtra() {
        mTicketType = (TicketType) getIntent().getSerializableExtra("ticketType");
    }

    @Override
    public ParityTrendPresenter getPresenter() {
        return new ParityTrendPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_avg_analysis;
    }

    @Override
    protected void init() {
        bcAvgAnalysis = findView(R.id.bcAvgAnalysis);
        if (mTicketType != null) {
            tvTitle.setText(mTicketType.descr + "号码频率");
            mPresenter.getRecentOpenDatas(mTicketType.code, "100");
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onGetHistoryRecentTicketListSuccess(List<TicketOpenData> list) {
        bcAvgAnalysis = BarChartHelper.getBarChartHelper().generateBarChartConfig(bcAvgAnalysis);
        BarData barData;
        if (bcAvgAnalysis.getData() != null &&
                bcAvgAnalysis.getData().getDataSetCount() > 0) {
            barData = bcAvgAnalysis.getBarData();
            for (int i = 0; i < barData.getDataSetCount(); i++) {
                BarDataSet barDataSet = (BarDataSet) barData.getDataSetByIndex(i);
                barDataSet.setValues(generateEntry(list));
            }
            bcAvgAnalysis.getData().notifyDataChanged();
            bcAvgAnalysis.notifyDataSetChanged();
        } else {
            List<Integer> colorList = new ArrayList<>();
            colorList.add(getResources().getColor(R.color.main_red_color));
            colorList.add(getResources().getColor(R.color.main_blue_color_4c65ed));
            IBarDataSet barDataSet = BarChartHelper.getBarChartHelper().generateBarDataSet(generateEntry(list), new String[]{"普通码", "特别码"}, colorList);
            barData = new BarData(barDataSet);
            bcAvgAnalysis.setData(barData);
            bcAvgAnalysis.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return (int) value + "号";
                }
            });
            bcAvgAnalysis.setMarker(new DataMarkView(this, new DataMarkView.IDataValueFormat() {
                @Override
                public String format(Entry e, Highlight highlight) {
                    return ((int) e.getX()) + "号：" + e.getY() + "次";
                }
            }));
        }

        bcAvgAnalysis.animateY(3000);
    }

    private List<BarEntry> generateEntry(List<TicketOpenData> list) {
        List<Integer> keyList = new ArrayList<>();
        List<Integer> specialKeyList = new ArrayList<>();
        ArrayMap<Integer, Integer> numberMap = new ArrayMap<>();
        ArrayMap<Integer, Integer> specialNumberMap = new ArrayMap<>();
        for (int j = 0; j < list.size(); j++) {
            Pair<String[], String[]> sPair = translateCodeToList(list.get(j).openCode);
            for (int k = 0; k < sPair.first.length; k++) {
                try {
                    int number = Integer.valueOf(sPair.first[k]);
                    if (keyList.contains(number)) {
                        numberMap.put(number, numberMap.get(number) + 1);
                    } else {
                        keyList.add(number);
                        numberMap.put(number, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            for (int k = 0; k < sPair.second.length; k++) {
                try {
                    int number = Integer.valueOf(sPair.second[k]);
                    if (specialKeyList.contains(number)) {
                        specialNumberMap.put(number, specialNumberMap.get(number) + 1);
                    } else {
                        specialKeyList.add(number);
                        specialNumberMap.put(number, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.sort(keyList);
        Collections.sort(specialKeyList);
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            barEntries.add(new BarEntry(keyList.get(i), new float[]{numberMap.get(keyList.get(i)),
                    specialNumberMap.get(keyList.get(i)) == null ? 0 : specialNumberMap.get(keyList.get(i))}));
        }
        return barEntries;
    }

    private Pair<String[], String[]> translateCodeToList(String openCode) {
        String[] splitString = openCode.split("\\+");
        String[] openNumbers = splitString[0].split(",");
        String[] specialNumbers = new String[]{};
        if (splitString.length > 1) {
            //说明是带特别码的彩种
            specialNumbers = splitString[1].split(",");
        }
        return new Pair<>(openNumbers, specialNumbers);
    }
}
