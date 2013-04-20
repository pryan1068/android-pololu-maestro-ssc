package org.pololu.maestro;

/**
 * Main class for controlling servos. Currently implemented using Compact
 * Protocol.
 * 
 * Android requires us to get user permission to access a USB device. Best
 * approach is to do that in the Activity and call setDevice().
 * 
 * @See MaestroSSCActivity.java
 * 
 * @author Patrick H. Ryan
 * 
 */
public class MaestroSSC extends MaestroUSBDevice {
	
	private static final String[][] errorMessages = {
			{
					"Serial Signal Error (bit 0)",
					"A hardware-level error that occurs when a byte’s stop bit is not detected at the expected place. This can occur if you are communicating at a baud rate that differs from the Maestro’s baud rate." 
			},
			{ 
					"Serial Overrun Error (bit 1)",
					"A hardware-level error that occurs when the UART’s internal buffer fills up. This should not occur during normal operation." 
			},
			{
					"Serial RX buffer full (bit 2)",
					"A firmware-level error that occurs when the firmware’s buffer for bytes received on the RX line is full and a byte from RX has been lost as a result. This error should not occur during normal operation." 
			},
			{
					"Serial CRC error (bit 3)",
					"This error occurs when the Maestro is running in CRC-enabled mode and the cyclic redundancy check (CRC) byte at the end of the command packet does not match what the Maestro has computed as that packet’s CRC (Section 5.d). In such a case, the Maestro ignores the command packet and generates a CRC error." 
			},
			{
					"Serial protocol error (bit 4)",
					"This error occurs when the Maestro receives an incorrectly formatted or nonsensical command packet. For example, if the command byte does not match a known command or an unfinished command packet is interrupted by another command packet, this error occurs." 
			},
			{
					"Serial timeout error (bit 5)",
					"When the serial timeout is enabled, this error occurs whenever the timeout period has elapsed without the Maestro receiving any valid serial commands. This timeout error can be used to make the servos return to their home positions in the event that serial communication between the Maestro and its controller is disrupted." 
			},
			{
					"Script stack error (bit 6)",
					"This error occurs when a bug in the user script has caused the stack to overflow or underflow. Any script command that modifies the stack has the potential to cause this error. The stack depth is 32 on the Micro Maestro and 126 on the Mini Maestros." 
			},
			{
					"Script call stack error (bit 7)",
					"This error occurs when a bug in the user script has caused the call stack to overflow or underflow. An overflow can occur if there are too many levels of nested subroutines, or a subroutine calls itself too many times. The call stack depth is 10 on the Micro Maestro and 126 on the Mini Maestros. An underflow can occur when there is a return without a corresponding subroutine call. An underflow will occur if you run a subroutine using the “Restart Script at Subroutine” serial command and the subroutine terminates with a return command rather than a quit command or an infinite loop." 
			},
			{
					"Script program counter error (bit 8)",
					"This error occurs when a bug in the user script has caused the program counter (the address of the next instruction to be executed) to go out of bounds. This can happen if your program is not terminated by a quit, return, or infinite loop." 
			}, 
	};
	
	public MaestroSSC() {		
	}
	
	public void setTarget(int channel, int target) {
		// Pololu Protocol expects you to pass in how many QUARTER-seconds.
		target = target * 4;
		
		sendCommand(CMD_SET_POSITION, channel, target);
	}
	
	public void setSpeed(int channel, int speed) {
		sendCommand(CMD_SET_SPEED, channel, speed);
	}
	
	public void setAcceleration(int channel, int acceleration) {		
		sendCommand(CMD_SET_ACCELERATION, channel, acceleration);
	}
	
	public void setPWM(int onTime, int period) {
		sendCommand(CMD_SET_PWM, onTime, period);
	}
	
	public int getPosition(int channel){
		sendCommand(CMD_GET_POSITION, 0, 0);
		return(0);
	}
	
	public int getMovingState(){
		sendCommand(CMD_GET_MOVING_STATE, 0, 0);
		return(0);
	}
	
	public int getErrors(){
		sendCommand(CMD_GET_ERRORS, 0, 0);
		return(0);
	}
	
	public void goHome() {
		sendCommand(CMD_GO_HOME, 0, 0);
	}
	
	public String getShortErrorMessage(int errors) {
		StringBuffer sb = new StringBuffer();
		
		if ((errors & 0x00) != 0)
			sb.append(errorMessages[0][0]);
		if ((errors & 0x01) != 0)
			sb.append(errorMessages[1][0]);
		if ((errors & 0x02) != 0)
			sb.append(errorMessages[2][0]);
		if ((errors & 0x03) != 0)
			sb.append(errorMessages[3][0]);
		if ((errors & 0x04) != 0)
			sb.append(errorMessages[4][0]);
		if ((errors & 0x05) != 0)
			sb.append(errorMessages[5][0]);
		if ((errors & 0x06) != 0)
			sb.append(errorMessages[6][0]);
		if ((errors & 0x07) != 0)
			sb.append(errorMessages[7][0]);
		if ((errors & 0x08) != 0)
			sb.append(errorMessages[8][0]);
		
		return (sb.toString());
	}
	
	public String getLongErrorMessage(int errors) {
		StringBuffer sb = new StringBuffer();
		
		if ((errors & 0x00) != 0)
			sb.append(errorMessages[0][1]);
		if ((errors & 0x01) != 0)
			sb.append(errorMessages[1][1]);
		if ((errors & 0x02) != 0)
			sb.append(errorMessages[2][1]);
		if ((errors & 0x03) != 0)
			sb.append(errorMessages[3][1]);
		if ((errors & 0x04) != 0)
			sb.append(errorMessages[4][1]);
		if ((errors & 0x05) != 0)
			sb.append(errorMessages[5][1]);
		if ((errors & 0x06) != 0)
			sb.append(errorMessages[6][1]);
		if ((errors & 0x07) != 0)
			sb.append(errorMessages[7][1]);
		if ((errors & 0x08) != 0)
			sb.append(errorMessages[8][1]);
		
		return (sb.toString());
	}
}
