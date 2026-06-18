Creational Design pattern used for creating complex objects
using method chaining
helps in preventing the use of telescoping constructor


class User{
    String id;
    String name;
    String city;
    String company;

    private User(Builder builder){
    this.id = builder.id;
    this.name = builder.name;
    this.city = builder.city;           
    this.company = builder.company;     
}
    }
    static class Builder{
        String id;
        String name;
        String city;
        String company;
        public Builder(){}

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder city(String city){
            this.city = city;
            return this;
        }
        public Builder company(String company){
            this.company = company;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}

User user = new User.Builder().name("Tanmay").id("123").city("bengaluru").build();