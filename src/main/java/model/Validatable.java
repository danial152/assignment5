package model;

public interface Validatable<T> {
    boolean isValid(T obj);
}
