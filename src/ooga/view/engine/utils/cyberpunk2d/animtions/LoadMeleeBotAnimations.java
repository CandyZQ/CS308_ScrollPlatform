package ooga.view.engine.utils.cyberpunk2d.animtions;

import com.google.gson.internal.LinkedHashTreeMap;
import java.io.IOException;
import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;

public class LoadMeleeBotAnimations {

  private static int fps = 18;
  private static LinkedHashTreeMap<String, Animation2D> meleeDict;

  // E sprintsheet
  private static String DIR_E_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private static String DIR_E_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private static String DIR_E_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private static String DIR_E_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\DEATH_9frames";
  private static String DIR_E_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\IDLE_11frames";
  private static String DIR_E_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\PRE-SPRINT_7frames";
  private static String DIR_E_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\SPRINT_6frames";

  // N_spritesheet

  private static String DIR_N_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private static String DIR_N_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private static String DIR_N_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private static String DIR_N_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\DEATH_9frames";
  private static String DIR_N_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\IDLE_11frames";
  private static String DIR_N_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\PRE-SPRINT_7frames";
  private static String DIR_N_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\SPRINT_6frames";

  // NE spritesheet

  private static String DIR_NE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private static String DIR_NE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private static String DIR_NE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private static String DIR_NE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\DEATH_9frames";
  private static String DIR_NE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\IDLE_11frames";
  private static String DIR_NE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\PRE-SPRINT_7frames";
  private static String DIR_NE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\SPRINT_6frames";

  // S Spritesheet

  private static String DIR_S_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private static String DIR_S_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private static String DIR_S_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private static String DIR_S_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\DEATH_9frames";
  private static String DIR_S_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\IDLE_11frames";
  private static String DIR_S_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\PRE-SPRINT_7frames";
  private static String DIR_S_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\SPRINT_6frames";

  // SE Spritesheet

  private static String DIR_SE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private static String DIR_SE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private static String DIR_SE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private static String DIR_SE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\DEATH_9frames";
  private static String DIR_SE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\IDLE_11frames";
  private static String DIR_SE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\PRE-SPRINT_7frames";
  private static String DIR_SE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\SPRINT_6frames";

  public LoadMeleeBotAnimations(){
    meleeDict = new LinkedHashTreeMap<String, Animation2D>();
  }

