var game;
var count = 1; // For counting lines in error
var count_score = -55;


function display(game) {
    console.log(game);
    console.log(game.discardPile);
    console.log(game.ver);
    // Display Score
    $('.score').html("Score: "+game.score);
    // Display Cards Left
    $('.cardsLeft').html("Cards Left: "+game.cardsLeft)

    if (game.ver == 0) {
        $('.notbold').html("<ul><li><p>Four cards are dealt to four tableau piles.</p></li><li><p>Click on a card to remove it or move it to an empty column.</p></li><li><p>A card can be removed when another card showing at the top of another tableau pile has the same suit and a higher value. For instance, a 2 of diamonds can be removed if a 6 of diamonds is showing.</p></li><li><p>A 2 is the lowest value and an ace is higher than a king. Therefore, an ace cannot be removed.</p></li><li><p>When one of the four stacks is empty, any single card can be moved to that spot.</p></li><li><p>Click on the deck to deal one card to each of the four stacks.</p></li><li><p>A game is won if all cards have been removed except for the four aces.</p></li></ul>")
    }
    else {
        $('.notbold').html('<ul><li><p>Same playing style as Standard Game, but with a few changes.</p></li><li><p>Jokers are wild cards. They match with any suit and act as the highest valued card for that suit, which means you can remove any card from the top of a pile.</p></li><li><p>If the Joker card has been matched with another card in the game, both cards are removed from the game.</p></li><li><p>Spanish deck contains 50 cards and will result in the last dealt set of cards having only two cards.</p></li></u>')
    }

    //Removing old set of cards
    $('#c0 .cardColHolder').empty();
    $('#c1 .cardColHolder').empty();
    $('#c2 .cardColHolder').empty();
    $('#c3 .cardColHolder').empty();

   if (game["@class"] == "models.EnglishGame") {
        //adding new card images
        $.each(game.cols.columns[0].pile, function( key, val ) {
        $('#c0 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cards/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[1].pile, function( key, val ) {
        $( '#c1 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cards/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[2].pile, function( key, val ) {
        $( '#c2 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cards/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[3].pile, function( key, val ) {
        $( '#c3 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cards/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });
    }
    else {
        //adding new card images
        $.each(game.cols.columns[0].pile, function( key, val ) {
        $('#c0 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cardsSpanish/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[1].pile, function( key, val ) {
        $( '#c1 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cardsSpanish/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[2].pile, function( key, val ) {
        $( '#c2 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cardsSpanish/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });

        $.each(game.cols.columns[3].pile, function( key, val ) {
        $( '#c3 .cardColHolder').append('<div class="cardLocation"><img src="http://web.engr.oregonstate.edu/~hainesg/assignment/cardsSpanish/' + val.value.toString() + val.suit.charAt(0) + '.png"></div>');
        });
    }

    // Adding error message

    if(game.check == true){
        $('.error').append("<p>"+count+ ". Error: Invalid Move!</p>")
        count = count+1;
    }

    // Adding card image to discard pile
    if (game.discardPile.pile.length > 0 && game["@class"] == "models.EnglishGame")
        $('.discard').css('background-image', 'url(' + '"' + 'http://web.engr.oregonstate.edu/~hainesg/assignment/cards/' + game.discardPile.pile[game.discardPile.pile.length - 1].value.toString() + game.discardPile.pile[game.discardPile.pile.length - 1].suit.charAt(0) + '.png' + '"' + ')');
    else if (game.discardPile.pile.length > 0 && game["@class"] == "models.SpanishGame")
        $('.discard').css('background-image', 'url(' + '"' + 'http://web.engr.oregonstate.edu/~hainesg/assignment/cardsSpanish/' + game.discardPile.pile[game.discardPile.pile.length - 1].value.toString() + game.discardPile.pile[game.discardPile.pile.length - 1].suit.charAt(0) + '.png' + '"' + ')');


    //Remove deck image if there are no cards left
    if (game.deck.pile.length == 0)
        $('#dealButton').css('background-image','url()')
    else
        $('#dealButton').css('background-image','url(https://web.engr.oregonstate.edu/~liji2/deckcard.png)')

    //Scroll to bottom of log
    var scroll = document.getElementById("log");
    scroll.scrollTop = scroll.scrollHeight;
}


$.getJSON("http://localhost:8080/game", function( data ) {
display(data);
game = data;
});


$("#dealButton").click(function(){
    $.ajax({
        type: "POST",
        url: "/dealGame",
        data: JSON.stringify(game),
        success: function(data, status){console.log("Data: " + data + "\nStatus: " + status);
            game = data;
            display(data);
            if (game.check == false) {
                $('.error').append("<p>" + count + ". Dealt four cards</p>");
                count = count+1;
            }
        },
        contentType:"application/json; charset=utf-8",
        dataType:"json",
    });
});


function removeCard(colNumber){
    $.ajax({
        type: "POST",
        url: "/removeCard/"+colNumber,
        data: JSON.stringify(game),
        success: function(data, status){console.log("Data: " + data + "\nStatus: " + status);
            game = data;
            display(data);
            if (game.check == false) {
                $('.error').append("<p>" + count + ". Removed card from column " + (colNumber + 1) + "</p>");
                count = count+1;
            }
        },
        contentType:"application/json; charset=utf-8",
        dataType:"json",
    });
}

function moveCard(to, from){
    var colFrom = from;
    var colTo = to;

    $.ajax({
        type: "POST",
        url: "/moveCard/"+colFrom+"/"+colTo,
        data: JSON.stringify(game),
        success: function(data, status){console.log("Data: " + data + "\nStatus: " + status);
            game = data;
            display(data);

            if (game.check == false) {
                $('.error').append("<p>"+count+ ". Moved card from column " + (from + 1) + " to " + (to + 1) + "</p>");
                count = count+1;
            }
            else {
                $('.error').append("<p>"+count+ ". Error: Invalid Move!</p>")
                count = count+1;
            }
        },
        contentType:"application/json; charset=utf-8",
        dataType:"json",
    });
}

function columnAction(game, from) {
    var anyEmpty = false;
    var to;
    $.each(game.cols.columns, function(index, value) {
        if (value.pile.length == 0) {
            anyEmpty = true;
            to = index;
        }
    });

    if (game.cols.columns[from].pile.length <= 1)
        anyEmpty = false;

    if (anyEmpty == true) {
        moveCard(to, from);
    }
    else if (game.cols.columns[from].pile.length != 0) {
        removeCard(from);
    }
}


$("#c0").click(function() {
    columnAction(game, 0);
});
$("#c1").click(function() {
    columnAction(game, 1);
});
$("#c2").click(function() {
    columnAction(game, 2);
});
$("#c3").click(function() {
    columnAction(game, 3);
});

$("#resetGame").click(function(){
$.ajax({
  type: "POST",
  url: "/resetGame",
  data: JSON.stringify(game),
  success: function(data, status){console.log("Data: " + data + "\nStatus: " + status);
        game = data;
        display(data);},
  contentType:"application/json; charset=utf-8",
  dataType:"json",
});
$('.discard').css('background-image','none');
$('.error').empty();
count = 1;
});

$("#resetGameSpanish").click(function(){
$.ajax({
  type: "POST",
  url: "/resetGameSpanish",
  data: JSON.stringify(game),
  success: function(data, status){console.log("Data: " + data + "\nStatus: " + status);
        game = data;
        display(data);},
  contentType:"application/json; charset=utf-8",
  dataType:"json",
});
$('.discard').css('background-image','none');
$('.error').empty();
count = 1;
});

/* might need this later on
$(".switchDeck").click(function(){
    $a.jax({
        type: "POST",
        url: "/toggleDeck",
        data: JSON.stringify(game),
        success: function (data, status){
            console.log("Data: " + data + "\nStatus: " + status);
            game = data;
            display(data);
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"

    });
    $(switchDeck).toggleClass("switchEnglish");
    $(switchDeck).toggleClass("switchSpanish");

});

*/