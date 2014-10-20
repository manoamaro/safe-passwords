package br.com.manoel.amaro.safepasswords.activity.fragment;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.manoel.amaro.safepasswords.R;
import br.com.manoel.amaro.safepasswords.activity.NewPasswordActivity;
import br.com.manoel.amaro.safepasswords.domain.adapter.PasswordsAdapter;

/**
 * Created by manoel on 18/10/14.
 */
public class PasswordListFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PasswordListFragment";

    protected RecyclerView mRecyclerView;
    protected RecyclerView.Adapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    private View addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_password_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.password_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PasswordsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), NewPasswordActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this.getActivity()).toBundle());
    }
}
