/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;

/** OI **/
import edu.nr.main.subsystems.RaspberryPie.ResetPieConnectionCommand;
import edu.nr.main.oi.OI;

/** Subsystems **/
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.NetworkCameraLights.NetworkCameraLights;
import edu.nr.main.subsystems.Compressor.InternalCompressor;
import edu.nr.main.subsystems.Drive.Drive;
import edu.nr.main.subsystems.Flower.Flower;
import edu.nr.main.subsystems.Compressor.ExternalCompressor;
import edu.nr.main.subsystems.Shooter.Puncher;
import edu.nr.main.subsystems.Shooter.Winch;
import edu.nr.main.subsystems.RaspberryPie.ListenForPieInputCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.Flower.TopArm;
import edu.nr.main.subsystems.RaspberryPie.RaspberryPie;

/** WPILibJ stuff **/
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Java VM stuff **/

//10.17.68.15:3200

public class Robot extends IterativeRobot 
{
    public Drive drive = null;
    public BottomRollers rollers = null;
    public Flower flower = null;
    public Puncher puncher = null;
    public Winch winch = null;
    public ShooterRotator shooterRotator = null;
    public InternalCompressor intCompressor = null;
    public TopArm topArm = null;
    public ExternalCompressor extCompressor = null;
    public NetworkCameraLights cameraLights = null;
    public RaspberryPie pie = null;
    
    private boolean sensorsStarted = false;
    
    public void robotInit() 
    {
        System.out.println("ROBOT STARTED");
        
        SmartDashboard.putData("Connect to Pie", new ResetPieConnectionCommand());
        SmartDashboard.putBoolean("Auto Compressor", false);
        SmartDashboard.putNumber("Tension Distance", 0);
        SmartDashboard.putNumber("Drive Distance", 10);
        
        drive = Drive.getInstance();
        drive.init();
        
        intCompressor = InternalCompressor.getInstance(); 
        intCompressor.init(RobotMap.ON_BOARD_COMPRESSOR_RELAY);
        
        rollers = BottomRollers.getInstance();
        rollers.init();
        
        puncher = Puncher.getInstance();
        puncher.init();
        
        winch = Winch.getInstance();
        winch.init();
        
        shooterRotator = ShooterRotator.getInstance();
        shooterRotator.init();
        
        topArm = TopArm.getInstance();
        topArm.init();
        
        flower = Flower.getInstance();
        flower.init();
        
        extCompressor = ExternalCompressor.getInstance();
        extCompressor.init(RobotMap.OFF_BOARD_COMPRESSOR_RELAY);
        
        cameraLights = NetworkCameraLights.getInstance();
        cameraLights.init();
        
        pie = RaspberryPie.getInstance();
        
        SmartDashboard.putNumber("Tensioner Speed", 0);
        SmartDashboard.putData(rollers);
        
        /** Put subsystem data on SmartDashboard **/
        topArm.sendInfo();
        puncher.sendInfo();
        drive.sendInfo();
        rollers.sendInfo();
        shooterRotator.sendInfo();
        intCompressor.sendInfo();
        extCompressor.sendInfo();
        cameraLights.sendInfo();
        
        /** Connect to the pie **/
        pie.connectToPie();
        
        new Thread(new Runnable()
        {
            public void run() 
            {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                drive.initGyroAccel();
                System.out.println("SENSORS STARTED");
                sensorsStarted = true;
            }
        }).start();
        
        System.out.println("ROBOT FINISHED STARTING");
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        /*SmartDashboard.putData("DriveDistanceCommand", new DriveDistanceCommand(10, 0.8f));
        SmartDashboard.putData("DriveAngleCommand", new DriveAngleCommand(90, 0.6));*/
        OI.init();
        System.out.println("TELEOP STARTED");
    }
    
    public void teleopPeriodic() {
        SmartDashboard.putBoolean("Pie Connection", pie.isConnectedToPie());
        if(sensorsStarted) {
            Scheduler.getInstance().run();
            SmartDashboard.putNumber("Potentiometer", shooterRotator.getShooterTiltEncClicks());
            SmartDashboard.putNumber("Linear Encoder", winch.getLinearEncoderDistance());
            SmartDashboard.putNumber("Encoder 1", drive.getRawEncoder(1));
            SmartDashboard.putNumber("Encoder 2", drive.getRawEncoder(2));
            SmartDashboard.putNumber("Gyro", drive.getGyroAngle());
            AccelerationCalc.run();
            SmartDashboard.putNumber("Ultrasonic Sensor (feet)", drive.getUltrasonicFeet());
            SmartDashboard.putBoolean("Infrared Sensor", topArm.getIRSensor());
            
            SmartDashboard.putBoolean("Tension Limit Condition", winch.getLimitSwitch());
        
            SmartDashboard.putString("Pie Message", "");
            if(pie.isConnectedToPie())
                new ListenForPieInputCommand().start();
        }
    }
    public void testPeriodic() {
        LiveWindow.run();
    }
}
