package validator;

import dto.CategoriesNameDto;
import dto.UpdateTaskDto;
import dto.UrgencyLevelsDto;
import util.LocalDateFormatter;
import util.LocalTimeFormatter;

public class UpdateTaskValidator implements Validator<UpdateTaskDto> {

    public static final UpdateTaskValidator INSTANCE = new UpdateTaskValidator();

    @Override
    public ValidationResult isValid(UpdateTaskDto object) {
        Error errorDate = new Error("invalid.Date","Не корректное поле - Дата");
        Error errorTime = new Error("invalid.Time","Не корректное поле - Время");
        Error errorCategories = new Error("invalid.CategoriesName","Не корректное или не заполненное поле - Категория");
        Error errorUrgencyLevel = new Error("invalid.UrgencyLevel","Не корректное или не заполненное поле - Важность");

        var validationResult = new ValidationResult();

        if (!LocalDateFormatter.isValid(object.getDate())) {
            validationResult.add(errorDate);
        }
        if (!LocalTimeFormatter.isValid(object.getTime())) {
            validationResult.add(errorTime);
        }
        if (object.getCategoryName() == null || CategoriesNameDto.valueOf(object.getCategoryName()) == null) {
            validationResult.add(errorCategories);
        }
        if (object.getUrgencyLevel() == null || UrgencyLevelsDto.valueOf(object.getUrgencyLevel()) == null) {
            validationResult.add(errorUrgencyLevel);
        }
        return validationResult;
    }

    public static UpdateTaskValidator getInstance() {
        return INSTANCE;
    }

}
