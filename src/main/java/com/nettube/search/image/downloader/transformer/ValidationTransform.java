package com.nettube.search.image.downloader.transformer;

import com.nettube.search.image.downloader.dto.FieldErrorDetailDto;
import jakarta.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationTransform {

    public static List<FieldErrorDetailDto> toErrorDetail(MethodArgumentNotValidException exception) {
        List<FieldErrorDetailDto> errorDetails = toErrorDetail(exception.getFieldErrors());
        errorDetails.addAll(globalErrorsToErrorDetail(exception.getGlobalErrors()));

        return errorDetails;
    }

    public static FieldErrorDetailDto toErrorDetail(ObjectError objectError) {
        return FieldErrorDetailDto.builder()
                .message(objectError.getDefaultMessage())
                .build();
    }

    public static List<FieldErrorDetailDto> globalErrorsToErrorDetail(List<ObjectError> objectErrors) {
        return objectErrors.stream()
                .map(ValidationTransform::toErrorDetail)
                .collect(Collectors.toList());
    }
    public static List<FieldErrorDetailDto> toErrorDetail(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(ValidationTransform::toErrorDetail)
                .collect(Collectors.toList());
    }

    public static FieldErrorDetailDto toErrorDetail(FieldError fieldError) {
        return FieldErrorDetailDto.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .value(fieldError.getRejectedValue())
                .build();
    }

    public static List<FieldErrorDetailDto> toErrorDetail(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(ValidationTransform::toErrorDetail)
                .collect(Collectors.toList());
    }

    public static FieldErrorDetailDto toErrorDetail(ConstraintViolation<?> constraintViolation) {
        String field = (constraintViolation.getPropertyPath() instanceof PathImpl propertyPath) ?
                propertyPath.getLeafNode().getName() :
                null;

        return FieldErrorDetailDto.builder()
                .field(field)
                .message(constraintViolation.getMessage())
                .value(constraintViolation.getInvalidValue())
                .build();
    }
}
