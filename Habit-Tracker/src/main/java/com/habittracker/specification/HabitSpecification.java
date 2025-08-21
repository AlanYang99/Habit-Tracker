package com.habittracker.specification;

import com.habittracker.entity.AbstractHabit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HabitSpecification {

    public static Specification<AbstractHabit> validDate(final LocalDate localDate) {
        return (root, query, criteriaBuilder) -> {
            final Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("start_date"), localDate);
            final Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("end_date"), localDate);

            return criteriaBuilder.and(startDatePredicate, endDatePredicate);
        };
    }

    public static Specification<AbstractHabit> userId(final Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
}
