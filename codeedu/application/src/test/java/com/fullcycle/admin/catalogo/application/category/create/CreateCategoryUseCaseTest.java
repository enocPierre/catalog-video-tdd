package com.fullcycle.admin.catalogo.application.category.create;


import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateCategoryUseCaseTest {

    // 1-teste do caminho feliz
    // 2- Test passando uma propriedade invalid(name)
    // 3- Test criando uma categoria inativa
    // 4- Test simulando un erro generico vindo do gatway

    @Test
    public void givenAvalidComand_whenCallsCreateCategory_souldReturnCategoryId() {

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);
        final CategoryGateway categoryGateway =  Mockito.mock(CategoryGateway.class);
        when(categoryGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var useCase = new DefaultCreateCategoryUseCase (categoryGateway);

        final var atualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(atualOutput);
        Assertions.assertNotNull(atualOutput.id());

        Mockito.verify(categoryGateway, times(1)).create(argThat(aCategory ->
                      Objects.equals(expectedName, aCategory.getName())
                            && Objects.equals(expectedDescription, aCategory.getDescription())
                            && Objects.equals(expectedIsActive, aCategory.isActive())
                            && Objects.nonNull(aCategory.getId())
                            && Objects.nonNull(aCategory.getCreatedAt())
                            && Objects.nonNull(aCategory.getUpdatedAt())
                            && Objects.isNull(aCategory.getDeletedAt())
                            //&& Objects.nonNull(aCategory.getDeletedAt());
                ));
    }
}
