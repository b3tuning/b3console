package com.b3tuning.b3console.testing.feature.highlight;

import org.fxmisc.richtext.model.StyleSpans;

import java.util.Collection;

public interface CssHighLight {

  public interface Element {
    String CSS = "css";
    String SELECTOR = "selector";
    String SELECTOR_ID = "selector-id";
    String SELECTOR_CLASS = "selector-class";
    String SELECTOR_STATE = "selector-state";
    String SELECTOR_JAVACLASS = "selector-java-class";
    String ENTRIES = "entries";
    String KEY = "key";
    String VALUE = "value";
    String MUTI_COMMENT = "muticomment";
    String ENTRY = "entry";
    String LEFT_BRACE = "leftbrace";
    String RIGHT_BRACE = "rightbrace";
    String COLON = "colon";
    String SEMICOLON = "semicolon";
  }

  StyleSpans<Collection<String>> compute(String text);
}
