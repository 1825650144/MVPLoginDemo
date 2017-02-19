package com.sadink.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @describe
 * @author dongdd
 * @date 2016-2-6下午12:28:33
 */
public class ToastUtils {

	private static Toast toast;

	/**
	 * 弹出默认的短时间Toast
	 * 
	 * @param context
	 * @param msg
	 */
	public static void showToastDefault(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 弹出Toast解决Toast显示时间累积的问题
	 * 
	 * @param context
	 * @param message
	 *            Toast.LENGTH_SHORT = 2s Toast.LENGTH_LONG = 3.5s
	 */
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		}else{
			toast.setText(message);
		}
		toast.show();
	}
}
