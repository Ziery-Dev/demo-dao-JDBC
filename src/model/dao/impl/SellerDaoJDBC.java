package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
	//classe que implementa a interface SellerDao
public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn; //Connection conn adicionado como atributo para ficar a disposição de toda a classe 
	
	public SellerDaoJDBC(Connection conn) {
		this.conn =  conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//Esse método trata de fazer a busca ddos dados de um vendedor de acordor com o Id inserido pelo usuário, sendo assim, o mesmo pega todos os dados do vendedor setando na instancia da classe seller
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( //Comando sql que retorna os dados de um vendodor através do id dele
					"SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"WHERE "+
					"seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) { //testa se veio algum resultado na consulta
				Department dep = new Department(); //intanciando a classe departamento e setando os valores
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				Seller obj = new Seller();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(dep);
				return obj;
			}
			return null;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	//Classe responsável pela implementação do JDBC da interface SellerDao

}
