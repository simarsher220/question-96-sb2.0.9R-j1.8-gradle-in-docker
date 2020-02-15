package org.codejudge.sb.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatKey implements Serializable {

    private Character row;
    private Integer column;
    private Integer theatreId;

    public SeatKey() {
    }

    public SeatKey(Character row, Integer column, Integer theatreId) {
        this.row = row;
        this.column = column;
        this.theatreId = theatreId;
    }

    @NotNull
    @Column(name = "row")
    public Character getRow() {
        return row;
    }

    public void setRow(Character row) {
        this.row = row;
    }

    @NotNull
    @Column(name = "seat_number")
    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @NotNull
    @Column(name = "theatre_id")
    public Integer getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Integer theatreId) {
        this.theatreId = theatreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        SeatKey that = (SeatKey) o;
        return Objects.equals(theatreId, that.getTheatreId()) &&
                Objects.equals(row, that.getRow()) &&
                Objects.equals(column, that.getColumn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId, row, column);
    }
}
