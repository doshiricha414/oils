package com.addit.drjainsoils.adapters.pairs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class TwoLineTextPair extends SingleTextPair {
    private String obj2;

    public TwoLineTextPair(@LayoutRes int LayoutResGroup, @LayoutRes int LayoutResChild, String id, String obj1, String obj2) {
        super(LayoutResGroup, LayoutResChild, id, obj1);
        this.obj2 = obj2;
    }

    public TwoLineTextPair(String id, String obj1, String obj2) {
        super(android.R.layout.simple_list_item_2, android.R.layout.simple_list_item_2, id, obj1);
        this.obj2 = obj2;
    }

    public String getObj2() {
        return obj2;
    }

    /*@Override
    public View getView(Context context, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);
        }
        return convertView;
    }*/

    @Override
    public void bindView(Context context, int position, @LayoutRes int viewType, View convertView) throws Exception {
        TwoLineListItem twoLineListItem = (TwoLineListItem) convertView;
        TextView textView1 = twoLineListItem.getText1();
        TextView textView2 = twoLineListItem.getText2();

        String obj1 = getObj1();
        String obj2 = getObj2();

        if (obj1 != null)
            textView1.setText(Html.fromHtml(obj1));
        if (obj2 != null)
            textView2.setText(Html.fromHtml(obj2));
    }
}
