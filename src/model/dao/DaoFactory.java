package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	//Classe auxiliar responsável por instanciar os daos com operações estáticas
	public static SellerDao createSellerDao() { //retorna um método da interface mas internamente instancia uma implementação
		return new SellerDaoJDBC();
	}

}
