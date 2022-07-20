package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.validation.Error;
import com.fullcycle.admin.catalogo.domain.category.validation.ValidationHandler;
import com.fullcycle.admin.catalogo.domain.category.validation.Validator;

public class CategoryValidator extends Validator {

    private final Category category;
    public static  final int  NEMA_MAX_LENGTH = 255;
    public static  final int NAME_MIN_LENGTH = 3;

    public CategoryValidator(final Category aCategory, final ValidationHandler anHandler) {
        super(anHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {

        final var name  = this.category.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NEMA_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be betwen 3 and 255 charactere"));
        }
    }

}
