package com.b3tuning.b3console;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import com.b3tuning.b3console.platform.ApplicationComponents;
import com.b3tuning.b3console.platform.DaggerMvvmfxBridge;
import com.b3tuning.b3console.platform.PlatformInjector;
import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.utils.GuiUtils;
import com.b3tuning.b3console.view.root.RootView;
import com.b3tuning.b3console.view.root.RootViewModel;
import com.sun.prism.GraphicsPipeline;
import com.sun.prism.sw.SWPipeline;
import com.vinumeris.crashfx.CrashFX;
import com.vinumeris.crashfx.CrashWindow;
import com.vinumeris.updatefx.AppDirectory;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.scenicview.ScenicView;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.ServiceLoader;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class App extends Application {

	public static final String DEFAULT_CSS = "app.css";

	private static final String APP_NAME = "B3Tuning Module ConfigTool";

	private static final double DEFAULT_FONT_SIZE = 12;

	@Getter
	private static ApplicationComponents applicationComponents;

	private AppProperties   props;
	private UserPreferences prefs;
	private StackPane       uiStack;
	public  Scene           scene;

	private boolean         initialUILoad = true;
	private BooleanProperty splashAnimationPlaying;

	@Override
	public void start(Stage stage) throws Exception {
		log.entry();
		// For some reason the JavaFX launch process results in us losing the
		// thread context class loader: reset it. This is fixed in 8u40+.
		Thread.currentThread().setContextClassLoader(App.class.getClassLoader());

		AppDirectory.initAppDir(APP_NAME);

		initDependencies();
		props = applicationComponents.provideAppProperties();

		setupLogging();

		log.info("****************************************************************************");
		log.info("******    {} version {}", APP_NAME, props.getVersion());
		log.info("******    App dir: '{}'.", AppDirectory.dir());
		log.info("******    User data dir: '{}'.", AppDirectory.getUserDataDir(APP_NAME));
		log.info("******    Running on: {}, with Java {}", System.getProperty("os.name"),
		         System.getProperty("java.version"));
		log.info("****************************************************************************");

		// configure crashfx - see https://github.com/vinumeris/crashfx
		CrashFX.setup(props.getUserAgent(), AppDirectory.getUserDataDir(APP_NAME).resolve("crashfx"),
		              props.getLogUrl());

		// ComboBox hangs on Windows 10 when clicked while
		// not in focus
		System.setProperty("glass.accessible.force", "false");

		// Workaround for patch entity cloning so converters can handle nulls
		// needed to not throw exceptions on null
		// Date/BigDecimal/Boolean/Integer/Long when using
		// BeanUtils.copyProperties() for cloning
		ConvertUtils.register(new DateConverter(null), Date.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new BooleanConverter(null), Boolean.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);

		// We have to load prefs before splash because we want to avoid
		// resizing/moving the window
		// after we display it.
		prefs = applicationComponents.provideUserPreferences();

		stage.setTitle(APP_NAME);

		// when the primary stage has been closed, make sure we terminate the
		// app
		stage.setOnCloseRequest(t -> {
			Platform.exit();
			System.exit(0);
		});

		initGui(stage);
		countingEights(2);

//		if (prefs.isFirstRun() && isUpdateUrlProvided()) {
//			// fresh install, therefore download the latest
//			performInitialAutoUpdate();
//		} else {
		stage.show();
		startAppLoad();
//		}
	}

	public static boolean isSoftwarePipeline() {
		return GraphicsPipeline.getPipeline() instanceof SWPipeline;
	}

	private void initGui(Stage primaryStage) {
		log.entry();
		if (isSoftwarePipeline()) {
			log.warn("Prism is using software rendering");
		} else {
			log.warn("Prism is using HARDWARE rendering");
		}

		// set the custom fonts
		Font.loadFont(getClass().getResourceAsStream("/fonts/fontawesome-webfont.ttf"), 20);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Raleway-Regular.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Raleway-Bold.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Raleway-Light.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Raleway-Thin.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Bold.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Light.ttf"), DEFAULT_FONT_SIZE);
		Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Thin.ttf"), DEFAULT_FONT_SIZE);

		// Create the scene with a StackPane so we can overlay things on top of
		// the main UI.
		splashAnimationPlaying = new SimpleBooleanProperty(true);
		final Node loadingUI = createLoadingUI();
		uiStack = new StackPane(loadingUI);

		scene = new Scene(uiStack, 1200, 800);

		// used for debugging
		scene.getAccelerators().put(KeyCombination.valueOf("Ctrl+Shift+D"),
		                            () -> Platform.runLater(() -> ScenicView.show(scene)));

		final String uri = getClass().getResource(DEFAULT_CSS).toExternalForm();
		scene.getStylesheets().add(uri);
		primaryStage.setScene(scene);

	}

	private Node createLoadingUI() {
		log.entry();
		Image     logo  = new Image(getClass().getResource("turbo.jpg").toExternalForm());
		ImageView image = new ImageView(logo);
		VBox      vBox  = new VBox(image);
		vBox.setPrefWidth(Double.MAX_VALUE);
		vBox.setPrefHeight(Double.MAX_VALUE);
		vBox.setAlignment(Pos.CENTER);
		vBox.setFillWidth(true);
		vBox.setSpacing(10.0);
		StackPane pane = new StackPane(vBox);
		pane.setPadding(new Insets(20));
		pane.setStyle("-fx-background-color: white");

		animateSplash(image);

		return pane;
	}

	private void animateSplash(Node splashScreen) {
		log.entry();
		try {
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), splashScreen);
			fadeIn.setFromValue(0.0f);
			fadeIn.setToValue(1.0f);
			fadeIn.setInterpolator(Interpolator.EASE_IN);

			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished((e) -> splashAnimationPlaying.set(false));

			ScaleTransition scaleOut = new ScaleTransition(Duration.seconds(2.0), splashScreen);
			scaleOut.setByX(10.0);
			scaleOut.setByY(10.0);
			scaleOut.setInterpolator(Interpolator.EASE_IN);

			FadeTransition fadeOut = new FadeTransition(Duration.seconds(2.0), splashScreen);
			fadeOut.setFromValue(1.0f);
			fadeOut.setToValue(0.0f);

			ParallelTransition explode = new ParallelTransition(splashScreen, scaleOut, fadeOut);

			SequentialTransition sequentialTransition = new SequentialTransition();
			sequentialTransition.getChildren().addAll(fadeIn, pause, explode);
			sequentialTransition.setOnFinished((e) -> splashAnimationPlaying.set(false));

			sequentialTransition.play();

		}
		catch (Throwable e) {
			log.error("Failed to animate splash screen: ", e);
			CrashWindow.open(e);
		}
	}

	private void initDependencies() {
		log.entry();

		// set up dagger dependency
		ServiceLoader<PlatformInjector> injectors = ServiceLoader.load(PlatformInjector.class);
		log.trace("injectors hasNext(): {}", injectors.iterator().hasNext());
		Iterator<PlatformInjector> iter  = injectors.iterator();
		int                        count = 0;
		while (iter.hasNext()) {
			PlatformInjector injector = iter.next();
			log.trace("Found module: {}", injector);
			log.trace("Creating injector");
			applicationComponents = injector.create();
			log.trace("Injector has been created!");
			count++;
		}

		if (count != 1) {
			throw new IllegalStateException(
					String.format("Expecting just 1 PlatformInjector, but discovered %s", count));
		}

		// initialize the dagger to mvvmfx bridge so dagger can act as DI for mvvmfx
		DaggerMvvmfxBridge bridge = applicationComponents.provideDaggerMvvmfxBridge();
		bridge.setApplicationComponents(applicationComponents);
		MvvmFX.setCustomDependencyInjector(bridge::getInstance);
	}

	@Override
	public void stop() throws Exception {
		log.entry();
	}

	private void setupLogging() {
		String logDirectory = AppDirectory.getUserDataDir(APP_NAME).resolve("logs").toString();
		log.entry(logDirectory, props.getLogLevel());

		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.reset();
		loggerContext.putProperty("log.directory", logDirectory);
		loggerContext.putProperty("log.level", props.getLogLevel());
		ContextInitializer ci = new ContextInitializer(loggerContext);
		try {
			ci.autoConfig();
		}
		catch (JoranException e) {
			log.error("Unable to configure logging: {}", e.getMessage());
		}

		log.info("Logging initialized!");
	}

	private void startAppLoad() {
		log.entry();

		loadRootPane();
	}

	private void loadRootPane() {
		log.entry();
		try {
			ViewTuple<RootView, RootViewModel> viewTuple = FluentViewLoader.fxmlView(RootView.class).load();
			viewTuple.getViewModel().setApplication(this);

			final Node splashScreen = uiStack.getChildren().get(0);

			if (initialUILoad) {
				loadInitialUI(viewTuple, splashScreen);
			} else {
				uiStack.getChildren().remove(splashScreen);
			}

		}
		catch (Throwable e) {
			log.error("Failed to load UI: ", e);
			CrashWindow.open(e);
		}
	}

	private void loadInitialUI(ViewTuple<RootView, RootViewModel> viewTuple, final Node splashScreen) {
		splashAnimationPlaying.addListener((l) -> {
			if (!splashAnimationPlaying.get()) {
				uiStack.getChildren().add(0, viewTuple.getView());
				GuiUtils.fadeOutAndRemove(Duration.seconds(2), uiStack, splashScreen);
				initialUILoad = false;
			}
		});
	}

	// TODO: Fix
