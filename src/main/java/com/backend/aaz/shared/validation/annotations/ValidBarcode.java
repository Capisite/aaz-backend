package com.backend.aaz.shared.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.backend.aaz.shared.validation.validators.BarcodeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = BarcodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBarcode {
    String message() default "Código de barras inválido (EAN-13 ou UPC-A esperado)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}