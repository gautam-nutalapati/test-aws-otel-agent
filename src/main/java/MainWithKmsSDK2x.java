
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.SignRequest;
import software.amazon.awssdk.services.kms.model.SignResponse;

import java.nio.ByteBuffer;

// Error is not seen when using SAWS KMS SDK 2.x
public class MainWithKmsSDK2x {
    public static void main(String[] args) {
        String message = "hello";

        SignRequest signRequest = SignRequest.builder()
                .message(SdkBytes.fromByteBuffer(ByteBuffer.wrap(message.getBytes())))
                .keyId("28e2ddc9-d92e-4e75-aa40-9db33e52a178")
                .signingAlgorithm("RSASSA_PKCS1_V1_5_SHA_256")
                .messageType("RAW")
                .build();

        final SignResponse signResult = KmsClient.create().sign(signRequest);
        System.out.println(signResult);
    }
}
