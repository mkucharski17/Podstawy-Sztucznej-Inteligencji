import lombok.Getter;

@Getter
public class Item {
    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return item.weight == this.weight && item.value == this.value;
        }

        return false;
    }
}
