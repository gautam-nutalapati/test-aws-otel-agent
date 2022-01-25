# test-aws-otel-agent
###This repo is demo project for reproducing issue https://github.com/aws-observability/aws-otel-java-instrumentation/issues/147

This repo is to demo exception thrown with [aws-otel auto-instrumentation java agent](https://github.com/aws-observability/aws-otel-java-instrumentation) when using AWS Java KMS SDK 1.X.

Run main program with VM arguments:
I use below in IntelliJ:
`-javaagent:"/Users/gautam/Documents/custom-docker-builds/service-base-image/files/otel-java-agent/aws-opentelemetry-agent.jar"`

The JAR is downloaded from [this page](https://aws-otel.github.io/docs/getting-started/java-sdk/trace-auto-instr). Latest at time of this issue: [Link](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.10.0/opentelemetry-javaagent.jar)

The same issue is not seen when using non-AWS otel instrumentation library from [this page](https://github.com/open-telemetry/opentelemetry-java-instrumentation).
Latest at time of this issue: [Link](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.10.0/opentelemetry-javaagent.jar)

I am using AWS credentials via Environment variables:
```
AWS_REGION=us-east-1
AWS_ACCESS_KEY_ID=***
AWS_SECRET_ACCESS_KEY=***
```

Exception thrown when using AWS OTEL java agent:
```
[otel.javaagent 2022-01-25 17:27:16:006 -0600] [main] INFO io.opentelemetry.javaagent.tooling.VersionLogger - opentelemetry-javaagent - version: 1.10.0-aws

Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end -7, length 0
at java.base/java.lang.String.checkBoundsBeginEnd(String.java:3734)
at java.base/java.lang.String.substring(String.java:1903)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.AwsSdkExperimentalAttributesExtractor$1.computeValue(AwsSdkExperimentalAttributesExtractor.java:34)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.AwsSdkExperimentalAttributesExtractor$1.computeValue(AwsSdkExperimentalAttributesExtractor.java:30)
at java.base/java.lang.ClassValue.getFromHashMap(ClassValue.java:228)
at java.base/java.lang.ClassValue.getFromBackup(ClassValue.java:210)
at java.base/java.lang.ClassValue.get(ClassValue.java:116)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.AwsSdkExperimentalAttributesExtractor.extractOperationName(AwsSdkExperimentalAttributesExtractor.java:55)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.AwsSdkExperimentalAttributesExtractor.onStart(AwsSdkExperimentalAttributesExtractor.java:43)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.AwsSdkExperimentalAttributesExtractor.onStart(AwsSdkExperimentalAttributesExtractor.java:26)
at io.opentelemetry.javaagent.shaded.instrumentation.api.instrumenter.Instrumenter.start(Instrumenter.java:174)
at io.opentelemetry.javaagent.shaded.instrumentation.awssdk.v1_11.TracingRequestHandler.beforeRequest(TracingRequestHandler.java:41)
at io.opentelemetry.javaagent.instrumentation.awssdk.v1_11.TracingRequestHandler.beforeRequest(TracingRequestHandler.java:45)
at com.amazonaws.http.AmazonHttpClient$RequestExecutor.runBeforeRequestHandlers(AmazonHttpClient.java:850)
at com.amazonaws.http.AmazonHttpClient$RequestExecutor.doExecute(AmazonHttpClient.java:792)
at com.amazonaws.http.AmazonHttpClient$RequestExecutor.executeWithTimer(AmazonHttpClient.java:779)
at com.amazonaws.http.AmazonHttpClient$RequestExecutor.execute(AmazonHttpClient.java:753)
at com.amazonaws.http.AmazonHttpClient$RequestExecutor.access$500(AmazonHttpClient.java:713)
at com.amazonaws.http.AmazonHttpClient$RequestExecutionBuilderImpl.execute(AmazonHttpClient.java:695)
at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:559)
at com.amazonaws.http.AmazonHttpClient.execute(AmazonHttpClient.java:539)
at com.amazonaws.services.kms.AWSKMSClient.doInvoke(AWSKMSClient.java:7785)
at com.amazonaws.services.kms.AWSKMSClient.invoke(AWSKMSClient.java:7752)
at com.amazonaws.services.kms.AWSKMSClient.invoke(AWSKMSClient.java:7741)
at com.amazonaws.services.kms.AWSKMSClient.executeSign(AWSKMSClient.java:6631)
at com.amazonaws.services.kms.AWSKMSClient.sign(AWSKMSClient.java:6600)
at Main.main(Main.java:17)
```
