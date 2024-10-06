# Currency Exchange

This microservice accepts bill details including items, their categories, total amount, user type, customer tenure, original currency, and target currency and 
then calculates the net payable amount in the specified target currency after applying applicable discounts and currency conversion.

## API Usage Guide
The API is secured using HTTP Basic Authentication and is designed for two primary functions:

1. **Retrieve Exchange Rates**: This feature allows users to obtain the real-time exchange rates, which is required when user want to calculate the total bill amount.
2. **Calculate total bill amount**: Using exchange rates the api calculate the total bill amount in desired currency.

## Getting Started
To begin using the Attributes API, you’ll need to follow these steps:
       
**API Endpoint**: /api/calculate

**Default Authentication**:
By default, the following credentials are available:
- **Username**: user
- **Password**: password

1. ### Retrieve-Exchange-Rates

- **Request Body**:

```json
{
  "baseCurrency": "GBP",
  "targetCurrency": "EUR",
  "customerType": "MEMBER",
  "customerTenureInMonths": "12",
  "items": [{
    "category": "FASHION",
    "itemName": "XYZ",
    "itemPrice": 10.0,
    "quantity": 2
  },
    {
      "category": "GROCERIES",
      "itemName": "XYZ",
      "itemPrice": 10.0
    }]

}
```
- **Response Body**: Success Response
```json
{
  "result": "success",
  "base_code": "GBP",
  "target_code": "EUR",
  "conversion_rate": 1.194,
  "conversion_result": 33.432,
  "error-type": null
}
```

- **Response Body**: Failure Response
```json
{
  "result": "error",
  "error-type": "unknown-code"
}
``` 
## Scripts

- **To Build Project**:
```json
mvn clean install 
``` 
- **To Run Static Code Analysis**: 
```json
mvn checkstyle:check spotbugs:check 
```
- **To Run Unit Tests and Generate Code Coverage**: 
```json
mvn test jacoco:report
```



