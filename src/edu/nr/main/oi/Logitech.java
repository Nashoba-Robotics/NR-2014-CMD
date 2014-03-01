package edu.nr.main.oi;

public class Logitech extends Controller
{

    public Logitech(int port) 
    {
        super(port);
        AxisType.leftXAxis = 1;
        AxisType.leftYAxis = 2;
        AxisType.rightXAxis = 3;
        AxisType.rightYAxis = 4;
        AxisType.extraAxis1 = 5;
        AxisType.extraAxis2 = 6;
        AxisType.extraAxis3 = 7;
        
        ButtonType.aButton = 1;
        ButtonType.bButton = 2;
        ButtonType.xButton = 3;
        ButtonType.yButton = 4;
        ButtonType.leftBumperButton = 5;
        ButtonType.rightBumperButton = 6;
        ButtonType.selectButton = 7;
        ButtonType.startButton = 8;
        ButtonType.leftStickButton = 9;
        ButtonType.rightStickButton = 10;
    }
    
    public boolean getButton(int button) {
       return true;
    }
    
    public double getRawAxis(int axis) {
        return 0.0;
    }     
}
