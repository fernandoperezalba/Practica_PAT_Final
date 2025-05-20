document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form = document.getElementById("myform");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        return validateForm();
    });
});

function tokenVerification() {

    if (typeof Cookies.get('token') !== 'undefined') {
        console.log("Cookie detected");
    }
}

function validateForm() {
    try {
        var inputValue1 = document.getElementById("input1").value;
        var inputValue2 = document.getElementById("input2").value;
        const data = { username: inputValue1, password: inputValue2 };
        const address = '/api/login';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (typeof data.jwttoken !== 'undefined') {
                    console.log("Authenticated");
                    Cookies.set('token', data.jwttoken);
                    sessionStorage.setItem("username", inputValue1);
                    fetch("/api/users/user-id?username="+inputValue1,{
                        method: 'GET',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        }
                        })
                        .then(response => response.json())
                        .then(id =>{
                            console.log(id);
                            sessionStorage.setItem("id", id);
                            fetch("/api/users/isAbuelo?id="+id,{
                                method: 'GET',
                                headers: {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                }
                                })
                                .then(response => response.json())
                                .then(isAbuelo =>{
                                    console.log(isAbuelo);
                                    fetch("/api/users/isAyudante?id="+id,{
                                        method: 'GET',
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'application/json'
                                        }
                                        })
                                        .then(response => response.json())
                                        .then(isAyudante =>{
                                            console.log(isAyudante);
                                            if(isAbuelo)
                                                document.location.href="/homeAbuelos.html";
                                            if(isAyudante)
                                                document.location.href="/homeAyudantes.html";
                                        });
                                });
                        });
                } else {
                    alert("Credential not recognized");
                }
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function setValues(){
    if(localStorage.getItem("id")==void(0)){
        localStorage.setItem("id", 1);
    }
    localStorage.setItem("name1", "");
    localStorage.setItem("lastName1", "");
    localStorage.setItem("fecha1", "");
    localStorage.setItem("user1", "");
    localStorage.setItem("name2", "");
    localStorage.setItem("lastName2", "");
    localStorage.setItem("correo2", "");
    localStorage.setItem("user2", "");
}