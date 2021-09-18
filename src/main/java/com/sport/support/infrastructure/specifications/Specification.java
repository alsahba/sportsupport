package com.sport.support.infrastructure.specifications;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    SpecificationName getName();

}
