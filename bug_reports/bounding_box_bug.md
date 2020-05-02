## Description

The bounding box implements the collision detection between map tiles and agents.

## Expected Behavior

1. Correctly calculate the center of meshes.
2. Correctly compare the collision by the given bounds specified by the object type (tiles and different agents have different shapes).

## Current Behavior

Failing at 1 -> cascading to 2.

## Steps to Reproduce

1. Set up two agents of different types and a basic map.
2. Use _sout_ to monitor the calculated center coordinates.

## Failure Logs

Agent almost roams freely on the map with weird air walls.

## Hypothesis for Fixing the Bug

Some trivial yet painful bug in the calculation of center. 