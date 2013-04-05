package com.jbrunton.organizr;

import android.content.res.ColorStateList;
import android.graphics.Color;

import com.bindroid.ValueConverter;

public class DisabledColorConverter extends ValueConverter {
	
	private int defaultColor;
	private int disabledColor;
	
	public DisabledColorConverter(int disabledColor, int defaultColor) {
		this.disabledColor = disabledColor;
		this.defaultColor = defaultColor;
	}

	public Object convertToSource(Object targetValue, Class<?> sourceType) {
		// this should throw an exception
	    return null;
	}

	public Object convertToTarget(Object sourceValue, Class<?> targetType) {
	    int color = (Boolean) sourceValue
	    		? this.disabledColor
	    		: this.defaultColor;

	    // it would be great to just return 'color' here, oh yes, but because this method
	    // returns type Object, the setTextColor(ColorStateList) overload takes precedence
	    // over setTextColor(int), so we just have to roll with it.
	    return new ColorStateList(
	            new int[][] {
	                    new int[0]
	                }, new int[] {
	                    color
	                }
	            );
	}
}
