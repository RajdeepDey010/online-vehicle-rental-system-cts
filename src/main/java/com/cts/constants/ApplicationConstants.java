package com.cts.constants;

public class ApplicationConstants {
    public static final String CLIENTURLPREFIX = "/api/client/*";
    public static final String ADMINURLPREFIX = "/api/admin/*";
    public static final String CLIENTPREFIX = "/api/client";
    public static final String ADMINPREFIX = "/api/admin";
    public static final String APIPREFIX = "/api";

    public static final String REGISTERVEHICLE = "/register/vehicle";
    public static final String GETALLVEHICLES = "/vehicles/all";
    public static final String GETALLBOOKING = "/booking/all";
    public static final String GETALLUSERS = "/users/list";
    public static final String DELETEVEHICLE = "/vehicle/delete";
    public static final String BLOCKUSER = "/user/block/{emailAddress}";
    public static final String UNBLOCKUSER = "/user/unblock/{emailAddress}";
    public static final String USERDETAILS = "/userdetails";

    public static final String VEHICLESEARCH = "/vehiclesearch";
    public static final String BOOK = "/book";
    public static final String BOOKINGDETAILS = "/book/details";
    public static final String BOOKINGCANCEL = "/book/cancel";
    public static final String RIDECOMPLETE = "/ride/complete";
    public static final String VEHICLESLOTS = "/vehicle/slots";

    public static final String USERVALIDATE = "/validate";
    public static final String USERREGISTER = "/register";

    public static final String JWTTOKENHEADERNAME = "jwttoken";
    public static final String VALIDATETOKEN = "/validatetoken";

}
