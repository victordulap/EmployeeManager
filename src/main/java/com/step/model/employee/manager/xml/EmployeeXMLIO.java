package com.step.model.employee.manager.xml;

import com.step.model.employee.Employee;
import com.step.model.employee.Job;
import com.step.model.employee.manager.EmployeeFileIO;
import com.step.utilities.FileUtilities;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeXMLIO implements EmployeeFileIO {
    private String path = ".\\data\\";
    private String fileName = "employeesSerialized.xml";
    private FileUtilities fileUtilities = new FileUtilities();

    @Override
    public List<Employee> importEmps() {
        File file = fileUtilities.newFile(this.path, this.fileName);
        List<Employee> importedEmps = new ArrayList<>();

        try {
            /*Simple API for XML*/
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = null;
            document = saxBuilder.build(file);
            Element employees = document.getRootElement(); // <employees>
            List<Element> employeeList = employees.getChildren("employee");
            for (Element employee : employeeList) {
                Element xmlId = employee.getChild("id");
                Element xmlName = employee.getChild("name");
                Element xmlSurname = employee.getChild("surname");
                Element xmlIdnp = employee.getChild("idnp");
                Element xmlBirthDate = employee.getChild("birthDate");
                Element xmlEngagedOn = employee.getChild("engagedOn");
                Element xmlSalary = employee.getChild("salary");
                Element xmlJob = employee.getChild("job");

                Integer id = Integer.valueOf(xmlId.getText());
                String name = xmlName.getText();
                String surname = xmlSurname.getText();
                String idnp = xmlIdnp.getText();
                LocalDate birthDate = LocalDate.parse(xmlBirthDate.getText());
                LocalDate engagedOn = LocalDate.parse(xmlEngagedOn.getText());
                Double salary = Double.valueOf(xmlSalary.getText());
                Job job = Job.valueOf(xmlJob.getText());

                importedEmps.add(new Employee(id, name, surname, idnp, birthDate, engagedOn, salary, job));
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }

        return importedEmps;
    }

    @Override
    public void exportEmps(List<Employee> emps) {
        File file = fileUtilities.newFile(this.path, this.fileName);

        Element employees = new Element("employees");               // <employees>
        Document doc = new Document(employees);
        for (Employee emp : emps) {
            Element employee = new Element("employee");             //  <employee>

            Element id = new Element("id");
            Element name = new Element("name");
            Element surname = new Element("surname");
            Element idnp = new Element("idnp");
            Element birthDate = new Element("birthDate");
            Element engagedOn = new Element("engagedOn");
            Element salary = new Element("salary");
            Element job = new Element("job");

            id.setText(String.valueOf(emp.getId()));
            name.setText(emp.getName());
            surname.setText(emp.getSurname());
            idnp.setText(emp.getIdnp());
            birthDate.setText(String.valueOf(emp.getBirthDate()));
            engagedOn.setText(String.valueOf(emp.getEngagedOn()));
            salary.setText(String.valueOf(emp.getSalary()));
            job.setText(String.valueOf(emp.getJob()));

            employee.addContent(id);
            employee.addContent(name);
            employee.addContent(surname);
            employee.addContent(idnp);
            employee.addContent(birthDate);
            employee.addContent(engagedOn);
            employee.addContent(salary);
            employee.addContent(job);

            employees.addContent(employee);
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        try {
            xmlOutputter.output(doc, new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
