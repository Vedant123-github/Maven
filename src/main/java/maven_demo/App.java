package maven_demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

	private static Statement stmt;
	private static ResultSet results;
	
	public static void main(String[] args) {
		
		String sql_select = "Select * From student2";
		
try(Connection conn = DBconnection.createNewDBconnection()){
			
			stmt = conn.createStatement();
			results = stmt.executeQuery(sql_select);
			
			List<Student> studentsList = new ArrayList<Student>();			
			
			 while (results.next()) {
				 
				Student stdObject = new Student();
				
				stdObject.setStudentId(Integer.valueOf(results.getString("StudentId")));
				stdObject.setStudentName(results.getString("StudentName"));
				stdObject.setStudentAge(results.getString("StudentAge"));
				stdObject.setStudentAddress(results.getString("StudentAddress"));
				
				studentsList.add(stdObject);
			 }
			
			ObjectMapper mapper = new ObjectMapper();
		    String JSONOutput = mapper.writeValueAsString(studentsList);
		    System.out.println(JSONOutput);
		    
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


	}

}
