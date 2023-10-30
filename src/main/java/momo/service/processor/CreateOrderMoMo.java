package momo.service.processor;

import momo.service.models.HttpResponse;
import momo.service.models.PaymentRequest;
import momo.service.models.PaymentResponse;
import momo.service.enums.*;
import momo.service.shared.constants.Parameter;
import momo.service.shared.exception.MoMoException;
import momo.service.config.Environment;
import momo.service.shared.utils.Encoder;
import momo.service.enums.Language;
import momo.service.enums.RequestType;

/**
 * @author hainguyen Documention: https://developers.momo.vn
 */
public class CreateOrderMoMo extends AbstractProcess<PaymentRequest, PaymentResponse> {

	public CreateOrderMoMo(Environment environment) {
		super(environment);
	}

	/**
	 * Capture MoMo Process on Payment Gateway
	 *
	 * @param amount
	 * @param extraData
	 * @param orderInfo
	 * @param env       name of the environment (dev or prod)
	 * @param orderId   unique order ID in MoMo system
	 * @param requestId request ID
	 * @param returnURL URL to redirect customer
	 * @param notifyURL URL for MoMo to return transaction status to merchant
	 * @return PaymentResponse
	 **/

	public static PaymentResponse process(Environment env, String orderId, String requestId, String amount,
			String orderInfo, String returnURL, String notifyURL, String extraData, RequestType requestType,
			Boolean autoCapture) throws Exception {
		try {
			CreateOrderMoMo m2Processor = new CreateOrderMoMo(env);

			PaymentRequest request = m2Processor.createPaymentCreationRequest(orderId, requestId, amount, orderInfo,
					returnURL, notifyURL, extraData, requestType, autoCapture);
			PaymentResponse captureMoMoResponse = m2Processor.execute(request);

			return captureMoMoResponse;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public PaymentResponse execute(PaymentRequest request) throws MoMoException {
		try {

			String payload = getGson().toJson(request, PaymentRequest.class);

			HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint().getCreateUrl(), payload);

			if (response.getStatus() != 200) {
				throw new MoMoException("[PaymentResponse] [" + request.getOrderId() + "] -> Error API");
			}

			System.out.println("uweryei7rye8wyreow8: " + response.getData());

			PaymentResponse captureMoMoResponse = getGson().fromJson(response.getData(), PaymentResponse.class);
			String responserawData = Parameter.REQUEST_ID + "=" + captureMoMoResponse.getRequestId() + "&"
					+ Parameter.ORDER_ID + "=" + captureMoMoResponse.getOrderId() + "&" + Parameter.MESSAGE + "="
					+ captureMoMoResponse.getMessage() + "&" + Parameter.PAY_URL + "=" + captureMoMoResponse.getPayUrl()
					+ "&" + Parameter.RESULT_CODE + "=" + captureMoMoResponse.getResultCode();
			System.out.println("vao day: " + responserawData);
			return captureMoMoResponse;

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new IllegalArgumentException("Invalid params capture MoMo Request");
		}
	}

	public PaymentRequest createPaymentCreationRequest(String orderId, String requestId, String amount,
			String orderInfo, String returnUrl, String notifyUrl, String extraData, RequestType requestType,
			Boolean autoCapture) {

		try {
			String requestRawData = new StringBuilder().append(Parameter.ACCESS_KEY).append("=")
					.append(partnerInfo.getAccessKey()).append("&").append(Parameter.AMOUNT).append("=").append(amount)
					.append("&").append(Parameter.EXTRA_DATA).append("=").append(extraData).append("&")
					.append(Parameter.IPN_URL).append("=").append(notifyUrl).append("&").append(Parameter.ORDER_ID)
					.append("=").append(orderId).append("&").append(Parameter.ORDER_INFO).append("=").append(orderInfo)
					.append("&").append(Parameter.PARTNER_CODE).append("=").append(partnerInfo.getPartnerCode())
					.append("&").append(Parameter.REDIRECT_URL).append("=").append(returnUrl).append("&")
					.append(Parameter.REQUEST_ID).append("=").append(requestId).append("&")
					.append(Parameter.REQUEST_TYPE).append("=").append(requestType.getRequestType()).toString();

			String signRequest = Encoder.signHmacSHA256(requestRawData, partnerInfo.getSecretKey());
			return new PaymentRequest(partnerInfo.getPartnerCode(), orderId, requestId, Language.EN, orderInfo,
					Long.valueOf(amount), "test MoMo", null, requestType, returnUrl, notifyUrl, "test store ID",
					extraData, null, autoCapture, null, signRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}