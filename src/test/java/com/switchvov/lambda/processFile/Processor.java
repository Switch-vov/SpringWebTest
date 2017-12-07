package com.switchvov.lambda.processFile;

@FunctionalInterface
public interface Processor<T, R> {
    R process(T t) throws Exception;
}
