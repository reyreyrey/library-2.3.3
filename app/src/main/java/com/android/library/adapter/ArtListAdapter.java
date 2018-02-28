package com.android.library.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.library.R;
import com.android.library.databinding.ItemArtListBinding;
import com.android.library.models.ArtListModel;
import com.android.library.ui.dialogs.PhotoViewDialog;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArtListAdapter extends RecyclerView.Adapter<ArtListAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<ArtListModel.DataBean.ExhibitArtistListBean> beans;

    public ArtListAdapter(FragmentActivity context) {
        this.context = context;
        this.beans = new ArrayList<>();
    }

    public void setData(ArtListModel artListModel) {
        List<ArtListModel.DataBean> datas = artListModel.getData();
        for (ArtListModel.DataBean b : datas) {
            this.beans.addAll(b.getExhibitArtistList());
        }
        notifyDataSetChanged();
    }

    @Override
    public ArtListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemArtListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_art_list, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArtListAdapter.ViewHolder holder, final int position) {
        holder.binding.exhibitworkAthor.setText(beans.get(position).getAuthor());
        holder.binding.exhibitworkName.setText(beans.get(position).getName());
        Glide.with(context).load(beans.get(position).getWorksPic()).into(holder.binding.exhibitworkImg);
        holder.binding.exhibitworkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoViewDialog dialog = new PhotoViewDialog();
                dialog.setImage(beans.get(position).getWorksPic());
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
        public ItemArtListBinding binding;

        public ViewHolder(ItemArtListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
