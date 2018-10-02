package com.recipeworld.knockmykitchen.validator;

import com.recipeworld.knockmykitchen.annotation.PasswordMatches;
import com.recipeworld.knockmykitchen.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator  implements ConstraintValidator<PasswordMatches, Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordMatchesValidator.class);

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        User user = (User) obj;
        boolean matched = user.getPassword().equalsIgnoreCase(user.getConfirmPassword());
        LOGGER.info("Are passwords matched? ------ {}", matched);
        return matched;
    }
}
