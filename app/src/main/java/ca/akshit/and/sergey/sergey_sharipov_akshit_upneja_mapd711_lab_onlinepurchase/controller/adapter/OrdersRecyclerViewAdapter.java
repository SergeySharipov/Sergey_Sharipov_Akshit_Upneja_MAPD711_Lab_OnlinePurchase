package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.controller.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.R;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.db.ProductLab;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Order;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Product;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.App;


public class OrdersRecyclerViewAdapter extends RecyclerView.Adapter<OrdersRecyclerViewAdapter.ViewHolder> {

    private final ProductLab mProductLab;
    private final List<Order> mValues;
    private final OnListItemClickListener mListener;
    private final boolean mCurrentUserCustomer;

    public OrdersRecyclerViewAdapter(List<Order> items, OnListItemClickListener listener,
                                     ProductLab productLab, boolean currentUserCustomer) {
        mValues = items;
        mListener = listener;
        mProductLab = productLab;
        mCurrentUserCustomer = currentUserCustomer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_orders_list, parent, false);
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
        void onListItemClick(Order item, int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mCategoryImageView;
        final TextView mNameView;
        final TextView mCategoryView;
        final TextView mPriceView;
        final TextView mDateView;
        final TextView mStatusView;
        final Button mOrderButton;
        Order mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryImageView = view.findViewById(R.id.categoryIV);
            mNameView = view.findViewById(R.id.nameTV);
            mCategoryView = view.findViewById(R.id.categoryTV);
            mPriceView = view.findViewById(R.id.priceTV);
            mDateView = view.findViewById(R.id.dateTV);
            mStatusView = view.findViewById(R.id.statusTV);
            mOrderButton = view.findViewById(R.id.orderBut);
        }

        void bind(Order item) {
            mItem = item;

            if(!mCurrentUserCustomer)
                mOrderButton.setText("✓");

            mDateView.setText(mItem.getFormatOrderDate());
            mStatusView.setText(mItem.getStringStatus());

            Product product = mProductLab.getProduct(item.getProductId());

            setImage(product.getProductName());
            mNameView.setText(product.getProductName());
            mCategoryView.setText(product.getCategory());
            mPriceView.setText(product.getPrice() + "$");

            mOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListItemClick(mItem, getAdapterPosition());
                    }
                }
            });
        }

        void setImage(String name) {
            if (name.contains("Cooler"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[6]);
            else if (name.contains("Pump"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[5]);
            else if (name.contains("25"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[4]);
            else if (name.contains("6"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[3]);
            else if (name.contains("3"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[2]);
            else if (name.contains("2"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[1]);
            else if (name.contains("1"))
                mCategoryImageView.setImageDrawable(App.getBottleIcons()[0]);
        }
    }
}
