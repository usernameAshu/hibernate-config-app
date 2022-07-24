package com.app.delivery.dao;

import java.util.List;

import com.app.delivery.entityPayment.PaymentInvoice;

public interface PaymentDao {

	public List<PaymentInvoice> getAllInvoice();

	public void savePaymentInvoice(PaymentInvoice paymentInvoice);

	public PaymentInvoice getPaymentInvoice(int id);
}
