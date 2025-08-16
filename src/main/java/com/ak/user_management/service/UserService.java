package com.ak.user_management.service;

import com.ak.user_management.entity.User;
import com.ak.user_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    //Automatically inject the UserRepository here
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    // Method to retrieve a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Method to create a users
    public List<User> createUser(List<User> usersList) {
        List<User> users= null;
        try {
            // Validate the input list
            validateUsersList(usersList);
            users= userRepository.saveAll(usersList);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating users: " + e.getMessage());
        }
        return users;
    }


    // Method to update a user
    public User updateUser(Long id, User user) {
       try{
         User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " does not exist"));
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setMobileNumber(user.getMobileNumber());
            existingUser.setAddress(user.getAddress());
            // Update other fields as necessary
          // Save the updated user
            return userRepository.save(existingUser);
       }catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            throw new IllegalArgumentException("Error updating user with ID " + id);
       }
    }
    // Method to delete a user
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
    }



    public void validateUsersList(List<User> usersList){
        if (usersList == null || usersList.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be null or empty");
        }
   /*     for (User user : usersList) {
            if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
                throw new IllegalArgumentException("User name cannot be null or empty");
            }
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new IllegalArgumentException("User email cannot be null or empty");
            }
            // Add more validation as needed
        }*/
    }

}