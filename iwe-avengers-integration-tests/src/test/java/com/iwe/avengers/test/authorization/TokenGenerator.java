package com.iwe.avengers.test.authorization;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.RespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.RespondToAuthChallengeResult;

public class TokenGenerator {

	public String getToken() {
		
		String authresult = null;

		final String clientId = "7fo84n4sffui0jmh8vs7s6vo00";
		final String userPoolId = "us-east-2_U5OVjpul4";

		final AuthenticationHelper auth = new AuthenticationHelper(userPoolId, clientId);

		final InitiateAuthRequest initiateAuthRequest = new InitiateAuthRequest();
		initiateAuthRequest.setAuthFlow(AuthFlowType.USER_SRP_AUTH);
		initiateAuthRequest.setClientId(clientId);
		initiateAuthRequest.addAuthParametersEntry("USERNAME", "LucianoPereira");
		initiateAuthRequest.addAuthParametersEntry("SRP_A", auth.getA().toString(16));

		final AnonymousAWSCredentials awsCreds = new AnonymousAWSCredentials();
		final AWSCognitoIdentityProvider cognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_2).build();
		
		final InitiateAuthResult initiateAuthResult = cognitoIdentityProvider.initiateAuth(initiateAuthRequest);

		if (ChallengeNameType.PASSWORD_VERIFIER.toString().equals(initiateAuthResult.getChallengeName())) {

			RespondToAuthChallengeRequest challengeRequest = auth.userSrpAuthRequest(initiateAuthResult, "12345678");
			RespondToAuthChallengeResult result = cognitoIdentityProvider.respondToAuthChallenge(challengeRequest);
			authresult = result.getAuthenticationResult().getIdToken();
		}

		return authresult;
	}

}
