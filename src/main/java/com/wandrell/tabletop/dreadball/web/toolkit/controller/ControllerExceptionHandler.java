/**
 * Copyright 2016 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.wandrell.tabletop.dreadball.web.toolkit.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wandrell.tabletop.dreadball.web.toolkit.controller.bean.DefaultErrorResponse;
import com.wandrell.tabletop.dreadball.web.toolkit.controller.bean.ErrorResponse;

/**
 * Initializes all the controllers with a common configuration.
 * <p>
 * TODO: Check http://www.baeldung.com/exception-handling-for-rest-with-spring
 * <p>
 * TODO: Tests
 * http://stackoverflow.com/questions/16669356/testing-spring-mvc-exceptionhandler-method-with-spring-mvc-test
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ControllerAdvice
public final class ControllerExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Default constructor.
     */
    public ControllerExceptionHandler(final MessageSource messageSource) {
        super();

        this.messageSource = checkNotNull(messageSource,
                "Received a null pointer as message source");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ErrorResponse
            processIllegalArgument(final IllegalArgumentException ex) {
        return new DefaultErrorResponse(
                resolveLocalizedErrorMessage(ex.getMessage()));
    }

    private final MessageSource getMessageSource() {
        return messageSource;
    }

    private final String resolveLocalizedErrorMessage(final String error) {
        final Locale currentLocale;
        String localizedErrorMessage;

        currentLocale = LocaleContextHolder.getLocale();
        try {
            localizedErrorMessage = getMessageSource().getMessage(error,
                    new Object[] {}, currentLocale);
        } catch (final NoSuchMessageException e) {
            localizedErrorMessage = error;
        }

        return localizedErrorMessage;
    }

}
