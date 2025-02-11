package strictpvp.chestGui;

public enum Rows {
    ONE(9),
    TWO(18),
    THREE(27),
    FOUR(36),
    FIVE(45),
    SIX(54);

    private final Integer maxSlot;

    Rows(Integer maxSlot) {
        this.maxSlot = maxSlot;
    }

    public Integer getMaxSlot() {
        return maxSlot;
    }
}
