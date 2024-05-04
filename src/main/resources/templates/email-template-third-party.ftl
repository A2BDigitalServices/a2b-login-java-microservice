<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>A2B Digital Service</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, Helvetica, sans-serif;
        background-color: #ffffff; /* Default white background color for the page */
    }

    .container {
        width: 100%;
        padding: 20px 0;
        text-align: center;
    }

    .content {
        width: 600px;
        background-color: #F0FFE0; /* Light mint background color for the content block */
        color: #000000;
        margin: 0 auto;
        padding: 20px;
        border-radius: 10px;
    }

    h1 {
        font-size: 36px;
        color: blue;
    }

    h2 {
        font-size: 24px;
        color: #555100;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: #F6FFC7; /* Light lime background color for the table */
    }

    td {
        border: 1px solid #555100;
        padding: 10px;
        text-align: left;
        color: #000000; /* Dark black text color */
    }

    .key {
        color: black;
    }

    .title {
        color: blue; /* Blue color for the title */
    }

    .highlight {
        color: red; /* Red color for the highlighted text */
    }

    .contact {
        color: blue; /* Blue color for the contact information */
    }

    .phone {
        color: red; /* Red color for the phone number */
    }
</style>
</head>

<body>
    <div class="container">
        <div class="content">
            <h1>A2B Digital Service</h1>
            <h2 class="title">Please find customer details for Case No: <span class="highlight">${caseId}</span></h2>
            <table>
                <tr>
                    <td>1</td>
                    <td class="key">First Name</td>
                    <td>${firstname}</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td class="key">Last Name</td>
                    <td>${lastname}</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td class="key">Email</td>
                    <td>${email}</td>
                </tr>
                                <tr>
                    <td>4</td>
                    <td class="key">Phone</td>
                    <td>${phone}</td>
                </tr>
				<tr>
                    <td>3</td>
                    <td class="key">Aadhar Number</td>
                    <td>${aadhar}</td>
                </tr>
				<tr>
                    <td>4</td>
                    <td class="key">Pan Number</td>
                    <td>${pan}</td>
                </tr>
				<tr>
                    <td>5</td>
                    <td class="key">Date Of Birth</td>
                    <td>${dob}</td>
                </tr>
				<tr>
                    <td>5</td>
                    <td class="key">Loan Type</td>
                    <td>${loantype}</td>
                </tr>
				<tr>
                    <td>6</td>
                    <td class="key">Father Name</td>
                    <td>${fname}</td>
                </tr>
				<tr>
                    <td>7</td>
                    <td class="key">Mother Phone</td>
                    <td>${mname}</td>
                </tr>
				<tr>
                    <td>8</td>
                    <td class="key">Spouse Email</td>
                    <td>${sname}</td>
                </tr>
				<tr>
                    <td>8</td>
                    <td class="key">Relationship Type</td>
                    <td>${relationship}</td>
                </tr>                
				<tr>
                    <td>9</td>
                    <td class="key">Present Address Line1</td>
                    <td>${praddressline1}</td>
                </tr>
				<tr>
                    <td>10</td>
                    <td class="key">Present Address Line2</td>
                    <td>${praddressline2}</td>
                </tr>
				<tr>
                    <td>11</td>
                    <td class="key">Present Landmark</td>
                    <td>${prlandmark}</td>
                </tr>
				<tr>
                    <td>11</td>
                    <td class="key">Present State</td>
                    <td>${prstate}</td>
                </tr>
				<tr>
                    <td>12</td>
                    <td class="key">Present Country</td>
                    <td>${prcountry}</td>
                </tr>
				<tr>
                    <td>13</td>
                    <td class="key">Present Pincode</td>
                    <td>${prpincode}</td>
                </tr>
				<tr>
                    <td>13</td>
                    <td class="key">Present Postal office</td>
                    <td>${prpostalcode}</td>
                </tr>                
				<tr>
                    <td>14</td>
                    <td class="key">Permanent Address Line1</td>
                    <td>${peaddressline1}</td>
                </tr>
				<tr>
                    <td>15</td>
                    <td class="key">Permanent Address Line2</td>
                    <td>${peaddressline2}</td>
                </tr>
				<tr>
                    <td>15</td>
                    <td class="key">Permanent Landmark</td>
                    <td>${pelandmark}</td>
                </tr>                
				<tr>
                    <td>16</td>
                    <td class="key">Company Name</td>
                    <td>${cname}</td>
                </tr>
				<tr>
                    <td>17</td>
                    <td class="key">Company Address Line1</td>
                    <td>${caddressline1}</td>
                </tr>
				<tr>
                    <td>18</td>
                    <td class="key">Company Address Line2</td>
                    <td>${caddressline2}</td>
                </tr>
				<tr>
                    <td>19</td>
                    <td class="key">Company State</td>
                    <td>${cstate}</td>
                </tr>
				<tr>
                    <td>20</td>
                    <td class="key">Company Country</td>
                    <td>${ccountry}</td>
                </tr>
				<tr>
                    <td>21</td>
                    <td class="key">Company Pincode</td>
                    <td>${cpincode}</td>
                </tr>
				<tr>
                    <td>22</td>
                    <td class="key">Company Email</td>
                    <td>${cemail}</td>
                </tr>
				<tr>
                    <td>23</td>
                    <td class="key">Monthly Mode</td>
                    <td>${monthlymode}</td>
                </tr>                
				<tr>
                    <td>23</td>
                    <td class="key">Monthly Income</td>
                    <td>${monthlyincome}</td>
                </tr>		
				<tr>
                    <td>24</td>
                    <td class="key">Tenure</td>
                    <td>${tenure}</td>
                </tr>
				<tr>
                    <td>25</td>
                    <td class="key">Active Loans</td>
                    <td>${activeloans}</td>
                </tr>
				<tr>
                    <td>26</td>
                    <td class="key">Total EMI Amount</td>
                    <td>${totalemiamount}</td>
                </tr>
				<tr>
                    <td>27</td>
                    <td class="key">Nominee Name</td>
                    <td>${nname}</td>
                </tr>				
					<tr>
                    <td>28</td>
                    <td class="key">Nominee DOB</td>
                    <td>${ndob}</td>
                </tr>	
				<tr>
                    <td>29</td>
                    <td class="key">Nominee Email</td>
                    <td>${nemail}</td>
                </tr>					
				
            </table>
            <p class="contact">Please reach our support team to know further details or call: <span class="phone">+91 9912905556</span></p>
        </div>
    </div>
</body>
</html>
