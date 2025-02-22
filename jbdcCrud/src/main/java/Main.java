
public class Main {

    public static void main(String[] args){

        UserDAO userDAO = new UserDAO();

        userDAO.AddUser("john doe","john@example.com");
        userDAO.AddUser("Alice Smith", "alice@example.com");
        userDAO.AddUser("Bob Johnson", "bob@example.com");
        userDAO.AddUser("Charlie Brown", "charlie@example.com");
        userDAO.AddUser("David White", "david@example.com");
        userDAO.AddUser("Emma Davis", "emma@example.com");

        for(User user : userDAO.getAllUsers()){
            System.out.println("ID:"+user.getId()+" ,Name:"+user.getName()+" ,Email:"+user.getEmail());
        }

        userDAO.updateUser(1, "John Doe", "john.updated@example.com");

        userDAO.deleteUser(3);

        for(User user : userDAO.getAllUsers()){
            System.out.println("ID:"+user.getId()+" ,Name:"+user.getName()+" ,Email:"+user.getEmail());
        }

    }
}