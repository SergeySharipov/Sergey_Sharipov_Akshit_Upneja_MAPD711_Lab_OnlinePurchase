package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Customer;

public class CustomersRecyclerViewAdapter extends RecyclerView.Adapter<CustomersRecyclerViewAdapter.ViewHolder> {

    private final List<Customer> mValues;
    private final OnListItemClickListener mListener;

    public CustomersRecyclerViewAdapter(List<Customer> items, OnListItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customers_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnListItemClickListener {
        void onListItemClick(Customer item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mLoginView;
        final TextView mFirstNameView;
        final TextView mLastNameView;
        final TextView mAddressView;
        final TextView mProvinceView;
        final TextView mCityView;
        final TextView mPostalCodeView;
        final Button mOrderBut;
        Customer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLoginView = view.findViewById(R.id.emailTV);
            mFirstNameView = view.findViewById(R.id.firstNameTV);
            mLastNameView = view.findViewById(R.id.lastNameTV);
            mAddressView = view.findViewById(R.id.addressTV);
            mProvinceView = view.findViewById(R.id.provinceTV);
            mCityView = view.findViewById(R.id.cityTV);
            mPostalCodeView = view.findViewById(R.id.postalCodeTV);

            mOrderBut = view.findViewById(R.id.orderBut);
        }

        void bind(Customer item) {
            mItem = item;

            mLoginView.setText(mItem.getLogin());
            mFirstNameView.setText(mItem.getFirstName());
            mLastNameView.setText(mItem.getLastName());
            mAddressView.setText(mItem.getAddress());
            mProvinceView.setText(mItem.getProvince());
            mCityView.setText(mItem.getCity());
            mPostalCodeView.setText(mItem.getPostalCode());

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListItemClick(mItem);
                    }
                }
            });
        }
    }
}
