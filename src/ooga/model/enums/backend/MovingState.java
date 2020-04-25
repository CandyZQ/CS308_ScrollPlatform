package ooga.model.enums.backend;

public enum  MovingState {
  // Big Boy
  ATTACK,
  DEATH,
  IDLE,
  HURT,
  WALK,

  // Engineer Bot
//  ATTACK,
//  DEATH,
//  HURT,
//  WALK,
//  IDLE,
  JUMP,
  FALL,
  SUMMON_TURRET,
  SUMMON_BIGBOY,
  SUMMON_WATCHER,

  // Load Soldier
//  ATTACK,
//  DEATH,
//  HURT,
//  FALL,
//  JUMP,
//  IDLE,
//  WALK

  // Player
//  IDLE,
  SPRINT,
// HURT,
  ATTACK1,
  ATTACK3,

  // Shield
//  ATTACK,
//  DEATH,
//  HURT,
//  FALL,
  JUMPING,
  SHIELD_HURT,
//  IDLE,
//  WALK

  // Turret
  ATTACK_1_SIDE,
  ATTACK_2_SIDE,
  ATTACK_BLASTS,
//  HURT,
//  IDLE

  // Watcher Bot
//  ATTACK,
//  HURT,
//  DEATH,
  WARNING_WALK,
  WARNING_IDLE,
  WARNING,
//  IDLE,
//  WALK
}
