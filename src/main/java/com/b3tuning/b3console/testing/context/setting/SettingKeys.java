package com.b3tuning.b3console.testing.context.setting;

import xdean.auto.message.AutoMessage;

import static xdean.css.editor.context.setting.RawSettingKeys.*;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_COMMENT;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_FIND;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_FORMAT;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_REDO;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_SUGGEST;
import static xdean.css.editor.context.setting.RawSettingKeys.EDIT_UNDO;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_CLOSE;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_EXIT;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_NEW;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_OPEN;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_REVERT;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_SAVE;
import static xdean.css.editor.context.setting.RawSettingKeys.FILE_SAVEAS;
import static xdean.css.editor.context.setting.RawSettingKeys.HELP_ABOUT;
import static xdean.css.editor.context.setting.RawSettingKeys.HELP_HELP;
import static xdean.css.editor.context.setting.RawSettingKeys.HELP_SETTINGS;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_FIND_CASE_SENSITIVE;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_FIND_REGEX;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_FIND_WRAP_SEARCH;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_COMMON;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_COMMON_AUTO_SUGGEST;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_COMMON_CHARSET;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_COMMON_OPEN_LAST;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_COMMON_SHOW_LINE;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_TEXT;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_TEXT_FONT_FAMILY;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_TEXT_FONT_SIZE;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_GENERAL_TEXT_WRAP_TEXT;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_LANGUAGE;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_RECENT_LOCATION;
import static xdean.css.editor.context.setting.RawSettingKeys.OPTION_SKIN;

@AutoMessage(path = "/message/settings.properties", generatedName = "RawSettingKeysStub")
public interface SettingKeys {
  String GENERAL = OPTION_GENERAL;

  interface General {

    String COMMON = OPTION_GENERAL_COMMON;

    interface Common {
      String AUTO_SUGGEST = OPTION_GENERAL_COMMON_AUTO_SUGGEST;
      String SHOW_LINE = OPTION_GENERAL_COMMON_SHOW_LINE;
      String OPEN_LAST = OPTION_GENERAL_COMMON_OPEN_LAST;
      String CHARSET = OPTION_GENERAL_COMMON_CHARSET;
    }

    String TEXT = OPTION_GENERAL_TEXT;

    interface Text {
      String FONT_FAMILY = OPTION_GENERAL_TEXT_FONT_FAMILY;
      String FONT_SIZE = OPTION_GENERAL_TEXT_FONT_SIZE;
      String WRAP_TEXT = OPTION_GENERAL_TEXT_WRAP_TEXT;
    }
  }

  interface Find {
    String REGEX = OPTION_FIND_REGEX;
    String WRAP_SEARCH = OPTION_FIND_WRAP_SEARCH;
    String CASE_SENSITIVE = OPTION_FIND_CASE_SENSITIVE;
  }

  String LANGUAGE = OPTION_LANGUAGE;
  String SKIN = OPTION_SKIN;
  String RECENT_LOC = OPTION_RECENT_LOCATION;

  interface File {
    String NEW = FILE_NEW;
    String OPEN = FILE_OPEN;
    String SAVE = FILE_SAVE;
    String SAVE_AS = FILE_SAVEAS;
    String CLOSE = FILE_CLOSE;
    String REVERT = FILE_REVERT;
    String EXIT = FILE_EXIT;
  }

  interface Edit {
    String UNDO = EDIT_UNDO;
    String REDO = EDIT_REDO;
    String SUGGEST = EDIT_SUGGEST;
    String FORMAT = EDIT_FORMAT;
    String COMMENT = EDIT_COMMENT;
    String FIND = EDIT_FIND;
  }

  interface Help {
    String SETTINGS = HELP_SETTINGS;
    String ABOUT = HELP_ABOUT;
    String HELP = HELP_HELP;
  }
}
