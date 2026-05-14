<!DOCTYPE html>
<html>
<head>
<title>Student Form</title>

<script>
function validateForm() {
    let roll = document.forms["form1"]["rollno"].value;
    let name = document.forms["form1"]["name"].value;
    let marks = document.forms["form1"]["marks"];

    if (roll === "" || name === "") {
        alert("All fields are required!");
        return false;
    }

    for (let i = 0; i < marks.length; i++) {
        if (marks[i].value === "" || marks[i].value < 0 || marks[i].value > 100) {
            alert("Enter valid marks (0-100)");
            return false;
        }
    }
    return true;
}
</script>

</head>
<body>

<h2>Student Details</h2>

<form name="form1" action="ResultServlet" method="post" onsubmit="return validateForm()">

Roll No: <input type="text" name="rollno"><br><br>
Name: <input type="text" name="name"><br><br>

Sub1: <input type="number" name="marks"><br><br>
Sub2: <input type="number" name="marks"><br><br>
Sub3: <input type="number" name="marks"><br><br>
Sub4: <input type="number" name="marks"><br><br>
Sub5: <input type="number" name="marks"><br><br>

<input type="submit" value="Calculate Result">

</form>

</body>
</html>