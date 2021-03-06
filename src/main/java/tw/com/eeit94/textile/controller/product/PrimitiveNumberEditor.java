package tw.com.eeit94.textile.controller.product;

import java.text.NumberFormat;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

public class PrimitiveNumberEditor extends CustomNumberEditor {

	public PrimitiveNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty)
			throws IllegalArgumentException {
		this(numberClass, null, allowEmpty);
	}

	public PrimitiveNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty)
			throws IllegalArgumentException {
		super(numberClass, numberFormat, allowEmpty);
		this.allowEmpty = allowEmpty;
	}

	private boolean allowEmpty;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() == 0 && allowEmpty) {
			setValue(0);
		} else {
			super.setAsText(text);
		}
	}

}
