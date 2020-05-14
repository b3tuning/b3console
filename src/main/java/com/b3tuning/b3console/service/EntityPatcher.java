package com.b3tuning.b3console.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Optional;

import static com.b3tuning.b3console.service.ReflectionUtils.coerceToOptionalString;
import static com.b3tuning.b3console.service.ReflectionUtils.getParameterizedType;
import static com.b3tuning.b3console.service.ReflectionUtils.isAssignableOptional;
import static com.b3tuning.b3console.service.ReflectionUtils.isAssignableParametrizedType;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class EntityPatcher {

	private ObjectMapper objectMapper;

//	@Inject
//	public EntityPatcher(ObjectMapper objectMapper){
//		log.entry();
//		this.objectMapper = objectMapper;
//	}

	@Inject
	public EntityPatcher() {
		log.entry();
	}

	public <T, R> void generateDelta(T from, T to, R delta) {
		log.entry();

		// generate delta
		DiffNode root = ObjectDifferBuilder.buildDefault().compare(to, from);
		if (!root.hasChanges()) {
			log.debug("No changes therefore not generating delta");
			return;
		}

		root.visit((DiffNode node, Visit visit) -> {
			if (node.hasChanges() && !node.hasChildren()) {
				set(delta, node.getPropertyName(), Optional.ofNullable(node.canonicalGet(to)));
			}
		});

		log.trace("Generated apiRequest: {}", delta);
	}

	private boolean set(Object object, String fieldName, Optional fieldValue) {
		log.entry(fieldName, fieldValue);

		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				if (field.getType().isAssignableFrom(Optional.class)) {
					field.setAccessible(true);

					// perfect match
					if (isAssignableOptional(field, fieldValue)) {
						field.set(object, fieldValue);
						return true;
					}

					// try to coerce
					if (isAssignableParametrizedType(field, String.class)) {
						field.set(object, coerceToOptionalString(fieldValue, objectMapper));
						return true;
					}

					if (fieldValue.isPresent()) {
						log.trace("Failed to assign {} to {}", getParameterizedType(field),
						          fieldValue.get().getClass());
					} else {
						log.trace("Failed to assign {} to null", getParameterizedType(field));
					}
					return false;
				}
				log.trace("Can't patch something that is not an Optional: {}", fieldValue);
				return false;
			}
			catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			}
			catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return false;
	}

}