import java.util.*;

class Address implements Comparable<Address> {
    private final String city;
    private final String street;
    private final String house;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    @Override
    public String toString() {
        return "%s, %s, %s".formatted(house, street, city);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null || getClass() != that.getClass()) {
            return false;
        }
        Address address = (Address) that;

        return Objects.equals(house, address.house) &&
                Objects.equals(street, address.street) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house);
    }

    @Override
    public int compareTo(Address o) {
        int res1 = this.house.compareTo(o.house);
        int res2 = this.street.compareTo(o.street);
        int res3 = this.city.compareTo(o.city);
        if (res1 != 0) {
            return res1 < 0 ? -1 : 1;
        } else if (res2 != 0) {
            return res2 < 0 ? -1 : 1;
        } else if (res3 != 0) {
            return res3 < 0 ? -1 : 1;
        } else {
            return 0;
        }
    }
}

// do not change the code below
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Address> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            String[] arguments = sc.nextLine().split(",");
            list.add(new Address(arguments[0], arguments[1], arguments[2]));
        }
        Collections.sort(list);
        Checker.check(list);
    }
}