//	private void handleAppUpdated(int lastRunVersion) {
//		log.info("App was upgraded from build {} to {}!", lastRunVersion, props.getBuild());
//		Platform.runLater(() -> Notifications.create().title(APP_NAME)
//		                                     .text("Your B3Tuning application has automatically updated.  Click here to see a summary of what has changed.\n")
//		                                     .hideAfter(Duration.seconds(10))
//		                                     .position(Pos.BOTTOM_RIGHT)
//		                                     //TODO: add action
//		                                     //.onAction(arg -> UpdateFX.restartApp())
//		                                     .showConfirm());
//	}

	public static int starter = 818825488;
	public static int working = 81882548;
	public static int master = 0;
	public static int placeHolder = 0;
	public static int nums1  = 818825488;
	public static int mod = 0;
	public static boolean found = false;

	public static void countingEights(int n) {
		log.warn("A: master = {} :: n = {}", master, n);
		if (n > 0) {
			placeHolder = working;
			mod = working % 10;
			if (mod == 8) {
				if (found) {
					master = master * 2;
				} else {
					found = true;
					++master;
				}
			}
			found = false;
			log.warn("B: master = {} :: n = {}", master, n);
//			int i = nums1;
			log.warn("C: master = {} :: n = {}", master, n);
//			i = i % 10;
			log.warn("D: master = {} :: n = {}", master, n);
//			System.out.println(i);
			if (i != 8) {
				log.warn("E: master = {} :: n = {}", master, n);
				log.warn("F: CALLING countingEights() AGAIN!!!!!");
				countingEights(n - 1);
				log.warn("G: continuing after entering countingEights from i != 8");
				log.warn("G: master = {} :: n = {}", master, n);
				nums1 = nums1 / 10;
				log.warn("H: master = {} :: n = {}", master, n);
//				log.warn("H: master = {}", master);
//				System.out.println(master);
			} else if (nums1 % 10 == 8) {
				++master;
				log.warn("I: master = {} :: n = {}", master, n);
				i = nums1;
				log.warn("J: master = {} :: n = {}", master, n);
				i = i / 10;
				log.warn("K: master = {} :: n = {}", master, n);
				i = i % 10;
				log.warn("L: master = {} :: n = {}", master, n);
				if (i == 8) {
					log.warn("M: master = {} :: n = {}", master, n);
					log.warn("M: CALLING countingEights() AGAIN!!!!!");
					countingEights(n - 1);
					log.warn("N: continuing after entering countingEights from i == 8");
					log.warn("N: master = {} :: n = {}", master, n);
					nums1 = nums1 / 10;
					log.warn("O: master = {} :: n = {}", master, n);
					master = master * 2;
					log.warn("P: master = {} :: n = {}", master, n);
//					System.out.println(master);
				}
				log.warn("Q: CALLING countingEights() AGAIN!!!!!");
				countingEights(n - 1);
				log.warn("R: continuing after entering countingEights from after the if block for i == 8");
				log.warn("R: master = {} :: n = {}", master, n);
				nums1 = nums1 / 10;
				log.warn("S: master = {} :: n = {}", master, n);
//				master = master + 1;
				log.warn("T: master = {} :: n = {}", master, n);
//				System.out.println(master);
			}
			log.warn("U: master = {} :: n = {}", master, n);
		}
		log.warn("V: master = {} :: n = {}", master, n);
	}
}