package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//Método que faz a inserção na tabela de um novo valor
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement(
					"INSERT INTO seller "
							+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
							+ "Values "
							+ "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) { //se linhas afetas for maior que 0
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else { //caso nenhuma linha tenha sido alterada
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
			}
			
		}
		catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
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
				Department dep =  instantiateDepartment(rs); 
				
				Seller obj = isntantiateSeller(rs, dep);
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
	
	//função da isntanciação do seller

	private Seller isntantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	//função da isntanciação do departamento
	public Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	
	//retorna todos o vendedores
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( 
					"SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"ORDER BY Name");
			
			rs = st.executeQuery();
			
			List <Seller> list = new ArrayList<>();
			Map <Integer, Department> map =  new HashMap<>();
			while(rs.next()) { 
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				 
				
				Seller obj = isntantiateSeller(rs, dep);
				
				list.add(obj);
				
			}
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	//método que filtra por busca por departamento
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement( //Comando sql que retorna os dados de um ou mais vendedores através do departamento dele
					"SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"WHERE DepartmentId = ? "+
					"ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List <Seller> list = new ArrayList<>();
			Map <Integer, Department> map =  new HashMap<>();
			while(rs.next()) { 
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				 
				
				Seller obj = isntantiateSeller(rs, dep);
				
				list.add(obj);
				
			}
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	

}
