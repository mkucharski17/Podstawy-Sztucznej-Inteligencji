import lombok.Getter;

/**
 * Representation of item which we can put in backpack.
 */

@Getter
public class Item {
    /**
     * Weight of item.
     */
    private int weight;
    /**
     * Value of item.
     */
    private int value;

    /**
     * Item constructor
     *
     * @param weight New item weight.
     * @param value  New item value.
     */
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    /**
     * Override equals method.
     *
     * @param obj Object which equality is being checked.
     * @return True if items are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return item.weight == this.weight && item.value == this.value;
        }

        return false;
    }
}
