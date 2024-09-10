package ru.egarschool.naapplication.Corporate.portal.entity.enums;

public enum TaskStatus {
    CREATED("Создан"),
    IN_PROGRESS("В процессе"),
    COMPLETED("Завершён"),
    CANCELLED("Отменён");

    private final String name;

    TaskStatus(String displayName) {
        this.name = displayName;
    }

    public String getName() {
        return name;
    }
}
