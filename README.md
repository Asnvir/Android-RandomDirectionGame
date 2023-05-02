# Random Direction Game

## Overview
This is a simple Android game called "Random Direction Game". The player has to mimic the steps of the computer by pressing the arrows on the screen. The order of the arrows is determined by the player's ID number, which is entered by user. If the user successfully follows the sequence of directions, a message is displayed showing a random state from state.json that was downloaded from the server.Otherwise, show failed.

## Acceptable Input
The ID number entered by the user must be at least 1 digit long. If the user enters no digits, the game will display a toast message informing him to enter an ID with at least one digit.

## How to play
1.  Enter ID number (at elast 1 digit).
2.  The ID number is converted into a sequence of steps using the modulo 4. For example, for ID number 0123, the win sequence will be up, right, down, left.
  - digit%4=0 is up direction
  - digit%4=1 is right direction
  - digit%4=2 is down direction
  - digit%4=3 is left direction
3.   Use the arrow keys on your keyboard to move.
4.  If you successfully follow the sequence of directions, a message is displayed showing a random state from state.json that was downloaded from the server.

## Zero-Width Space
The game makes a server call to retrieve the JSON of statements to display to the user upon winning the game. However, it's important to note that the server URL provided by the developer has zero-width space characters that need to be removed for the game to function properly. This has been done in the code provided.

## Images of app
<p align="center">
  <img src="https://user-images.githubusercontent.com/51398263/235799287-d1ac1e94-2935-4d6a-90d9-74ab085fc3c1.png" width="200" height ="350"/>
  <img src="https://user-images.githubusercontent.com/51398263/235799476-467f58ed-e7ca-43be-8da4-09b913860e9a.png" width="200" height ="350"/>
</p>
