package model;

public enum Role {
    ADMIN,
    MODERATOR,
    GARDENER,
    CONSULT,
    MANAGER,
    SALE;
    
    public static Role getById(int id) {
        return values()[id - 1];
    }
    
    public int getId() {
        return ordinal() + 1;
    }
}
