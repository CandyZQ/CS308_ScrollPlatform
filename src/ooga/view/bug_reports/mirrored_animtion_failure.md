## Description

As the provided animation resources are mostly in one direction, additional steps need to be taken to mirror the texture. Currently, the mirrored texture are not being rendered. 

## Expected Behavior

The expected behavior would be that when the agent is moving west, the displaying animation would automatically reverse the texture for the action at the east direction.

## Current Behavior

The current behavior is that the animation remains the same

## Steps to Reproduce

 1. Create an Agent2DView object that used as the example.
 2. Bind basic keys to the directional moving of the agent.

## Failure Logs

Wrong animation on the screen

## Hypothesis for Fixing the Bug

Further investigation on the manipulation of texture coordinates (uv) on the meshes of agents.