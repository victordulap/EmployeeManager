package com.step.model.employee.manager.json;

import com.step.model.employee.Employee;
import com.step.model.employee.Job;
import com.step.model.employee.manager.EmployeeFileIO;
import com.step.utilities.FileUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJSONIO implements EmployeeFileIO {
    private String path = ".\\data\\";
    private String fileName = "employees.json";
    private FileUtilities fileUtilities = new FileUtilities();

    @Override
    public List<Employee> importEmps() {
        File file = fileUtilities.newFile(this.path, this.fileName);
        List<Employee> importedEmps = new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (int i = 0; i < jsonArray.size(); i++) {
                Object o = jsonArray.get(i);
                JSONObject jsonObject = (JSONObject) o;
                Employee emp = new Employee(Integer.parseInt(String.valueOf(jsonObject.get("id"))),
                        String.valueOf(jsonObject.get("name")),
                        String.valueOf(jsonObject.get("surname")),
                        String.valueOf(jsonObject.get("idnp")),
                        LocalDate.parse(String.valueOf(jsonObject.get("birthDate"))),
                        LocalDate.parse(String.valueOf(jsonObject.get("engagedOn"))),
                        Double.parseDouble(String.valueOf(jsonObject.get("salary"))),
                        Job.valueOf(String.valueOf(jsonObject.get("job"))));

                importedEmps.add(emp);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return importedEmps;
    }

    @Override
    public void exportEmps(List<Employee> emps) {
        File file = fileUtilities.newFile(this.path, this.fileName);

        JSONArray arrJson = new JSONArray();
        for (Employee emp : emps) {
            JSONObject empJson = new JSONObject();

            empJson.put("id", emp.getId());
            empJson.put("name", emp.getName());
            empJson.put("surname", emp.getSurname());
            empJson.put("idnp", emp.getIdnp());
            empJson.put("birthDate", String.valueOf(emp.getBirthDate()));
            empJson.put("engagedOn", String.valueOf(emp.getEngagedOn()));
            empJson.put("salary", emp.getSalary());
            empJson.put("job", String.valueOf(emp.getJob()));

            arrJson.add(empJson);
        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(arrJson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
