package model;

import exception.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private boolean mined;
    private final int row;
    private final int column;
    private boolean opened;
    private boolean checked = false;
    private List<Field> neighbors = new ArrayList<>();

    public Field (int row, int column) {
        this.row = row;
        this.column = column;
    }
    public void addNeighbor(Field neighbor) {
        boolean differentRow = row != neighbor.getRow();
        boolean differentColumn = column != neighbor.getColumn();
        boolean diagonal = differentRow && differentColumn;

        int deltaRow = Math.abs(row - neighbor.getRow());
        int deltaColumn = Math.abs(column - neighbor.getColumn());
        int deltaGeneral = deltaColumn + deltaRow;

        if (deltaGeneral == 2 && diagonal) {
            neighbors.add(neighbor);
        }
        if (deltaGeneral == 1 && !diagonal) {
            neighbors.add(neighbor);
        }
    }
    public void checkField() {
        if (!opened) {
            checked = !checked;
        }
    }
    public boolean openField() {
        if (!opened && !checked) {
            opened = true;
            if (mined)  {
                throw new ExplosionException();
            }
            if (safeNeighbors()) {
                neighbors.forEach(v->v.openField());
            }
            return true;
        }
        return false;
    }

    public boolean safeNeighbors() {
        return neighbors.stream().noneMatch(v->v.isMined());
    }
    public void mine() {
        mined = true;
    }

    boolean goalAchieved() {
        boolean disclosed = !mined && opened;
        boolean secured = mined && checked;
        return disclosed || secured;
    }

    long nearByMines() {
        return neighbors.stream().filter(v -> v.isMined()).count();
    }

    void restart() {
        opened = false;
        mined = false;
        checked = false;
    }

    public String toString() {
        if (checked) {
            return "x";
        }
        else if (opened && mined) {
            return "*";
        }
        else if (opened && nearByMines() > 0) {
            return Long.toString(nearByMines());
        }
        else if (opened) {
            return " ";
        }
        else {
            return "?";
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isOpened() {
        return opened;
    }

    public List<Field> getNeighbors() {
        return neighbors;
    }
}
