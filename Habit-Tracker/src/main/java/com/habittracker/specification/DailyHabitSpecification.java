package com.habittracker.specification;

import com.habittracker.entity.DailyHabit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DailyHabitSpecification {

    private DailyHabitSpecification() {
    }

    public static Specification<DailyHabit> validDate(final LocalDate localDate) {
        return (root, query, criteriaBuilder) -> {
            final Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("start_date"), localDate);
            final Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("end_date"), localDate);

            return criteriaBuilder.and(startDatePredicate, endDatePredicate);
        };
    }

    public static Specification<DailyHabit> userId(final Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
}
