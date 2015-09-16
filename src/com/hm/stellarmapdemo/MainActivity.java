package com.hm.stellarmapdemo;

import java.util.Random;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.stellarmapdemo.lib.randomlayout.StellarMap;
import com.hm.stellarmapdemo.lib.randomlayout.StellarMap.Adapter;
import com.hm.stellarmapdemo.utils.ColorUtil;

public class MainActivity extends Activity {

	private StellarMap stellarMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		stellarMap = new StellarMap(this);
		int dimension = (int) getResources().getDimension(R.dimen.stellar_map_padding);
		stellarMap.setInnerPadding(dimension, dimension, dimension, dimension);
		stellarMap.setAdapter(new StellarMapAdapter());
		stellarMap.setGroup(0, true);
		stellarMap.setRegularity(15, 15);
		setContentView(stellarMap);
	}

	class StellarMapAdapter implements Adapter {

		@Override
		public int getGroupCount() {
			return 3;
		}

		@Override
		public int getCount(int group) {
			return 11;
		}

		@Override
		public View getView(int group, int position, View convertView) {
			final TextView textView = new TextView(MainActivity.this);
			//1.����group��group�ܵ�position�������Ӧ��list�е�λ��
			int listPosition = group*getCount(group) + position;
			textView.setText(Constant.ITEM[listPosition]);
			textView.setTypeface(Typeface.DEFAULT_BOLD);//���ô���
			//2.������������С
			Random random = new Random();
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, random.nextInt(8)+14);//14-21
			//3.��ɫ�����������ɫ
			textView.setTextColor(ColorUtil.randomColor());
			
			//���õ���¼�
			textView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), textView.getText().toString(), 0);
				}
			});
			
			return textView;
		}

		@Override 
		public int getNextGroupOnPan(int group, float degree) {
			return 0;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			return (group+1)%getGroupCount();
		}
		
	}
}
