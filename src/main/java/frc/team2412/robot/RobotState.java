package frc.team2412.robot;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

public class RobotState implements Loggable {

	private RobotContainer robotContainer = RobotMap.m_robotContainer;

	@Log.ToString
	public static UnbalancedSide m_unbalancedSide;

	@Log.Dial(min = 0, max = 5, showValue = true, name = "Power Cell Count")
	public static int m_ballCount = 0;

	@Log.ToString
	public static IntakeDirection m_intakeDirection = IntakeDirection.NONE;

	@Log.ToString
	public static ClimbState m_climbState = ClimbState.NOT_CLIMBING;

	@Log.ToString
	public static LiftState m_liftSolenoidState = LiftState.WITHDRAWN;

	@Log.ToString
	public static GearboxState m_gearState = GearboxState.LOW;

	@Config.ToggleSwitch
	public static boolean sixBallAuto = true;

	@Config.ToggleSwitch
	public static boolean threeBallAuto = false;

	@Config.ToggleSwitch
	public static boolean justMoveAuto = true;

	@Log(tabName = "Misc.")
	public static String eventName = "N/A";

	public static MatchType matchType = MatchType.None;

	@Log(tabName = "Misc.")
	public static int matchNumber = 0;

	public static Alliance alliance = Alliance.Invalid;

	@Log(tabName = "Misc.")
	public static int location = 0;

	public RobotState() {

	}

	public static enum UnbalancedSide {
		FRONT, BACK;

		public String toString() {
			if (this.equals(FRONT)) {
				return "Front";
			} else {
				return "Back";
			}
		}
	}

	public static enum IntakeDirection {
		NONE, FRONT, BACK, BOTH;

		public String toString() {
			if (this.equals(FRONT)) {
				return "Front";
			} else if (this.equals(BACK)) {
				return "Back";
			} else if (this.equals(BOTH)) {
				return "Both";
			} else {
				return "None";
			}
		}
	}

	public static enum ClimbState {
		CLIMBING, NOT_CLIMBING;

		public String toString() {
			if (this.equals(NOT_CLIMBING)) {
				return "Not Climbing, woohoo";
			} else {
				return "We climbin boiz";
			}
		}
	}

	public static enum LiftState {
		WITHDRAWN, EXTENDED;

		public String toString() {
			if (this.equals(WITHDRAWN)) {
				return "Withdrawn";
			} else {
				return "Extended";
			}
		}
	}

	public static enum GearboxState {
		HIGH, LOW;
		public String toString() {
			if (this.equals(HIGH)) {
				return "High";
			} else {
				return "Low";
			}
		}
	}

	public static UnbalancedSide flip(UnbalancedSide s) {
		switch (s) {
		case FRONT:
			return UnbalancedSide.BACK;
		default:
			return UnbalancedSide.FRONT;
		}

	}

	public static int getBallCount() {
		return m_ballCount;
	}

	public static void setBallCount(int m_ballCount) {
		RobotState.m_ballCount = m_ballCount;
	}

	public static void addBall() {
		m_ballCount += 1;
		if (m_ballCount > 5) {
			System.out.println("Too many balls!");
		}
	}

	public static boolean hasFiveBalls() {
		return m_ballCount == 5;
	}

	public static void removeBall() {
		m_ballCount -= 1;
		if (m_ballCount < 0) {
			m_ballCount = 0;
			System.out.println("Removed non-existant ball");
		}
	}

	public static IntakeDirection getintakeDirection() {
		return m_intakeDirection;
	}

	public static void setintakeDirection(IntakeDirection m_intakeDirection) {
		RobotState.m_intakeDirection = m_intakeDirection;
	}

	public static ClimbState getclimbState() {
		return m_climbState;
	}

	public static void setclimbState(ClimbState m_climbState) {
		RobotState.m_climbState = m_climbState;
	}

	public static LiftState getliftSolenoidState() {
		return m_liftSolenoidState;
	}

	public static void setliftSolenoidState(LiftState m_liftSolenoidState) {
		RobotState.m_liftSolenoidState = m_liftSolenoidState;
	}

	public double getTotalCurrentDraw() {
		double currentDraw = robotContainer.m_climbMotorSubsystem.getCurrentDraw()
				+ robotContainer.m_driveBaseSubsystem.getCurrentDraw()
				+ robotContainer.m_flywheelSubsystem.getCurrentDraw()
				+ robotContainer.m_indexerMotorSubsystem.getCurrentDraw()
				+ robotContainer.m_intakeMotorOnOffSubsystem.getCurrentDraw()
				+ robotContainer.m_turretSubsystem.getCurrentDraw() + RobotMap.compressor.getCompressorCurrent();

		return currentDraw;
	}

}
