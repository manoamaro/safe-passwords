package br.com.manoel.amaro.safepasswords.activity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.manoel.amaro.safepasswords.R;
import br.com.manoel.amaro.safepasswords.activity.DetailActivity;
import br.com.manoel.amaro.safepasswords.activity.NewPasswordActivity;
import br.com.manoel.amaro.safepasswords.domain.Password;
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
        mAdapter = new PasswordsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button:
                Intent newPassIntent = new Intent(getActivity(), NewPasswordActivity.class);
                startActivity(newPassIntent);
                break;
            case R.id.password_card_layout:
                View title = view.findViewById(R.id.info_text);
                String viewName = getString(R.string.password_title_transition_name);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Password password = ((PasswordsAdapter.ViewHolder)
                        mRecyclerView.getChildViewHolder(view)).getPassword();
                intent.putExtra("PASSWORD", password);
                ActivityCompat.startActivity(getActivity(), intent,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                getActivity(), title, viewName
                        ).toBundle());
                break;
        }
    }

}
