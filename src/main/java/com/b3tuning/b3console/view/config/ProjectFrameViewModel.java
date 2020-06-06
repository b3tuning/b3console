package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.DetailMode;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.loader.ViewManagerImpl;
import com.b3tuning.b3console.view.loader.ViewManagerImpl.ViewInfo;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import com.b3tuning.b3console.view.notifications.PopViewNotification;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;
import org.fxmisc.easybind.EasyBind;
import org.reactfx.EventStream;

import javax.inject.Inject;
import java.util.UUID;

import static org.reactfx.EventStreams.combine;
import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

@XSlf4j
public class ProjectFrameViewModel extends BaseViewModel implements Refreshable {

	// injected
	@SuppressWarnings("unused")
//	private AuthenticatedUser  user;
//	@SuppressWarnings("unused")
//	private ProjectsService    projectsService;
	private NotificationCenter globalNotifications;
	private ViewManager        viewManager;

	// must be set by the creator of the controller. these controllers are single use only, not
	// singletons
//	private ObjectProperty<UUID>                 projectUid           = new SimpleObjectProperty<>();
//	private ObjectProperty<ProjectDetail>        project              = new SimpleObjectProperty<>();
//	private ObjectProperty<ProjectRoleHierarchy> userRole             = new SimpleObjectProperty<>();
//	private BooleanProperty                      projectEditorOrAbove = new SimpleBooleanProperty(false);
	private ObjectProperty<StackPane>            childViewPane        = new SimpleObjectProperty<>();

	// private
//	private ObjectProperty<DetailMode> mode = new SimpleObjectProperty<>();

	public enum MenuAction {
		BACK, JOBS, INGEST, OPTIONS
	}

	@Inject
	public ProjectFrameViewModel(//AuthenticatedUser user, ProjectsService projectsService,
			NotificationCenter globalNotifications, ViewManager viewManager) {
		log.entry();

//		this.user = user;
//		this.projectsService = projectsService;
		this.globalNotifications = globalNotifications;
		this.viewManager = viewManager;

		// the overall state of the form is a combination of uid and mode
//		EventStream<UUID> projectUidStream = nonNullValuesOf(projectUid);
//		EventStream<DetailMode> modeStream = nonNullValuesOf(mode);

//		manage(combine(projectUidStream, modeStream).subscribe((p,m) -> {
//			log.entry(p,m);
//
//			if (p==null || m==null) {
//				return;
//			}
//
//			// if this is a new project, stop tracking changes of the old
//			if (project.get()!=null) {
//				project.get().stopTrackingChanges();
//			}
//
//			project.set(projectsService.project(projectUid.get()));
//
//			// org admins gain editor permissions for all projects
//			EasyBind.when(user.orgAdminOrAboveProperty()).bind(userRole,
//					new SimpleObjectProperty<>(ProjectRoleHierarchy.EDITOR));
//
//			if (project.get() == null) {
//				log.error("couldn't get project, probably an auth error.");
//				return;
//			}
//
//			// but a project specific role overrides the above
//			project.get().getProjectMembers().stream().filter(member -> user.getUid().equals(member.getUid())).forEach(member -> userRole.bind(member.roleProperty()));
//
//			// prepare the first page
//			if (DetailMode.VIEW.equals(m)) {
//				ClickButtonNotification.fire(globalNotifications, MainMenuItemAction.PROJECTS, "jobsButton");
//			} else {
//				ClickButtonNotification.fire(globalNotifications, MainMenuItemAction.PROJECTS, "optionsButton");
//			}
//
//		}));

//		projectEditorOrAbove.bind(EasyBind.monadic(userRole).filter(r -> r != null)
//				.map(r -> r.isEqualOrAbove(ProjectRoleHierarchy.EDITOR)));
//
//		// if the organization is switched, we need to reset everything
//		manage( valuesOf(user.organizationProperty()).subscribe(v -> {
//			log.entry(v);
//			Platform.runLater(()->project.set(null));
//		}));
//
	}

