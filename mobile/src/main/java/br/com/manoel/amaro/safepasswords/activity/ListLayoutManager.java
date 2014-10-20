package br.com.manoel.amaro.safepasswords.activity;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by manoel on 28/09/14.
 */
public class ListLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
