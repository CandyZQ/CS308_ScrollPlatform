package ooga.view.engine.utils.cyberpunk2d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ooga.view.engine.utils.PngMerger;

/**
 * a temporary util class tha merge sprites
 * @author qiaoyi fang
 */
public class GenerateMergedSprites {

  // E sprintsheet
  private String DIR_E_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String DIR_E_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String DIR_E_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String DIR_E_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\DEATH_9frames";
  private String DIR_E_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\IDLE_11frames";
  private String DIR_E_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\PRE-SPRINT_7frames";
  private String DIR_E_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\E_spritesheet\\SPRINT_6frames";

  // N_spritesheet

  private String DIR_N_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String DIR_N_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String DIR_N_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String DIR_N_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\DEATH_9frames";
  private String DIR_N_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\IDLE_11frames";
  private String DIR_N_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\PRE-SPRINT_7frames";
  private String DIR_N_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\N_spritesheet\\SPRINT_6frames";

  // NE spritesheet

  private String DIR_NE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String DIR_NE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String DIR_NE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String DIR_NE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\DEATH_9frames";
  private String DIR_NE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\IDLE_11frames";
  private String DIR_NE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\PRE-SPRINT_7frames";
  private String DIR_NE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\NE_spritesheet\\SPRINT_6frames";

  // S Spritesheet

  private String DIR_S_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String DIR_S_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String DIR_S_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String DIR_S_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\DEATH_9frames";
  private String DIR_S_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\IDLE_11frames";
  private String DIR_S_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\PRE-SPRINT_7frames";
  private String DIR_S_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\S_spritesheet\\SPRINT_6frames";

  // SE Spritesheet

  private String DIR_SE_ATT1 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK1_5frames";
  private String DIR_SE_ATT2 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACK2_6frames";
  private String DIR_SE_ATT3 = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\ATTACKCOMBO_26frames\\ATTACKfinal_15frames";
  private String DIR_SE_DEATH = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\DEATH_9frames";
  private String DIR_SE_IDLE = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\IDLE_11frames";
  private String DIR_SE_PRESPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\PRE-SPRINT_7frames";
  private String DIR_SE_SPRINT = "\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT\\SE_spritesheet\\SPRINT_6frames";

  public GenerateMergedSprites() throws IOException {

    //merge E sprites

    generateMergedSpritesForEachAnimation(DIR_E_ATT1.replace("\\", "/"), 5, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_ATT2.replace("\\", "/"), 6, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_ATT3.replace("\\", "/"), 15, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_DEATH.replace("\\", "/"), 9, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_IDLE.replace("\\", "/"), 11, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_PRESPRINT.replace("\\", "/"), 7, false, false);
    generateMergedSpritesForEachAnimation(DIR_E_SPRINT.replace("\\", "/"), 6, false, false);

    // merge N sprites

    generateMergedSpritesForEachAnimation(DIR_N_ATT1.replace("\\", "/"), 5, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_ATT2.replace("\\", "/"), 6, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_ATT3.replace("\\", "/"), 15, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_DEATH.replace("\\", "/"), 9, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_IDLE.replace("\\", "/"), 11, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_PRESPRINT.replace("\\", "/"), 7, false, false);
    generateMergedSpritesForEachAnimation(DIR_N_SPRINT.replace("\\", "/"), 6, false, false);

    // merge NE sprites

    generateMergedSpritesForEachAnimation(DIR_NE_ATT1.replace("\\", "/"), 5, true, true);
    generateMergedSpritesForEachAnimation(DIR_NE_ATT2.replace("\\", "/"), 6, true, true);
    generateMergedSpritesForEachAnimation(DIR_NE_ATT3.replace("\\", "/"), 15, true, true);
    generateMergedSpritesForEachAnimation(DIR_NE_DEATH.replace("\\", "/"), 9, true, false);
    generateMergedSpritesForEachAnimation(DIR_NE_IDLE.replace("\\", "/"), 11, true, false);
    generateMergedSpritesForEachAnimation(DIR_NE_PRESPRINT.replace("\\", "/"), 7, true, true);
    generateMergedSpritesForEachAnimation(DIR_NE_SPRINT.replace("\\", "/"), 6, true, false);

    // merge S sprites

    generateMergedSpritesForEachAnimation(DIR_S_ATT1.replace("\\", "/"), 5, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_ATT2.replace("\\", "/"), 6, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_ATT3.replace("\\", "/"), 15, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_DEATH.replace("\\", "/"), 9, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_IDLE.replace("\\", "/"), 11, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_PRESPRINT.replace("\\", "/"), 7, false, false);
    generateMergedSpritesForEachAnimation(DIR_S_SPRINT.replace("\\", "/"), 6, false, false);

    // merge SE sprites

    generateMergedSpritesForEachAnimation(DIR_SE_ATT1.replace("\\", "/"), 5, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_ATT2.replace("\\", "/"), 6, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_ATT3.replace("\\", "/"), 15, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_DEATH.replace("\\", "/"), 9, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_IDLE.replace("\\", "/"), 11, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_PRESPRINT.replace("\\", "/"), 7, false, false);
    generateMergedSpritesForEachAnimation(DIR_SE_SPRINT.replace("\\", "/"), 6, false, false);

  }

  private void generateMergedSpritesForEachAnimation(String dir, int amount_frames, boolean isFront, boolean isBack)
      throws IOException {

    for (int i=0; i<amount_frames; i++){

      List<String> pngs_to_merge = new ArrayList<>();
      String body_png = String.format("BODY_%s_0.png", i);
      pngs_to_merge.add(body_png);

      if (isFront){
        String weapon_front_png = String.format("WEAPONfront_%s_0.png", i);
        pngs_to_merge.add(weapon_front_png);
      }

      if (isBack){
        String weapon_back_png = String.format("WEAPONback_%s_0.png", i);
        pngs_to_merge.add(weapon_back_png);
      }

      if (!isFront && !isBack){
        String weapon_png = String.format("WEAPON_%s_0.png", i);
        pngs_to_merge.add(weapon_png);
      }

      String newFilepath = String.format("%s.png", i);
      PngMerger.mergePngPics(dir, pngs_to_merge, newFilepath);

    }

  }
}
