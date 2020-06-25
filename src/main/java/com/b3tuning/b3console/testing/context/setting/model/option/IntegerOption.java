package com.b3tuning.b3console.testing.context.setting.model.option;

import javafx.beans.property.IntegerProperty;
import xdean.jfxex.util.StringConverters;

import static xdean.jex.util.cache.CacheUtil.cache;
import static xdean.jfxex.bean.BeanConvertUtil.toInteger;

public class IntegerOption extends RangeOption<Integer> {

  public IntegerOption(String key, int defaultValue) {
    super(key, defaultValue, StringConverters.forInteger(defaultValue));
  }

  public IntegerProperty intProperty() {
    return cache(this, () -> toInteger(valueProperty()));
  }
}