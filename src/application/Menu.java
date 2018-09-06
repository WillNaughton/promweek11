package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.FamilyDAO;
import entity.Family;

public class Menu {
	
	private TeamDAO TeamDAO = new TeamDAO();
	private MemberDAO MemberDAO = new MemberDAO();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Families", 
			"Display a Family", 
			"Add Family", 
			"Delete Family",
			"Add Family Member");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayFamilies();
				} else if (selection.equals("2")) {
					displayFamily();
				} else if (selection.equals("3")) {
					addFamily();
				} else if (selection.equals("4")) {
					deleteFamily();
				} else if (selection.equals("5")) {
					addFamilyMember();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Press enter to continue....");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n--------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayFamilies() throws SQLException {
		List<Family> teams = FamilyDAO.getFamily();
		for (Team team : teams) {
			System.out.println(team.getTeamId() + ": " + team.getName());
		}
	}
	
	private void displayFamily() throws SQLException  {
		System.out.print("Enter team id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Team team = TeamDAO.getTeamById(id);
		System.out.println(team.getTeamId() + ": " + team.getName());
		for (Member member : team.getMembers()) {
			System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName() + " " + member.getLastName());
		}
	}

	private void addFamily() throws SQLException {
		System.out.print("Enter new team name:");
		String teamName = scanner.nextLine();
		TeamDAO.createNewTeam(teamName);
	}
	
	private void deleteFamily() throws SQLException {
		System.out.print("Enter team id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		TeamDAO.deleteTeamById(id);
	}
	
	private void addFamilyMember() throws SQLException {
		System.out.print("Enter first name of new member:");
		String firstName = scanner.nextLine();
		System.out.print("Enter last name of new member:");
		String lastName = scanner.nextLine();
		System.out.print("Enter team id of new member:");
		int teamId = Integer.parseInt(scanner.nextLine());
		MemberDAO.createNewMember(firstName, lastName, teamId);
	}
	
}