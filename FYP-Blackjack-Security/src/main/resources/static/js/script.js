let bettingTotal = 0
let accountTotal = 0;
let totalWon = 0;
let totalLost = 0;

let initialBetAmount = 0;

//Player & Dealer Cards
let playerCards = [];
let dealerCards = [];
let cardDeck = [];

//create log file array
let logFile = [];
let logFileText = '';

//Card Tracker
let cardTracker = 0;

//Hand totals
let playerHandTotal = 0;
let dealerHandTotal = 0;

let wonOrLost = 0;
let numberHandsWon = 0;

//get betting information
$(document).ready(function(){

    //get log file information
    getLogFileInformation();

    //get betting information
    getBettingInformation();

    if(accountTotal !== 0){
        //update betting details
        updateBettingDetails();
    }

    //Remove Round Over Btn
    let roundOverBtn = document.getElementById("roundOverBtn");
    if (roundOverBtn.style.display === "none") {
        roundOverBtn.style.display = "block";
    } else {
        roundOverBtn.style.display = "none";
    }
});


//Initial Betting Methods
function place100Bet(){

    if(accountTotal === 0){
        document.getElementById("message").innerText = "You have no chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else if(accountTotal < 100){
        document.getElementById("message").innerText = "You have insufficient chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else{
        bettingTotal = bettingTotal + 100;
        document.getElementById("betTlt").innerText = 'Betting Total: €' + bettingTotal;
        accountTotal = accountTotal - 100;
        document.getElementById("accTlt").innerText = 'Account Total: €' + accountTotal;

        //Deal cards to the player and the dealer
        createShuffleCards();
        dealCards();
        removeBetFeatures();

        //update betting details
        updateBettingDetails();

        //set initial bet amount variable
        initialBetAmount = 100;

        //set log file text
        logFileText = 'placed at €100 bet';
    }

    logFile.push(logFileText);
    logFileText = '';
}

function place200Bet(){
    if(accountTotal === 0){
        document.getElementById("message").innerText = "You have no chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else if(accountTotal < 200){
        document.getElementById("message").innerText = "You have insufficient chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else{
        bettingTotal = bettingTotal + 200;
        document.getElementById("betTlt").innerText = 'Betting Total: €' +  bettingTotal;
        accountTotal = accountTotal - 200;
        document.getElementById("accTlt").innerText = 'Account Total: €' +  accountTotal;

        //Deal cards to the player and the dealer
        createShuffleCards();
        dealCards();
        removeBetFeatures();

        //update betting details
        updateBettingDetails();

        //set initial bet amount variable
        initialBetAmount = 200;

        //set log file text
        logFileText = 'placed at €200 bet';
    }

    logFile.push(logFileText);
    logFileText = '';
}

function place300Bet(){
    if(accountTotal === 0){
        document.getElementById("message").innerText = "You have no chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else if(accountTotal < 300){
        document.getElementById("message").innerText = "You have insufficient chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else{
        bettingTotal = bettingTotal + 300;
        document.getElementById("betTlt").innerText = 'Betting Total: €' +  bettingTotal;
        accountTotal = accountTotal - 300;
        document.getElementById("accTlt").innerText = 'Account Total: €' +  accountTotal;

        //Deal cards to the player and the dealer
        createShuffleCards();
        dealCards();
        removeBetFeatures();

        //update betting details
        updateBettingDetails();

        //set initial bet amount variable
        initialBetAmount = 300;

        //set log file text
        logFileText = 'placed at €300 bet';
    }

    logFile.push(logFileText);
    logFileText = '';
}

function place500Bet(){
    if(accountTotal === 0){
        document.getElementById("message").innerText = "You have no chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else if(accountTotal < 500){
        document.getElementById("message").innerText = "You have insufficient chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else{
        bettingTotal = bettingTotal + 500;
        document.getElementById("betTlt").innerText = 'Betting Total: €' +  bettingTotal;
        accountTotal = accountTotal - 500;
        document.getElementById("accTlt").innerText = 'Account Total: €' +  accountTotal;

        //Deal cards to the player and the dealer
        createShuffleCards();
        dealCards();
        removeBetFeatures();

        //update betting details
        updateBettingDetails();

        //set initial bet amount variable
        initialBetAmount = 500;

        //set log file text
        logFileText = 'placed at €500 bet';
    }

    logFile.push(logFileText);
    logFileText = '';
}

