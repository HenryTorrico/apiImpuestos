package com.example.demo.configurate;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import java.time.LocalDate;

public class LocalDateConverter extends AbstractSingleValueConverter {

    public boolean canConvert(Class type) {
        return (type!=null) && LocalDate.class.getPackage().equals(type.getPackage());
    }

    public String toString (Object source) {
        return source.toString();
    }

    public Object fromString(String str) {
        try {
            return LocalDate.parse(str);
        } catch (Exception e) {
            return "error";
        }
    }

}