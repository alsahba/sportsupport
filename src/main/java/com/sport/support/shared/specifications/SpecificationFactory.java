package com.sport.support.shared.specifications;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpecificationFactory<T> {

    private Map<SpecificationName, Specification<T>> specMap;

    @Autowired
    public SpecificationFactory(Set<Specification<T>> specSet) {
        createSpecification(specSet);
    }

    public boolean execute(SpecificationName specificationName, T t) {
        return specMap.get(specificationName).isSatisfiedBy(t);
    }

    private void createSpecification(Set<Specification<T>> specSet) {
        specMap = new HashMap<>();
        specSet.forEach(spec -> specMap.put(spec.getName(), spec));
    }
}
