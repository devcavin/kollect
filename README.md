# Kollect

A merchant-facing payment dashboard built on top of Credit Bank's CB Konnect API. Designed to give merchants a clean interface for monitoring account balances, viewing transaction history, and initiating M-Pesa collections purpose-built against Credit Bank's live sandbox environment.

---

## Tech Stack

- **Kotlin** + **Spring Boot**
- **Spring Web** (RestClient for CB Konnect API consumption)
- **Spring Validation** (request validation)
- **Spring Boot DevTools**

---

## Live Demo

> The project is currently in active development against Credit Bank's sandbox environment. A live deployment link will be added once the remaining endpoints are integrated.

---

## API Endpoints

### Accounts

| Method | Endpoint                     | Description                                 | Status  |
|--------|------------------------------|---------------------------------------------|---------|
| GET    | `/api/v1/accounts/balance`   | Retrieve merchant account balance           | Working |
| GET    | `/api/v1/accounts/statement` | Retrieve account statement for a date range | Working |

### Payments (In Progress)

| Method | Endpoint                        | Description                                                 | Status      |
|--------|---------------------------------|-------------------------------------------------------------|-------------|
| POST   | `/api/v1/safaricon-stkpush`     | Initiate M-Pesa STK Push payment prompt on customer's phone | Working     |
| GET    | `/api/v1/safaricom-static-code` | Generate Safaricom static QR code for merchant              | In progress |

---

## CB Konnect APIs Integrated

This project consumes the following APIs from Credit Bank's CB Konnect developer platform:

| API                           | Category      | Purpose                                                        |
|-------------------------------|---------------|----------------------------------------------------------------|
| Balance Inquiry               | Accounts      | Merchant CBL wallet balance                                    |
| Account Statement             | Accounts      | Transaction history by date range                              |
| Safaricom STK Push            | Receive Money | Trigger M-Pesa payment prompt on customer's phone              |
| Safaricom Static Code         | Receive Money | Generate merchant QR code for walk-in payments *(in progress)* |
| Pesalink Payment Status Check | Send Money    | Verify bank-to-bank transaction status *(in progress)*         |

---

## Getting Started

### Prerequisites

- JDK 21
- Access to Credit Bank's CB Konnect sandbox — register at [developers.creditbank.co.ke](https://developers.creditbank.co.ke)

### Setup

1. Clone the repository

   ```bash
   git clone https://github.com/devcavin/kollect
   cd kollect
   ```

2. Configure your environment variables

   Create a `.env` file or export the following before running:

   ```bash
   export BASE_URL=https://sandbox.creditbank.co.ke
   export APP_ID=your_app_id_here
   export API_KEY=your_api_key_here
   ```

   These map to the `spring.credit-bank.api` properties in `application.yaml`.

3. Run the application

   ```bash
   ./gradlew bootRun
   ```

The API will be available at `http://localhost:8080`

---

## Example Requests

### Balance Inquiry

```shell
GET /api/v1/accounts/balance
```

**Response:**
```json
{
  "responseCode": "00",
  "dateAndTimeTransmission": "2026-04-24T10:30:00",
  "countryCode": "KE",
  "actualBalance": 125000.00,
  "availableBalance": 120000.00
}
```

### Account Statement

```shell
GET /api/v1/accounts/statement
Content-Type: application/json

{
  "startDate": "20260401",
  "endDate": "20260424"
}
```

**Response:**
```json
{
  "openingBalance": 100000.00,
  "accountNumber": 1234567890,
  "accountCurrency": "KES",
  "bookingDate": "20260401",
  "valueDate": "20260401",
  "currency": "KES",
  "amount": 25000.00,
  "reference": "TXN-001",
  "description": "M-Pesa collection",
  "transactionCode": "CR"
}
```

### Safaricom STK Push

```shell
GET /api/v1/accounts/safaricom-stkpush
Content-Type: application/json

{
  "phoneNumber": "254700000000",
  "amount": 1,
  "reference": "AAwWEQ4Q2EEO",
  "countryCode": "KE",
  "narration": "InvoiceNumber",
  "callBackUrl": "https://webhook.site/9e759bd7-5ea2-41d7-a825-7583ffc736cf",
  "errorCallBackUrl": "https://webhook.site/9e759bd7-5ea2-41d7-a825-7583ffc73f6"
}
```

**Response**

```json
{
  "merchantRequestID": "AG_20191219_000043fdf61864fe9fh5",
  "checkoutRequestID": "ws_CO_191220191020363945",
  "responseCode": "QR Code Successfully Generated.",
  "responseDescription": "Success. Request accepted for processing",
  "customerMessage": "Success. Request accepted for processing"
}
```

### Safaricom Static Code

```shell
GET /api/v1/accounts//safaricom-static-code
Content-Type: application/json

{
  "RefNo": "AERDUDSODEF",
  "Amount": 1
}
```

**Response**

```json
{
  "ResponseCode": "AG_20191219_000043fdf61864fe9ff5",
  "RequestID": "16738-27456357-1",
  "ResponseDescription": "QR Code Successfully Generated.",
  "QRCode": "SYEEHEHJJSJSKKKSSSHDBDDBBHSHSBSBHSHSBBBXBXXB"
}
```

---

## Design Decisions

### RestClient over OkHttp or HttpClient
Spring Boot's built-in `RestClient` (Spring 6.1+) was chosen over OkHttp or Java's `HttpClient`. For a Spring Boot application consuming a REST API, `RestClient` is the idiomatic choice it integrates naturally with Spring's bean lifecycle, requires no extra dependency, and produces clean, readable code. OkHttp's reputation largely comes from the Android ecosystem and predates `RestClient`'s existence.

### Configuration via `@ConfigurationProperties`
CB Konnect credentials (`base-url`, `app-id`, `api-key`) are bound via `@ConfigurationProperties` rather than scattered `@Value` annotations. This keeps credentials organized, type-safe, and easy to validate at startup.

### Request Validation on Date Range
`AccountStatementRequest` enforces that `startDate` is not after `endDate` at the DTO level using an `init` block. This catches invalid date ranges before they reach the service layer or the external API, producing clearer error messages and avoiding unnecessary API calls.

### Error Logging at Service Layer
Each service method catches exceptions and logs them via SLF4J before rethrowing. This ensures failures against the CB Konnect API are traceable in logs without swallowing the original exception or hiding stack traces from callers.

---

## Project Structure

```
src/
└── main/
    └── kotlin/io/github/devcavin/kollect/
        ├── config/
        │   └── CreditBankConfig.kt       # RestClient bean wired with CB Konnect credentials
        ├── controller/
        │   └── CreditBankController.kt   # REST endpoints exposed to frontend/consumers
        ├── dto/
        │   ├── CreditBankProperties.kt   # @ConfigurationProperties binding
        │   ├── request/
        │   │   └── AccountStatementRequest.kt
        │   └── response/
        │       ├── BalanceInquiryResponse.kt
        │       └── AccountStatementResponse.kt
        └── service/
            └── CreditBankService.kt      # CB Konnect API calls via RestClient
```

---

## License

MIT
