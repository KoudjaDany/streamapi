package com.ddf.training.streamapi.problem2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class IndexedValue<T> {
    private final int index;
    private final T value;

    @Override
    public String toString() {
        return "(" + index + ", " + value + ")";
    }
}
