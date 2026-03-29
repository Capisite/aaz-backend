package com.backend.aaz.shared.validation.validators;

import com.backend.aaz.shared.validation.annotations.ValidBarcode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BarcodeValidator implements ConstraintValidator<ValidBarcode, String> {

    @Override
    public void initialize(ValidBarcode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String barcode, ConstraintValidatorContext context) {

        if (barcode == null || barcode.isBlank()) {
            return true; // Use @NotBlank for null/empty check
        }

        // Must be only digits
        if (!barcode.matches("\\d+")) {
            return false;
        }

        int length = barcode.length();
        if (length != 12 && length != 13) {
            return false;
        }

        return validateCheckDigit(barcode);
    }

    private boolean validateCheckDigit(String barcode) {
        int length = barcode.length();
        int sum = 0;
        
        // Algorithm for EAN-13 / UPC-A
        // For EAN-13 (13 digits): 
        //   Weight 1 for digits at positions 1, 3, 5, 7, 9, 11
        //   Weight 3 for digits at positions 2, 4, 6, 8, 10, 12
        // For UPC-A (12 digits):
        //   Weight 3 for digits at positions 1, 3, 5, 7, 9, 11
        //   Weight 1 for digits at positions 2, 4, 6, 8, 10
        
        // A common way to implement both is to iterate from right to left (excluding the check digit)
        // The weight starts at 3 for the first digit to the left of the check digit, and alternates 3, 1, 3, 1...
        
        int weight = 3;
        for (int i = length - 2; i >= 0; i--) {
            int digit = Character.getNumericValue(barcode.charAt(i));
            sum += digit * weight;
            weight = (weight == 3) ? 1 : 3;
        }

        int checkDigit = Character.getNumericValue(barcode.charAt(length - 1));
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;

        return checkDigit == calculatedCheckDigit;
    }
    
}