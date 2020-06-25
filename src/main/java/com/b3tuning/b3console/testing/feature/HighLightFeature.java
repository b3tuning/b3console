package com.b3tuning.b3console.testing.feature;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import org.springframework.stereotype.Service;
import xdean.css.editor.control.CssEditor;
import xdean.css.editor.feature.highlight.CssHighLight;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@Service
public class HighLightFeature implements CssEditorFeature {

  @Inject
  CssHighLight cssHighLight;

  @Override
  public void bind(CssEditor cssEditor) {
    JavaFxObservable.valuesOf(cssEditor.textProperty())
        .debounce(300, TimeUnit.MILLISECONDS)
        .observeOn(JavaFxScheduler.platform())
        .subscribe(e -> cssEditor.setStyleSpans(0, cssHighLight.compute(e)));
  }
}
