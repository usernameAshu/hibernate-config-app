package com.app.delivery.entityPayment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "PAYMENT_INVOICE_T")
public class PaymentInvoice {

	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paymentId;

	@Column(name = "order_id")
	private long orderId;

	@Column(name = "order_value")
	private double orderValue;

	@Column(name = "order_date")
	private Date orderDate;

	public PaymentInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "PaymentInvoice [paymentId=" + paymentId + ", orderId=" + orderId + ", orderValue=" + orderValue
				+ ", orderDate=" + orderDate + "]";
	}
	
	
}
