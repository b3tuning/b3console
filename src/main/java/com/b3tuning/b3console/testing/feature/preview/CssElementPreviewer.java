package com.b3tuning.b3console.testing.feature.preview;

import io.reactivex.Maybe;
import javafx.scene.canvas.GraphicsContext;
import xdean.css.editor.model.CssContext;

public interface CssElementPreviewer<T> {
  Maybe<T> parse(CssContext context, String text);

  void preview(GraphicsContext gc, T value, double width, double height);
}
