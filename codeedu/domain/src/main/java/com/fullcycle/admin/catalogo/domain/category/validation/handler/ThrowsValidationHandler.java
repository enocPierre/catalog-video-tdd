package com.fullcycle.admin.catalogo.domain.category.validation.handler;

import com.fullcycle.admin.catalogo.domain.exception.DomainException;
import com.fullcycle.admin.catalogo.domain.category.validation.Error;
import com.fullcycle.admin.catalogo.domain.category.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(List.of());
    }

    @Override
    public ValidationHandler append(ValidationHandler anHandler) {
        return null;
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}
