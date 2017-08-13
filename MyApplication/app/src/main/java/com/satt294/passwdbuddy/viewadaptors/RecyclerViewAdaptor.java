package com.satt294.passwdbuddy.viewadaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.entities.entity.Credential;

import java.util.List;

/**
 * Created by sathish on 8/12/17.
 */

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.RecyclerViewHolder> {

    private List<Credential> credentialList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdaptor(List<Credential> credentialList, View.OnLongClickListener longClickListener) {
        this.credentialList = credentialList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Credential credential = credentialList.get(position);

        holder.tvLogin.setText(credential.getLogin());
        holder.tvDesc.setText(credential.getDescription());

        holder.itemView.setTag(credential);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return credentialList.size();
    }

    /**
     * Functionality to add the credentials to the Live data and
     * broadcast notification of data changed.
     *
     * @param credentialList
     */
    public void addItems(List<Credential> credentialList) {
        this.credentialList = credentialList;
        notifyDataSetChanged();
    }

    /**
     * Class to hold the views & data
     */
    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvLogin = (TextView) itemView.findViewById(R.id.tvLogin);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        }

        /**
         * Fields for an item
         */
        private TextView tvLogin;
        private TextView tvDesc;
    }
}
