package com.b3tuning.b3console.view.actionmenu;

import javafx.scene.control.Control;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.b3tuning.b3console.view.root.FontAwesome.DELETE_X;
import static com.b3tuning.b3console.view.root.FontAwesome.EXCHANGE;
import static com.b3tuning.b3console.view.root.FontAwesome.INFO;
import static com.b3tuning.b3console.view.root.FontAwesome.MODIFY;
import static com.b3tuning.b3console.view.root.FontAwesome.PLUS;
import static com.b3tuning.b3console.view.root.FontAwesome.WRENCH;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@RequiredArgsConstructor
public class Action {

	private final Type type;

	private HideActionCallback hideActionCallback;

	private ViewControlCallback styleCallback;


	public Action(Type type, HideActionCallback hideActionCallback) {
		this(type);
		this.hideActionCallback = hideActionCallback;
	}

	public static interface HideActionCallback {
		boolean isHidden(Object hideActionArg);
	}

	public static interface ViewControlCallback {
		void apply(Object data, Control control);
	}

	public static enum Type {
		VIEW(INFO),
		ADD(PLUS),
		EDIT(MODIFY),
		OPTIONS(WRENCH),
		DELETE(DELETE_X),
		CANCEL(DELETE_X),
		DOWNLOAD(EXCHANGE),

		PROGRESS("progress");

		@Getter
		private String fontAwesomeIcon;

		Type(String fontAwesomeIcon) {
			this.fontAwesomeIcon = fontAwesomeIcon;
		}

		public String toString() {
			return this.name();
		}

	}
}
