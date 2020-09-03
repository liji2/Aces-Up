/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.CardPile;
import models.Columns;
import models.Game;
import models.EnglishGame;
import models.SpanishGame;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;


@Singleton
public class ApplicationController {

    public Result index() {
        return Results.html().template("views/AcesUp/AcesUp.ftl.html");
    }
    
    public Result gameGet(){
        EnglishGame g = new EnglishGame();
        g.buildDeck();
        g.shuffle();
        g.dealFour();
        g.check = false;

        return Results.json().render(g);
    }

    // INITIALIZE SPANISH GAME
    public Result gameGetSpanish() {
        SpanishGame g = new SpanishGame();
        g.buildDeck();
        g.shuffle();
        g.dealFour();
        g.check = false;

        return Results.json().render(g);
    }

    public Result dealPost(Context context, Game g) {
        if(context.getRequestPath().contains("deal")){
            g.dealFour();
        }
        g.check = false;
        return Results.json().render(g);
    }

    public Result removeCard(Context context, @PathParam("column") int colNumber, Game g){
        g.remove(colNumber);
        //g.add_score = true;
        return  Results.json().render(g);
    }

    public Result moveCard(Context context, @PathParam("columnFrom") int colFrom, @PathParam("columnTo") int colTo, Game g){
        g.move(colFrom,colTo);
        return  Results.json().render(g);
    }
  
    public Result resetGame(Context context) {
        EnglishGame g = new EnglishGame();
        g.buildDeck();
        g.shuffle();
        g.dealFour();
        g.score = 0;
        g.cardsLeft = 52;
        g.ver = 0;

        return Results.json().render(g);
    }

    // INITIALIZE RESET GAME FOR SPANISH VERSION
    public Result resetGameSpanish(Context context) {
        SpanishGame g = new SpanishGame(); //we need a spanish game for this instead of regular game, rules need to be changed for this
        g.buildDeck();
        g.shuffle();
        g.dealFour();
        g.score = 0;
        g.cardsLeft = 50;
        g.ver = 1;

        return Results.json().render(g);
    }

    /*
    public Result switchDeck(Context context){

        Game g = new Game(); //new game for Spanish Deck
        g.shuffle();
        g.dealFour();
        g.score = 0;
        g.cardsLeft = 46;
        return Results.json().render(g);
    }
     */
}