
package com.ep_movil.passwordConfig;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            // 5 caracteres min - 25 max
            new LengthRule(5, 25),

            // una mayuscula como mínimo
            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // una minuscula como mínimo
            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            // un numero como mínimo
            new CharacterRule(EnglishCharacterData.Digit, 1),

            // un caracter especial como mínimo
            new CharacterRule(EnglishCharacterData.Special, 1),

            // sin espacios
            new WhitespaceRule()

        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
//        List<String> messages = validator.getMessages(result);
//        String messageTemplate = messages.stream()
//            .collect(Collectors.joining(","));
//        context.buildConstraintViolationWithTemplate(messageTemplate)
//            .addConstraintViolation()
//            .disableDefaultConstraintViolation();
        return false;
    }
}
