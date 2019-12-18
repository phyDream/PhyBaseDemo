package com.demo.phy.phybasedemo.widget.recycler;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.phy.phybasedemo.R;

import java.util.List;

/**
 * Author:phy
 * Date:2017/7/6 16:54
 */

public abstract class BaseLoadAdapter<T> extends RecyclerView.Adapter {


    //region 常量
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_LOAD_MORE = 2;

    public static final int STATE_LOADING = 1;
    public static final int STATE_LASTED = 2;
    public static final int STATE_ERROR = 3;
    //endregion

    //region 变量
    public int loadState;
    boolean hasMore = true;
    boolean isLoading = false;
    private int pageCount = 10;//每一页和后台说定的条数
    public List<T> list;
    //endregion

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOAD_MORE) {
            return new BottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_load_more, parent, false));
        } else {
            return setItemViewHolder(parent, viewType);
        }
    }

    protected abstract RecyclerView.ViewHolder setItemViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (TYPE_LOAD_MORE == getItemViewType(position)) {

            final ProgressBar progressBar = ((BottomViewHolder) holder).progressBar;
            final TextView bottomTextView = ((BottomViewHolder) holder).bottomTextView;
            final ImageView bottomIcon = ((BottomViewHolder) holder).bottomIcon;


            switch (loadState) {
                case STATE_LOADING:
                    progressBar.setVisibility(View.VISIBLE);
                    bottomIcon.setVisibility(View.GONE);
                    bottomTextView.setText(R.string.loading);
                    holder.itemView.setOnClickListener(null);
                    hasMore = true;
                    break;
                case STATE_LASTED:
                    progressBar.setVisibility(View.GONE);
                    bottomIcon.setVisibility(View.VISIBLE);
                    bottomIcon.setImageResource(R.drawable.info_icon);
                    bottomTextView.setText(R.string.no_more);
                    holder.itemView.setOnClickListener(null);
                    hasMore = false;
                    break;
                case STATE_ERROR:
                    progressBar.setVisibility(View.GONE);
                    bottomIcon.setVisibility(View.VISIBLE);
                    bottomIcon.setImageResource(R.drawable.error_icon);
                    bottomTextView.setText(R.string.load_failed_try_again);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressBar.setVisibility(View.VISIBLE);
                            bottomIcon.setVisibility(View.GONE);
                            bottomTextView.setText(R.string.loading);
                            LoadingMore();
                        }
                    });
                    hasMore = true;
                    break;
            }
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {

        if (list.size() < pageCount) {//数量过少，不满一页
            return TYPE_ITEM;
        } else {
            if (position == list.size()) {//当前是最后一条
                return TYPE_LOAD_MORE;
            } else {
                return TYPE_ITEM;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < pageCount) {//数量过少，不满一页
//            hasMore = false;
            return list.size();
        } else {
            return list.size() + 1;
        }
    }


    protected abstract void LoadingMore();

    public void setErrorStatus() {
        loadState = STATE_ERROR;
        notifyItemChanged(list.size());
        setLoading(false);
    }

    public void setLastedStatus() {
        loadState = STATE_LASTED;
        notifyItemChanged(list.size());
    }


    public void addList(List addList) {
        int count = this.list.size();
        this.list.addAll(addList);
        notifyItemRangeChanged(count, addList.size());
        setLoading(false);
    }

    public void refreshList(List newList) {
        this.list.clear();
        this.list.addAll(newList);
        notifyDataSetChanged();
    }

    public class BottomViewHolder extends RecyclerView.ViewHolder {

        TextView bottomTextView;
        ImageView bottomIcon;
        ProgressBar progressBar;

        public BottomViewHolder(View itemView) {
            super(itemView);
            bottomTextView = itemView.findViewById(R.id.bottom_title);
            bottomIcon = itemView.findViewById(R.id.bottom_icon);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
