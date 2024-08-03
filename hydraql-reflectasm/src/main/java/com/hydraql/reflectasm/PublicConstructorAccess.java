package com.hydraql.reflectasm;

/**
 * @author leojie@apache.org 2024/7/23 21:58
 */
public abstract class PublicConstructorAccess<T> extends ConstructorAccess<T> {
    protected PublicConstructorAccess(ClassAccess classAccess) {
        super(classAccess);
    }
}