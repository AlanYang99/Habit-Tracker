package com.habittracker.specification;

import com.habittracker.entity.DailyHabit;
import com.habittracker.entity.WeeklyHabit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyHabitSpecification {
    public static Specification<WeeklyHabit> validDate(final LocalDate localDate) {
        return (root, query, criteriaBuilder) -> {
            final DayOfWeek dayOfWeek = localDate.getDayOfWeek();

            final Predicate startDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("start_date"), localDate);
            final Predicate endDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("end_date"), localDate);
            final Predicate dayOfWeekPredicate = criteriaBuilder.isMember(dayOfWeek, root.get("daysOfWeek"));

            return criteriaBuilder.and(startDatePredicate, endDatePredicate, dayOfWeekPredicate);
        };
    }

    public static Specification<WeeklyHabit> userId(final Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
}
