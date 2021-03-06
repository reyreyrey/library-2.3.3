package ticketsystem.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.android.library.BuildConfig;
import com.android.library.R;

import library.util.ToastUtil;

/**
 * <生成中奖列表>
 *
 * @data: 2016/8/9 11:18
 * @version: V1.0
 */
public class TicketGroupListAdapter extends RecyclerView.Adapter<TicketGroupListAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mCodeGroups;

    public TicketGroupListAdapter(Context context, List<String> result) {
        mContext = context;
        mCodeGroups = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listitem_code_group, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mCodeGroups.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mCodeGroups.size();
    }

    public void addCodeGroup(String codeGroup) {
        mCodeGroups.add(codeGroup);
        notifyDataSetChanged();
    }

    public List<String> getCodeGroups() {
        return mCodeGroups;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvCodeGroup;
        private TextView tvOperate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvOperate = (TextView) itemView.findViewById(R.id.tvOperate);
            rvCodeGroup = (RecyclerView) itemView.findViewById(R.id.rvCodeGroup);
        }

        public void setData(String codeGroup, int realPosition) {
            CodeGroupAdapter codeGroupAdapter = new CodeGroupAdapter(mContext, codeGroup);
            rvCodeGroup.setLayoutManager(new GridLayoutManager(mContext, 7));
            rvCodeGroup.setAdapter(codeGroupAdapter);
            PopupMenu popupMenu = new PopupMenu(mContext, tvOperate);
            popupMenu.inflate(R.menu.operate_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.copy) {
                        ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        clipboardManager.setPrimaryClip(ClipData.newPlainText(null, codeGroup));
                        ToastUtil.showToast("已经复制到剪切板");
                        return false;
                    }
                    if (item.getItemId() == R.id.delete) {
                        if (mCodeGroups.contains(codeGroup)) {
                            int index = mCodeGroups.indexOf(codeGroup);
                            mCodeGroups.remove(index);
                            notifyItemRemoved(index);
                        }
                        return false;
                    }
                    return false;
                }
            });
            tvOperate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupMenu.show();
                }
            });
        }
    }
}
