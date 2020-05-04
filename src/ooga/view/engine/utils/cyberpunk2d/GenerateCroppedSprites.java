package ooga.view.engine.utils.cyberpunk2d;

import ooga.view.engine.utils.SpriteCropper;

/**
 * a temporary util class that crops sprites
 * @author qiaoyi fang
 */
public class GenerateCroppedSprites {

  // E_spritesheet

  private String DIR_E_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String FILENAME_E_ATT1_BODY = "E_ATT1_BODY.png";
  private String FILENAME_E_ATT1_WEAPON = "E_ATT1_WEAPON.png";

  private String DIR_E_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String FILENAME_E_ATT2_BODY = "E_ATT2_BODY.png";
  private String FILENAME_E_ATT2_WEAPON = "E_ATT2_WEAPON.png";

  private String DIR_E_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String FILENAME_E_ATT3_BODY = "E_ATT3_BODY.png";
  private String FILENAME_E_ATT3_WEAPON = "E_ATT3_WEAPON.png";

  private String DIR_E_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\DEATH_9frames";
  private String FILENAME_E_DEATH_BODY = "E_DEATH_BODY.png";
  private String FILENAME_E_DEATH_WEAPON = "E_DEATH_WEAPON.png";

  private String DIR_E_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\IDLE_11frames";
  private String FILENAME_E_IDLE_BODY = "E_IDLE_BODY.png";
  private String FILENAME_E_IDLE_WEAPON = "E_IDLE_WEAPON.png";

  private String DIR_E_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\PRE-SPRINT_7frames";
  private String FILENAME_E_PRESPRINT_BODY = "E_PRESPRINT_BODY.png";
  private String FILENAME_E_PRESPRINT_WEAPON = "E_PRESPRINT_WEAPON.png";

  private String DIR_E_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\SPRINT_6frames";
  private String FILENAME_E_SPRINT_BODY = "E_SPRINT_BODY.png";
  private String FILENAME_E_SPRINT_WEAPON = "E_SPRINT_WEAPON.png";

  // N_spritesheet

  private String DIR_N_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String FILENAME_N_ATT1_BODY = "N_ATT1_BODY.png";
  private String FILENAME_N_ATT1_WEAPON = "N_ATT1_WEAPON.png";

  private String DIR_N_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String FILENAME_N_ATT2_BODY = "N_ATT2_BODY.png";
  private String FILENAME_N_ATT2_WEAPON = "N_ATT2_WEAPON.png";

  private String DIR_N_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String FILENAME_N_ATT3_BODY = "N_ATT3_BODY.png";
  private String FILENAME_N_ATT3_WEAPON = "N_ATT3_WEAPON.png";

  private String DIR_N_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\DEATH_9frames";
  private String FILENAME_N_DEATH_BODY = "N_DEATH_BODY.png";
  private String FILENAME_N_DEATH_WEAPON = "N_DEATH_WEAPON.png";

  private String DIR_N_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\IDLE_11frames";
  private String FILENAME_N_IDLE_BODY = "N_IDLE_BODY.png";
  private String FILENAME_N_IDLE_WEAPON = "N_IDLE_WEAPON.png";

  private String DIR_N_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\PRE-SPRINT_7frames";
  private String FILENAME_N_PRESPRINT_BODY = "N_PRESPRINT_BODY.png";
  private String FILENAME_N_PRESPRINT_WEAPON = "N_PRESPRINT_WEAPON.png";

  private String DIR_N_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\SPRINT_6frames";
  private String FILENAME_N_SPRINT_BODY = "N_SPRINT_BODY.png";
  private String FILENAME_N_SPRINT_WEAPON = "N_SPRINT_WEAPON.png";

  // NE spritesheet

  private String DIR_NE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String FILENAME_NE_ATT1_BODY = "NE_ATT1_BODY.png";
  private String FILENAME_NE_ATT1_WEAPONfront = "NE_ATT1_WEAPONfront.png";
  private String FILENAME_NE_ATT1_WEAPONback = "NE_ATT1_WEAPONback.png";


  private String DIR_NE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String FILENAME_NE_ATT2_BODY = "NE_ATT2_BODY.png";
  private String FILENAME_NE_ATT2_WEAPONfront = "NE_ATT2_WEAPONfront.png";
  private String FILENAME_NE_ATT2_WEAPONback = "NE_ATT2_WEAPONback.png";

