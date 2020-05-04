package ooga.view.engine.utils.cyberpunk2d;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.PngMerger;

/**
 * a temporary util class that resizes the sprites (to center the character )
 * @author qiaoyi fang
 */
public class ResizeSprites {

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

  public static void createCanvas() throws IOException {
    int maxHeight = 0;
    int maxWidth = 0;

    // E
    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_ATT1.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_ATT1.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_ATT2.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_ATT2.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_ATT3.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_ATT3.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_DEATH.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_DEATH.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_IDLE.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_IDLE.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_SPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_SPRINT.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_E_PRESPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_E_PRESPRINT.replace("\\", "/"))));

    // N

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_ATT1.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_ATT1.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_ATT2.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_ATT2.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_ATT3.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_ATT3.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_DEATH.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_DEATH.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_IDLE.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_IDLE.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_SPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_SPRINT.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_PRESPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_PRESPRINT.replace("\\", "/"))));

    // NE

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_ATT1.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_ATT1.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_ATT2.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_ATT2.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_ATT3.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_ATT3.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_DEATH.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_DEATH.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_IDLE.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_IDLE.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_SPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_SPRINT.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_NE_PRESPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_NE_PRESPRINT.replace("\\", "/"))));

    // S

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_S_ATT1.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_S_ATT1.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_S_ATT2.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_S_ATT2.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_ATT3.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_ATT3.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_DEATH.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_DEATH.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_IDLE.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_IDLE.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_SPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_SPRINT.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_N_PRESPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_N_PRESPRINT.replace("\\", "/"))));

    // SW

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_ATT1.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_ATT1.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_ATT2.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_ATT2.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_ATT3.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_ATT3.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_DEATH.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_DEATH.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_IDLE.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_IDLE.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_SPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_SPRINT.replace("\\", "/"))));

    maxHeight = Math.max(maxHeight, ImageLoader.getImageHeight(String.format("%s/0.png",DIR_SE_PRESPRINT.replace("\\", "/"))));
    maxWidth = Math.max(maxWidth, ImageLoader.getImageWidth(String.format("%s/0.png", DIR_SE_PRESPRINT.replace("\\", "/"))));

    BufferedImage canvas = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics g = canvas.getGraphics();
    g.dispose();

    String dir = "view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT".replace("\\", "/");
    String newpath = "canvas.png";
    String newImagePath = String.format("resources/%s/%s", dir, newpath);
    System.out.println(newImagePath);
    File outputfile = new File(newImagePath);
    ImageIO.write(canvas, "png", outputfile);

  }

  public static void resizeAll() throws IOException {

    resizeAnimation(DIR_E_ATT1, 5);
    resizeAnimation(DIR_E_ATT2, 6);
    resizeAnimation(DIR_E_ATT3, 15);
    resizeAnimation(DIR_E_DEATH, 9);
    resizeAnimation(DIR_E_IDLE, 11);
    resizeAnimation(DIR_E_PRESPRINT, 7);
    resizeAnimation(DIR_E_SPRINT, 6);

    resizeAnimation(DIR_N_ATT1, 5);
    resizeAnimation(DIR_N_ATT2, 6);
    resizeAnimation(DIR_N_ATT3, 15);
    resizeAnimation(DIR_N_DEATH, 9);
    resizeAnimation(DIR_N_IDLE, 11);
    resizeAnimation(DIR_N_PRESPRINT, 7);
    resizeAnimation(DIR_N_SPRINT, 6);

    resizeAnimation(DIR_NE_ATT1, 5);
    resizeAnimation(DIR_NE_ATT2, 6);
    resizeAnimation(DIR_NE_ATT3, 15);
    resizeAnimation(DIR_NE_DEATH, 9);
    resizeAnimation(DIR_NE_IDLE, 11);
    resizeAnimation(DIR_NE_PRESPRINT, 7);
    resizeAnimation(DIR_NE_SPRINT, 6);

    resizeAnimation(DIR_S_ATT1, 5);
    resizeAnimation(DIR_S_ATT2, 6);
    resizeAnimation(DIR_S_ATT3, 15);
    resizeAnimation(DIR_S_DEATH, 9);
    resizeAnimation(DIR_S_IDLE, 11);
    resizeAnimation(DIR_S_PRESPRINT, 7);
    resizeAnimation(DIR_S_SPRINT, 6);

    resizeAnimation(DIR_SE_ATT1, 5);
    resizeAnimation(DIR_SE_ATT2, 6);
    resizeAnimation(DIR_SE_ATT3, 15);
    resizeAnimation(DIR_SE_DEATH, 9);
    resizeAnimation(DIR_SE_IDLE, 11);
    resizeAnimation(DIR_SE_PRESPRINT, 7);
    resizeAnimation(DIR_SE_SPRINT, 6);

  }

  public static void resizeAnimation(String dir, int frame_amount) throws IOException {
    for (int i=0; i<frame_amount; i++){
      PngMerger.mergeCanvasPic("\\view\\textures\\2d\\cyberpunk\\playable\\MELEE ROBOT/canvas.png".replace("\\", "/"),
          String.format("%s/%s.png", dir,i).replace("\\", "/"), String.format("%s/%s_.png",dir, i).replace("\\", "/"));
    }
  }



}
