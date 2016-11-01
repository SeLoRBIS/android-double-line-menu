package com.gwen.android_double_line_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class DrawerListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    Context mContext;

    ArrayList<NavItem> mNavItems = new ArrayList();
    ArrayList<NavTitle> mNavTitles = new ArrayList();

    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public DrawerListAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final NavItem item) {
        mNavItems.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final NavItem item) {
        mNavItems.add(item);
        sectionHeader.add(mNavItems.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int rowType = getItemViewType(position);

        if (convertView == null) {
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.drawer_item, null);
                    TextView titleView = (TextView) convertView.findViewById(R.id.title);
                    TextView subtitleView = (TextView) convertView.findViewById(R.id.subTitle);
                    ImageView iconView = (ImageView) convertView.findViewById(R.id.icon);

                    titleView.setText( mNavItems.get(position).getmTitle());
                    subtitleView.setText( mNavItems.get(position).getmSubtitle() );
                    iconView.setImageResource(mNavItems.get(position).getmIcon());
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.drawer_item_title, null);
                    TextView title = (TextView) convertView.findViewById(R.id.title);
                    title.setText(mNavItems.get(position).getmTitle());
                    break;
            }
        }

        return convertView;
    }
}
