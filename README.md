# NopCommerce Automation Testing

## Test Case Summary

<table>
  <tr>
    <th>Test Case ID</th>
    <th>Description</th>
    <th>Status</th>
  </tr>
  <tr>
    <td>TC_Register_1</td>
    <td>Verify user registration with valid credentials</td>
    <td>Passed</td>
  </tr>
  <tr>
    <td>TC_AddToCart_2</td>
    <td>Verify adding an item to the cart</td>
    <td>Passed</td>
  </tr>
  <tr>
    <td>TC_Checkout_3</td>
    <td>Verify checkout process as a guest</td>
    <td>Passed</td>
  </tr>
  <tr>
    <td>TC_ConfirmOrder_4</td>
    <td>Verify order confirmation</td>
    <td>Passed</td>
  </tr>
</table>

## Detailed Test Cases

### Test Case ID: TC_Register_1
- **Description**: Verify user registration by entering valid credentials.
- **Preconditions**:
  - Valid user details
- **Steps to Execute**:
  1. Go to <a href="https://demo.nopcommerce.com/register">NopCommerce Registration</a>.
  2. Fill in the registration form with valid details.
  3. Click on the "Register" button.
  4. Validate successful registration.
- **Expected Result**: Registration successful and user is redirected to the home page.
- **Actual Result**: As expected.
- **Status**: Passed

### Test Case ID: TC_AddToCart_2
- **Description**: Verify adding an item to the cart.
- **Preconditions**:
  - User is logged in.
- **Steps to Execute**:
  1. Navigate to the book section.
  2. Add a specific item to the cart.
  3. Verify the item is added to the cart.
- **Expected Result**: Item is added to the cart successfully.
- **Actual Result**: As expected.
- **Status**: Passed

### Test Case ID: TC_Checkout_3
- **Description**: Verify the checkout process as a guest.
- **Preconditions**:
  - Items are added to the cart.
- **Steps to Execute**:
  1. Go to the Shopping Cart.
  2. Agree to terms and conditions.
  3. Proceed to checkout as a guest.
  4. Enter required billing details.
  5. Continue with the shipping method.
  6. Choose Check/Money Order for payment.
  7. Continue with the address.
  8. Confirm the order.
- **Expected Result**: Successful checkout and order is placed.
- **Actual Result**: As expected.
- **Status**: Passed

### Test Case ID: TC_ConfirmOrder_4
- **Description**: Verify that the order is confirmed and the confirmation message is displayed.
- **Preconditions**:
  - Order is placed.
- **Steps to Execute**:
  1. Validate the order confirmation message.
  2. Ensure that “Your order has been successfully processed” message is displayed.
- **Expected Result**: Confirmation message is displayed successfully.
- **Actual Result**: As expected.
- **Status**: Passed

## Summary Report

All test cases have been executed successfully, with each scenario validating the registration, cart addition, checkout, and order confirmation processes on the NopCommerce website. Screenshots of each outcome have been captured and stored in the designated directory.

---

*Documentation written and executed by Omkar G.*
