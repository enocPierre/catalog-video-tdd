package com.fullcycle.admin.catalogo.domain.category.validation;

import com.fullcycle.admin.catalogo.domain.category.Category;

public class CategoryValidator extends Validator {

    private final Category category;

    public CategoryValidator(final Category aCategory, final ValidationHandler anHandler) {
        super(anHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        if(this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
