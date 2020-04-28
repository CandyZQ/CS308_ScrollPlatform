## Description

The agent needs to move in a given direction on the screen when its status is updated to _MOVE_.
## Expected Behavior

The expected behavior would be that the agent is translated in the given direction at delta distance vector.
## Current Behavior

The current behavior is that the agent is stuck.

## Steps to Reproduce

 1. Create an Agent2DView object that used as the example.
 2. Bind basic keys to the directional moving of the agent.

## Failure Logs

On the screen

## Hypothesis for Fixing the Bug

Further investigation on the state update of the agent (probably broken at the refactoring).