package PD7;

import java.util.Objects;

public class Alumno {
    private int ID;
    private String fullName;
    private String email;

    // Constructor, getters, setters, etc.

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Alumno other = (Alumno) obj;
        return ID == other.ID && Objects.equals(fullName, other.fullName) && Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, fullName, email);
    }
}
