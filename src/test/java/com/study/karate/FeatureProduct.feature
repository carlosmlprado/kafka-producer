Scenario: Get a list of products
Given url 'http://localhost:9091/products'
When method GET
Then status 200
And match response.data[0].name == 'product1'