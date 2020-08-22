package com.ktds.esign.common.enums.converter;

public interface IEnum<T> {
    T getCode(); // DB 코드명 (selectbox key)
    T getDesc(); // DB 코드설명 (selectbox value)
}
