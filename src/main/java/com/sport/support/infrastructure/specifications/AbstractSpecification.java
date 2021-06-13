package com.sport.support.infrastructure.specifications;

public abstract class AbstractSpecification<T> implements Specification<T> {

    public abstract boolean isSatisfiedBy(T t);

    public AbstractSpecification<T> or(Specification<T> s) {
        return new OrSpecification<>(this, s);
    }

    public AbstractSpecification<T> and(Specification<T> s) {
        return new AndSpecification<>(this, s);
    }

    public AbstractSpecification<T> not() {
        return new NotSpecification<>(this);
    }
}
