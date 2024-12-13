package com.mshah.crowdfunding.specification.Idea;

import com.mshah.crowdfunding.dao.entity.Idea;
import com.mshah.crowdfunding.model.dto.IdeaFilterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class IdeaSpecification implements Specification<Idea> {
    private final IdeaFilterDto filter;

    @Override
    public Predicate toPredicate(Root<Idea> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null) {
            if (filter.getName() != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (filter.getCategory() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), filter.getCategory()));
            }

            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            if (filter.getSortBy() != null) {
                if (filter.getSortOrder() != null && "desc".equalsIgnoreCase(filter.getSortOrder())) {
                    query.orderBy(criteriaBuilder.desc(root.get(filter.getSortBy())));
                } else {
                    query.orderBy(criteriaBuilder.asc(root.get(filter.getSortBy())));
                }
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}