function place1000Bet(){
    if(accountTotal === 0){
        document.getElementById("message").innerText = "You have no chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else if(accountTotal < 1000){
        document.getElementById("message").innerText = "You have insufficient chips in your account to place any bets.";
        logFileText = 'tried to place bet with insufficient funds in account.';
        logFile.push(logFileText);
        logFileText = '';
    }else{
        bettingTotal = bettingTotal + 1000;
        document.getElementById("betTlt").innerText = 'Betting Total: €' +  bettingTotal;
        accountTotal = accountTotal - 1000;
        document.getElementById("accTlt").innerText = 'Account Total: €' +  accountTotal;

        //Deal cards to the player and the dealer
        createShuffleCards();

        //deal cards
        dealCards();
        //add new log file text
        logFileText = 'Cards have been dealt to both user and house'
        logFile.push(logFileText);
        logFileText = '';

        removeBetFeatures();

        //update betting details
        updateBettingDetails();

        //set initial bet amount variable
        initialBetAmount = 1000;

        //set log file text
        logFileText = 'placed at €1,000 bet';
    }

    logFile.push(logFileText);
    logFileText = '';
}

function createShuffleCards(){
    generateCardDeck();
    shuffleDeck();

    //add new log file text
    logFileText = 'Card deck has been generated & card deck has been shuffled.'
    addToLogFile();
    logFileText = '';
}

function generateCardDeck(){

    let suits = ['-H','-D', '-S', '-C'];
    let values = ['A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K'];

    //Loop through both arrays to create card deck
    for (let i = 0; i < values.length; i++) {
        for (let j = 0; j < suits.length; j++) {
            cardDeck.push(values.at(i) + suits.at(j));
        }
    }
}

function shuffleDeck(){
    for (let i = cardDeck.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [cardDeck[i], cardDeck[j]] = [cardDeck[j], cardDeck[i]];
    }
}

function dealCards(){
    //Deal player and dealer cards
    playerCards.push(cardDeck.at(0));
    dealerCards.push(cardDeck.at(1));
    playerCards.push(cardDeck.at(2));
    dealerCards.push(cardDeck.at(3));

    cardTracker = 4;
}

function roundOver(){

    //add new log file text
    logFileText = 'this round is over and new round is commencing.';
    logFile.push(logFileText);
    logFileText = '';

    let roundOverBtn = document.getElementById("roundOverBtn");

    document.getElementById("tltWon").innerText = 'Total Won: €' +  totalWon;
    document.getElementById("tltLst").innerText = 'Total Lost: €' +  totalLost;
    document.getElementById("betTlt").innerText = 'Betting Total: €' +  bettingTotal;

    //Show Round Over Btn
    if (roundOverBtn.style.display === "none") {
        roundOverBtn.style.display = "block";
    } else {
        roundOverBtn.style.display = "none";
    }
}

function dealNewRound() {

    //Remove Round Over Btn
    let roundOverBtn = document.getElementById("roundOverBtn");
    if (roundOverBtn.style.display === "none") {
        roundOverBtn.style.display = "block";
    } else {
        roundOverBtn.style.display = "none";
    }

    //reset all variables for new round
    initialBetAmount = 0;

    playerCards = [];
    dealerCards = [];

    //Card Tracker
    cardTracker = 0;

    //Hand totals
    playerHandTotal = 0;
    dealerHandTotal = 0;

    //display bet features
    addBetFeatures();

    //remove cards displayed
    let playerHandElement = document.getElementById('playerHand');
    playerHandElement.innerHTML = '';

    let dealerHandElement = document.getElementById('dealerHand');
    dealerHandElement.innerHTML = '';
}

function addBetFeatures(){
    let chip100 = document.getElementById("100chip");
    let chip200 = document.getElementById("200chip");
    let chip300 = document.getElementById("300chip");
    let chip500 = document.getElementById("500chip");
    let chip1000 = document.getElementById("1000chip");

    //Remove 100 Chip
    if (chip100.style.display === "none") {
        chip100.style.display = "block";
    } else {
        chip100.style.display = "none";
    }

    //Remove 200 Chip
    if (chip200.style.display === "none") {
        chip200.style.display = "block";
    } else {
        chip200.style.display = "none";
    }

    //Remove 300 Chip
    if (chip300.style.display === "none") {
        chip300.style.display = "block";
    } else {
        chip300.style.display = "none";
    }

    //Remove 500 Chip
    if (chip500.style.display === "none") {
        chip500.style.display = "block";
    } else {
        chip500.style.display = "none";
    }

    //Remove 1000 Chip
    if (chip1000.style.display === "none") {
        chip1000.style.display = "block";
    } else {
        chip1000.style.display = "none";
    }
}

