package com.sport.support.infrastructure.specifications;

import java.util.HashSet;
import java.util.Set;

public class OrSpecification<T> extends AbstractSpecification<T> {

    private final Set<Specification<T>> specificationSet = new HashSet<>();

    public OrSpecification(Specification<T> specification, Specification<T> rightSpecification) {
        specificationSet.add(specification);
        specificationSet.add(rightSpecification);
    }

    public boolean isSatisfiedBy(T t) {
        return specificationSet.stream().anyMatch(specification -> specification.isSatisfiedBy(t));
    }

    @Override
    public AbstractSpecification<T> or(Specification<T> spec) {
        specificationSet.add(spec);
        return this;
    }
}