	/**
	 * Loads the IngestView in the centerPane for the selected project.
	 */
//	private void showIngestView() {
//		log.entry(project.get().getUid());
//
//		String key = IngestView.class.getName();
//		if (!viewManager.handledDirty(key)) {
//			return;
//		}
//		if (viewManager.contains(key)) {
//			viewManager.toFront(key);
//		} else {
//			@SuppressWarnings("unchecked") ViewTuple<IngestView<IngestViewModel>, IngestViewModel> viewTuple = FluentViewLoader
//					.fxmlView(IngestView.class).load();
//			viewTuple.getViewModel().projectProperty().bind(project);
//			viewManager.push(key, viewTuple, childViewPane.get(), MainMenuItemAction.PROJECTS);
//		}
//	}

	/**
	 * Loads the JobView in the centerPane for the selected project.
	 */
//	private void showJobsView() {
//		if (project.get()==null) {
//			log.trace("Somehow project is not available yet, therefore ignoring request to view jobs");
//			return;
//		}
//		log.entry(project.get().getUid());
//
//		String key = JobsListView.class.getName();
//		if (!viewManager.handledDirty(key)) {
//			return;
//		}
//		if (viewManager.contains(key)) {
//			viewManager.toFront(key);
//		} else {
//			ViewTuple<JobsListView, JobsListViewModel> viewTuple = FluentViewLoader
//					.fxmlView(JobsListView.class).load();
//			viewTuple.getViewModel().projectProperty().bind(project);
//			viewManager.push(key, viewTuple, childViewPane.get(), MainMenuItemAction.PROJECTS);
//		}
//	}

	/**
	 * Loads the options view in the centerPane for the selected project.
	 */
//	private void showOptionsView() {
//		log.entry(project.get().getUid());
//
//
//		String key = ProjectOptionsView.class.getName();
//		if (!viewManager.handledDirty(key)) {
//			return;
//		}
//		if (viewManager.contains(key)) {
//			viewManager.toFront(key);
//		} else {
//			ViewTuple<ProjectOptionsView, ProjectOptionsViewModel> viewTuple = FluentViewLoader
//					.fxmlView(ProjectOptionsView.class).load();
//
//			viewTuple.getViewModel().projectEditorOrAboveProperty().bind(projectEditorOrAbove);
//			viewTuple.getViewModel().projectProperty().bindBidirectional(project);
//
//			viewManager.push(key, viewTuple, childViewPane.get(), MainMenuItemAction.PROJECTS);
//		}
//	}

//	public void onBackClicked() {
//		log.entry();
//
//		globalNotifications.publish(PopViewNotification.class.getName(), new PopViewNotification(true));
//	}
//
//	public void onJobsClicked() {
//		log.entry();
//		showJobsView();
//	}
//
//	public void onIngestClicked() {
//		log.entry();
//		showIngestView();
//	}
//
//	public void onOptionsClicked() {
//		log.entry();
//		showOptionsView();
//	}

	/**
	 * JAVAFX PROPERTIES
	 */
//	public ObjectProperty<ProjectDetail> projectProperty() {
//		return project;
//	}
//
//	public void setProjectUid(UUID value) {
//		projectUid.set(value);
//	}
//
//	public ObjectProperty<UUID> projectUidProperty() {
//		return projectUid;
//	}
//
//	public BooleanProperty projectEditorOrAboveProperty() {
//		return projectEditorOrAbove;
//	}
//
//	public void setChildViewPane(StackPane value) {
//		childViewPane.set(value);
//	}
//
//	public ObjectProperty<StackPane> childViewPaneProperty() {
//		return childViewPane;
//	}
//
//	public void setMode(DetailMode value) {
//		mode.set(value);
//	}
//
	@Override
	public void refresh() {
		log.entry();
		ViewInfo currentView = viewManager.peek(childViewPane.get());
		if (currentView!=null) {
			ViewModel vm = currentView.getViewTuple().getViewModel();
			if (vm instanceof Refreshable) {
				((Refreshable)vm).refresh();
			}
		}
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();

//		project.get().stopTrackingChanges();
//		project.unbind();
//		projectUid.unbind();

		viewManager.destroyAll(childViewPane.get());
		viewManager=null;
	}

}