function removeBetFeatures(){
    let chip100 = document.getElementById("100chip");
    let chip200 = document.getElementById("200chip");
    let chip300 = document.getElementById("300chip");
    let chip500 = document.getElementById("500chip");
    let chip1000 = document.getElementById("1000chip");

    //Remove 100 Chip
    if (chip100.style.display === "none") {
        chip100.style.display = "block";
    } else {
        chip100.style.display = "none";
    }

    //Remove 200 Chip
    if (chip200.style.display === "none") {
        chip200.style.display = "block";
    } else {
        chip200.style.display = "none";
    }

    //Remove 300 Chip
    if (chip300.style.display === "none") {
        chip300.style.display = "block";
    } else {
        chip300.style.display = "none";
    }

    //Remove 500 Chip
    if (chip500.style.display === "none") {
        chip500.style.display = "block";
    } else {
        chip500.style.display = "none";
    }

    //Remove 1000 Chip
    if (chip1000.style.display === "none") {
        chip1000.style.display = "block";
    } else {
        chip1000.style.display = "none";
    }

    displayHands();
}

function displayHands(){

    let playerHandElement = document.getElementById('playerHand');
    playerHandElement.innerHTML = '';

    for (let card of playerCards){

        //Creating Card Image
        let cardImage = document.createElement('img');
        cardImage.width = 95;
        cardImage.height = 150;
        cardImage.alt = card;
        cardImage.src = '/images/' + card + '.png';
        cardImage.style.borderTopRightRadius = '10px';
        cardImage.style.borderTopLeftRadius = '10px';
        cardImage.style.borderBottomRightRadius = '10px';
        cardImage.style.borderBottomLeftRadius = '10px';
        cardImage.style.margin = '15px';

        playerHandElement.appendChild(cardImage);
    }

    let dealerHandElement = document.getElementById('dealerHand');
    dealerHandElement.innerHTML = '';

    for (let card of dealerCards){

        //Creating Card Image
        let dealerCardImage = document.createElement('img');
        dealerCardImage.width = 95;
        dealerCardImage.height = 150;
        dealerCardImage.alt = card;
        dealerCardImage.src = '/images/' + card + '.png';
        dealerCardImage.style.borderTopRightRadius = '10px';
        dealerCardImage.style.borderTopLeftRadius = '10px';
        dealerCardImage.style.borderBottomRightRadius = '10px';
        dealerCardImage.style.borderBottomLeftRadius = '10px';
        dealerCardImage.style.margin = '15px';

        dealerHandElement.appendChild(dealerCardImage);
    }
}

function hit(){

    //add new log file text
    logFileText = 'has chosen to hit'
    logFile.push(logFileText);
    logFileText = '';

    //Count the players hand
    countPlayersHand();

    if(playerHandTotal === 21){
        window.alert('you have won, you got blackjack');

        //increment hands won var
        numberHandsWon = numberHandsWon + 1;

        //increment total won
        totalWon = totalWon + bettingTotal;

        //add new log file text
        logFileText = 'has won this round as they have gotten blackjack.';
        logFile.push(logFileText);
        logFileText = '';

        //call game over method
        roundOver();
    }else{

        //Give Player another Card
        playerCards.push(cardDeck.at(cardTracker));
        cardTracker++;

        //Check hand value after new card has been delt
        //Count the players hand
        countPlayersHand();

        if(playerHandTotal === 21){

            window.alert('you have won, you got blackjack');

            //increment hands won var
            numberHandsWon = numberHandsWon + 1;

            //increment win total
            totalWon = totalWon + bettingTotal;

            //add new log file text
            logFileText = 'has won this round as they have been dealt another card and gotten blackjack.';
            logFile.push(logFileText);
            logFileText = '';

            //call game over method
            roundOver();

        }else if(playerHandTotal < 21){

            //Generate Card Images
            playerCardImageGeneration();

        }else if(playerHandTotal > 21){

            window.alert('you have gone bust, you lose');

            //Generate Card Images
            playerCardImageGeneration();
            //increment total lost
            totalLost = totalLost + bettingTotal;

            //add new log file text
            logFileText = 'has gone bust and lost this round.';
            logFile.push(logFileText);
            logFileText = '';

            //call game over method
            roundOver();
        }
    }
    let playerHandTotalElement = document.getElementById('playerHandTotal');
    playerHandTotalElement.innerHTML = playerHandTotal;
}

