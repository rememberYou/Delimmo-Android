package heh.be.delimmo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by someone on 5/22/17.
 */

public class RealEstate {

    private int id;

    private String picture;
    private String country;
    private String title;
    private String content;

    private double price;

    private double latitude;
    private double longitude;

    private String city;
    private String zip_code;
    private String street;

    private int type_id;

    private String slug;

    private String created_at;
    private String updated_at;

    public RealEstate(int id, String picture, String country, String title,
                      String content, double price, double latitude,
                      double longitude, String city, String zip_code,
                      String street, int type_id, String slug,
                      String created_at, String updated_at) {
        this.id = id;
        this.picture = picture;
        this.country = country;
        this.title = title;
        this.content = content;
        this.price = price;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.zip_code = zip_code;
        this.street = street;
        this.type_id = type_id;
        this.slug = slug;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Latitude: " + latitude + " Longitude: " + longitude + " Price: " + price;
    }

    public static String jsonToPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        return gson.toJson(json);
    }


    public int getId() { return id; }

    public String getPicture() { return picture; }

    public String getCountry() { return country; }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public double getPrice() { return price; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getCity() { return city; }

    public String getZip_code() { return zip_code; }

    public String getStreet() { return street; }

    public int getType_id() { return type_id; }

    public String getSlug() { return slug; }

    public String getCreated_at() { return created_at; }

    public String getUpdated_at() { return updated_at; }
}