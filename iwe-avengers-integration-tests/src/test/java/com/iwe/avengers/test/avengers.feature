Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://hmjd1x1add.execute-api.us-east-2.amazonaws.com/dev'

Scenario:  Avenger Not Found

Given path 'avengers', 'invalid'
When method get
Then status 404

Scenario: Registry a new Avenger

Given path 'avengers' 
And request {name:'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

* def savedAvenger = response 

#Get Avenger by id
Given path 'avengers', savedAvenger.id
When method get
Then status 200
And match $ == savedAvenger

Scenario: Delete Avenger

Given path 'avengers' ,'sdsa-sasa-asas-sasa'
When method delete
Then status 204

Scenario: Update Avenger

Given path 'avengers' ,'aaaa-aaaa-aaaa-aaaa'
And request {name:'Hulk', secretIdentity: 'Bruce Banner'}
When method put
Then status 200
And match response == {id: '#string', name: 'Hulk', secretIdentity: 'Bruce Banner'}

Scenario: Registry Avenger whit Invalid Playload

Given path 'avengers' 
And request {secretIdentity: 'Steve Rogers'}
When method post
Then status 400

Scenario: Updates Avenger whit Invalid

Given path 'avengers' ,'aaaa-aaaa-aaaa-aaaa'
And request {secretIdentity: 'Bruce Banner'}
When method put
Then status 400