function split(){

}

function stand(){

}

function doubleDown(){

    //this method is to double the first bet placed. Once doubled down, bet is doubled and one extra card is delt.

    //add new log file text
    logFileText = 'player has chosen to doubled down.';
    logFile.push(logFileText);
    logFileText = '';

    bettingTotal = (initialBetAmount * 2);
    accountTotal = accountTotal - initialBetAmount;

    //update betting details
    updateBettingDetails();

    //Give Player another Card
    playerCards.push(cardDeck.at(cardTracker));
    cardTracker++;

    //Check hand value after new card has been delt
    //Count the players hand
    countPlayersHand();

    if(playerHandTotal === 21){
        window.alert('you have won, you got blackjack');

        //increment total won
        totalWon = totalWon + bettingTotal;

        //increment hands won var
        numberHandsWon = numberHandsWon + 1;

        //add new log file text
        logFileText = 'has won this round as they have been dealt another card and gotten blackjack.';
        logFile.push(logFileText);
        logFileText = '';

    }else if(playerHandTotal < 21){
        //Generate Card Images
        playerCardImageGeneration();

        do {
            //count dealers hand
            countDealersHand();
            if (dealerHandTotal < 17){
                //Give dealer another Card
                dealerCards.push(cardDeck.at(cardTracker));
                cardTracker++;

                countDealersHand();
            }
        }while(dealerHandTotal < 17);

        //compare dealers hand to players hand
        if(dealerHandTotal > playerHandTotal || dealerHandTotal === 21){
            window.alert('dealer has won');

            //increment total lost
            totalLost = totalLost + bettingTotal;

            //add new log file text
            logFileText = 'has lost this round and the dealer has won.';
            logFile.push(logFileText);
            logFileText = '';

        }else{
            window.alert('player has won');

            //increment total won
            totalWon = totalWon + bettingTotal;

            //increment hands won var
            numberHandsWon = numberHandsWon + 1;

            //add new log file text
            logFileText = 'has won this round'
            logFile.push(logFileText);
            logFileText = '';
        }

        //generate dealer cards
        dealerCardImageGeneration();

    }else if(playerHandTotal > 21){
        window.alert('you have gone bust, you lose');

        //increment total lost
        totalLost = totalLost +  bettingTotal;

        //add new log file text
        logFileText = 'has lost this round has you have gone bust.';
        logFile.push(logFileText);
        logFileText = '';

        //Generate Card Images
        playerCardImageGeneration();
    }

    let playerHandTotalElement = document.getElementById('playerHandTotal');
    playerHandTotalElement.innerHTML = playerHandTotal;

    roundOver();
}

function countPlayersHand(){

    let cardValues = [];
    let value = '';
    let AceValue = false;
    playerHandTotal = 0;

    for (let i = 0; i < playerCards.length; i++) {
        value = playerCards.at(i);
        let Values = value.split("-");
        cardValues.push(Values[0]);
    }

    //Count the card values & change J,Q,K to 10 & A to 11 or 1
    for (let i = 0; i < cardValues.length; i++) {
        value = cardValues.at(i);
        if(value === 'J' || value === 'Q' || value === 'K'){
            playerHandTotal = playerHandTotal + 10;
        }else if(value === 'A'){
            AceValue = true;
        }else{
            playerHandTotal = playerHandTotal + parseInt(value);
        }
    }

    //check to see if Ace should be 1 or 11
    if(AceValue === true){
        let diff ;
        diff = 21 - playerHandTotal;
        if (diff > 11){
            playerHandTotal = playerHandTotal + 1;
        }else if(diff <= 11){
            playerHandTotal = playerHandTotal + 11;
        }
    }
}

