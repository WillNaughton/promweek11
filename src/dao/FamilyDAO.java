package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Family;

public class FamilyDAO {
	
	private Connection connection;
	private final String GET_FAMILY_QUERY = "SELECT * FROM famaly";
	private final String GET_FAMILY_BY_ID_QUERY = "SELECT * FROM Famaly WHERE id = ?";
	private final String CREATE_NEW_FAMILY_QUERY = "INSERT INTO famaly(lastName, firstName, age, relation) VALUES(?, ?, ?, ?)";
	private final String DELETE_FAMILY_BY_ID_QUERY = "DELETE FROM famaly WHERE id = ?";
	private final String GET_MEMBERS_BY_TEAM_ID_QUERY = "SELECT * FROM members WHERE team_id = ?";
	private final String DELETE_MEMBERS_BY_TEAM_ID_QUERY = "DELETE FROM members WHERE team_id = ?";
	private final String CREATE_NEW_MEMBER_QUERY = "INSERT INTO members(first_name, last_name, team_id) VALUES(?,?,?)";
	private final String DELETE_MEMBER_BY_ID_QUERY = "DELETE FROM members WHERE id = ?";
	
	public FamilyDAO() {
		connection = DBConnection.getConnection();
	}
	public List<Family> getMembersByTeamId(int familyId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MEMBERS_BY_TEAM_ID_QUERY);
		ps.setInt(1, familyId);
		ResultSet rs = ps.executeQuery();
		List<Family> members = new ArrayList<Family>();
		
		while (rs.next()) {
			Family.add(new Family(rs.getInt(1), rs.getString(2), rs.getString(3), members));
		}
	public List<Family> getFamilys() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_FAMILY_QUERY).executeQuery();
		List<Family> teams = new ArrayList<Family>();
		
		while (rs.next()) {
			teams.add(populateFamily(rs.getInt(1), rs.getString(3), rs.getString(2)));
		}
		
		return teams;
	}
	
	public Family getFamilyById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FAMILY_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateFamily(rs.getInt(1), rs.getString(3), rs.getString(2));
	}
	
	public void createNewFamily(String lastName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_FAMILY_QUERY);
		ps.setString(1, lastName);
		ps.executeUpdate();
	}
	
	public void deleteFamilyById(int familyId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_FAMILY_BY_ID_QUERY);
		ps.setInt(1, familyId);
		ps.executeUpdate();
	}
	
	private Family populateFamily(int id, String firstName, String lastName) throws SQLException {
		return new Family(id, firstName, lastName, FamilyDAO.getMembersByFamilyId(id));
	}

}