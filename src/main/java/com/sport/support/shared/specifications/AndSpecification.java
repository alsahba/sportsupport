package com.sport.support.shared.specifications;

import java.util.HashSet;
import java.util.Set;

public class AndSpecification<T> extends AbstractSpecification<T> {

    private final Set<Specification<T>> specificationSet = new HashSet<>();

    public AndSpecification(Specification<T> specification, Specification<T> rightSpecification) {
        specificationSet.add(specification);
        specificationSet.add(rightSpecification);
    }

    public boolean isSatisfiedBy(T t) {
        return specificationSet.stream().allMatch(specification -> specification.isSatisfiedBy(t));
    }

    @Override
    public AbstractSpecification<T> and(Specification<T> spec) {
        specificationSet.add(spec);
        return this;
    }
}
