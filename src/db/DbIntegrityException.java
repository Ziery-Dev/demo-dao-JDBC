package db;

public class DbIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	//Essa excessão é responsável por identificar problema de integridade referencial no banco de dados. Por exemplo na tentiva de apagar um departamento do qual existem um seler com o id daquele departamento, por padroão o programa não irá deixar
	
	public DbIntegrityException(String msg) {
		super(msg);
	}
} 
