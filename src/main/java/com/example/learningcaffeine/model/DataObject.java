package com.example.learningcaffeine.model;

import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@RequiredArgsConstructor
public class DataObject {
    private final String data;

    private static int objectCounter = 0;
    // standard constructors/getters

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }

    @Override
    public String toString() {
        return "DataObject{" + data + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObject that = (DataObject) o;
        return Objects.equals(data, that.data);
    }
}
