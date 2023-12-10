package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    // Use the DBConnect class to get the database connection
    public Student getUserByUsername(String username) {
        String query = "SELECT * FROM myapp_students WHERE username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String role = rs.getString("role");
                return new Student(user, pass, role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean addUser(Student student) {
        String query = "INSERT INTO myapp_students (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getUsername());
            pstmt.setString(2, student.getPassword());
            pstmt.setString(3, student.getRole());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<University> getAllUniversities() {
        List<University> universities = new ArrayList<>();
        String sql = "SELECT * FROM myapp_universities";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("universityId");
                String name = rs.getString("universityName");
                String location = rs.getString("location");
                universities.add(new University(id, name, location));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return universities;
    }
    
    public boolean addUniversity(University university) {
        String sql = "INSERT INTO myapp_universities (universityName, location) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, university.getuniversityName());
            pstmt.setString(2, university.getlocation());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        university.setuniversityId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM myapp_courses";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int universityId = rs.getInt("universityId");
                courses.add(new Course(courseId, courseName, universityId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return courses;
    }
    
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO myapp_courses (courseName, universityId) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getuniversityId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<Stream> getAllStreams() {
        List<Stream> streams = new ArrayList<>();
        String sql = "SELECT * FROM myapp_streams";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int streamId = rs.getInt("streamId");
                String streamName = rs.getString("streamName");
                streams.add(new Stream(streamId, streamName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return streams;
    }
    public boolean addStream(Stream stream) {
        String sql = "INSERT INTO myapp_streams (streamName) VALUES (?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stream.getstreamName());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    
    public List<Scholarship> getAllScholarships() {
        List<Scholarship> scholarships = new ArrayList<>();
        String sql = "SELECT * FROM myapp_scholarships";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("scholarshipId");
                String name = rs.getString("scholarshipName");
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                scholarships.add(new Scholarship(id, name, description, amount));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scholarships;
    }
    
    public boolean addScholarship(Scholarship scholarship) {
        String sql = "INSERT INTO myapp_scholarships (scholarshipName, description, amount) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, scholarship.getScholarshipName());
            pstmt.setString(2, scholarship.getDescription());
            pstmt.setDouble(3, scholarship.getAmount());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateScholarship(Scholarship scholarship) {
        String sql = "UPDATE myapp_scholarships SET scholarshipName = ?, description = ?, amount = ? WHERE scholarshipId = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, scholarship.getScholarshipName());
            pstmt.setString(2, scholarship.getDescription());
            pstmt.setDouble(3, scholarship.getAmount());
            pstmt.setInt(4, scholarship.getScholarshipId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteScholarship(int scholarshipId) {
        String sql = "DELETE FROM myapp_scholarships WHERE scholarshipId = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scholarshipId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
