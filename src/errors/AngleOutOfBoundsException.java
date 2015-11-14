package errors;

/**
 * Exception to be thrown when any Angle is used outside of the valid range.
 * @author Christopher McDonald, Eric Le Fort
 * @version 1.0
 */
public class AngleOutOfBoundsException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls Parent's Constructor class to make Throwable Exception
	 * @param exceptionMessage - Message thrown with Exception
	 */
	public AngleOutOfBoundsException(String exceptionMessage){
		super(exceptionMessage);
	}//Constructor

}//AngleOutOfBoundsException
