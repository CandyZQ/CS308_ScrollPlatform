## Description

The program throws null pointer exception before the game view is loaded

## Expected Behavior

No null pointer exceptions are thrown 

## Current Behavior

The program throws null pointer exception before the game view is loaded

## Steps to Reproduce

1. Run example game 2
2. Before animations loaded, exceptions are printed out in teh console 

## Hypothesis for Fixing the Bug

Gameview is called to check distance and attack between characters. Does not have impact on game performance, 
but can be removed with a try catch block and ignore the exception. 