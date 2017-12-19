package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.adapter.ProductsRecyclerViewAdapter;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.OrderLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.ProductLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Order;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Product;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.SharedPreferencesHelper;

import static android.content.Context.MODE_PRIVATE;

public class ProductsFragment extends Fragment implements ProductsRecyclerViewAdapter.OnListItemClickListener{

    private SharedPreferencesHelper mSharedPrefHelper;
    private OrderLab mOrderLab;
    private ProductLab mProductLab;
    private List<Product> mProducts;
    private ProductsRecyclerViewAdapter mProductsRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPrefHelper = SharedPreferencesHelper.get(getActivity().getSharedPreferences(
                SharedPreferencesHelper.NAME, MODE_PRIVATE));

        mProductLab = new ProductLab(getContext());
        mOrderLab = new OrderLab(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        mProducts = mProductLab.getProducts();
        mProductsRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);

        mProducts = mProductLab.getProducts();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mProductsRecyclerViewAdapter=new ProductsRecyclerViewAdapter(mProducts, this);
            recyclerView.setAdapter(mProductsRecyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onListItemClick(Product item) {
        if(mProductLab.minusProduct(item)) {
            UUID customerId = UUID.fromString(mSharedPrefHelper.getCurrentUserID());
            UUID productId = item.getId();
            Order order = new Order(customerId, productId);
            Log.e("omg", productId.toString());
            mOrderLab.addOrder(order);

            Toast.makeText(getContext(), R.string.message_order_added, Toast.LENGTH_SHORT).show();
            mProductsRecyclerViewAdapter.notifyDataSetChanged();
        } else
            Toast.makeText(getContext(), R.string.message_no_available, Toast.LENGTH_SHORT).show();
    }
}
