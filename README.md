Introduction:

Our project involves automating the order placement workflow on NopCommerce, an Ecommerce website, using Selenium WebDriver framework. The primary objective is to ensure the smooth execution of the order process, encompassing login, order submission, payment processing, and order confirmation, through automated tests.

Setup and Configuration:
We initiated the project by establishing a Maven project structure to manage dependencies efficiently and streamline the build process. We integrated Selenium WebDriver into the project to automate browser interactions. Additionally, we incorporated the TestNG framework for defining and executing test cases, benefiting from its annotations for test methods and parameterization capabilities. To enhance test result analysis, we integrated Extent Report to generate detailed HTML reports.

Test Implementation:
Our test implementation focused on validating each step of the order placement workflow using Selenium WebDriver. We structured the test execution flow and set up test preconditions using TestNG annotations such as @Test, @BeforeMethod, and @AfterMethod. We prioritized test methods to ensure sequential execution of login, order submission, and confirmation steps. Utilizing TestNG's dependsOnMethods attribute, we established dependencies between test methods to execute certain actions only after successful completion of prerequisite steps.

TestNG XML Configuration:
We created a TestNG XML file to define the test suite, include test cases, and specify parameters such as parallel execution and thread count. This XML file facilitated effective organization and management of test case execution.

Extent Report Integration:
We integrated Extent Report listeners to capture test status, logs, screenshots, and other relevant information during test execution. These listeners enabled the generation of comprehensive HTML reports, providing insights into test results and aiding in issue identification and resolution.

Conclusion:

Through the integration of Selenium WebDriver, TestNG framework, and Extent Report, we successfully automated the order placement workflow on NopCommerce. Our project ensures robustness and reliability in Ecommerce operations by automating critical functionalities such as login, order submission, payment processing, and order confirmation. By leveraging appropriate annotations, priorities, and dependencies, we optimized the structure and efficiency of our test suite, facilitating seamless execution and detailed reporting of test results.
