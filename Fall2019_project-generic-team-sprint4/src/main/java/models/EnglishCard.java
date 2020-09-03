package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by michaelhilton on 1/25/16.
 */

public class EnglishCard extends Card implements Serializable {

    @JsonCreator
    public EnglishCard(@JsonProperty("value") int value, @JsonProperty("suit") EnglishSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    public EnglishSuit getSuit() {
        return (EnglishSuit) suit;
    }
}
