package com.example.pathbutton;


import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private View mUgcView;
	private RelativeLayout mUgcLayout;
	private ImageView mUgc;
	private ImageView mUgcBg;
	private ImageView mUgcVoice;
	private ImageView mUgcPhoto;
	private ImageView mUgcRecord;
	private ImageView mUgcLbs;
	/**
	 * �жϵ�ǰ��path�˵��Ƿ��Ѿ���ʾ
	 */
	private boolean mUgcIsShowing = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		mUgcView = (View) findViewById(R.id.home_ugc);
		mUgcLayout = (RelativeLayout) findViewById(R.id.ugc_layout);
		mUgc = (ImageView) findViewById(R.id.ugc);
		mUgcBg = (ImageView) findViewById(R.id.ugc_bg);
		mUgcVoice = (ImageView) findViewById(R.id.ugc_voice);
		mUgcPhoto = (ImageView) findViewById(R.id.ugc_photo);
		mUgcRecord = (ImageView) findViewById(R.id.ugc_record);
		mUgcLbs = (ImageView) findViewById(R.id.ugc_lbs);
		// Path����
		mUgcView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// �ж��Ƿ��Ѿ���ʾ,��ʾ��رղ�����
				if (mUgcIsShowing) {
					mUgcIsShowing = false;
					UgcAnimations.startCloseAnimation(mUgcLayout, mUgcBg, mUgc,
							500);
					return true;
				}
				return false;
			}
		});
		// Path����
		mUgc.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// �ж��Ƿ���ʾ,�Ѿ���ʾ������,��������ʾ
				mUgcIsShowing = !mUgcIsShowing;
				if (mUgcIsShowing) {
					UgcAnimations.startOpenAnimation(mUgcLayout, mUgcBg, mUgc,
							500);
				} else {
					UgcAnimations.startCloseAnimation(mUgcLayout, mUgcBg, mUgc,
							500);
				}
			}
		});
		// Path ������ť����
		mUgcVoice.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Animation anim = UgcAnimations.clickAnimation(500);
				anim.setAnimationListener(new AnimationListener() {

					public void onAnimationStart(Animation animation) {

					}

					public void onAnimationRepeat(Animation animation) {

					}

					public void onAnimationEnd(Animation animation) {
//						mContext.startActivity(new Intent(mContext,
//								VoiceActivity.class));
						Toast.makeText(MainActivity.this, "������ť����", Toast.LENGTH_LONG).show();
						closeUgc();
					}
				});
				mUgcVoice.startAnimation(anim);
			}
		});
		// Path ���հ�ť����
		mUgcPhoto.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Animation anim = UgcAnimations.clickAnimation(500);
				anim.setAnimationListener(new AnimationListener() {

					public void onAnimationStart(Animation animation) {

					}

					public void onAnimationRepeat(Animation animation) {

					}

					public void onAnimationEnd(Animation animation) {
						Toast.makeText(MainActivity.this, "���հ�ť����", Toast.LENGTH_LONG).show();
						
						closeUgc();
					}
				});
				mUgcPhoto.startAnimation(anim);
			}
		});
		// Path ��¼��ť����
		mUgcRecord.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Animation anim = UgcAnimations.clickAnimation(500);
				anim.setAnimationListener(new AnimationListener() {

					public void onAnimationStart(Animation animation) {

					}

					public void onAnimationRepeat(Animation animation) {

					}

					public void onAnimationEnd(Animation animation) {
						Toast.makeText(MainActivity.this, "��¼��ť����", Toast.LENGTH_LONG).show();
						
						closeUgc();
					}
				});
				mUgcRecord.startAnimation(anim);
			}
		});
		// Path ǩ����ť����
		mUgcLbs.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Animation anim = UgcAnimations.clickAnimation(500);
				anim.setAnimationListener(new AnimationListener() {

					public void onAnimationStart(Animation animation) {

					}

					public void onAnimationRepeat(Animation animation) {

					}

					public void onAnimationEnd(Animation animation) {
						Toast.makeText(MainActivity.this, " ǩ����ť����", Toast.LENGTH_LONG).show();
						
						closeUgc();
					}
				});
				mUgcLbs.startAnimation(anim);
			}
		});
	}

	
	/**
	 * �ر�Path�˵�
	 */
	public void closeUgc() {
		mUgcIsShowing = false;
		UgcAnimations.startCloseAnimation(mUgcLayout, mUgcBg, mUgc, 500);
	}

}
