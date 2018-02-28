package ticketsystem.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.library.BuildConfig;
import com.android.library.R;

import library.listview.adapter.LoadMoreRecycleAdapter;
import library.model.ListData;
import library.rx.CSubscriber;
import library.rx.ErrorThrowable;
import library.util.TimeUtils;
import library.util.ToastUtil;
import ticketsystem.api.DataManager;
import ticketsystem.bean.TicketOpenData;
import ticketsystem.bean.TicketType;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/13 10:21
 */

public class TicketRecentOpenAdapter extends LoadMoreRecycleAdapter<TicketType, TicketRecentOpenAdapter.ViewHolder> {

    public TicketRecentOpenAdapter(Context mContext) {
        super(mContext);
        removeHeaderView(0X666);
        removeFooterView(0X11);
    }

    @Override
    public TicketRecentOpenAdapter.ViewHolder onCreateContentView(ViewGroup parent, int viewType) {
        return new TicketRecentOpenAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_ticket_recentopen, parent, false));
    }

    @Override
    public void onBindView(ViewHolder viewHolder, int realPosition) {
        viewHolder.setData(mData.get(realPosition), realPosition);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTicketName;
        private TextView tvTime;
        private TextView tvOpenTime;
        private RecyclerView rvOpenResult;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTicketName = (TextView) itemView.findViewById(R.id.tvTicketName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            rvOpenResult = (RecyclerView) itemView.findViewById(R.id.rvOpenResult);
            tvOpenTime = (TextView) itemView.findViewById(R.id.tvOpenTime);
        }

        public void setData(TicketType typeInfo, int realPosition) {
            tvTicketName.setText(typeInfo.descr);
            DataManager.getSinglePeroidCheck(typeInfo.code, "").subscribe(new CSubscriber<ListData<TicketOpenData>>() {
                @Override
                public void onPrepare() {

                }

                @Override
                public void onError(ErrorThrowable throwable) {
                    ToastUtil.showToast(throwable.msg);
                }

                @Override
                public void onSuccess(ListData<TicketOpenData> ticketOpenDataListData) {
                    tvOpenTime.setText("开奖时间：" + TimeUtils.milliseconds2String(ticketOpenDataListData.list.get(0).timestamp * 1000));
                    tvTime.setText("第" + ticketOpenDataListData.list.get(0).expect + "期");
                    OpenCodeAdapter openCodeAdapter = new OpenCodeAdapter(mContext, ticketOpenDataListData.list.get(0).openCode);
                    rvOpenResult.setLayoutManager(new GridLayoutManager(mContext, 7));
                    rvOpenResult.setAdapter(openCodeAdapter);
                }
            });
            itemView.findViewById(R.id.rlBottom).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        v.setTag(typeInfo);
                        mOnItemClickListener.onClick(v);
                    }
                }
            });
        }
    }
}
