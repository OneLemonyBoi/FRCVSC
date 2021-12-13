// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.MoveDriveTrain;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrainSubsystem;
  private final LimeLight m_limeLightSubsystem;
  private final XboxController m_xboxController;
  private final MoveDriveTrain m_moveDriveTrainCommand;
  private final MoveDriveTrain m_seekObjectCommand;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveTrainSubsystem = new DriveTrain();
    m_limeLightSubsystem = new LimeLight();
    m_xboxController = new XboxController(0);
    m_moveDriveTrainCommand = new MoveDriveTrain(m_driveTrainSubsystem, () -> m_xboxController.getY(), () -> m_xboxController.getX());
    m_seekObjectCommand = new MoveDriveTrain(m_driveTrainSubsystem, () -> 0, () -> m_limeLightSubsystem.getData()[1] * 0.2);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Hypothetically, this SHOULD allow for homing to an object on button click
    new JoystickButton(m_xboxController, XboxController.Button.kA.value).toggleWhenPressed(m_seekObjectCommand);
  }

  public void teleopInit() {
    m_moveDriveTrainCommand.schedule();
  }
}
