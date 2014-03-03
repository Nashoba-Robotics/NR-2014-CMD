/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;


import edu.nr.main.oi.OI;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.BottomRollers.ResetPieConnectionCommand;
import edu.nr.main.subsystems.Camera.Camera;
import edu.nr.main.subsystems.Compressor.OnBoard.Compressor;
import edu.nr.main.subsystems.Drive.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.nr.main.subsystems.Compressor.OffBoard.OffBoardCompressor;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArm;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//10.17.68.15:3200

public class Robot extends IterativeRobot 
{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public static Drive drive = null;
    public static BottomRollers rollers = null;
    public static Puncher puncher = null;
    public static ShooterRotator shooterRotator = null;
    public static Compressor compressor;
    public static TopArm topArm;
    public static OffBoardCompressor extCompressor;
    public static Camera camera;
    
    static SocketConnection pieConnection;
    static InputStream pieInput;
    
    static boolean connectedToPie = false;
    boolean sensorsStarted =false;
    
    public void robotInit() 
    {
        System.out.println("ROBOT STARTED");
        SmartDashboard.putData("Connect to Pie", new ResetPieConnectionCommand());
        SmartDashboard.putBoolean("Auto Compressor", false);
        SmartDashboard.putNumber("Tension Distance", 0);
        SmartDashboard.putNumber("Drive Distance", 10);
        drive = new Drive();
        compressor = new Compressor();
        rollers = new BottomRollers();
        puncher = new Puncher();
        shooterRotator = new ShooterRotator();
        topArm = new TopArm();
        extCompressor = new OffBoardCompressor();
        camera = new Camera();
        
        SmartDashboard.putNumber("Tensioner Speed", 0);
        SmartDashboard.putData(rollers);
        
        topArm.sendInfo();
        puncher.sendInfo();
        drive.sendInfo();
        rollers.sendInfo();
        shooterRotator.sendInfo();
        compressor.sendInfo();
        extCompressor.sendInfo();
        camera.sendInfo();
        
        connectToPie();
        
        new Thread(new Runnable()
        {
            public void run() 
            {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Robot.drive.initGyroAccel();
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
    
    static Thread t = null;
    
    static public void connectToPie()
    {
        if(pieConnection != null)
        {    
            try 
            {
                pieConnection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        pieConnection = null;
        connectedToPie = false;
        
        if(t != null)
            t.interrupt();
        t = new Thread(new Runnable()
        {
            public void run()  
            {
                while(connectedToPie == false)
                {
                    try
                    {
                        if(pieConnection != null)
                            pieConnection.close();
                        pieConnection = (SocketConnection) Connector.open("socket://10.17.68.15:1180");
                        if(pieConnection == null)
                            throw new RuntimeException("ERRROR: Didn't actually connect to pie!!!");
                        pieInput = pieConnection.openInputStream();
                        connectedToPie = true;
                        System.out.println("[ DBG ] CONNECTED TO PIE");
                    }
                    catch(IOException e)
                    {
                        SmartDashboard.putString("Pie Error", e.toString());//System.err.println(e.toString());
                    }
                }
            }
        });
        t.start();
    }

    public void teleopInit() 
    {
        /*SmartDashboard.putData("DriveDistanceCommand", new DriveDistanceCommand(10, 0.8f));
        SmartDashboard.putData("DriveAngleCommand", new DriveAngleCommand(90, 0.6));*/
        OI.init();
        System.out.println("TELEOP STARTED");
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() 
    {
        SmartDashboard.putBoolean("Pie Connection", connectedToPie);
        if(sensorsStarted)
        {
            Scheduler.getInstance().run();
            SmartDashboard.putNumber("Potentiometer",Robot.shooterRotator.getRotation());
            SmartDashboard.putNumber("Linear Encoder", Robot.puncher.getLinearEncoderDistance());
            SmartDashboard.putNumber("Encoder 1", Robot.drive.getRawEncoder(1));
            SmartDashboard.putNumber("Encoder 2", Robot.drive.getRawEncoder(2));
            SmartDashboard.putNumber("Gyro", Robot.drive.getGyroAngle());
            SmartDashboard.putNumber("Accel y", Robot.drive.getAccel(ADXL345_I2C.Axes.kY));
            SmartDashboard.putNumber("Accel x", Robot.drive.getAccel(ADXL345_I2C.Axes.kX));
            SmartDashboard.putNumber("Accel z", Robot.drive.getAccel(ADXL345_I2C.Axes.kZ));
            SmartDashboard.putNumber("Ultrasonic Sensor (feet)", Robot.drive.getUltrasonicFeet());
            SmartDashboard.putBoolean("Infrared Sensor", Robot.topArm.getIRSensor());
            
            SmartDashboard.putBoolean("Tension Limit Condition", Robot.puncher.getForwardLimitOK());
        
            SmartDashboard.putString("Pie Message", "");
            if(connectedToPie)
                listenForPieInput();
        }
    }
    
    static Vector pieBytes = new Vector();
    static boolean startedReading = false;
    static void listenForPieInput()
    {
        try 
        {
            while(pieInput.available() > 0)
            {
                byte input = (byte)pieInput.read();
                if(!startedReading)
                {
                    if(input == 10)
                    {
                        startedReading = true;
                    }
                }
                else
                {
                    if(input == 0)
                    {
                        startedReading = false;
                        byte[] bytes = new byte[pieBytes.size()];
                        for(int i = 0; i < bytes.length; i++)
                        {
                            bytes[i] = ((Byte)pieBytes.elementAt(i)).byteValue();
                        }
                        String message = new String(bytes);
                        SmartDashboard.putString("Pie Message", message);
                        pieBytes.removeAllElements();
                    }
                    else
                    {
                        pieBytes.addElement(Byte.valueOf(input));
                    }
                }
            }
        } 
        catch (IOException ex) 
        {
            SmartDashboard.putString("Pie Error", ex.toString());
            System.out.println("ERROR TALKING TO PIE");
            connectToPie();
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
