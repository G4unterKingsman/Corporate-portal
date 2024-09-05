package ru.egarschool.naapplication.Corporate.portal.exception;

public class EmployeNotFoundException extends RuntimeException{
    public EmployeNotFoundException(String message) {
        super(message);
    }
}
