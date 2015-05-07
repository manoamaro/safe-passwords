package br.com.manoel.amaro.safepasswords.mobile.activity.fragment;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.activity.DetailActivity;
import br.com.manoel.amaro.safepasswords.mobile.activity.NewPasswordActivity;
import br.com.manoel.amaro.safepasswords.mobile.domain.Password;
import br.com.manoel.amaro.safepasswords.mobile.domain.adapter.PasswordsAdapter;

/**
 * Created by manoel on 18/10/14.
 */
public class PasswordListFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "PasswordListFragment";

    protected RecyclerView mRecyclerView;
    protected RecyclerView.Adapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_password_list, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.password_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PasswordsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        View addButton = rootView.findViewById(R.id.add_button);
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
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Password password = ((PasswordsAdapter.ViewHolder) mRecyclerView.getChildViewHolder(view)).getPassword();
                intent.putExtra("PASSWORD", password);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String passwordDetailTransition = getString(R.string.password_detail_transition_name);
                    String passwordDetailToolbarTransition = getString(R.string.password_detail_toolbar_transition_name);
                    String passwordDetailTitleTransition = getString(R.string.password_detail_title_transition_name);
                    String passwordImageCatTransition = getString(R.string.password_image_transition);
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                                    Pair.create(view.findViewById(R.id.password_list_card), passwordDetailTransition),
                                    Pair.create(getActivity().findViewById(R.id.my_awesome_toolbar), passwordDetailToolbarTransition),
                                    Pair.create(view.findViewById(R.id.info_text), passwordDetailTitleTransition),
                                    Pair.create(view.findViewById(R.id.password_image_cat), passwordImageCatTransition)
                            ).toBundle());
                } else
                    startActivity(intent);
                break;
        }
    }

}