  //Key: DIR_ACTION
  public static Map<String, Animation2D> loadAnimations() throws IOException {

    String mapKey;
    meleeDict = new LinkedHashTreeMap<>();

    //load E animations
    mapKey = "E_ATTACK1";
    meleeDict.put(mapKey, new Animation2D(5, fps, DIR_E_ATT1));
    mapKey = "E_ATTACK2";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_E_ATT2));
    mapKey = "E_ATTACK3";
    meleeDict.put(mapKey, new Animation2D(15, fps, DIR_E_ATT3));
    mapKey = "E_ATTACKCOMBO";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(6, fps, DIR_E_ATT2), new Animation2D(15, fps, DIR_E_ATT3)));
    mapKey = "E_DEATH";
    meleeDict.put(mapKey, new Animation2D(9, fps, DIR_E_DEATH));
    mapKey = "E_IDLE";
    meleeDict.put(mapKey, new Animation2D(11, fps, DIR_E_IDLE));
    mapKey = "E_PRESPRINT";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(7, fps, DIR_E_PRESPRINT), new Animation2D(6, fps, DIR_E_SPRINT)));
    mapKey = "E_SPRINT";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_E_SPRINT));

    //load N animations
    mapKey = "N_ATTACK1";
    meleeDict.put(mapKey, new Animation2D(5, fps, DIR_N_ATT1));
    mapKey = "N_ATTACK2";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_N_ATT2));
    mapKey = "N_ATTACK3";
    meleeDict.put(mapKey, new Animation2D(15, fps, DIR_N_ATT3));
    mapKey = "N_ATTACKCOMBO";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(6, fps, DIR_N_ATT2), new Animation2D(15, fps, DIR_N_ATT3)));
    mapKey = "N_DEATH";
    meleeDict.put(mapKey, new Animation2D(9, fps, DIR_N_DEATH));
    mapKey = "N_IDLE";
    meleeDict.put(mapKey, new Animation2D(11, fps, DIR_N_IDLE));
    mapKey = "N_PRESPRINT";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(7, fps, DIR_N_PRESPRINT), new Animation2D(6, fps, DIR_N_SPRINT)));
    mapKey = "N_SPRINT";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_N_SPRINT));

    //load E animations
    mapKey = "NE_ATTACK1";
    meleeDict.put(mapKey, new Animation2D(5, fps, DIR_NE_ATT1));
    mapKey = "NE_ATTACK2";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_NE_ATT2));
    mapKey = "NE_ATTACK3";
    meleeDict.put(mapKey, new Animation2D(15, fps, DIR_NE_ATT3));
    mapKey = "NE_ATTACKCOMBO";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(6, fps, DIR_NE_ATT2), new Animation2D(15, fps, DIR_NE_ATT3)));
    mapKey = "NE_DEATH";
    meleeDict.put(mapKey, new Animation2D(9, fps, DIR_NE_DEATH));
    mapKey = "NE_IDLE";
    meleeDict.put(mapKey, new Animation2D(11, fps, DIR_NE_IDLE));
    mapKey = "NE_PRESPRINT";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(7, fps, DIR_NE_PRESPRINT), new Animation2D(6, fps, DIR_NE_SPRINT)));
    mapKey = "NE_SPRINT";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_NE_SPRINT));

    //load S animations
    mapKey = "S_ATTACK1";
    meleeDict.put(mapKey, new Animation2D(5, fps, DIR_S_ATT1));
    mapKey = "S_ATTACK2";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_S_ATT2));
    mapKey = "S_ATTACK3";
    meleeDict.put(mapKey, new Animation2D(15, fps, DIR_S_ATT3));
    mapKey = "S_ATTACKCOMBO";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(6, fps, DIR_S_ATT2), new Animation2D(15, fps, DIR_S_ATT3)));
    mapKey = "S_DEATH";
    meleeDict.put(mapKey, new Animation2D(9, fps, DIR_S_DEATH));
    mapKey = "S_IDLE";
    meleeDict.put(mapKey, new Animation2D(11, fps, DIR_S_IDLE));
    mapKey = "S_PRESPRINT";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(7, fps, DIR_S_PRESPRINT), new Animation2D(6, fps, DIR_S_SPRINT)));
    mapKey = "S_SPRINT";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_S_SPRINT));

    //load SE animations
    mapKey = "SE_ATTACK1";
    meleeDict.put(mapKey, new Animation2D(5, fps, DIR_SE_ATT1));
    mapKey = "SE_ATTACK2";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_SE_ATT2));
    mapKey = "SE_ATTACK3";
    meleeDict.put(mapKey, new Animation2D(15, fps, DIR_SE_ATT3));
    mapKey = "SE_ATTACKCOMBO";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(6, fps, DIR_SE_ATT2), new Animation2D(15, fps, DIR_SE_ATT3)));
    mapKey = "SE_DEATH";
    meleeDict.put(mapKey, new Animation2D(9, fps, DIR_SE_DEATH));
    mapKey = "SE_IDLE";
    meleeDict.put(mapKey, new Animation2D(11, fps, DIR_SE_IDLE));
    mapKey = "SE_PRESPRINT";
    meleeDict.put(mapKey, Animation2D.combineAnimations(new Animation2D(7, fps, DIR_SE_PRESPRINT), new Animation2D(6, fps, DIR_SE_SPRINT)));
    mapKey = "SE_SPRINT";
    meleeDict.put(mapKey, new Animation2D(6, fps, DIR_SE_SPRINT));

    return meleeDict;
  }

}
