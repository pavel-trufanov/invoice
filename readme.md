Developer test

Create a method for calculating invoice total, which returns invoice total and prints out invoice rows.

calculateInvoiceTotal(usage, priceList, package)

Package S
10 minutes
50 sms
5 eur

Package M
50 minutes
100 sms
10 eur

Package L
500 minutes
500 sms
20 eur

Price list
1 minute - 0.2 eur
1 sms - 0.3 eur

------------------------------
Assumptions:

* Package price is included anyway even though there is no usage
* Amount for package invoice row is always equal to 1, meaning 1 package
* The customer is billed for each extra traffic amount that exceeds the amounts included into package according to a price list
* Price list and package data is considered as always present in application scope, so there is no specific logic to check if it is missing.