  private String DIR_NE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String FILENAME_NE_ATT3_BODY = "NE_ATT3_BODY.png";
  private String FILENAME_NE_ATT3_WEAPONfront = "NE_ATT3_WEAPONfront.png";
  private String FILENAME_NE_ATT3_WEAPONback = "NE_ATT3_WEAPONback.png";

  private String DIR_NE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\DEATH_9frames";
  private String FILENAME_NE_DEATH_BODY = "NE_DEATH_BODY.png";
  private String FILENAME_NE_DEATH_WEAPONfront = "NE_DEATH_WEAPONfront.png";

  private String DIR_NE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\IDLE_11frames";
  private String FILENAME_NE_IDLE_BODY = "NE_IDLE_BODY.png";
  private String FILENAME_NE_IDLE_WEAPONfront = "NE_IDLE_WEAPONfront.png";

  private String DIR_NE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\PRE-SPRINT_7frames";
  private String FILENAME_NE_PRESPRINT_BODY = "NE_PRERUN_BODY.png";
  private String FILENAME_NE_PRESPRINT_WEAPONfront = "NE_PRERUN_WEAPONfront.png";
  private String FILENAME_NE_PRESPRINT_WEAPONback = "NE_PRERUN_WEAPONback.png";

  private String DIR_NE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\SPRINT_6frames";
  private String FILENAME_NE_SPRINT_BODY = "NE_SPRINT_BODY.png";
  private String FILENAME_NE_SPRINT_WEAPONfront = "NE_SPRINT_WEAPONfront.png";

  // S Spritesheet

  private String DIR_S_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String FILENAME_S_ATT1_BODY = "S_ATT1_BODY.png";
  private String FILENAME_S_ATT1_WEAPON = "S_ATT1_WEAPON.png";

  private String DIR_S_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String FILENAME_S_ATT2_BODY = "S_ATT2_BODY.png";
  private String FILENAME_S_ATT2_WEAPON = "S_ATT2_WEAPON.png";

  private String DIR_S_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String FILENAME_S_ATT3_BODY = "S_ATT3_BODY.png";
  private String FILENAME_S_ATT3_WEAPON = "S_ATT3_WEAPON.png";

  private String DIR_S_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\DEATH_9frames";
  private String FILENAME_S_DEATH_BODY = "S_DEATH_BODY.png";
  private String FILENAME_S_DEATH_WEAPON = "S_DEATH_WEAPON.png";

  private String DIR_S_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\IDLE_11frames";
  private String FILENAME_S_IDLE_BODY = "S_IDLE_BODY.png";
  private String FILENAME_S_IDLE_WEAPON = "S_IDLE_WEAPON.png";

  private String DIR_S_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\PRE-SPRINT_7frames";
  private String FILENAME_S_PRESPRINT_BODY = "S_PRESPRINT_BODY.png";
  private String FILENAME_S_PRESPRINT_WEAPON = "S_PRESPRINT_WEAPON.png";

  private String DIR_S_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\SPRINT_6frames";
  private String FILENAME_S_SPRINT_BODY = "S_SPRINT_BODY.png";
  private String FILENAME_S_SPRINT_WEAPON = "S_SPRINT_WEAPON.png";

  // SE Spritesheet

  private String DIR_SE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String FILENAME_SE_ATT1_BODY = "SE_ATT1_BODY.png";
  private String FILENAME_SE_ATT1_WEAPON = "SE_ATT1_WEAPON.png";

  private String DIR_SE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String FILENAME_SE_ATT2_BODY = "SE_ATT2_BODY.png";
  private String FILENAME_SE_ATT2_WEAPON = "SE_ATT2_WEAPON.png";

  private String DIR_SE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String FILENAME_SE_ATT3_BODY = "SE_ATT3_BODY.png";
  private String FILENAME_SE_ATT3_WEAPON = "SE_ATT3_WEAPON.png";

  private String DIR_SE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\DEATH_9frames";
  private String FILENAME_SE_DEATH_BODY = "SE_DEATH_BODY.png";
  private String FILENAME_SE_DEATH_WEAPON = "SE_DEATH_WEAPON.png";

  private String DIR_SE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\IDLE_11frames";
  private String FILENAME_SE_IDLE_BODY = "SE_IDLE_BODY.png";
  private String FILENAME_SE_IDLE_WEAPON = "SE_IDLE_WEAPON.png";

