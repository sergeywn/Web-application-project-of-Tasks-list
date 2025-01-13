package dto;

import entity.CategoriesNameEntity;
import entity.UrgencyLevelsEntity;

import java.time.LocalDate;

public record TaskFilter(int limit,
                         int offset,
                         String taskName,
                         CategoriesNameEntity categoryName,
                         UrgencyLevelsEntity urgencyLevel,
                         LocalDate date) {
}
