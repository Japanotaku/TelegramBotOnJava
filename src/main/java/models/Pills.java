package models;

public class Pills {
    public PillsType pillsType;
    public int cost;

    public Pills(PillsType pillsType, int cost) {
        this.pillsType = pillsType;
        this.cost = cost;
    }

    public PillsType getPillsType() {
        return pillsType;
    }

    public void setPillsType(PillsType pillsType) {
        this.pillsType = pillsType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return
                "Таблетка: " + pillsType +
                        ", стоимость :" + cost;
    }
}