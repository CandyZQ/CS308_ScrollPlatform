## Description

The frontend will automatically remove the Agent2DView object from the map that maintain all rendering agents when the status of object is updated to DEATH after its death animation is finished.

## Expected Behavior

The expected behavior would be that the agent disappear after it death animation is displayed and the game continues.

## Current Behavior

The current behavior is that the window will quit after a null pointer error in the rendering loop of agent map.

## Steps to Reproduce

 1. Create an Agent2DView object that used as the example.
 2. Bind a key to the trigger of DEATH state of the agent.

## Failure Logs

a null pointer exception on the agentMap that I forgot to copy.

## Hypothesis for Fixing the Bug

The delete of the Agent2DView object from the map needs to be conducted within the rendering loop to avoid concurrency in multiple threads.