package tn.esprit.spring;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IDepartementService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class TimesheetServiceImplTest {

	@Autowired
	ITimesheetService timesheetService;
	@Autowired
	IEmployeService employeeService;
	
	
	/*
	@Test
	public void testajouterMission() {
		log.info("********************************Start Method Test Add Mission ******************************************************");
	
		//création d'une mission
		Mission m = new Mission();
		m.setName("Mission Mise en Prod");
		m.setDescription("Description de la mission de mise en Prod");
		int missionID = timesheetService.ajouterMission(m);
		assertNotNull(timesheetService.getAllEmployeByMission(missionID));
	
		//logging
		log.info("The number of Employees affected to the mission " + m.getName() + "is " + timesheetService.getAllEmployeByMission(missionID).size());	
		log.info("********************************End Method Test Add Mission ******************************************************");

		}
	*/
	
	
	@Test
	public void testajouterTimesheet() {
		log.info("********************************Start Method Test Add Timesheet******************************************************");

		
		//création d'une mission
				Mission m = new Mission();
				m.setName("Mission Mise en Prod");
				m.setDescription("Description de la mission de mise en Prod");
				int missionID = timesheetService.ajouterMission(m);
				assertNotNull(timesheetService.getAllEmployeByMission(missionID));
				
		//création d'un employee
				Employe e = new Employe();
				e.setEmail("mgara@vermeg.com");
				e.setNom("Gara");
				e.setPrenom("Malek");
				int employeeID = employeeService.addOrUpdateEmploye(e);
		
		Date startDate = new Date(1997,06,12);
		Date endDate = new Date(2021,06,12);
		timesheetService.ajouterTimesheet(missionID, employeeID, startDate, endDate);
		
		log.info("This is One TimeSheet of " + employeeService.getTimesheetsByMissionAndDate(e, m, startDate, endDate).size() + "Timesheets" );
		log.info("********************************End Method Test Add Timesheet ******************************************************");

	}
	
	
	
	@Test
	public void testvaliderTimesheet() {
		
		log.info("********************************Start Method Test Validate Timesheet******************************************************");

		Date startDate = new Date(1997,06,12);
		Date endDate = new Date(2021,06,12);
		
		int missionID = 2;
		int employeeID = 3;
		
		employeeService.affecterEmployeADepartement(employeeID, 1);
		employeeService.affecterEmployeADepartement(employeeID, 2);
		//employeeService.affecterEmployeADepartement(employeeID, 3);
		
		
		//création d'un département
		timesheetService.affecterMissionADepartement(missionID,2);
		
		//création d'un timesheet
		timesheetService.ajouterTimesheet(missionID, employeeID, startDate, endDate);

		//validation du timesheet
		timesheetService.validerTimesheet(missionID, employeeID, startDate, endDate, employeeID);
		
		log.info("********************************End Method Test Validate TimeSheet ******************************************************");

				
	}
	
	
	

}
