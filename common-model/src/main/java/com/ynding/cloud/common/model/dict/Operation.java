package com.ynding.cloud.common.model.dict;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * 运算枚举
 *
 * @author dyn
 * @version 2020/1/10
 */
@AllArgsConstructor
public enum Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    public abstract double apply(double x, double y);

    @Override
    public String toString() {
        return symbol;
    }

    private final static Map<String, Operation> STRING_OPERATION_MAP =
            Stream.of(values()).collect(
                    toMap(Object::toString, e -> e));

    public static Optional<Operation> fromString(String symbol) {
        return Optional.ofNullable(STRING_OPERATION_MAP.get(symbol));
    }

    public static void main(String[] args) {
        double x = 2;
        double y = 3;
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
        }

        System.out.println(fromString("+"));
    }
}
