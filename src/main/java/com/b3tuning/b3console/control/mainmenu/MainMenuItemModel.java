package com.b3tuning.b3console.control.mainmenu;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainMenuItemModel {

	private String expandedTitle;
	private String compressedTitle;
	private MainMenuItemAction action;
	private Position position;
	private Mode mode;
	
	public static enum Position {
		TOP, BOTTOM;
	}
	public static enum Mode {
		APP, EXTERNAL;
	}
	
}
