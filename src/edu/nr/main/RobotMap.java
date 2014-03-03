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
    
    public static final int GYRO = 1;
    
    //Misc. Speed Controllers
    public static final int ROLLER_JAG = 2;
    public static final int ROLLER_VICTOR = 5;
    public static final int WINCH_JAG = 3;
    public static final int TOP_ARM_JAG = 1;
    public static final int SHOOTER_ROTATION_JAG = 4;
    
    //Digital Sidecar
    public static final int CAMERA_LIGHT_1 = 1;
    public static final int CAMERA_LIGHT_2 = 2;
    public static final int ULTRASONIC_A = 3;
    public static final int ULTRASONIC_B = 4;
    public static final int PRESSURE_LIMIT = 5;
    public static final int ENCODER_1_A = 6;
    public static final int ENCODER_1_B = 7;
    public static final int ENCODER_2_A = 8;
    public static final int ENCODER_2_B = 9;
    public static final int TOP_ARM_IR_SENSOR = 10;
    
    //Pneumatics
    public static final int DOG_GEAR_SOLENOID_UNDEPLOY = 5;
    public static final int DOG_GEAR_SOLENOID_DEPLOY = 6;
    public static final int FLOWER_SOLENOID_DEPLOY = 3;
    public static final int FLOWER_SOLENOID_UNDEPLOY=4;
    public static final int SHIFTER_ENGAGE = 1;
    public static final int SHIFTER_DISENGAGE = 2;
    public static final int ON_BOARD_COMPRESSOR_RELAY=1;
    public static final int OFF_BOARD_COMPRESSOR_RELAY=2;
    
    public static final boolean USING_LINCODER = false;
    public static final double WINCH_JAG_REV_SOFT_LIM = 0.0;
    public static final double WINCH_REGULAR_SPEED = 0.7d;
    public static final double SHOOTER_ROT_REV_SOFT_LIM = 0.3;
    public static final int POT_TURNS = 1;
    public static final int LINCODER_CLICKS = 250;
    
    public static final String ROBO_REALM_TABLE_NAME = "Roborealm";
}
