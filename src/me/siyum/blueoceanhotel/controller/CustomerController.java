package me.siyum.blueoceanhotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.siyum.blueoceanhotel.model.Customer;
import me.siyum.blueoceanhotel.util.CRUDUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    public static ResultSet searchText(String searchText) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM customers WHERE id LIKE ? || name LIKE ?", searchText, searchText);
    }

    public static ResultSet getLastID() throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT id FROM customers ORDER BY id DESC LIMIT 1");
    }

    public static boolean saveCustomer(Customer r) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO customers VALUES(?,?,?,?,?,?)",
                r.getId(), r.getName(), r.getNic(), r.getPhone(), r.getEmail(), r.getAddress());
    }

    public static boolean updateCustomer(Customer r) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE customers SET name=?,nic=?,phone=?,email=?, address=? WHERE id=?",
                r.getName(), r.getNic(), r.getPhone(), r.getEmail(), r.getAddress(), r.getId());
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM customers WHERE id=?", id);
    }

    public static ObservableList<String> getAllAvailableRooms() throws SQLException, ClassNotFoundException {
        ResultSet set = CRUDUtil.execute("SELECT id FROM customers");

        ArrayList<String> idList = new ArrayList<>();

        while (set.next()) {
            idList.add(set.getString("id"));
        }
        return FXCollections.observableArrayList(idList);
    }
}