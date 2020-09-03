package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Columns {

	//Attributes

	public java.util.List<CardPile> columns;

	//Methods

	public Columns(){
		columns = new ArrayList<>();
	}

	//Getter for columns
	public java.util.List<CardPile> getColumns() {
		return columns;
	}

	//Setter for columns
	public void setColumns(java.util.List<CardPile> newColumns) {
		columns = newColumns;
	}

	//Add a new CardPile to the list of columns
	public void addColumn(CardPile newColumn) {
		columns.add(newColumn);
	}

	//Return one of the columns
	@JsonIgnore public CardPile getColumn(int columnNum) {
		return columns.get(columnNum);
	}

	//Remove a card from a column
	public void remove(int columnNumber) {
		getColumn(columnNumber).popTopCard();
	}

	//Move a card between two columns
	public void move(int columnFrom, int columnTo) {
		Card cardToMove = getColumn(columnFrom).getTopCard();
		getColumn(columnFrom).popTopCard();
		getColumn(columnTo).addCard(cardToMove);
	}

}
