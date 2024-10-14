package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== Teste 1: seller findById ");
		
		Seller seller =  sellerDao.findById(3);
		
		System.out.println(seller);
		System.out.println();
		
		System.out.println("=== Teste 2: seller findByDepartment  ");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(System.out::println);
		
		System.out.println();

		
		System.out.println("=== Teste 3: seller findAll  ");
		 list = sellerDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println();
		
		
		System.out.println("=== Teste 4: seller insert  ");
		Seller newSeller = new Seller(null, "fulano", "fulano@gmail.com", new Date(), 5.000, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserido! novo id= " + newSeller.getId());

	}
}
