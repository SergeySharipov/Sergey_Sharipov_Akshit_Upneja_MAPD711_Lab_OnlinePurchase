package ca.akshit.and.sergey.sergey_sharipov_akshit_upneja_mapd711_lab_onlinepurchase.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Sergey-PC on 04.12.2017.
 */

public class Order {
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private UUID employeeId;
    private Date orderDate;
    private int status;

    public Order(UUID customerId, UUID productId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.productId = productId;
        this.orderDate = new Date();
        setStatus(Constants.Status.ACTIVE);
        this.employeeId = UUID.randomUUID();
    }

    public Order(UUID id) {
        this.id = id;
    }

    public Order() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getFormatOrderDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss-a dd.MM.yyyy");
        return formatForDateNow.format(getOrderDate());
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public String getStringStatus() {
        switch (status){
            case Constants.Status.ACTIVE:
                return "In progress";
            case Constants.Status.DONE:
                return "Delivery";
            default:
                return "";
        }
    }

    public void setStatus(int status) {
        if (status >= 0 && status <= 3)
            this.status = status;
        else this.status = -1;
    }
}
