package com.example.sublime.layout;

import java.util.List;

import com.example.sublime.LoadingActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomItemAdapter extends BaseAdapter implements OnClickListener {

	private class OnItemClickListener implements OnClickListener{           
		private int mPosition;
		OnItemClickListener(int position){
			mPosition = position;
		}
		@Override
		public void onClick(View arg0) {
            PromoListItem item = items.get(mPosition);
            if (item.isStatus()){
            	String url = item.getAddress();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                	   url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            } else {
            	Intent intent = new Intent(context, LoadingActivity.class);
            	intent.putExtra("updating", true);
            	intent.putExtra("key", item.getKey());
            	context.startActivity(intent);
            }
		}               
	}

	private Context context;
	private List<PromoListItem> items;
	
	public CustomItemAdapter(Context context, List<PromoListItem> items){
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PromoListItem item = items.get(position);
		View v = new CustomItemView(this.context, item);
		if(!item.isStatus()){
			v.setBackgroundColor(Color.LTGRAY);
		} else {
			v.setBackgroundColor((position % 2) == 1 ? Color.CYAN : Color.WHITE);
		}
		v.setOnClickListener(new OnItemClickListener(position));
		return v;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<PromoListItem> getItems() {
		return items;
	}

	public void setItems(List<PromoListItem> items) {
		this.items = items;
	}

	@Override
	public void onClick(View arg0) {
		Log.v("ddd", "Row button clicked");
	}

}
