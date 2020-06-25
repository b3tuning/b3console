package com.b3tuning.b3console.testing.context.setting.model.option;

import javafx.beans.property.StringProperty;
import xdean.jfxex.bean.BeanConvertUtil;
import xdean.jfxex.util.StringConverters;

import java.util.function.UnaryOperator;

public class StringOption extends SimpleOption<String> {

  public StringOption(String key, String defaultValue) {
    super(key, defaultValue, StringConverters.create(UnaryOperator.identity()));
  }

  public StringProperty stringProperty() {
    return BeanConvertUtil.toString(valueProperty());
  }
}
