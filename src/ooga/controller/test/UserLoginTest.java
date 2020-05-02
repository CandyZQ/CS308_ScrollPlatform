package ooga.controller.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.controller.LogInControl;
import ooga.controller.UserProfileControl;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorer;
import ooga.data.DataStorerAPI;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.view.game_menu.LogInView;
import ooga.view.game_menu.UserProfileView;
import org.junit.jupiter.api.Test;

public class UserLoginTest  {

    private LogInControl myControl;

    @Test
    void testExistingUser ()  {

//        myControl = new LogInControl();
//        String s = myControl.getMyView().getMessage();
//        myControl.getMyView().setInput("User1", "password");
//        assertEquals(s, myControl.getMyView().getMessage());
//        assertEquals(myWinControl.isLogIn(),true);
//        assertEquals(myWinControl.getUserName(), "User1");
    }
}
