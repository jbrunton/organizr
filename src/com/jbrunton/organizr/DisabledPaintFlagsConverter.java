package com.jbrunton.organizr;

import android.graphics.Paint;
import android.widget.TextView;

import com.bindroid.ValueConverter;

public class DisabledPaintFlagsConverter extends ValueConverter {
	
	private TextView target;
	
	public DisabledPaintFlagsConverter(TextView target) {
		this.target = target;
	}

	public Object convertToSource(Object targetValue, Class<?> sourceType) {
		// this should throw an exception
	    return null;
	}

	public Object convertToTarget(Object sourceValue, Class<?> targetType) {
	    int flags = (Boolean) sourceValue
	    		? this.target.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
	    		: this.target.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG;
	    return flags;
	}
	
}
