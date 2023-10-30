package momo.service.processor;

import momo.service.config.Environment;
import momo.service.enums.ConfirmRequestType;
import momo.service.enums.Language;
import momo.service.models.*;
import momo.service.models.ConfirmRequest;
import momo.service.shared.constants.Parameter;
import momo.service.shared.exception.MoMoException;
import momo.service.shared.utils.Encoder;
import momo.service.shared.utils.LogUtils;
import momo.service.models.ConfirmResponse;
import momo.service.models.HttpResponse;

public class ConfirmTransaction extends AbstractProcess<ConfirmRequest, ConfirmResponse> {
    public ConfirmTransaction(Environment environment) {
        super(environment);
    }

    public static ConfirmResponse process(Environment env, String orderId, String requestId, String amount, ConfirmRequestType requestType, String description) throws Exception {
        try {
            ConfirmTransaction m2Processor = new ConfirmTransaction(env);

            ConfirmRequest request = m2Processor.createConfirmTransactionRequest(orderId, requestId, amount, requestType, description);
            ConfirmResponse response = m2Processor.execute(request);
            System.out.println("tra ve cai nay: "+response);
            return response;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ConfirmResponse execute(ConfirmRequest request) throws MoMoException {
        try {

            String payload = getGson().toJson(request, ConfirmRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint().getConfirmUrl(), payload);

            if (response.getStatus() != 200) {
                throw new MoMoException("[ConfirmTransactionResponse] [" + request.getOrderId() + "] -> Error API");
            }
            System.out.println("tra ve cai nay: "+response);

            ConfirmResponse confirmResponse = getGson().fromJson(response.getData(), ConfirmResponse.class);
//            String responserawData =   Parameter.ORDER_ID + "=" + confirmResponse.getOrderId() +
//                    "&" + Parameter.MESSAGE + "=" + confirmResponse.getMessage() +
//                    "&" + Parameter.RESULT_CODE + "=" + confirmResponse.getResultCode();

            return confirmResponse;

        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid params confirm MoMo Request");
        }
    }

    public ConfirmRequest createConfirmTransactionRequest(String orderId, String requestId, String amount, ConfirmRequestType requestType, String description) {

        try {
            String requestRawData = new StringBuilder()
                    .append(Parameter.ACCESS_KEY).append("=").append(partnerInfo.getAccessKey()).append("&")
                    .append(Parameter.AMOUNT).append("=").append(amount).append("&")
                    .append(Parameter.DESCRIPTION).append("=").append(description).append("&")
                    .append(Parameter.ORDER_ID).append("=").append(orderId).append("&")
                    .append(Parameter.PARTNER_CODE).append("=").append(partnerInfo.getPartnerCode()).append("&")
                    .append(Parameter.REQUEST_ID).append("=").append(requestId).append("&")
                    .append(Parameter.REQUEST_TYPE).append("=").append(requestType.getConfirmRequestType())
                    .toString();

            String signRequest = Encoder.signHmacSHA256(requestRawData, partnerInfo.getSecretKey());
            return new ConfirmRequest(partnerInfo.getPartnerCode(), orderId, requestId, Language.EN, Long.valueOf(amount), "", ConfirmRequestType.CAPTURE, signRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
