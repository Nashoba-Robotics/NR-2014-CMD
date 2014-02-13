package edu.nr.main;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    public static final int BACK_LEFT_TALON=1;
    public static final int BACK_RIGHT_TALON=3;
    public static final int FRONT_LEFT_TALON=2;
    public static final int FRONT_FRONT_TALON=4;
    public static final int ROLLER_JAG = 5;
    public static final int ROLLER_VICTOR = 7;
    public static final int WINCH_JAG = 4;
    
    public static final int DOG_EAR_SOLENOID_UNDEPLOY = 5;
    public static final int DOG_EAR_SOLENOID_DEPLOY = 6;
}
