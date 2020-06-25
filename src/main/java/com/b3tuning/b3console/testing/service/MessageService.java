package com.b3tuning.b3console.testing.service;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import xdean.css.editor.context.setting.model.option.Option;

import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.Locale;

@Service
public class MessageService {

  private @Inject MessageSource  messageSource;
  private @Inject Option<Locale> localeOption;

  public String getMessage(String code, Object... args) throws NoSuchMessageException {
    return messageSource.getMessage(code, args, localeOption.getValue());
  }

  public String getMessageDefault(String defaultMessage, String code, Object... args) {
    return messageSource.getMessage(code, args, defaultMessage, localeOption.getValue());
  }

  public MessageFormat getMessageFormat(String code) {
    return new MessageFormat(getMessage(code), localeOption.getValue());
  }
}
