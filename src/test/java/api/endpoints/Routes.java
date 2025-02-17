package api.endpoints;

public class Routes {
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public static class Auth {
        public static final String AUTHORIZATION = BASE_URL+"/auth";
    }

    public static class Booking {
        public static final String GET_BOOKINGS = BASE_URL+"/booking";
        public static final String GET_BOOKING = BASE_URL+"/booking/{id}";
        public static final String POST_CREATE_BOOKING = BASE_URL+"/booking";
        public static final String PUT_UPDATE_BOOKING = BASE_URL+"/booking/{id}";
        public static final String PATCH_PARTIAL_UPDATE_BOOKING = BASE_URL+"/booking/{id}";
        public static final String DELETE_BOOKING = BASE_URL+"/booking/{id}";
    }

    public static class Ping {
        public static final String GET_PING = BASE_URL+"/ping";
    }
}
