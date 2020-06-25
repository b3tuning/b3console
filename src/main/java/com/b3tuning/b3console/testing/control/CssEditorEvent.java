package com.b3tuning.b3console.testing.control;

import javafx.event.Event;
import javafx.event.EventType;

import javax.annotation.Nullable;
import java.util.Optional;

public class CssEditorEvent<T> extends Event {

  public static final EventType<CssEditorEvent<?>> ANY = new EventType<>(Event.ANY, "css-editor");

  private final Optional<CssEditor> editor;
  private final Optional<T> data;

  public CssEditorEvent(CssEditor editor, EventType<? extends CssEditorEvent<T>> eventType, @Nullable T data) {
    super(editor, editor, eventType);
    this.editor = Optional.ofNullable(editor);
    this.data = Optional.ofNullable(data);
  }

  public CssEditorEvent(CssEditor editor, EventType<? extends CssEditorEvent<T>> eventType) {
    this(editor, eventType, null);
  }

  public CssEditorEvent(EventType<? extends CssEditorEvent<T>> eventType) {
    this(null, eventType);
  }

  public Optional<CssEditor> getEditor() {
    return editor;
  }

  public Optional<T> getData() {
    return data;
  }
}
