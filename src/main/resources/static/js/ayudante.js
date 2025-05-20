document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form1 = document.getElementById("myform1");
    form1.addEventListener("submit", function(e) {
        e.preventDefault();
        var inputValue1 = document.getElementById("password1").value;
        var inputValue2 = document.getElementById("password2").value;
        return validateForm(inputValue1, inputValue2, Number(sessionStorage.getItem("id"))-1);
    });
    var form2 = document.getElementById("myform2");
    form2.addEventListener("submit", function(e) {
        e.preventDefault();
        var inputValue1 = document.getElementById("password3").value;
        var inputValue2 = document.getElementById("password4").value;
        return validateForm(inputValue1, inputValue2, sessionStorage.getItem("id"));
    });
});

function tokenVerification() {

    if (typeof Cookies.get('token') !== 'undefined') {
        console.log("Cookie detected");
    } else {
        document.location.href = "/index.html";
    }
}

function validateForm(inputValue1, inputValue2, my_id) {
    try {
        const data = {  id: my_id,
                        newPassword: inputValue1,
                        newPassword2: inputValue2
                      };
        const address = '/api/users/update-password';
        var bearer = 'Bearer ' + Cookies.get('token');
        var headers = {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': bearer
                };
        fetch(address, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                alert("La contraseña se ha cambiado correctamente.");
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}