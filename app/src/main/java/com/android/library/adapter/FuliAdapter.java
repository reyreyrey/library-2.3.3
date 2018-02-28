package com.android.library.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.R;
import com.android.library.databinding.ItemFuliBinding;
import com.android.library.models.FuliModel;
import com.android.library.ui.dialogs.PhotoViewDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<FuliModel.ShowapiResBodyBean.DataBean> beans;

    public FuliAdapter(FragmentActivity context) {
        this.context = context;
        this.beans = new ArrayList<>();
    }

    public void setData(List<FuliModel.ShowapiResBodyBean.DataBean> list) {
        this.beans.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public FuliAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFuliBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_fuli, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FuliAdapter.ViewHolder holder, final int position) {
        holder.binding.exhibitworkName.setText(beans.get(position).getTitle());
        Glide.with(context).load(beans.get(position).getImgurl()).into(holder.binding.exhibitworkImg);
        holder.binding.exhibitworkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoViewDialog dialog = new PhotoViewDialog();
                dialog.setImage(beans.get(position).getImgurl());
                dialog.show(context.getSupportFragmentManager(), "1");
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public void clear() {
        beans.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemFuliBinding binding;

        public ViewHolder(ItemFuliBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
