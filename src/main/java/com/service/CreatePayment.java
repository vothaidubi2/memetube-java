package com.service;

import org.springframework.stereotype.Service;
import momo.service.config.Environment;
import momo.service.processor.CreateOrderMoMo;
import momo.service.enums.RequestType;
import momo.service.models.PaymentResponse;

@Service
public class CreatePayment {
    public String createPayment(long inputAmount,String extraData) throws Exception {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        long amount = inputAmount;
        String orderInfo = "Pay the emoji on MemeTube";
        String returnURL = "http://localhost:3000/success-transaction";
        String notifyURL = "http://localhost:3000/success-transaction";

        Environment environment = Environment.selectEnv("dev");
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, extraData, RequestType.CAPTURE_WALLET, Boolean.TRUE);
        return captureWalletMoMoResponse.getPayUrl();
    }

}
