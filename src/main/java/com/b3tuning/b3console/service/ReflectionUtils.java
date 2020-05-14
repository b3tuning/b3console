package com.b3tuning.b3console.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.XSlf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ReflectionUtils {

	/**
	 * Check if the provided Optional can be assigned to the field taking
	 * into account the parameterized type.
	 * <p>
	 * For example given declared fields:
	 * private Optional<String> myOptional;
	 * private List<String> myList;
	 * private String myString;
	 * <p>
	 * this will result in:
	 * (myOptional, Optional<String>) -> true
	 * (myOptional, <any>)            -> false
	 * (myList, <any>)                -> false
	 * (myString, <any>)              -> false
	 */
	public static boolean isAssignableOptional(Field field, Optional fieldValue) {
		if (!field.getType().isAssignableFrom(Optional.class)) {
			return false;    // not an optional
		}
		if (!fieldValue.isPresent()) {
			return true;    // empty optional is always assignable
		}
		return isAssignableParametrizedType(field, fieldValue.get().getClass());
	}

	/**
	 * Check if the parameterized type of a generic field can be set to the
	 * provided class. If the field is not generic then false is returned.
	 * <p>
	 * For example given declared fields:
	 * private List<String> myList;
	 * private String myString;
	 * <p>
	 * this will result in:
	 * (myList, String.class)   -> true
	 * (myList, <any>)          -> false
	 * (myString, String.class) -> false
	 * (myString, <any>)        -> false
	 */
	public static boolean isAssignableParametrizedType(Field field, Class<?> clazz) {
		Type type = getParameterizedType(field);
		if (type == null) {
			return false;
		}
		return type.getTypeName().equals(clazz.getTypeName());
	}

	public static Optional<String> coerceToOptionalString(Optional optional) {
		return coerceToOptionalString(optional, null);
	}

	/**
	 * Try to convert an Optional<Object> to Optional<String> using the provided ObjectMapper
	 * to do the conversion. If the ObjectMapper is null or an error occurs then
	 * enums are converted using Enum.name() and everything else using toString().
	 */
	public static Optional<String> coerceToOptionalString(Optional optional, ObjectMapper objectMapper) {
		if (!optional.isPresent()) {
			return Optional.empty();
		}
		Object value = optional.get();
		if (objectMapper != null) {
			try {
				return Optional.of(objectMapper.writeValueAsString(optional));
			}
			catch (Exception e) {
				log.error("{}", e.getMessage(), e);
			}
		}
		if (Enum.class.isAssignableFrom(value.getClass())) {
			return Optional.of(((Enum) value).name());
		}
		return Optional.of(optional.get().toString());
	}

	/**
	 * Get the parameterized type of a generic field such as:
	 * private Optional<String> myOptional -> String
	 * private List<Integer> myList -> Ingeger
	 */
	public static Type getParameterizedType(Field field) {
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			return ((ParameterizedType) type).getActualTypeArguments()[0];
		}
		return null;
	}
}
