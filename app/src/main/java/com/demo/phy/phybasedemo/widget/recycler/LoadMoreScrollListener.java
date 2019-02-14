package com.demo.phy.phybasedemo.widget.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Project:CustomRecycleView
 * Author:dyping
 * Date:2017/7/6 18:45
 */

public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView recyclerView;

    public LoadMoreScrollListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        BaseLoadAdapter adapter = (BaseLoadAdapter) recyclerView.getAdapter();

        if (null == manager) {
            throw new RuntimeException("you should call setLayoutManager() first!!");
        }

        if(manager instanceof LinearLayoutManager){
            int lastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
            if(adapter.hasMore && adapter.getItemCount() > adapter.getPageCount() && adapter.getItemCount()-1 == lastVisibleItemPosition){
                if(!adapter.isLoading()){
                    adapter.LoadingMore();
                }

            }
        }
    }
}
