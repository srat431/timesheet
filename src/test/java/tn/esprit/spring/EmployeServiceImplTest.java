
package tn.esprit.spring;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	@Autowired
	IEmployeService es;

	@Test
	public void testGetNombreEmployeJPQL() {
		int nbr = es.getNombreEmployeJPQL();
		assertEquals(3, nbr);
	}

	@Test
	public void testGetAllEmployeNamesJPQL() {
		List<String> listEmp = es.getAllEmployeNamesJPQL();
		// System.out.println(listEmp.size());
		assertEquals(2, listEmp.size());
	}

}