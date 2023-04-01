package org.example.DIFramework;

import lombok.var;
import org.reflections.Reflections;
import java.util.HashMap;
import java.util.Map;


public class DIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();
    public Reflections DIContainer(String packageName){
        Reflections reflections = new Reflections(packageName);
        var classes = reflections.getTypesAnnotatedWith(Injectable.class);
        for (var _class : classes) {
            register(_class);
        }
        return reflections;
    }
    public <T> void register(Class<T> classToRegister) {
        if (classToRegister.isInterface()) {
            throw new RuntimeException("Class " + classToRegister.getName() + " is an interface");
        }
        if (instances.containsKey(classToRegister)) {
            throw new RuntimeException("Class " + classToRegister.getName() + " already registered");
        }
        try {
            var constructor = classToRegister.getConstructors()[0];
            var params = constructor.getParameters();
            for (var param : params) {
                if (!instances.containsKey(param.getType())) {
                    throw new RuntimeException("Class " + param.getType().getName() + " not registered");
                }
            }
            var paramsInstances = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsInstances[i] = instances.get(params[i].getType());
            }
            var instance = constructor.newInstance(paramsInstances);
            instances.put(classToRegister, instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void register(Object instance) {
        if (instances.containsKey(instance.getClass())) {
            throw new RuntimeException("Class " + instance.getClass().getName() + " already registered");
        }
        instances.put(instance.getClass(), instance);
    }
    public <TInterface, TImplementation extends TInterface>
    void register(Class<TInterface> _interface, Class<TImplementation> _class) {
        if (instances.containsKey(_interface)) {
            throw new RuntimeException("Class " + _interface.getName() + " already registered");
        }
        try {
            var constructor = _class.getConstructors()[0];
            var params = constructor.getParameters();
            for (var param : params) {
                if (!instances.containsKey(param.getType())) {
                    throw new RuntimeException("Class " + param.getType().getName() + " not registered");
                }
            }
            var paramsInstances = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                paramsInstances[i] = instances.get(params[i].getType());
            }
            var instance = constructor.newInstance(paramsInstances);
            instances.put(_interface, instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public <T> T resolve(Class<T> classToResolve) {
        if (!instances.containsKey(classToResolve)) {
            throw new RuntimeException("Class " + classToResolve.getName() + " not registered");
        }
        return (T) instances.get(classToResolve);
    }
}
