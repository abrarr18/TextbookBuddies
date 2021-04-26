package com.example.textbookbuddies.search;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.textbookbuddies.R;
import com.example.textbookbuddies.models.Book;
import com.google.firebase.firestore.Query;

/**
 * Dialog Fragment containing filter form.
 */
public class FilterDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "FilterDialog";

    interface FilterListener {

        void onFilter(Filters filters);

    }

    private View mRootView;

    private Spinner mClassesSpinner;
    private Spinner mLocationSpinner;
    private Spinner mSortSpinner;
    private Spinner mPriceSpinner;

    private FilterListener mFilterListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.dialog_filters, container, false);

        mClassesSpinner = mRootView.findViewById(R.id.spinner_classes);
        mLocationSpinner = mRootView.findViewById(R.id.spinner_location);
        mSortSpinner = mRootView.findViewById(R.id.spinner_sort);
        mPriceSpinner = mRootView.findViewById(R.id.spinner_price);

        mRootView.findViewById(R.id.button_search).setOnClickListener(this);
        mRootView.findViewById(R.id.button_cancel).setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FilterListener) {
            mFilterListener = (FilterListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_search:
                onSearchClicked();
                break;
            case R.id.button_cancel:
                onCancelClicked();
                break;
        }
    }

    public void onSearchClicked() {
        if (mFilterListener != null) {
            mFilterListener.onFilter(getFilters());
        }

        dismiss();
    }

    public void onCancelClicked() {
        dismiss();
    }

    @Nullable
    private String getSelectedClasses() {
        String selected = (String) mClassesSpinner.getSelectedItem();
        if (getString(R.string.value_any_classes).equals(selected)) {
            return null;
        } else {
            return selected;
        }
    }

    @Nullable
    private String getSelectedLocation() {
        String selected = (String) mLocationSpinner.getSelectedItem();
        if (getString(R.string.value_any_location).equals(selected)) {
            return null;
        } else {
            return selected;
        }
    }

    private int getSelectedPrice() {
        String selected = (String) mPriceSpinner.getSelectedItem();
        if (selected.equals(getString(R.string.price_1))) {
            return 1;
        } else if (selected.equals(getString(R.string.price_2))) {
            return 2;
        } else if (selected.equals(getString(R.string.price_3))) {
            return 3;
        } else {
            return -1;
        }
    }

    @Nullable
    private String getSelectedSortBy() {
        String selected = (String) mSortSpinner.getSelectedItem();
        if (getString(R.string.sort_by_price).equals(selected)) {
            return Book.FIELD_PRICE;
        }
        if (getString(R.string.sort_by_title).equals(selected)) {
            return Book.FIELD_TITLE;
        }

        return null;
    }

    @Nullable
    private Query.Direction getSortDirection() {
        String selected = (String) mSortSpinner.getSelectedItem();
        if (getString(R.string.sort_by_price).equals(selected)) {
            return Query.Direction.ASCENDING;
        }
        if (getString(R.string.sort_by_title).equals(selected)) {
            return Query.Direction.ASCENDING;
        }

        return null;
    }

    public void resetFilters() {
        if (mRootView != null) {
            mClassesSpinner.setSelection(0);
            mLocationSpinner.setSelection(0);
            mPriceSpinner.setSelection(0);
            mSortSpinner.setSelection(0);
        }
    }

    public Filters getFilters() {
        Filters filters = new Filters();

        if (mRootView != null) {
            //filters.setClasses(getSelectedClasses());
            //filters.setLocation(getSelectedLocation());
            filters.setPrice(getSelectedPrice());
            filters.setSortBy(getSelectedSortBy());
            filters.setSortDirection(getSortDirection());
        }

        return filters;
    }
}