# Currency Exchange

This microservice accepts bill details including items, their categories, total amount, user type, customer tenure, original currency, and target currency and 
then calculates the net payable amount in the specified target currency after applying applicable discounts and currency conversion.

## API Usage Guide
The API is secured using HTTP Basic Authentication and is designed for two primary functions:

1. **Retrieve Exchange Rates**: This feature allows users to obtain the real-time exchange rates, which is required when user want to calculate the total bill amount.
2. **Calculate total bill amount**: Using exchange rates the api calculate the total bill amount in desired currency.

## Use Case: 
### Discount Calculation
The application supports various discount rules for users based on their relationship with the store and their purchase history. The following discounts are applied based on the user's eligibility:
1. Employee Discount:
- If the user is an employee of the store, they receive a 30% discount on non-grocery items.
2. Member Discount:
- If the user is a member of the store, they receive a 10% discount on non-grocery items.
3. Loyalty Discount:
- If the user has been a customer for over 2 years, they receive a 5% discount on non-grocery items.
4. Bill-Based Discount:
- For every $100 spent on the bill, a $5 discount is applied to the total bill. This applies to all categories, including groceries.

### Discount Rules:
- **Non-combinable percentage-based discounts**:
  Users are only eligible for one of the percentage-based discounts (Employee, Affiliate, or Loyalty). The highest applicable percentage discount is automatically selected.
- **Groceries Exclusion**:
  Percentage-based discounts do not apply to grocery items.

### Discount Implementations:

In this project, we have used the Factory Pattern to create different types of discount objects dynamically based on the context.

- **EmployeeDiscount**: Applies a discount specific to employees.
- **MemberDiscount**: Applies a discount for regular members.
- **LoyalCustomerDiscount**: Provides a discount for loyal customers.
- **ValueBasedDiscount**: Provides a discount based on the total value of the purchase.

## Example Scenarios:
1. Employee purchasing $150 worth of items ($50 groceries, $100 non-groceries) in USD as base currency and target currency is EUR:

    Eligible Discount: 30% Employee Discount on non-grocery items.  
   **Calculation**:  
   - Non-grocery items ($100) receive 30% discount: $30 discount.
   Groceries ($50) are excluded from the percentage discount.
     - Total Bill: $150 - $30 = $120.
     - Bill-based discount for spending over $100: $5.
     - Final Total: $115.
     - After conversion to EUR the bill amount: 104.79 EUR 


2. Member purchasing $220 worth of items ($150 groceries, $70 non-groceries):

    Eligible Discount: 10% Affiliate Discount on non-grocery items.

    **Calculation**:
    - Non-grocery items ($70) receive 10% discount: $7 discount.
    Groceries ($150) are excluded from the percentage discount.
      - Total Bill: $220 - $7 = $213.
      - Bill-based discount for spending over $200: $10.
      - Final Total: $203.
      - After conversion to EUR the bill amount: 104.79 EUR

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
    "itemId": 1,
    "itemName": "XYZ",
    "itemPrice": 10.0,
    "quantity": 2
  },
    {
      "category": "GROCERIES",
      "itemId": 2,
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



