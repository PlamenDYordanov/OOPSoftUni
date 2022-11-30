package Builder;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Bulgaria", "Varna");
        Address address1 = Address.builder()
                .withCity("Sofia")
                .withCountry("Bulgaria")
                .build();

    }
}
