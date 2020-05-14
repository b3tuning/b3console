package com.b3tuning.b3console.utils;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinumeris.crashfx.CrashWindow;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.WritableDoubleValue;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.extern.slf4j.XSlf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static javafx.scene.CacheHint.SPEED;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class GuiUtils {


	public static final int      UI_ANIMATION_TIME_MSEC = 2000;
	public static final Duration UI_ANIMATION_TIME      = Duration.millis(UI_ANIMATION_TIME_MSEC);

	public static Animation fadeIn(Node ui) {
		return fadeIn(ui, 0, 1.0);
	}

	public static Animation fadeIn(Node ui, int delayMillis, double targetValue) {
		ui.setCache(true);
		ui.setCacheHint(SPEED);
		FadeTransition ft = new FadeTransition(Duration.millis(UI_ANIMATION_TIME_MSEC), ui);
		ft.setFromValue(ui.getOpacity());
		ft.setToValue(targetValue);
		ft.setOnFinished(ev -> ui.setCache(false));
		ft.setDelay(Duration.millis(delayMillis));
		ft.play();
		return ft;
	}

	public static Animation fadeOut(Node ui) {
		ui.setCache(true);
		ui.setCacheHint(SPEED);
		FadeTransition ft = new FadeTransition(Duration.millis(UI_ANIMATION_TIME_MSEC), ui);
		ft.setFromValue(ui.getOpacity());
		ft.setToValue(0.0);
		ft.setOnFinished(ev -> ui.setCache(false));
		ft.play();
		return ft;
	}

	public static Animation fadeOutAndRemove(Pane parentPane, Node... nodes) {
		Animation animation = fadeOut(nodes[0]);
		animation.setOnFinished(actionEvent -> parentPane.getChildren().removeAll(nodes));
		for (int i = 1; i < nodes.length; i++) {
			fadeOut(nodes[i]);
		}
		return animation;
	}

	public static Animation fadeOutAndRemove(Duration duration, Pane parentPane, Node... nodes) {
		nodes[0].setCache(true);
		FadeTransition ft = new FadeTransition(duration, nodes[0]);
		ft.setFromValue(nodes[0].getOpacity());
		ft.setToValue(0.0);
		ft.setOnFinished(ev -> parentPane.getChildren().removeAll(nodes));
		ft.play();
		return ft;
	}

	public static void blurOut(Node node) {
		GaussianBlur blur = new GaussianBlur(0.0);
		node.setEffect(blur);
		Timeline timeline = new Timeline();
		KeyValue kv       = new KeyValue(blur.radiusProperty(), 10.0);
		KeyFrame kf       = new KeyFrame(UI_ANIMATION_TIME, kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public static void blurIn(Node node, Duration duration) {
		GaussianBlur blur = (GaussianBlur) node.getEffect();
		if (blur == null) {
			log.error("BUG: Attempted to cancel non-existent blur.");
			return;
		}
		Timeline timeline = new Timeline();
		KeyValue kv       = new KeyValue(blur.radiusProperty(), 0.0);
		KeyFrame kf       = new KeyFrame(duration, kv);
		timeline.getKeyFrames().add(kf);
		timeline.setOnFinished(actionEvent -> node.setEffect(null));
		timeline.play();
	}

	public static ScaleTransition zoomIn(Node node) {
		return zoomIn(node, 0);
	}

	public static ScaleTransition zoomIn(Node node, int delayMillis) {
		return scaleFromTo(node, 0.95, 1.0, delayMillis);
	}

	public static ScaleTransition explodeOut(Node node) {
		return scaleFromTo(node, 1.0, 1.05, 0);
	}

	public static ScaleTransition scaleFromTo(Node node, double from, double to, int delayMillis) {
		//node.setCache(true);
		//node.setCacheHint(CacheHint.SPEED);
		ScaleTransition scale = new ScaleTransition(Duration.millis(UI_ANIMATION_TIME_MSEC), node);
		scale.setFromX(from);
		scale.setFromY(from);
		scale.setToX(to);
		scale.setToY(to);
		scale.setDelay(Duration.millis(delayMillis));
		//scale.setOnFinished(ev -> node.setCache(false));
		scale.play();
		return scale;
	}

	public static void dropShadowOn(Node node) {
		DropShadow dropShadow = node.getEffect() != null
		                        ? (DropShadow) node.getEffect()
		                        : new DropShadow(BlurType.THREE_PASS_BOX, Color.BLACK, 0.0, 0.0, 0, 0);
		node.setEffect(dropShadow);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(UI_ANIMATION_TIME_MSEC / 3),
				             new KeyValue(dropShadow.radiusProperty(), 3.0))
		                           );
		timeline.play();
	}

	public static void dropShadowOff(Node node) {
		DropShadow dropShadow = (DropShadow) node.getEffect();
		Timeline   timeline   = new Timeline();
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(UI_ANIMATION_TIME_MSEC / 3),
				             new KeyValue(dropShadow.radiusProperty(), 0.0))
		                           );
		timeline.setOnFinished((ev) -> node.setEffect(null));
		timeline.play();
	}

	public static void brightnessAdjust(Node node, double adjustment) {
		node.setCache(true);
		node.setCacheHint(SPEED);
		ColorAdjust adjust = new ColorAdjust();
		adjust.setBrightness(0.0);
		node.setEffect(adjust);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(UI_ANIMATION_TIME_MSEC * 0.7),
		                                              new KeyValue(adjust.brightnessProperty(), adjustment)));
		timeline.play();
	}

	public static void brightnessUnadjust(Node node) {
		ColorAdjust effect = (ColorAdjust) node.getEffect();
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(UI_ANIMATION_TIME_MSEC * 0.7),
		                                              new KeyValue(effect.brightnessProperty(), 0.0)));
		timeline.setOnFinished(ev -> node.setCache(false));
		timeline.play();
	}

	public static void checkGuiThread() {
		if (!Platform.isFxApplicationThread()) {
			// Don't just throw directly here to avoid missing the problem when buggy code swallows the exceptions.
			IllegalStateException ex = new IllegalStateException();
			log.error("Threading violation: not on FX UI thread", ex);
			CrashWindow.open(ex);
			throw ex;
		}
	}

	public static BooleanBinding conjunction(List<BooleanProperty> list) {
		BooleanBinding accumulator = new SimpleBooleanProperty(true).and(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			accumulator = accumulator.and(list.get(i));
		}
		return accumulator;
	}

	public static void runOnGuiThreadAfter(long millis, Runnable runnable) {
		new Thread(() -> {
			Uninterruptibles.sleepUninterruptibly(millis, TimeUnit.MILLISECONDS);
			Platform.runLater(runnable);
		}).start();
	}

	public static void runAfterFrame(Runnable runnable) {
		AnimationTimer frameWaiter = new AnimationTimer() {
			private int frames;

			@Override
			public void handle(long l) {
				frames++;
				if (frames > 2) {
					stop();
					runnable.run();
				}
			}
		};
		frameWaiter.start();
	}

	public static void roundCorners(ImageView view, double amount) {
		// This should be easier to do just with CSS.
		Rectangle clipRect = new Rectangle(view.getFitWidth(), view.getFitHeight());
		clipRect.setArcWidth(amount);
		clipRect.setArcHeight(amount);
		view.setClip(clipRect);
	}

	public static class AnimatedBindInfo {
		public Timeline      timeline;
		public NumberBinding bindFrom;
		public Runnable      onAnimFinish;
	}

	public static AnimatedBindInfo animatedBind(Node node, WritableDoubleValue bindTo, NumberBinding bindFrom) {
		return animatedBind(node, bindTo, bindFrom, null);
	}

	public static AnimatedBindInfo animatedBind(Node node, WritableDoubleValue bindTo, NumberBinding bindFrom,
	                                            Interpolator interpolator) {
		bindTo.set(bindFrom.doubleValue());   // Initialise.
		bindFrom.addListener((o, prev, cur) -> {
			AnimatedBindInfo info = (AnimatedBindInfo) node.getUserData();
			if (info.timeline != null) {
				info.timeline.stop();
			}
			info.timeline = new Timeline(new KeyFrame(UI_ANIMATION_TIME,
			                                          interpolator != null
			                                          ? new KeyValue(bindTo, cur, interpolator)
			                                          : new KeyValue(bindTo, cur)));
			info.timeline.setOnFinished(ev -> {
				((AnimatedBindInfo) node.getUserData()).timeline = null;
				if (info.onAnimFinish != null) {
					info.onAnimFinish.run();
				}
			});
			info.timeline.play();
		});
		// We must pin bindFrom into the object graph, otherwise something like:
		//    animatedBind(node, node.opacityProperty(), when(a).then(1).otherwise(2))
		// will mysteriously stop working when the result of when() gets garbage collected and the listener with it.
		AnimatedBindInfo info = new AnimatedBindInfo();
		info.bindFrom = bindFrom;
		node.setUserData(info);
		return info;
	}

//	public static boolean isSoftwarePipeline() {
//		return GraphicsPipeline.getPipeline() instanceof SWPipeline;
//	}
}
