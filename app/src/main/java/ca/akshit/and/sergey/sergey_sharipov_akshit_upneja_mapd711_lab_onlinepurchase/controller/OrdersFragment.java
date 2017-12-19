package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.adapter.OrdersRecyclerViewAdapter;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.OrderLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.ProductLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Constants;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Order;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.SharedPreferencesHelper;

import static ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.OrdersActivity.CUSTOMER_ID;

public class OrdersFragment extends Fragment implements OrdersRecyclerViewAdapter.OnListItemClickListener {

    private OrderLab mOrderLab;
    private ProductLab mProductLab;
    private List<Order> mOrders;
    private SharedPreferencesHelper mSharedPrefHelper;
    boolean mCurrentUserCustomer;
    private OrdersRecyclerViewAdapter mOrdersRecyclerViewAdapter;

    public static OrdersFragment newInstance(UUID customerId) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(CUSTOMER_ID, customerId.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrderLab = new OrderLab(getContext());
        mProductLab = new ProductLab(getContext());

        mSharedPrefHelper = SharedPreferencesHelper.get(getActivity().getSharedPreferences(
                SharedPreferencesHelper.NAME, Context.MODE_PRIVATE
        ));

        mCurrentUserCustomer = mSharedPrefHelper.isCurrentUserCustomer();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_list, container, false);

        String customerId = "";
        if (!mCurrentUserCustomer) {
            if (getArguments() != null)
                customerId = getArguments().getString(CUSTOMER_ID);
        } else
            customerId = mSharedPrefHelper.getCurrentUserID();

        if (customerId != null && !customerId.equals(""))
            mOrders = mOrderLab.getOrders(UUID.fromString(customerId));
        else
            mOrders = mOrderLab.getOrders();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mOrdersRecyclerViewAdapter = new OrdersRecyclerViewAdapter(mOrders, this,
                    mProductLab, mCurrentUserCustomer);
            recyclerView.setAdapter(mOrdersRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onListItemClick(Order item, int adapterPosition) {
        if(!mCurrentUserCustomer){
            mOrderLab.updateOrderStatus(item, Constants.Status.DONE);
            Toast.makeText(getContext(), R.string.message_order_updated, Toast.LENGTH_SHORT).show();
            mOrdersRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            if(item.getStatus()== Constants.Status.ACTIVE) {
                mProductLab.plusProduct(item.getProductId());
                mOrderLab.deleteOrder(item);
                mOrders.remove(item);
                mOrdersRecyclerViewAdapter.notifyItemRemoved(adapterPosition);
            } else
                Toast.makeText(getContext(), R.string.message_order_delivered, Toast.LENGTH_SHORT).show();
        }
    }

}
