package Builder;

public class Address {
    public String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public static Address.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String country;
        private String city;

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }
        public Address build() {
            return new Address(this.country, this.city);
        }
    }


}
