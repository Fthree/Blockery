package Service;

import Factory.UtilFactory;
import Messenging.Broadcast.InputBroadcast;
import Options.MouseOptions;
import Type.Keyboard;
import Type.Mouse;
import Util.MouseUtil;
import Util.PreviewCubeUtil;
import com.jme3.app.Application;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import lombok.Data;

@Data
public class InputService {
    InputBroadcast inputBroadcast;

    public InputService() {
        inputBroadcast = new InputBroadcast();
    }

    public void setup(Application application, Node guiNode) {
        setupKeys(application);
        setupMouse(application, guiNode);
        inputBroadcast.setDirection(PreviewCubeUtil.calculatePositionInSpace(application.getCamera(), 2f));
    }

    public void resetJump() {
        inputBroadcast.getKeyboard().setJump(false);
    }

    public void resetMouse(MouseOptions mouseOptions) {
        if(mouseOptions == MouseOptions.LEFT) { inputBroadcast.getMouse().setLeft(false); return; }
        if(mouseOptions == MouseOptions.RIGHT) { inputBroadcast.getMouse().setRight(false); }
    }

    private void setupMouse(Application application, Node guiNode) {
        MouseUtil mouseUtil = UtilFactory.getMouseUtil();
        mouseUtil.initCrosshair(application, guiNode);
        ActionListener actionListener = getMouseClickListener(application.getCamera());
        mouseUtil.initMouseKeys(application.getInputManager(), actionListener, MouseOptions.LEFT, MouseInput.BUTTON_LEFT);
        mouseUtil.initMouseKeys(application.getInputManager(), actionListener, MouseOptions.RIGHT, MouseInput.BUTTON_RIGHT);
        AnalogListener mouseAxisListener = getMouseAxisListener(application.getCamera());
        mouseUtil.initMouseMovement(
                application.getInputManager(),
                mouseAxisListener
        );

        inputBroadcast.setMouse(Mouse.builder().build());
    }

    private void setupKeys(Application application) {
        application.getInputManager().addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        application.getInputManager().addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        application.getInputManager().addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        application.getInputManager().addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        application.getInputManager().addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        ActionListener actionListener = getKeyboardActionListener(application.getCamera());
        application.getInputManager().addListener(actionListener, "Left");
        application.getInputManager().addListener(actionListener, "Right");
        application.getInputManager().addListener(actionListener, "Forward");
        application.getInputManager().addListener(actionListener, "Backward");
        application.getInputManager().addListener(actionListener, "Jump");

        inputBroadcast.setKeyboard(Keyboard.builder().build());
    }

    private ActionListener getMouseClickListener(Camera cam) {
        return (name, keyPressed, tpf) -> {
            //LMB
            if(name.equals(MouseOptions.LEFT.getType()) && !keyPressed) {
                inputBroadcast.getMouse().setLeft(true);
                inputBroadcast.setDirection(PreviewCubeUtil.calculatePositionInSpace(cam, 2f));
            }
            //LMB
            if(name.equals(MouseOptions.RIGHT.getType()) && !keyPressed) {
                inputBroadcast.getMouse().setRight(true);
            }
        };
    }

    private AnalogListener getMouseAxisListener(Camera cam) {
        return (name, value, tpf) -> {
            if("RotateX".equals(name) || "RotateX_negative".equals(name) || "RotateY".equals(name) || "RotateY_Negative".equals(name)) {
                inputBroadcast.setDirection(PreviewCubeUtil.calculatePositionInSpace(cam, 2f));
        }
        };
    }

    private ActionListener getKeyboardActionListener(Camera cam) {
        return (binding, isPressed, tpf) -> {
            inputBroadcast.setDirection(PreviewCubeUtil.calculatePositionInSpace(cam, 2f));
            switch (binding) {
                case "Left":
                    inputBroadcast.getKeyboard().setLeft(isPressed);
                    break;
                case "Right":
                    inputBroadcast.getKeyboard().setRight(isPressed);
                    break;
                case "Forward":
                    inputBroadcast.getKeyboard().setForward(isPressed);
                    break;
                case "Backward":
                    inputBroadcast.getKeyboard().setBackward(isPressed);
                    break;
                case "Jump":
                    inputBroadcast.getKeyboard().setJump(isPressed);
                    break;
            }
        };
    }
}
