package br.com.dexcodifica.validacao.anotacao;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.dexcodifica.validacao.ValidaEnqueteUnica;

@Constraint(validatedBy = ValidaEnqueteUnica.class)
@Documented
@Target({ ANNOTATION_TYPE, TYPE })
@Retention(RUNTIME)
public @interface EnqueteUnica {
	String message() default "A Enquete ja existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
