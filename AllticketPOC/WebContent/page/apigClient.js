/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

var apigClientFactory = {};
apigClientFactory.newClient = function (config) {
    var apigClient = { };
    if(config === undefined) {
        config = {
            accessKey: '',
            secretKey: '',
            sessionToken: '',
            region: '',
            apiKey: undefined,
            defaultContentType: 'application/json',
            defaultAcceptType: 'application/json',
            invokeUrl:''
        };
    }
    if(config.accessKey === undefined) {
        config.accessKey = '';
    }
    if(config.secretKey === undefined) {
        config.secretKey = '';
    }
    if(config.apiKey === undefined) {
        config.apiKey = '';
    }
    if(config.sessionToken === undefined) {
        config.sessionToken = '';
    }
    if(config.region === undefined) {
        config.region = 'us-east-1';
    }
    //If defaultContentType is not defined then default to application/json
    if(config.defaultContentType === undefined) {
        config.defaultContentType = 'application/json';
    }
    //If defaultAcceptType is not defined then default to application/json
    if(config.defaultAcceptType === undefined) {
        config.defaultAcceptType = 'application/json';
    }

    
    // extract endpoint and path from url
    var invokeUrl = config.invokeUrl ; 
    
    var endpoint = /(^https?:\/\/[^\/]+)/g.exec(invokeUrl)[1];
    var pathComponent = invokeUrl.substring(endpoint.length);

    var sigV4ClientConfig = {
        accessKey: config.accessKey,
        secretKey: config.secretKey,
        sessionToken: config.sessionToken,
        serviceName: 'execute-api',
        region: config.region,
        endpoint: endpoint,
        defaultContentType: config.defaultContentType,
        defaultAcceptType: config.defaultAcceptType
    };

    var authType = 'NONE';
    if (sigV4ClientConfig.accessKey !== undefined && sigV4ClientConfig.accessKey !== '' && sigV4ClientConfig.secretKey !== undefined && sigV4ClientConfig.secretKey !== '') {
        authType = 'AWS_IAM';
    }

    var simpleHttpClientConfig = {
        endpoint: endpoint,
        defaultContentType: config.defaultContentType,
        defaultAcceptType: config.defaultAcceptType
    };

    var apiGatewayClient = apiGateway.core.apiGatewayClientFactory.newClient(simpleHttpClientConfig, sigV4ClientConfig);
    
    
    
    apigClient.rootGet = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['Booking_Id'], ['body']);
        
        var rootGetRequest = {
            verb: 'get'.toUpperCase(),
            path: pathComponent + uritemplate('/').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['Booking_Id']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(rootGetRequest, authType, additionalParams, config.apiKey);
    };
    
    apigClient.bookingGet = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId'], ['body']);
        
        var bookingGetRequest = {
            verb: 'get'.toUpperCase(),
            path: pathComponent + uritemplate('/booking').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(bookingGetRequest, authType, additionalParams, config.apiKey);
    };
    
    
    apigClient.bookingPost = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['roundId', 'performId', 'userLogin', 'seatId'], ['body']);
        
        var bookingPostRequest = {
            verb: 'post'.toUpperCase(),
            path: pathComponent + uritemplate('/booking').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['roundId', 'performId', 'userLogin', 'seatId']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(bookingPostRequest, authType, additionalParams, config.apiKey);
    };
    
    
    apigClient.bookingFunctionGet = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId'], ['body']);
        
        var bookingFunctionGetRequest = {
            verb: 'get'.toUpperCase(),
            path: pathComponent + uritemplate('/bookingFunction').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(bookingFunctionGetRequest, authType, additionalParams, config.apiKey);
    };
    
    
    apigClient.bookingFunctionPost = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId'], ['body']);
        
        var bookingFunctionPostRequest = {
            verb: 'post'.toUpperCase(),
            path: pathComponent + uritemplate('/bookingFunction').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['roundId', 'performId', 'userLogin', 'zoneId', 'seatId']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(bookingFunctionPostRequest, authType, additionalParams, config.apiKey);
    };
    
    
    apigClient.rootPost = function (params, body, additionalParams) {
        if(additionalParams === undefined) { additionalParams = {}; }
        
        apiGateway.core.utils.assertParametersDefined(params, ['Booking_Id'], ['body']);
        
        var rootPostRequest = {
            verb: 'post'.toUpperCase(),
            path: pathComponent + uritemplate('/').expand(apiGateway.core.utils.parseParametersToObject(params, [])),
            headers: apiGateway.core.utils.parseParametersToObject(params, []),
            queryParams: apiGateway.core.utils.parseParametersToObject(params, ['Booking_Id']),
            body: body
        };
        
        
        return apiGatewayClient.makeRequest(rootPostRequest, authType, additionalParams, config.apiKey);
    };
    

    return apigClient;
};
