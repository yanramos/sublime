package com.example.sublime.layout;

import java.io.InputStream;
import java.net.URL;

import com.example.sublime.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomItemView extends LinearLayout {

	public CustomItemView(Context context, PromoListItem item) {
		super(context);
		
		setId(item.getId());
		setOrientation(LinearLayout.HORIZONTAL);
		setPadding(0, 6, 0, 6);
		
		LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		Params.setMargins(6, 0, 6, 0);
		ImageView logo = new ImageView(context);
		if (!item.isStatus()){
			logo.setImageDrawable(context.getResources().getDrawable(R.drawable.icon));
		} else {
			try {
				logo.setTag(item.getIcon());
				new DownloadImagesTask().execute(logo);
				logo.setAdjustViewBounds(true);
				logo.setMaxHeight(100);
				logo.setMaxWidth(100);
			} catch (Exception e) {
					e.printStackTrace();
					logo.setImageDrawable(context.getResources().getDrawable(R.drawable.icon));
			}
		}
		addView(logo, Params);
		
		Params = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		LinearLayout PanelV = new LinearLayout(context);
		PanelV.setOrientation(LinearLayout.VERTICAL);
		PanelV.setGravity(Gravity.BOTTOM);
		
		TextView title = new TextView(context);
		title.setTextSize(16);
		title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
		title.setText(item.getTitle());
		title.setGravity(Gravity.CENTER_VERTICAL);
		PanelV.addView(title);       
 
		TextView subtitle = new TextView(context);
		subtitle.setTextSize(16);
		subtitle.setText(item.getSubtitle());
		subtitle.setGravity(Gravity.CENTER_VERTICAL);
		PanelV.addView(subtitle);    
 
		addView(PanelV, Params);
	}
}
