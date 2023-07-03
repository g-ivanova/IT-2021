package users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user") 
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
UserCollection collection;
 
	public void init(ServletConfig config) throws ServletException {
	collection = UserCollection.getInstance();
	}

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		int id = Integer.parseInt(request.getParameter("id"));
		
		User registeredUser = collection.getById(id);
		
		request.setAttribute("registeredUser", registeredUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/EditProfilePage.jsp");
		rd.forward(request, response);
		
		
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//да се допълни за домашно
		
		request.setCharacterEncoding("UTF-8");
		
		int id= Integer.parseInt(request.getParameter("id"));
		
		String name = request.getParameter("personal_name");
		String jobTitle = request.getParameter("job_title");
		String description = request.getParameter("description");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String street = request.getParameter("street");

		
		
		User updatedUser=collection.getById(id);
		
		updatedUser.setPersonalName(name);
		updatedUser.setJobTitle(jobTitle);
		updatedUser.setDescription(description);
		updatedUser.setEmail(email);
		updatedUser.setPhone(phone);
		updatedUser.getAddress().setCity(city);
		updatedUser.getAddress().setStreet(street);
		
		
		for(int i=0; i<updatedUser.getProffesionalSkills().size();i++)
		{
			String profSkillName = request.getParameter("profSkillName"+i);
			int profSkillLevel = Integer.parseInt(request.getParameter("profSkillLevel"+i));
			updatedUser.getProffesionalSkills().get(i).setSkillName(profSkillName);
			updatedUser.getProffesionalSkills().get(i).setLevel(profSkillLevel);
		}
		
		for(int i=0; i<updatedUser.getPersonalSkills().size();i++)
		{
			String personalSkillName = request.getParameter("personalSkillName"+i);
			int personalSkillLevel = Integer.parseInt(request.getParameter("personalSkillLevel"+i));
			updatedUser.getPersonalSkills().get(i).setSkillName(personalSkillName);
			updatedUser.getPersonalSkills().get(i).setLevel(personalSkillLevel);
		}
		
		
		request.setAttribute("registeredUser", updatedUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ProfilePage.jsp");
		rd.forward(request, response);
		 
	}

}
