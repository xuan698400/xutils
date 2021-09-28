package com.xuan.mix.domain.product.model;

/**
 * @author xuan
 * @since 2021/9/28
 */
public class Inventory {

    public static final Inventory ZERO = new Inventory(0);

    private Inventory(long value) {
        this.value = value;
    }

    private long value;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Inventory) || getClass() != other.getClass()) {
            return false;
        }

        return value == ((Inventory)other).value;
    }

    public static Inventory of(long inventory) {
        return new Inventory(inventory);
    }

}
