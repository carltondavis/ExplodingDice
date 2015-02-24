package com.dasixes.explodingdice;

/**
 * Created by cdavis on 1/19/2015.
 */

public class CustomDice {

    private int id;
    private Integer amount;
    private Integer dicepileid;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getDicePileId() {
        return this.dicepileid;
    }

    public void setDicePileId(Integer dicepileid) {
        this.dicepileid = dicepileid;
    }

}