function countDealersHand(){
    let cardValues = [];
    let value = '';
    let AceValue = false;
    dealerHandTotal = 0;

    for (let i = 0; i < dealerCards.length; i++) {
        value = dealerCards.at(i);
        let Values = value.split("-");
        cardValues.push(Values[0]);
    }

    //Count the card values & change J,Q,K to 10 & A to 11 or 1
    for (let i = 0; i < cardValues.length; i++) {
        value = cardValues.at(i);
        if(value === 'J' || value === 'Q' || value === 'K'){
            dealerHandTotal = dealerHandTotal + 10;
        }else if(value === 'A'){
            AceValue = true;
        }else{
            dealerHandTotal = dealerHandTotal + parseInt(value);
        }
    }

    //check to see if Ace should be 1 or 11
    if(AceValue === true){
        let diff ;
        diff = 21 - dealerHandTotal;
        if (diff > 11){
            dealerHandTotal = dealerHandTotal + 1;
        }else if(diff <= 11){
            dealerHandTotal = dealerHandTotal + 11;
        }
    }
}

function playerCardImageGeneration(){

    let playerHandElement = document.getElementById('playerHand');
    playerHandElement.innerHTML = '';

    for (let card of playerCards){

        //Creating Card Image
        let cardImage = document.createElement('img');
        cardImage.width = 95;
        cardImage.height = 150;
        cardImage.alt = card;
        cardImage.src = '/images/' + card + '.png';
        cardImage.style.borderTopRightRadius = '10px';
        cardImage.style.borderTopLeftRadius = '10px';
        cardImage.style.borderBottomRightRadius = '10px';
        cardImage.style.borderBottomLeftRadius = '10px';
        cardImage.style.margin = '15px';

        playerHandElement.appendChild(cardImage);
    }
}

function dealerCardImageGeneration(){
    let dealerHandElement = document.getElementById('dealerHand');
    dealerHandElement.innerHTML = '';

    for (let card of dealerCards){

        //Creating Card Image
        let cardImage = document.createElement('img');
        cardImage.width = 95;
        cardImage.height = 150;
        cardImage.alt = card;
        cardImage.src = '/images/' + card + '.png';
        cardImage.style.borderTopRightRadius = '10px';
        cardImage.style.borderTopLeftRadius = '10px';
        cardImage.style.borderBottomRightRadius = '10px';
        cardImage.style.borderBottomLeftRadius = '10px';
        cardImage.style.margin = '15px';

        dealerHandElement.appendChild(cardImage);
    }
}

function updateBettingDetails(){

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/updateGameDetails", // URL of your Spring Boot controller
        data: JSON.stringify(accountTotal),
        success: function (data) {
            // Handle success response
            console.log("POST request successful", data);
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error("POST request failed", error);
        }
    });
}

function getBettingInformation(){
    $.ajax({
        type: "GET",
        url: "/retrieveBettingDetails", // URL of your Spring Boot controller
        success: function (data) {
            accountTotal = data;
            //window.alert(credits)
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error("GET request failed", error);
        }
    });
}

function gameOver(){

    var gameDetailsData = {
        gameId: 0,
        playerId: 0,
        totalBet: bettingTotal,
        totalWon: totalWon,
        totalLost: totalLost,
        numberHandsWon: numberHandsWon,
        gameStartTime: 0,
        gameEndTime: 0
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addGameDetailsToDb", // URL of your Spring Boot controller
        data: JSON.stringify(gameDetailsData),
        success: function (data) {
            // Handle success response
            console.log("POST request successful", data);
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error("POST request failed", error);
        }
    });

    //add new log file text
    logFileText = 'Game is now over.';
    logFile.push(logFileText);
    addToLogFile();
    logFileText = '';

    window.location.href = "/home";

}

function getLogFileInformation(){

    $.ajax({
        type: "GET",
        url: "/getLogFileInformation", // URL of your Spring Boot controller
        success: function (data) {
            logFile = data;
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error("GET request failed", error);
        }
    });

}

function addToLogFile(){

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/addToLogFile", // URL of your Spring Boot controller
        data: JSON.stringify(logFile),
        success: function (data) {
            // Handle success response
            console.log("POST request successful", data);
        },
        error: function (xhr, status, error) {
            // Handle error response
            console.error("POST request failed", error);
        }
    });

}