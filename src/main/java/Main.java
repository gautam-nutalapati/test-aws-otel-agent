import java.nio.ByteBuffer;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.SignRequest;
import com.amazonaws.services.kms.model.SignResult;

public class Main {
    public static void main(String[] args) {
        String message = "hello";

        SignRequest signRequest = new SignRequest() {{
            setKeyId("28e2ddc9-d92e-4e75-aa40-9db33e52a178");
            setMessage(ByteBuffer.wrap(message.getBytes()));
            setSigningAlgorithm("RSASSA_PKCS1_V1_5_SHA_256");
            setMessageType("RAW");
        }};

        final SignResult signResult = AWSKMSClientBuilder.defaultClient().sign(signRequest);
        System.out.println(signResult);
    }
}
