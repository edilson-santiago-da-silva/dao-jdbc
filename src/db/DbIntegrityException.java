package db;

public class DbIntegrityException extends RuntimeException{ // exceção que costuma acontecer com um problema de integridade referencial
	
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
}
