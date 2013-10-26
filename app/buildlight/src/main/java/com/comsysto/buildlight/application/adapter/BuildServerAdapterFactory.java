package com.comsysto.buildlight.application.adapter;

import com.comsysto.buildlight.respository.common.BuildServerRepository;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * @author zutherb
 */
public class BuildServerAdapterFactory {

    private final static Reflections reflections = new Reflections("com.comsysto.buildlight.application.adapter");

    public static BuildServerAdapter create(BuildServerRepository repository) {
        Set<Class<? extends BuildServerAdapter>> classes = reflections.getSubTypesOf(BuildServerAdapter.class);
        for (Class<? extends BuildServerAdapter> clazz : classes) {
            try {
                Constructor<?> constructor = clazz.getConstructor(repository.getClass().getInterfaces()[0]);
                return (BuildServerAdapter) constructor.newInstance(repository);
            } catch (InvocationTargetException |
                    InstantiationException |
                    IllegalAccessException |
                    IllegalArgumentException |
                    NoSuchMethodException e) { /* NOOP */ }
        }
        throw new IllegalStateException("No Buildserver Adapter found:");
    }
}
