package com.example.pathbutton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Path动画类
 * 
 * @author rendongwei
 * 
 */
public class UgcAnimations {
	private static int xOffset = 15;
	private static int yOffset = -13;

	public static void initOffset(Context context) {
		xOffset = (int) (15 * context.getResources().getDisplayMetrics().density);
		yOffset = -(int) (13 * context.getResources().getDisplayMetrics().density);
	}
	
	/**
	 * 旋转动画效果
	 * @param fromDegrees
	 * @param toDegrees
	 * @param durationMillis
	 * @return
	 */
	public static Animation getRotateAnimation(float fromDegrees,
			float toDegrees, long durationMillis) {
		RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(durationMillis);
		rotate.setFillAfter(true);
		return rotate;
	}

	/**
	 * 透明度动画效果
	 * @param fromAlpha
	 * @param toAlpha
	 * @param durationMillis
	 * @return
	 */
	public static Animation getAlphaAnimation(float fromAlpha, float toAlpha,
			long durationMillis) {
		AlphaAnimation alpha = new AlphaAnimation(fromAlpha, toAlpha);
		alpha.setDuration(durationMillis);
		alpha.setFillAfter(true);//动画结束后停止不回到原位置
		return alpha;
	}

	/**
	 * 缩放动画效果
	 * @param durationMillis
	 * @return
	 */
	public static Animation getScaleAnimation(long durationMillis) {
		ScaleAnimation scale = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scale.setDuration(durationMillis);
		return scale;
	}

	/**
	 * 位移动画效果
	 * @param fromXDelta
	 * @param toXDelta
	 * @param fromYDelta
	 * @param toYDelta
	 * @param durationMillis
	 * @return
	 */
	public static Animation getTranslateAnimation(float fromXDelta,
			float toXDelta, float fromYDelta, float toYDelta,
			long durationMillis) {
		TranslateAnimation translate = new TranslateAnimation(fromXDelta,
				toXDelta, fromYDelta, toYDelta);
		translate.setDuration(durationMillis);
		translate.setFillAfter(true);
		return translate;
	}

	/**
	 * 打开菜单
	 * @param relativeLayout  小菜单布局
	 * @param background   线
	 * @param menu   点击的主按钮
	 * @param durationMillis  整个动画完成时间
	 */
	public static void startOpenAnimation(RelativeLayout relativeLayout,
			ImageView background, ImageView menu, long durationMillis) {
		background.setVisibility(View.VISIBLE);
		relativeLayout.setVisibility(View.VISIBLE);
		background.startAnimation(getAlphaAnimation(0f, 1f, durationMillis));//让线的透明度从0变为完全可见
		menu.startAnimation(getRotateAnimation(0, 90, durationMillis));//让点击的按钮旋转90度
		for (int i = 0; i < relativeLayout.getChildCount(); i++) {//relativeLayout的子菜单添加旋转效果
			ImageView imageView = (ImageView) relativeLayout.getChildAt(i);//拿到对应的子菜单
			imageView.setVisibility(View.VISIBLE);//设置为可见
			MarginLayoutParams params = (MarginLayoutParams) imageView
					.getLayoutParams();
			AnimationSet set = new AnimationSet(true);
			set.addAnimation(getRotateAnimation(-270, 0, durationMillis));
			set.addAnimation(getAlphaAnimation(0.5f, 1.0f, durationMillis));
			set.addAnimation(getTranslateAnimation(
					-params.leftMargin + xOffset, 0f, params.bottomMargin
							+ yOffset, 0f, durationMillis));
			set.setFillAfter(true);
			set.setDuration(durationMillis);
			//设置启动时间
			set.setStartOffset((i * 100)
					/ (-1 + relativeLayout.getChildCount()));
			set.setInterpolator(new OvershootInterpolator(1f));
			imageView.startAnimation(set);
		}
	}

	public static void startCloseAnimation(final RelativeLayout relativeLayout,
			final ImageView background, ImageView menu, long durationMillis) {
		background.startAnimation(getAlphaAnimation(1f, 0f, durationMillis));
		menu.startAnimation(getRotateAnimation(90, 0, durationMillis));
		for (int i = 0; i < relativeLayout.getChildCount(); i++) {
			final ImageView imageView = (ImageView) relativeLayout
					.getChildAt(i);
			MarginLayoutParams params = (MarginLayoutParams) imageView
					.getLayoutParams();
			AnimationSet set = new AnimationSet(true);
			set.addAnimation(getRotateAnimation(0, -270, durationMillis));
			set.addAnimation(getAlphaAnimation(1.0f, 0.5f, durationMillis));
			set.addAnimation(getTranslateAnimation(0f, -params.leftMargin
					+ xOffset, 0f, params.bottomMargin + yOffset,
					durationMillis));
			set.setFillAfter(true);
			set.setDuration(durationMillis);
			set.setStartOffset(((relativeLayout.getChildCount() - i) * 100)
					/ (-1 + relativeLayout.getChildCount()));
			set.setInterpolator(new AnticipateInterpolator(1f));
			set.setAnimationListener(new Animation.AnimationListener() {
				public void onAnimationStart(Animation arg0) {
				}

				public void onAnimationRepeat(Animation arg0) {
				}

				public void onAnimationEnd(Animation arg0) {
					relativeLayout.setVisibility(View.GONE);
					background.setVisibility(View.GONE);
				}
			});
			imageView.startAnimation(set);
		}
	}

	/**
	 * 点击动画
	 * @param durationMillis
	 * @return
	 */
	public static Animation clickAnimation(long durationMillis) {
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(getAlphaAnimation(1.0f, 0.3f, durationMillis));
		set.addAnimation(getScaleAnimation(durationMillis));
		set.setDuration(durationMillis);
		return set;
	}
}
