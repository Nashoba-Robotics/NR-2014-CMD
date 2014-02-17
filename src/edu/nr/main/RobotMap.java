package edu.nr.main;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    //Drive
    public static final int BACK_LEFT_TALON=1;
    public static final int BACK_RIGHT_TALON=3;
    public static final int FRONT_LEFT_TALON=2;
    public static final int FRONT_FRONT_TALON=4;
    public static final int ENCODER_1_A = 4;
    public static final int ENCODER_1_B = 5;
    public static final int ENCODER_2_A = 6;
    public static final int ENCODER_2_B = 7;
    
    public static final int GYRO = 1;
    
    //Misc. Speed Controllers
    public static final int ROLLER_JAG = 2;
    public static final int ROLLER_VICTOR = 5;
    public static final int WINCH_JAG = 3;
    public static final int TOP_ARM_JAG = 1;
    public static final int SHOOTER_ROTATION_JAG = 4;
    
    
    //Pneumatics
    public static final int DOG_EAR_SOLENOID_UNDEPLOY = 5;
    public static final int DOG_EAR_SOLENOID_DEPLOY = 6;
    public static final int TOP_ARM_SOLENOID_DEPLOY = 3;
    public static final int TOP_ARM_SOLENOID_UNDEPLOY=4;
    public static final int PRESSURE_LIMIT = 3;
    public static final int SHIFTER_ENGAGE = 1;
    public static final int SHIFTER_DISENGAGE = 2;
    public static final int OFF_BOARD_COMPRESSOR_RELAY=2;
}
