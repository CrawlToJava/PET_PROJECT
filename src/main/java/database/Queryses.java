package database;

public class Queryses {
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ";

    public static final String FIND_USER_BY_LAST_NAME = "SELECT * FROM users WHERE last_name = ?";

    public static final String DELETE_USER = "DELETE FROM users WHERE id = ";

    public static final String SAVE_USER = "INSERT INTO users VALUES(?,?,?,?,?,?)";

    public static final String UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, age = ?, sex = ?, status = ? WHERE id =";

    public static final String FIND_ALL_SCOOTERS = "SELECT * FROM scooters";

    public static final String FIND_SCOOTER_BY_ID = "SELECT * FROM scooters WHERE id = ";

    public static final String DELETE_SCOOTER = "DELETE FROM scooters WHERE id = ";

    public static final String SAVE_SCOOTER = "INSERT INTO scooters VALUES(?,?,?,?,?,?)";

    public static final String UPDATE_SCOOTER = "UPDATE scooters SET price = ?, rental_point_id = ?, model_id = ?, scooter_status = ?, user_id = ? WHERE id =";

    public static final String FIND_ALL_ORDERS = "SELECT * FROM orders";

    public static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ";

    public static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ";

    public static final String SAVE_ORDER = "INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";

    public static final String UPDATE_RENTAL_POINT = "UPDATE rental_points SET location = ?, rental_point_status = ? WHERE id =";

    public static final String FIND_ALL_RENTAL_POINTS = "SELECT * FROM rental_points";

    public static final String FIND_RENTAL_POINT_BY_ID = "SELECT * FROM rental_points WHERE id = ";

    public static final String DELETE_RENTAL_POINT = "DELETE FROM rental_points WHERE id = ";

    public static final String SAVE_RENTAL_POINT = "INSERT INTO rental_points VALUES(?,?,?)";

    public static final String UPDATE_ORDER = "UPDATE orders SET ordered_at = ?, finished_at = ?, total_price = ?, order_status = ?, scooter_id = ?, rental_point_id = ?, user_id = ? WHERE id = ";

    public static final String DELETE_MODEL = "DELETE FROM models WHERE id = ";

    public static final String FIND_ALL_MODELS = "SELECT * FROM models";

    public static final String FIND_MODEL_BY_ID = "SELECT * FROM models WHERE id =";

    public static final String SAVE_MODEL = "INSERT INTO models VALUES(?,?,?,?,?,?,?)";

    public static final String UPDATE_MODEL = "UPDATE models SET brand = ?, model = ?, year = ?, max = ?, range = ?, power = ? WHERE id = ";
}
