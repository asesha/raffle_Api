package com.alkimi.service;

import com.alkimi.vo.PaymentVO;

public interface PaymentService {
	public PaymentVO createPayment(PaymentVO vo);
	public PaymentVO getPaymentById(int paymentId);
	public PaymentVO updatePayment(int paymentId,PaymentVO vo);
}
