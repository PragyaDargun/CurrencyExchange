package com.currency.exchange.util;

import com.currency.exchange.dto.request.User;
import com.currency.exchange.enums.CustomerType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * TODO: This is the placeholder to fetch the user details like user tenure, user type (Member, Employee).
 * To get the User Details using userId.
 */
@Service
public class UserDetailsUtil {

    /**
     * Added the mocked values for few userIDs to execute the logic end to end.
     *
     * @param userID - userID
     * @return User - User object
     */
    public User getUserDetails(String userID) {
        //Add the call to Database or any other microservice to get the User details.
        return switch (userID) {
            case "1" -> User.builder().userId("1")
                    .customerType(CustomerType.EMPLOYEE)
                    .joiningDate(
                            LocalDate.of(2022, 2, 12)).build();
            case "2" ->
                    User.builder().userId("2").customerType(CustomerType.MEMBER)
                            .joiningDate(
                                    LocalDate.of(2022, 2, 12))
                            .build();
            case "3" ->
                    User.builder().userId("3").customerType(CustomerType.NONE)
                            .joiningDate(
                                    LocalDate.of(2022, 2, 12)).build();
            case "4" ->
                    User.builder().userId("4").customerType(CustomerType.NONE)
                            .joiningDate(
                                    LocalDate.now()).build();
            default -> throw new IllegalStateException(
                    "User not found with userId: " + userID);
        };

    }

    /**
     * method to calculate user tenure.
     *
     * @param user user
     * @return boolean
     */
    public boolean isCustomerForMoreThanTwoYears(User user) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
        return user.getJoiningDate().isBefore(twoYearsAgo);
    }
}

