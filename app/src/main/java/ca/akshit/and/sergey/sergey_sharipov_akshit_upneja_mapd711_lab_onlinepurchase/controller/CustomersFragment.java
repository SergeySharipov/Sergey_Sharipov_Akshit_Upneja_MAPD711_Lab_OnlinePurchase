package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.adapter.CustomersRecyclerViewAdapter;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.CustomerLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Customer;

public class CustomersFragment extends Fragment implements CustomersRecyclerViewAdapter.OnListItemClickListener{

    private CustomerLab mCustomerLab;
    private List<Customer> mCustomers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCustomerLab = new CustomerLab(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customers_list, container, false);

        mCustomers = mCustomerLab.getCustomers();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new CustomersRecyclerViewAdapter(mCustomers, this));
        }
        return view;
    }

    @Override
    public void onListItemClick(Customer item) {
        Intent intent = new Intent(getContext(),OrdersActivity.class);
        intent.putExtra(OrdersActivity.CUSTOMER_ID,item.getId().toString());
        startActivity(intent);
    }

}
