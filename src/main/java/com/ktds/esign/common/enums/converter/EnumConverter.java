package com.ktds.esign.common.enums.converter;

import lombok.AllArgsConstructor;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Optional;

/**
 * @param <T> enum
 * @param <E> code
 */
@AllArgsConstructor
public class EnumConverter<T extends IEnum<E>, E> implements AttributeConverter<T, E> {
    private Class<T> clazz;

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return Optional.ofNullable(attribute).isPresent() ? attribute.getCode() : null;
    }

    @Override
    public T convertToEntityAttribute(E code) {
        return Arrays.stream(clazz.getEnumConstants()).filter(e -> e.getCode().equals(code)).findAny().orElse(null);
    }
}
