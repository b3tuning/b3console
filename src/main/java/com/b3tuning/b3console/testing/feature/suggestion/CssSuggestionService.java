package com.b3tuning.b3console.testing.feature.suggestion;

import javafx.scene.control.IndexRange;
import xdean.css.editor.model.CssContext;

import java.util.Collection;

public interface CssSuggestionService {
  Collection<String> getSuggestion(String text, int caretPos, CssContext context);

  IndexRange getReplaceRange(String text, int caretPos, CssContext context);
}