  private String DIR_SE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\PRE-SPRINT_7frames";
  private String FILENAME_SE_PRESPRINT_BODY = "SE_PRESPRINT_BODY.png";
  private String FILENAME_SE_PRESPRINT_WEAPON = "SE_PRESPRINT_WEAPON.png";

  private String DIR_SE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\SPRINT_6frames";
  private String FILENAME_SE_SPRINT_BODY = "SE_SPRINT_BODY.png";
  private String FILENAME_SE_SPRINT_WEAPON = "SE_SPRINT_WEAPON.png";

  public GenerateCroppedSprites(){

    SpriteCropper cropper;

    // E Spritesheet

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT1, FILENAME_E_ATT1_BODY), 5, DIR_E_ATT1, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT1, FILENAME_E_ATT1_WEAPON), 5, DIR_E_ATT1, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT2, FILENAME_E_ATT2_BODY), 6, DIR_E_ATT2, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT2, FILENAME_E_ATT2_WEAPON), 6, DIR_E_ATT2, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT3, FILENAME_E_ATT3_BODY), 15, DIR_E_ATT3, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_ATT3, FILENAME_E_ATT3_WEAPON), 15, DIR_E_ATT3, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_DEATH, FILENAME_E_DEATH_BODY), 9, DIR_E_DEATH, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_DEATH, FILENAME_E_DEATH_WEAPON), 9, DIR_E_DEATH, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_IDLE, FILENAME_E_IDLE_BODY), 11, DIR_E_IDLE, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_IDLE, FILENAME_E_IDLE_WEAPON), 11, DIR_E_IDLE, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_PRESPRINT, FILENAME_E_PRESPRINT_BODY), 7, DIR_E_PRESPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_PRESPRINT, FILENAME_E_PRESPRINT_WEAPON), 7, DIR_E_PRESPRINT, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_SPRINT, FILENAME_E_SPRINT_BODY), 6, DIR_E_SPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_E_SPRINT, FILENAME_E_SPRINT_WEAPON), 6, DIR_E_SPRINT, "WEAPON");

    // N Spritesheet

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT1, FILENAME_N_ATT1_BODY), 5, DIR_N_ATT1, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT1, FILENAME_N_ATT1_WEAPON), 5, DIR_N_ATT1, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT2, FILENAME_N_ATT2_BODY), 6, DIR_N_ATT2, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT2, FILENAME_N_ATT2_WEAPON), 6, DIR_N_ATT2, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT3, FILENAME_N_ATT3_BODY), 15, DIR_N_ATT3, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_ATT3, FILENAME_N_ATT3_WEAPON), 15, DIR_N_ATT3, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_DEATH, FILENAME_N_DEATH_BODY), 9, DIR_N_DEATH, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_DEATH, FILENAME_N_DEATH_WEAPON), 9, DIR_N_DEATH, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_IDLE, FILENAME_N_IDLE_BODY), 11, DIR_N_IDLE, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_IDLE, FILENAME_N_IDLE_WEAPON), 11, DIR_N_IDLE, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_PRESPRINT, FILENAME_N_PRESPRINT_BODY), 7, DIR_N_PRESPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_PRESPRINT, FILENAME_N_PRESPRINT_WEAPON), 7, DIR_N_PRESPRINT, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_SPRINT, FILENAME_N_SPRINT_BODY), 6, DIR_N_SPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_N_SPRINT, FILENAME_N_SPRINT_WEAPON), 6, DIR_N_SPRINT, "WEAPON");

    // NE Spritesheet

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT1, FILENAME_NE_ATT1_BODY), 5, DIR_NE_ATT1, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT1, FILENAME_NE_ATT1_WEAPONfront), 5, DIR_NE_ATT1, "WEAPONfront");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT1, FILENAME_NE_ATT1_WEAPONback), 5, DIR_NE_ATT1, "WEAPONback");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT2, FILENAME_NE_ATT2_BODY), 6, DIR_NE_ATT2, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT2, FILENAME_NE_ATT2_WEAPONfront), 6, DIR_NE_ATT2, "WEAPONfront");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT2, FILENAME_NE_ATT2_WEAPONback), 6, DIR_NE_ATT2, "WEAPONback");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT3, FILENAME_NE_ATT3_BODY), 15, DIR_NE_ATT3, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT3, FILENAME_NE_ATT3_WEAPONfront), 15, DIR_NE_ATT3, "WEAPONfront");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_ATT3, FILENAME_NE_ATT3_WEAPONback), 15, DIR_NE_ATT3, "WEAPONback");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_DEATH, FILENAME_NE_DEATH_BODY), 9, DIR_NE_DEATH, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_DEATH, FILENAME_NE_DEATH_WEAPONfront), 9, DIR_NE_DEATH, "WEAPONfront");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_IDLE, FILENAME_NE_IDLE_BODY), 11, DIR_NE_IDLE, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_IDLE, FILENAME_NE_IDLE_WEAPONfront), 11, DIR_NE_IDLE, "WEAPONfront");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_PRESPRINT, FILENAME_NE_PRESPRINT_BODY), 7, DIR_NE_PRESPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_PRESPRINT, FILENAME_NE_PRESPRINT_WEAPONfront), 7, DIR_NE_PRESPRINT, "WEAPONfront");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_PRESPRINT, FILENAME_NE_PRESPRINT_WEAPONback), 7, DIR_NE_PRESPRINT, "WEAPONback");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_SPRINT, FILENAME_NE_SPRINT_BODY), 6, DIR_NE_SPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_NE_SPRINT, FILENAME_NE_SPRINT_WEAPONfront), 6, DIR_NE_SPRINT, "WEAPONfront");

    // S Spritesheet

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT1, FILENAME_S_ATT1_BODY), 5, DIR_S_ATT1, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT1, FILENAME_S_ATT1_WEAPON), 5, DIR_S_ATT1, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT2, FILENAME_S_ATT2_BODY), 6, DIR_S_ATT2, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT2, FILENAME_S_ATT2_WEAPON), 6, DIR_S_ATT2, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT3, FILENAME_S_ATT3_BODY), 15, DIR_S_ATT3, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_ATT3, FILENAME_S_ATT3_WEAPON), 15, DIR_S_ATT3, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_DEATH, FILENAME_S_DEATH_BODY), 9, DIR_S_DEATH, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_DEATH, FILENAME_S_DEATH_WEAPON), 9, DIR_S_DEATH, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_IDLE, FILENAME_S_IDLE_BODY), 11, DIR_S_IDLE, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_IDLE, FILENAME_S_IDLE_WEAPON), 11, DIR_S_IDLE, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_PRESPRINT, FILENAME_S_PRESPRINT_BODY), 7, DIR_S_PRESPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_PRESPRINT, FILENAME_S_PRESPRINT_WEAPON), 7, DIR_S_PRESPRINT, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_SPRINT, FILENAME_S_SPRINT_BODY), 6, DIR_S_SPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_S_SPRINT, FILENAME_S_SPRINT_WEAPON), 6, DIR_S_SPRINT, "WEAPON");

    // SE Spritesheet

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT1, FILENAME_SE_ATT1_BODY), 5, DIR_SE_ATT1, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT1, FILENAME_SE_ATT1_WEAPON), 5, DIR_SE_ATT1, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT2, FILENAME_SE_ATT2_BODY), 6, DIR_SE_ATT2, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT2, FILENAME_SE_ATT2_WEAPON), 6, DIR_SE_ATT2, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT3, FILENAME_SE_ATT3_BODY), 15, DIR_SE_ATT3, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_ATT3, FILENAME_SE_ATT3_WEAPON), 15, DIR_SE_ATT3, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_DEATH, FILENAME_SE_DEATH_BODY), 9, DIR_SE_DEATH, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_DEATH, FILENAME_SE_DEATH_WEAPON), 9, DIR_SE_DEATH, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_IDLE, FILENAME_SE_IDLE_BODY), 11, DIR_SE_IDLE, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_IDLE, FILENAME_SE_IDLE_WEAPON), 11, DIR_SE_IDLE, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_PRESPRINT, FILENAME_SE_PRESPRINT_BODY), 7, DIR_SE_PRESPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_PRESPRINT, FILENAME_SE_PRESPRINT_WEAPON), 7, DIR_SE_PRESPRINT, "WEAPON");

    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_SPRINT, FILENAME_SE_SPRINT_BODY), 6, DIR_SE_SPRINT, "BODY");
    cropper = new SpriteCropper(String.format("%s\\%s",DIR_SE_SPRINT, FILENAME_SE_SPRINT_WEAPON), 6, DIR_SE_SPRINT, "WEAPON");

  }

}
