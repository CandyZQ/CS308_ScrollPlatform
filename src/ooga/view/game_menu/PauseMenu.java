package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.*;

/**
 * Menu for pause screen
 *
 * @author Lucy
 */
public class PauseMenu implements MenuView {

    private PrettyButtons ResumeButton;
    private PrettyButtons BackToMenuButton;
    private PrettyButtons SaveGameButton;


    private List<PrettyButtons> myButtonList = new ArrayList<>();

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;

    private Label currentScore;
    private Label scorelist;
    private Label life;

    public PauseMenu(){
        myLanguage = "English";
        setUpButton();
        currentScore = new Label();
        currentScore.setFont(Font.font("Ariel", 18));
        scorelist = new Label();
        scorelist.setFont(Font.font("Ariel", 18));
        life = new Label();
        life.setFont(Font.font("Ariel", 18));
        changeLabelText();
        setUpVBox();
        myScene = new Scene(vBox, 800, 800);
    }

    private void changeLabelText() {
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        currentScore.setText(resource.getString("currentScore"));
    }

    /**
     *
     * @return resume game button
     */
    public Button getResumeButton(){return ResumeButton;}

    /**
     *
     * @return back to menu button
     */
    public Button getBackToMenuButton(){return BackToMenuButton;}

    /**
     *
     * @return save game button
     */
    public Button getSaveGameButton(){return SaveGameButton;}

    /**
     *
     * @return scene
     */
    @Override
    public Scene getMenuView() {
        return myScene;
    }

    /**
     * set text to use resource file
     * @param language name of language
     */
    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for(PrettyButtons button:myButtonList) button.changeLanguage(myLanguage);
    }

    /**
     * change background color
     * @param color input color
     */
    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     *
     * @param dark true = dark mode
     */
    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    private void setColor(){
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);

        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        life.setTextFill(dark?Color.DARKGRAY:Color.BLACK);

        for(PrettyButtons button:myButtonList) button.switchMode(dark);
    }

    private void setUpButton(){
        ResumeButton = new PrettyButtons("resume", myLanguage);
        BackToMenuButton = new PrettyButtons("menu", myLanguage);
        SaveGameButton = new PrettyButtons("save", myLanguage) ;

        myButtonList = List.of(ResumeButton, BackToMenuButton, SaveGameButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scorelist, life, ResumeButton, BackToMenuButton, SaveGameButton);
    }
    /**
     * Update the text of score
     * @param list list of id:score
     */
    public void updateScore(Map<Integer, Integer> list){
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" score: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        scorelist.setText(s);
    }
    /**
     * Update the text of life
     * @param list list of id:hp
     */
    public void updateLife(Map<Integer, Integer> list) {
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" life: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        life.setText(s);

    }
}
