package com.sport.support.shared.specifications;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    SpecificationName getName();

}
