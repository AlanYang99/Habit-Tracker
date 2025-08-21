package com.habittracker.specification;

import com.habittracker.entity.CalendarHabit;
import com.habittracker.entity.WeeklyHabit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CalendarHabitSpecification {
    public static Specification<CalendarHabit> validDate(final LocalDate localDate) {
        return (root, query, criteriaBuilder) -> {

            final Predicate isCalendarDayPredicate = criteriaBuilder.isMember(localDate, root.get("scheduledDates"));

            return criteriaBuilder.and(isCalendarDayPredicate);
        };
    }

    public static Specification<CalendarHabit> userId(final Long userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }
}
