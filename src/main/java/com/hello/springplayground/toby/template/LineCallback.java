package com.hello.springplayground.toby.template;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
