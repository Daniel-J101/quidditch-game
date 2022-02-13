package main.models;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.util.Arrays;
import java.util.Objects;

public class Team {

    public static final String POSITION_CHASER = "chaser";
    public static final String POSITION_SEEKER = "seeker";
    public static final String POSITION_KEEPER = "keeper";
    private String house, keeper, seeker;
    private String[] chasers;

    public Team(String house, String keeper, String seeker, String[] chasers) {
        if(
                house == null ||
                house.isEmpty() ||
                keeper == null ||
                keeper.isEmpty() ||
                seeker == null ||
                seeker.isEmpty() ||
                chasers == null ||
                chasers.length != 3 ||
                Team.hasNull(chasers) ||
                Team.hasBlank(chasers)
        )
            throw new IllegalArgumentException("Arguments cannot be null/blank. Chasers has to have length of 3");
        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = chasers.clone();

    }

    public Team(Team source) {
        house = source.house;
        keeper = source.keeper;
        seeker = source.seeker;
        chasers = source.chasers.clone();
    }

    public static boolean hasNull(String[] arr) {
        return Arrays.stream(arr).anyMatch(thing -> thing == null);
    }

    public static boolean hasBlank(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].trim().length() == 0)
                return true;
        }
        return false;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        if(house == null || house.isEmpty())
            throw new IllegalArgumentException(house + " cannot be null or blank");
        this.house = house;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        if(keeper == null || keeper.isEmpty())
            throw new IllegalArgumentException(keeper + " cannot be null or blank");
        this.keeper = keeper;
    }

    public String getSeeker() {
        return seeker;
    }

    public void setSeeker(String seeker) {
        if(seeker == null || seeker.isEmpty())
            throw new IllegalArgumentException(seeker + " cannot be null or blank");
        this.seeker = seeker;
    }

    public String[] getChasers() {
        return chasers.clone();

    }

    public void setChasers(String[] chasers) {
        this.chasers = chasers.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Team))
            return false;

        Team team = (Team) obj;
        return team.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(house, keeper, seeker, Arrays.toString(chasers));
    }

    @Override
    public String toString() {
        return "House: " + this.house + "\n" +
                "Keeper: " + this.keeper + "\n" +
                "Seeker: "  + this.seeker + "\n" +
                "Chasers: " + Arrays.toString(this.chasers) + "\n";
    }
}