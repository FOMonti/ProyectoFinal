
package com.ep_movil.passwordConfig;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented //indica si agrega o no documentancion a javadocs
@Constraint(validatedBy = PasswordConstraintValidator.class) //Marca la anotacion como un Bean Validation Constraint. El elemento validatedBy specifies the classes implementing the constraint.
@Target({ FIELD, ANNOTATION_TYPE })//donde puede ser usada la anotacion(ej: variables)
@Retention(RUNTIME)
public @interface ValidPassword {
    
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
