package br.com.manoel.amaro.safepasswords.mobile.domain.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.manoel.amaro.safepasswords.mobile.R;
import br.com.manoel.amaro.safepasswords.mobile.domain.Password;

/**
 * Created by manoel on 18/10/14.
 */
public class PasswordsAdapter extends RecyclerView.Adapter<PasswordsAdapter.ViewHolder> {

    Password.Dao passwordDao = new Password.Dao();
    List<Password> passwords = new ArrayList<>();
    private View.OnClickListener onClickListener;

    public PasswordsAdapter(View.OnClickListener onClickListener) {
        super();
        this.onClickListener = onClickListener;
        this.refresh();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.password_card, viewGroup, false);
        view.setOnClickListener(this.onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setPassword(passwords.get(i));
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public void refresh() {
        passwords.clear();
        passwords.addAll(passwordDao.findAll());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        private Password password;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.info_text);
        }

        public void setPassword(Password password) {
            this.password = password;
            this.mTextView.setText(this.password.getTitle());
        }

        public Password getPassword() {
            return password;
        }
    }

}
