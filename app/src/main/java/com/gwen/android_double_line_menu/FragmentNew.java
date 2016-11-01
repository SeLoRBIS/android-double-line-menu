package com.gwen.android_double_line_menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class FragmentNew extends Fragment {

    private static final String TAG = "FragmentNew";
    private RecyclerView rv;

    /**
     * Constructeur
     * @return FragmentShowcase
     */
    public static FragmentNew newInstance() {

        FragmentNew fragmentNew = new FragmentNew();
        Bundle args = new Bundle();
        fragmentNew.setArguments(args);

        return fragmentNew;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setToolbar(R.string.fragment_title);
        setHasOptionsMenu(true);

        rv = (RecyclerView) inflater.inflate(R.layout.list_recycler, container, false);

        setupRecyclerView(rv);

        return rv;
    }

     private void setupRecyclerView(RecyclerView recyclerView) {
         recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
     }

    private void setToolbar(@StringRes int fragment_title){
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(fragment_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

}
