 package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
		void insert (Department obj);//Operação responsável por inserir no banco de dados 
		void update (Department obj);//Operação responsável por atualizar no banco de dados 
		void deletById (Integer id);//Operação responsável por remover através do Id no banco de dados 
		Department findById(Integer id); //consulta no banco de dados um objeto(departamento) com o id passado como argumento, se não encontrar, retorna null
		List<Department> findAll(); //retorna todos os departamento adicionandos a uma lista
}