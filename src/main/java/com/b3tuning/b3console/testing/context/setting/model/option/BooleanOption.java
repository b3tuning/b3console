package com.b3tuning.b3console.testing.context.setting.model.option;

import javafx.beans.property.BooleanProperty;
import xdean.jfxex.bean.BeanConvertUtil;
import xdean.jfxex.util.StringConverters;

import static xdean.jex.util.cache.CacheUtil.cache;

public class BooleanOption extends SimpleOption<Boolean> {
  public BooleanOption(String key, boolean defaultValue) {
    super(key, defaultValue, StringConverters.create(Boolean::valueOf));
  }

  public BooleanProperty booleanProperty() {
    return cache(this, () -> BeanConvertUtil.toBoolean(valueProperty()));
  }
}