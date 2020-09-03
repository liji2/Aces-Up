package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

/**
 * Created by michaelhilton on 1/25/16.
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class Card implements Serializable {
	public int value;
	public Suit suit;

	public Card() {
		this.value = 2;
		this.suit = null;
	}

	@JsonCreator
	public Card(@JsonProperty("value") int value, @JsonProperty("suit") Suit newSuit) {
		this.value = value;
		this.suit = newSuit;

	}

	public abstract Suit getSuit();

	public int getValue() {
		return value;
	}

	public String toString() {
		return this.value + this.suit.toString();
	}
}
