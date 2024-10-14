package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
		void insert (Seller obj);//Operação responsável por inserir no banco de dados 
		void update (Seller obj);//Operação responsável por atualizar no banco de dados 
		void deletById (Integer id);//Operação responsável por remover através do Id no banco de dados 
		Seller findById(Integer id); //consulta no banco de dados um objeto(seller) com o id passado como argumento, se não encontrar, retorna null
		List<Seller> findAll(); //retorna todos os sellers adicionandos a uma lista
		 List<Seller> findByDepartment (Department department); //Busca o vendedores de acordo o departamento
}