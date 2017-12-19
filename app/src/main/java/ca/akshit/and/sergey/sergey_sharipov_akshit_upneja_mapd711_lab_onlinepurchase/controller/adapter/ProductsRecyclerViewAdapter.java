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
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model.Product;
import ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.util.App;

public class ProductsRecyclerViewAdapter extends RecyclerView.Adapter<ProductsRecyclerViewAdapter.ViewHolder> {

    private final List<Product> mValues;
    private final OnListItemClickListener mListener;

    public ProductsRecyclerViewAdapter(List<Product> items, OnListItemClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_products_list, parent, false);
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
        void onListItemClick(Product item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mCategoryImageView;
        final TextView mNameView;
        final TextView mCategoryView;
        final TextView mAvailableView;
        final TextView mPriceView;
        final Button mOrderBut;
        Product mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCategoryImageView = view.findViewById(R.id.categoryIV);
            mNameView = view.findViewById(R.id.nameTV);
            mCategoryView = view.findViewById(R.id.categoryTV);
            mAvailableView = view.findViewById(R.id.availableTV);
            mPriceView = view.findViewById(R.id.priceTV);
            mOrderBut = view.findViewById(R.id.orderBut);
        }

        void bind(Product item) {
            mItem = item;

            setImage(mItem.getProductName());
            mNameView.setText(mItem.getProductName());
            mCategoryView.setText(mItem.getCategory());
            mAvailableView.setText(mItem.getQuantity()+"");
            mPriceView.setText(mItem.getPrice() + "$");

            mOrderBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListItemClick(mItem);
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
