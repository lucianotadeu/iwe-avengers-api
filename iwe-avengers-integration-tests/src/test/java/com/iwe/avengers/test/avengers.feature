Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://hmjd1x1add.execute-api.us-east-2.amazonaws.com/dev'

Scenario: Get Avenger by Id

Given path 'avengers', 'sdsa-sasa-asas-sasa'
When method get
Then status 200
And match response == {id: '#string', name: 'Iron Man', secretIdentity: 'Tony Stark'}


Scenario: Registry a new Avenger

Given path 'avengers' 
And request {name:' Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {name: '', secretIdentity: ''}

Scenario: Delete a new Avenger

Given path 'avengers' 
And request {name:' Captain America', secretIdentity: 'Steve Rogers'}
When method delete
Then status 201
